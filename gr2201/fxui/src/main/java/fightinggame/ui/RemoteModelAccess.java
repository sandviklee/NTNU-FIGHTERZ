package fightinggame.ui;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;

import fightinggame.users.User;

import java.net.http.HttpResponse;

public class RemoteModelAccess {

    private URI uri;

    public RemoteModelAccess (URI uri){
        this.uri = uri;
    }
    
    public User getUser(String username, String password){
        
    }
}
