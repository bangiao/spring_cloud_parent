package com.zhazha.service.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ServiceController {

    private static NamingService namingService;

    static {
        try {
            namingService = NacosFactory.createNamingService("127.0.0.1:8840");
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("register")
    public void register(String serverName, String ip, int port) {
        try {
            namingService.registerInstance(serverName, ip, port);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("deregister")
    public void  deRegister(String serverName, String ip, int port) {
        try {
            namingService.deregisterInstance(serverName, ip, port);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("subscribe")
    public void subscribe(String serverName) {
        System.out.println("订阅" + serverName);
        try {
            namingService.subscribe(serverName, event -> {
                NamingEvent namingEvent = (NamingEvent) event;
                String serviceName = namingEvent.getServiceName();
                String groupName = namingEvent.getGroupName();
                List<Instance> instances = namingEvent.getInstances();
                System.err.println("serviceName = " + serviceName);
                System.err.println("groupName = " + groupName);
                instances.forEach(System.err::println);
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("unsubscribe")
    public void unSubscribe(String serverName) {
        try {
            namingService.subscribe(serverName, event -> {
                System.out.println("取消订阅" + serverName);
                NamingEvent namingEvent = (NamingEvent) event;
                String serviceName = namingEvent.getServiceName();
                String groupName = namingEvent.getGroupName();
                List<Instance> instances = namingEvent.getInstances();
                System.err.println("serviceName = " + serviceName);
                System.err.println("groupName = " + groupName);
                instances.forEach(System.err::println);
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}