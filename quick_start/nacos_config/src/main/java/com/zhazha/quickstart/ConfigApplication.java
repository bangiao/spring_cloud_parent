package com.zhazha.quickstart;


import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.listener.Listener;

import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class ConfigApplication {

	public static void main(String[] args) throws Exception {
		String serverAddr = "localhost:8840,localhost:8850,localhost:8860";
		String groupId = "group_quick";
		String dataId = "quick_start.properties";
		Properties properties = new Properties();
		properties.setProperty(PropertyKeyConst.SERVER_ADDR, serverAddr);
//		properties.setProperty(PropertyKeyConst.NAMESPACE, "972fd314-7e76-4979-aab3-241c7b550bad");
		ConfigService configService = NacosFactory.createConfigService(properties);
		configService.addListener(dataId, groupId, new Listener() {
			@Override
			public Executor getExecutor() {
				return null;
			}

			@Override
			public void receiveConfigInfo(String configInfo) {
				System.out.printf("groupId: %s, dataId: %s, content: %s\n%n", groupId, dataId, configInfo);
			}
		});
		TimeUnit.SECONDS.sleep(3);
		configService.publishConfig(dataId, groupId, "username=zhazha", ConfigType.PROPERTIES.getType());
		TimeUnit.SECONDS.sleep(3);
	}

}