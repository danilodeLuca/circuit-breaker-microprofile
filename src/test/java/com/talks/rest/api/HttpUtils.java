package com.talks.rest.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class HttpUtils {
    public static HttpURLConnection sendRequest(String path, String methods, Map<String, String> requestHeaders)
            throws IOException {
        return getHttpConnection(new URL(path), methods, requestHeaders);
    }

    public static HttpURLConnection getHttpConnection(URL targetURL, String httpRequestMethod,
                                                      Map<String, String> headers) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) targetURL.openConnection();
        Iterator<Map.Entry<String, String>> entries = headers.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
        connection.setRequestMethod(httpRequestMethod);
        return connection;
    }
}
