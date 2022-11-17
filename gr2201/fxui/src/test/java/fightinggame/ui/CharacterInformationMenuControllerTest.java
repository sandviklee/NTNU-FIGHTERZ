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
import javafx.scene.control.Label;

public class CharacterInformationMenuControllerTest extends ApplicationTest {
    private CharacterInformationMenuController controller;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("CharacterInformationMenu.fxml"));
        root = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private String getLabelText(String label){
        return ((Label) getCurrentRootById("characterInformationRoot").lookup(label)).getText();
    }

    @Test
    public void testClickRam(){
        clickOn("#AngryCyclist");
        String title = getLabelText("#title");
        Assertions.assertEquals("AngryCyclist", title, "Not correct title");
    }

    @Test
    public void testClickSly(){
        clickOn("#PriestOfVengeance");
        String title = getLabelText("#title");
        Assertions.assertEquals("PriestOfVengeance", title, "Not correct title");
    }

    @Test
    public void testClickSol(){
        clickOn("#JinjerJink");
        String title = getLabelText("#title");
        Assertions.assertEquals("JinjerJink", title, "Not correct title");
    }

    @Test
    public void testGoBack(){
        clickOn("#JinjerJink");
        clickOn("#goBack");
        Assertions.assertNotNull(getCurrentRootById("characterInformationMenuRoot"), "Wrong root when pressing goBack button");
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
