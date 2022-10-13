package fightinggame.ui;

import fightinggame.users.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController {
    private User user;

    protected User getUser(){
        return this.user;
    }

    protected void setUser(User user){
        this.user = user;
    }

    /**
     * Changes scenes with the corresponding root
     * @param title
     * @param root
     * @param event
     */
    protected void changeScene(String title, Parent root, ActionEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    protected void changeSceneFullscreen(String title, Parent root, ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
       
    }

    protected void changeSceneMouseEvent(String title, Parent root, MouseEvent event){
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
