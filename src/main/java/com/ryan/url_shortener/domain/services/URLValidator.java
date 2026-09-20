package com.ryan.url_shortener.domain.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class URLValidator {

    private static final Logger log = LoggerFactory.getLogger(URLValidator.class);

    public static boolean UrlExists(String urlString) {
        try {
            log.debug("Checking if URL exists: {}", urlString);
            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(5))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .method("HEAD", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            int statusCode = response.statusCode();

            return (statusCode >= 200 && statusCode < 400);
        } catch (Exception e){
            log.error("Error while checking URL: {}", urlString, e);
            return false;
        }

    }
}
