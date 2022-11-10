package fightinggame.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import fightinggame.users.User;

public class RemoteModelAccess {

    private URI uri;
    private User user;

    public RemoteModelAccess (URI uri){
        this.uri = uri;
    }
    
    public User getUser(String username, String password){
        HttpRequest request = HttpRequest.newBuilder(uri).headers("username", username, "password", password ).GET().build();
        try {
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            this.user = response.body();
          }
           catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
          }
        return this.user;
    }

    public boolean putUser(String username, String password, String confirmPassword){
        HttpRequest request = HttpRequest.newBuilder(uri).headers()
    }


}
