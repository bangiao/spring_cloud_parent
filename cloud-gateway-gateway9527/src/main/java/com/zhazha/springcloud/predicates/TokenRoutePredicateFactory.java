package com.zhazha.springcloud.predicates;

import com.zhazha.springcloud.predicates.config.MyConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.function.Predicate;

//@Component
public class TokenRoutePredicateFactory extends AbstractRoutePredicateFactory<MyConfig> {

    public TokenRoutePredicateFactory() {
        super(MyConfig.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("token");
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyConfig config) {
        return serverWebExchange -> {
            // 拿到网页参数给出的 token 值
            String token = serverWebExchange.getRequest().getQueryParams().getFirst("token");
            // config.getToken() 找到application.yml配置的值 - Token=myToken123456
            // 两个值进行对比, 如果相同 返回 true, 否则返回 false
            return StringUtils.equalsAnyIgnoreCase(token, config.getToken());
        };
    }
}
