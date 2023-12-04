package com.talks.rest.api;

import com.talks.rest.services.ConferenceService;
import jakarta.ejb.EJB;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("conferences")
public class ConferencesResource {

    @EJB
    private ConferenceService conferenceService;

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConferences(@PathParam("name") String name) {
        String conferenceDetails = conferenceService.findConferenceDetails(name);
        return Response.status(Response.Status.OK)
                .entity(conferenceDetails).build();
    }

}
