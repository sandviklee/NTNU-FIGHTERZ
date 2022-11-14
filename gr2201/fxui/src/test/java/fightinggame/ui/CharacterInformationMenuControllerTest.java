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
        clickOn("#Ram");
        String title = getLabelText("#title");
        Assertions.assertEquals("Ram", title, "Not correct title");
    }

    @Test
    public void testClickSly(){
        clickOn("#Sly");
        String title = getLabelText("#title");
        Assertions.assertEquals("Sly", title, "Not correct title");
    }

    @Test
    public void testClickSol(){
        clickOn("#Sol");
        String title = getLabelText("#title");
        Assertions.assertEquals("Sol", title, "Not correct title");
    }

    @Test
    public void testGoBack(){
        clickOn("#Sol");
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
