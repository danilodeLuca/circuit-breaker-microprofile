package com.talks.rest.http;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class TDCConferenceHTTP {

    @Inject
    private HttpHitCounter httpHitCounter;

    public String findTDCFuture() {
        Integer hits = httpHitCounter.incrementAndGet("FUTURE");
        System.out.println(hits);
        return "The Developers Conference Future";
    }

    public String findTDCBusiness() {
        Integer hits = httpHitCounter.incrementAndGet("BUSINESS");
        return "The Developers Conference Business";
    }

    public String findTDCInnovation() {
        Integer hits = httpHitCounter.incrementAndGet("INNOVATION");
        return "The Developers Conference Innovation";
    }
}
