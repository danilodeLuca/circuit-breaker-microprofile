package com.talks.rest.http;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class HttpHitCounter {

    private static ConcurrentHashMap<String, Integer> hits;

    @PostConstruct
    public void initialize() {
        hits = new ConcurrentHashMap<>();
    }

    public Integer incrementAndGet(String key) {
        return hits.merge(key, 1, Integer::sum);
    }
}
