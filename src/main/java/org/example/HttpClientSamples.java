package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientSamples {
    public static void doSampleGet(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("https://postman-echo.com/post"))
                .header("accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doSamplePost() {
        HttpClient client = HttpClient.newHttpClient();
        Book book = new Book("Sample", "Description");
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create("https://postman-echo.com/get"))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(book.toJson()))
                .build();
        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
