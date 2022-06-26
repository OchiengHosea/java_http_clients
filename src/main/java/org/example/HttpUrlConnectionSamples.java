package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class HttpUrlConnectionSamples {
    public static void performSampleRequest() {
        try {
            URL url = new URL("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // set headers
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            String result = bufferedReader.lines().collect(Collectors.joining());
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void performSamplePOST() {
        try {
            URL url = new URL("https://postman-echo.com/post");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // set headers
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            Book book = new Book("Sample", "Description");
            outputStream.write(book.toJson().getBytes("utf-8"));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            String result = bufferedReader.lines().collect(Collectors.joining());
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
