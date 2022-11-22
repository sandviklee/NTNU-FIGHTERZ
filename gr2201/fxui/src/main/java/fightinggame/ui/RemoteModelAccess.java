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

    public RemoteModelAccess() {
        try {
            this.uriBase = new URI("http://localhost:8080/api/v1/user/");
        } catch (URISyntaxException e) {
            System.err.println(e);
        }

        this.mapper = new ObjectMapper();
        mapper.registerModule(new UserModule());
    }

    private String uriParam(String param) {
        return URLEncoder.encode(param, StandardCharsets.UTF_8);
    }

    /**
     * Sends a HTTP GET() request with the given username and password. The response
     * will be a User.
     * 
     * @param username
     * @param password
     * @return A User. The user might be null if password is incorrect or username
     *         invalid
     */
    public User getUser(String username, String password) {
        HttpRequest request = HttpRequest.newBuilder(uriBase.resolve(uriParam(username) + "/" + uriParam(password)))
                .header("Accept", "application/json")
                .GET().build();
        try {
            final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            String userString = response.body();
            return mapper.readValue(userString, User.class);
        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Sends a HTTP POST() request with the given username, passwords and confirm
     * passwords. The response will be a User.
     * 
     * @param username
     * @param password
     * @param confirmPassword
     * @return A User. The user might be null if password is incorrect, username
     *         invalid or passwords not matching
     */
    public User postUser(String username, String password, String confirmPassword) {
        try {
            HttpRequest request = HttpRequest.newBuilder(uriBase.resolve(uriParam(username)))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(BodyPublishers.ofString("userData=" + username + "." + password + "." + confirmPassword))
                    .build();

            HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            String userString = response.body();
            return mapper.readValue(userString, User.class);

        } catch (IOException | InterruptedException | IllegalArgumentException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     * Sends a HTTP PUT() request with a given User. The response will be a boolean
     * describing if the user was updated or not.
     * 
     * @param user
     * @return A boolean describing if the User was updated
     */
    public boolean putUser(User user) {
        try {
            HttpRequest request = HttpRequest.newBuilder(uriBase.resolve(uriParam(user.getUserId().getUserId())))
                    // .header("Content-Type", "application/x-www-form-urlencoded")
                    .PUT(BodyPublishers.ofString(user.getPassword())).build();
            System.out.println("URI:" + uriBase.resolve("/" + user.getUserId()));
            System.out.println("The UserJSON; " + mapper.writeValueAsString(user));
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            System.out.println(responseString);
            Boolean updated = mapper.readValue(responseString, Boolean.class);
            return updated;
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
            return false;
        }
    }

    /**
     * Sends a HTTP DELETE() request with a given userId. The response will be a
     * boolean describing if the user was updated or not.
     * 
     * @param user
     * @return
     */
    public boolean deleteUser(User user) {
        try {
            HttpRequest request = HttpRequest.newBuilder(uriBase.resolve(user.getUserId().getUserId())).DELETE().build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
                    HttpResponse.BodyHandlers.ofString());
            String responseString = response.body();
            Boolean deleted = mapper.readValue(responseString, Boolean.class);
            return deleted;
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
            return false;
        }
    }

}
