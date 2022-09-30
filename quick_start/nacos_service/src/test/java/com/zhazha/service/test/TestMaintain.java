package com.zhazha.service.test;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.naming.NamingMaintainService;
import com.alibaba.nacos.api.naming.NamingService;

import java.util.concurrent.TimeUnit;

public class TestMaintain {
    public static void main(String[] args) throws Exception {
        NamingMaintainService maintainService = NacosFactory.createMaintainService("127.0.0.1:8840");
        maintainService.createService("NamingMaintainService", "zhazha_group", 0.5f);
        NamingService namingService = NacosFactory.createNamingService("127.0.0.1:8840");
        namingService.registerInstance("NamingService", "127.0.0.1", 8888);
        TimeUnit.SECONDS.sleep(100);
    }
}
