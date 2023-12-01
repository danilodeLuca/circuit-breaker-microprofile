package com.talks.rest.services;

import com.talks.rest.http.TDCConferenceHTTP;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class ConferenceService {

    @EJB
    private TDCConferenceHTTP tDCConferenceHTTP;

    public String findConferenceDetails(String name) {
        String details;
        switch (name) {
            case "FUTURE" -> details = tDCConferenceHTTP.findTDCFuture();
            case "BUSINESS" -> details = tDCConferenceHTTP.findTDCBusiness();
            case "INNOVATION" -> details = tDCConferenceHTTP.findTDCInnovation();
            default -> details = "fail";
        }
        return details;
    }
}
