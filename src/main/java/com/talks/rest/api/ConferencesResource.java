package com.talks.rest.api;

import com.talks.rest.services.ConferenceService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.concurrent.ConcurrentHashMap;

@Path("conferences")
public class ConferencesResource {

    @Inject
    private ConferenceService conferenceService;

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConferences(@PathParam("name") String name) {
        String conferenceDetails = conferenceService.findConferenceDetails(name);
        return Response.status(Response.Status.OK)
                .entity(conferenceDetails).build();
    }

    @GET
    @Path("/hits")
    @Produces(MediaType.APPLICATION_JSON)
    public Response hits() {
        ConcurrentHashMap<String, Integer> hits = conferenceService.hits();
        return Response.status(Response.Status.OK)
                .entity(hits).build();
    }

    @POST
    @Path("/reset")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reset() {
        ConcurrentHashMap<String, Integer> reset = conferenceService.reset();
        return Response.status(Response.Status.OK)
                .entity(reset).build();
    }
}
