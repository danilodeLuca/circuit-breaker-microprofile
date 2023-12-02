package com.talks.rest.services;

import com.talks.rest.http.TDCConferenceHTTP;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import static com.talks.rest.model.Conference.*;

@Stateless
public class ConferenceService {

    @Inject
    private TDCConferenceHTTP tDCConferenceHTTP;

    public String findConferenceDetails(String name) {
        String details;
        switch (name) {
            case TDC_FUTURE -> details = tDCConferenceHTTP.findTDCFuture();
            case TDC_BUSINESS -> details = tDCConferenceHTTP.findTDCBusiness();
            case TDC_INNOVATION -> details = tDCConferenceHTTP.findTDCInnovation();
            default -> details = tDCConferenceHTTP.dumbExample();
        }
        return details;
    }
}
