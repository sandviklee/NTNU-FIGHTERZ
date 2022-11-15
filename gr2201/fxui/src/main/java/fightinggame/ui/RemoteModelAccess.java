package fightinggame.ui;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.net.URI;
import java.net.URISyntaxException;
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
    private ObjectMapper mapper;
    
    public RemoteModelAccess (){
        try {
            this.uriBase =  new URI("http://localhost:8080/api/v1/user/");
            //this.uriBase = new URI("https://8080-it1901groups2022-gr2201-iyc01r01ajd.ws.gitpod.stud.ntnu.no");
        } catch (URISyntaxException e) {
            System.err.println(e);
        }
        
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
        HttpRequest request = HttpRequest.newBuilder(uriBase.resolve(uriParam(username)).resolve("/" + uriParam(password))).GET().build();
        try {
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String userString = response.body();
            return mapper.readValue(userString, User.class);
          }
           catch (IOException | InterruptedException e) {
            System.err.println(e);
            return null;
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
    public User postUser(String username, String password, String confirmPassword){
        try {
            HttpRequest request = HttpRequest.newBuilder(uriBase.resolve(uriParam(username)))
            .header("Accept", "application/json")
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(BodyPublishers.ofString("userData="+username + "." + password + "." + confirmPassword)).build();
            
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String userString = response.body();
            return mapper.readValue(userString, User.class);

        } catch (IOException| InterruptedException e) {
            System.err.println(e);
            return null;
        }
    }

    public boolean putUser(User user){
        try {
            HttpRequest request = HttpRequest.newBuilder(uriBase.resolve("/"+user.getUserName())).PUT(BodyPublishers.ofString(mapper.writeValueAsString(user))).build();
            
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            Boolean updated = mapper.readValue(responseString, Boolean.class);
            return updated;
        } catch (Exception e) {
            return false;
        }
        
    }


}
