package com.zhazha.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class NacosLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private static final Log log = LogFactory.getLog(RoundRobinLoadBalancer.class);

    @NacosValue(value = "spring.application.name", autoRefreshed = true)
    final String serviceId;

    final AtomicInteger position = new AtomicInteger(new Random().nextInt(1000));

    @Resource
    NacosServiceManager nacosServiceManager;

    @Resource
    NacosDiscoveryProperties nacosDiscoveryProperties;

    ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public <T> NacosLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider
                .getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next()
                .map(serviceInstances -> processInstanceResponse(supplier, serviceInstances));
    }

    private Response<ServiceInstance> processInstanceResponse(ServiceInstanceListSupplier supplier, List<ServiceInstance> serviceInstances) {
        Response<ServiceInstance> serviceInstanceResponse = getInstanceResponse(serviceInstances);
        if (supplier instanceof SelectedInstanceCallback && serviceInstanceResponse.hasServer()) {
            ((SelectedInstanceCallback) supplier).selectedServiceInstance(serviceInstanceResponse.getServer());
        }
        return serviceInstanceResponse;
    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances) {
        if (instances.isEmpty()) {
            if (log.isWarnEnabled()) {
                log.warn("No servers available for service: " + serviceId);
            }
            return new EmptyResponse();
        }

        ServiceInstance instance = null;
        try {
            NamingService namingService = nacosServiceManager.getNamingService(nacosDiscoveryProperties.getNacosProperties());
            Instance healthyInstance = namingService.selectOneHealthyInstance(serviceId, nacosDiscoveryProperties.getGroup());
            Stream<ServiceInstance> stream = instances.parallelStream().filter(serviceInstance -> {
                if (serviceInstance.getPort() == healthyInstance.getPort() && healthyInstance.getServiceName().contains(serviceInstance.getServiceId()) && healthyInstance.getInstanceId().contains(serviceInstance.getHost())) {
                    return true;
                }
                return false;
            });
            instance = stream.findFirst().orElseGet(() -> {
                int pos = this.position.incrementAndGet() & Integer.MAX_VALUE;
                return instances.get(pos % instances.size());
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return new DefaultResponse(instance);
    }

}
