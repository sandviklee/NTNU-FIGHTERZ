package fightinggame.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import fightinggame.json.*;
import fightinggame.users.User;

public class RemoteModelAccess {

    private URI uriBase;
    private User user = null;
    private ObjectMapper mapper;
    

    public RemoteModelAccess (URI uriBase){
        this.uriBase = uriBase;
        this.mapper = new ObjectMapper();
        
    }

    private String uriParam(String param) {
        return URLEncoder.encode(param, StandardCharsets.UTF_8);
      }
    
    public User getUser(String username, String password){
        HttpRequest request = HttpRequest.newBuilder(uriBase.resolve(uriParam(username) + "/").resolve(uriParam(password))).GET().build();
        try {
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String userString = response.body();
            this.user = mapper.readValue(userString, User.class);
          }
           catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
          }
        return this.user;
    }

    public boolean putUser(String username, String password, String confirmPassword){
        HttpRequest request = HttpRequest.newBuilder(uri).headers("username", username, "password", password, "confirmPassword", confirmPassword).GET().build();
    }


}
