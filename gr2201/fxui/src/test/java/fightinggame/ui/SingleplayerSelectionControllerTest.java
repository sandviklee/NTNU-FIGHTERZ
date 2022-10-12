package fightinggame.ui;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SingleplayerSelectionControllerTest extends ApplicationTest{
    private SingleplayerSelectionController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("CharacterSelectSingle.fxml"));
        root = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void testSelectCharacter(){
        clickOn("#Ram");
        Assertions.assertEquals("javafx.scene.image.Image@1cf6d1be", controller.getCharacterSelected().getImage().toString());
        clickOn("#Sly");
        Assertions.assertEquals("javafx.scene.image.Image@4b013c76", controller.getCharacterSelected().getImage().toString());
        clickOn("#Sol");
        Assertions.assertEquals("javafx.scene.image.Image@cb0755b", controller.getCharacterSelected().getImage().toString());
    }

    @Test
    public void testBack(){
        clickOn("#goBack");
        Assertions.assertNotNull(getCurrentRootById("mainMenuRoot"), "Wrong root when pressing goBack button");
        
    }

    private Parent getCurrentRootById(String id){
        for (Window window: Window.getWindows()){
            if (window.isShowing() && window instanceof Stage){
                if (window.getScene().getRoot().getId().equals(id)){
                    return window.getScene().getRoot();
                }
            }
        }
        return null;
    }

   
}
