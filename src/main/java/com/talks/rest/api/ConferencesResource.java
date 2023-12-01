package com.talks.rest.api;

import com.talks.rest.services.ConferenceService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("conferences")
public class ConferencesResource {

    @EJB
    private ConferenceService conferenceService;
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getConferences(@PathParam("name") String name) {
        String conferenceDetails = conferenceService.findConferenceDetails(name);
        return "batata" + conferenceDetails;
    }

}
