package com.gotta_watch_them_all.app.infrastructure.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class ApiRequester {

    private final HttpClient httpClient;

    public String request(HttpRequest request) {
        //ceci doit être fait par un request builder je suis désolé je n'ai que 2 pieds
        //
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .header("x-rapidapi-key", "6da17873e5msh1a5598a15b875ddp151843jsnfe48d4eaed87")
//                .header("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}

//    HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create("https://movie-database-imdb-alternative.p.rapidapi.com/?i=tt4154796&r=json"))
//            .header("x-rapidapi-key", "6da17873e5msh1a5598a15b875ddp151843jsnfe48d4eaed87")
//            .header("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
//            .method("GET", HttpRequest.BodyPublishers.noBody())
//            .build();
