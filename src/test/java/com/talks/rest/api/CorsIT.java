package com.talks.rest.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CorsIT {

    String port = System.getProperty("default.http.port");
    String pathToHost = "http://localhost:9080/";

    @BeforeEach
    public void setUp() {
        // JVM does not allow restricted headers by default
        // Set to true for CORS testing
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
    }

    @Test
    public void testSimpleCorsRequest() throws IOException {
        HttpURLConnection connection = HttpUtils.sendRequest(
                pathToHost + "circuit-breaker/api/conferences", "GET",
                TestData.simpleRequestHeaders);
        checkCorsResponse(connection, TestData.simpleResponseHeaders);

        printResponseHeaders(connection, "Simple CORS Request");
    }

    @Test
    public void testPreflightCorsRequest() throws IOException {
        System.out.print(pathToHost + "circuit-breaker/api/conferences/FUTURE");
        HttpURLConnection connection = HttpUtils.sendRequest(
                pathToHost + "circuit-breaker/api/conferences/FUTURE", "GET",
                TestData.preflightRequestHeaders);
        checkCorsResponse(connection, TestData.preflightResponseHeaders);

        printResponseHeaders(connection, "Preflight CORS Request");
    }

    public void checkCorsResponse(HttpURLConnection connection,
                                  Map<String, String> expectedHeaders) throws IOException {
        assertEquals(200, connection.getResponseCode(), "Invalid HTTP response code");
        expectedHeaders.forEach((responseHeader, value) -> {
            assertEquals(value, connection.getHeaderField(responseHeader),
                    "Unexpected value for " + responseHeader + " header");
        });
    }

    public static void printResponseHeaders(HttpURLConnection connection,
                                            String label) {
        System.out.println("--- " + label + " ---");
        Map<String, List<String>> map = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println("Header " + entry.getKey() + " = " + entry.getValue());
        }
        System.out.println();
    }

}
