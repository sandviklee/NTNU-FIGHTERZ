package fightinggame.ui;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;


import fightinggame.json.UserModule;
import fightinggame.users.User;

public class RemoteModelAccess {

    private URI uriBase;
    private User user = null;
    private ObjectMapper mapper;
    
    public RemoteModelAccess (URI uriBase){
        this.uriBase = uriBase;
        this.mapper = new ObjectMapper();
        mapper.registerModule(new UserModule());
    }

    private String uriParam(String param) {
        return URLEncoder.encode(param, StandardCharsets.UTF_8);
    }
    // headers to http request might need to be added

    /**
     * Sends a HTTP request with the given username and password. The response will be a User.
     * @param username
     * @param password
     * @return A User. The user might be null if password is incorrect or username invalid
     */
    public User getUser(String username, String password){
        HttpRequest request = HttpRequest.newBuilder(uriBase.resolve(uriParam(username) + "/").resolve(uriParam(password))).GET().build();
        try {
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String userString = response.body();
            return mapper.readValue(userString, User.class);
          }
           catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
          }       
    }
    // headers to http request might need to be added

    /**
     * Sends a HTTP request with the given username, passwords and confirm passwords. The response will be a User.
     * @param username
     * @param password
     * @param confirmPassword
     * @return A User. The user might be null if password is incorrect, username invalid or passwords not matching
     */
    public User putUser(String username, String password, String confirmPassword){
        HttpRequest request = HttpRequest.newBuilder(uriBase.resolve(uriParam(username)))
        .PUT(BodyPublishers.ofString(username + "." + password + "." + confirmPassword)).build();
        try {
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String userString = response.body();
            return mapper.readValue(userString, User.class);

        } catch (IOException| InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
