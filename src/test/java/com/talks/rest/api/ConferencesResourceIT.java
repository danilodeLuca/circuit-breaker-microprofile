package com.talks.rest.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConferencesResourceIT {

    private static final Jsonb JSONB = JsonbBuilder.create();

    @Test
    public void testGetConferences() {
        String port = System.getProperty("http.port");
        String context = System.getProperty("context.root");
        String url = "http://localhost:" + port + "/" + context + "/";

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(url + "api/conferences");
        Response response = target.request().get();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus(),
                "Incorrect response code from " + url);

        String json = response.readEntity(String.class);
        System.out.println(json);
//        Properties sysProps = JSONB.fromJson(json, Properties.class);
//
//        assertEquals(System.getProperty("os.name"), sysProps.getProperty("os.name"),
//                "The system property for the local and remote JVM should match");
        response.close();
        client.close();
    }
}
