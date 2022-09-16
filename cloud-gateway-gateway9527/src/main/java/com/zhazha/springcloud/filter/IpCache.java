package com.zhazha.springcloud.filter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

//用于保存次数的缓存
public class IpCache {
    public static final Map<String, AtomicInteger> CACHE = new ConcurrentHashMap<>();
}
