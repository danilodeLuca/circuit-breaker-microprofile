package com.talks.rest.services;

import com.talks.rest.http.GeekleHTTP;
import com.talks.rest.http.TDCConferenceHTTP;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.concurrent.ConcurrentHashMap;

import static com.talks.rest.model.Conference.*;

@Stateless
public class ConferenceService {

    @Inject
    private TDCConferenceHTTP tDCConferenceHTTP;
    @Inject
    private GeekleHTTP geekleHTTP;

    public String findConferenceDetails(String name) {
        System.out.print("Finding for " + name);
        String details = switch (name) {
            case GEEKLE_JAVA -> geekleHTTP.findGeekleJava();
            case GEEKLE_ARCHITECTURE -> geekleHTTP.findGeekleArchitecture();
            case GEEKLE_DEVOPS -> geekleHTTP.findGeekleDevops();
            default -> geekleHTTP.findGeekleCommunity();
        };
        System.out.print("Response: " + details);
        return details;
    }

    public ConcurrentHashMap<String, Integer> reset() {
        return geekleHTTP.reset();
    }

    public ConcurrentHashMap<String, Integer> hits() {
        return geekleHTTP.hits();
    }
}
