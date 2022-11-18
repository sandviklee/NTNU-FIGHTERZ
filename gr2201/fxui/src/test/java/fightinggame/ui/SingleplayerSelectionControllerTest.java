package fightinggame.ui;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        String pathRam = "file:/workspace/gr2201/gr2201/fxui/target/classes/fightinggame/ui/AngryCyclistSplashArt.png";
        String pathSly = "file:/workspace/gr2201/gr2201/fxui/target/classes/fightinggame/ui/PriestOfVengeanceSplashArt.png";
        String pathSol = "file:/workspace/gr2201/gr2201/fxui/target/classes/fightinggame/ui/JinjerJinkSplashArt.png";
        String selectedImage;
        clickOn("#AngryCyclist");
        selectedImage = controller.getCharacterSelected().getImage().getUrl();
        Assertions.assertEquals(pathRam.substring(pathRam.lastIndexOf("/") + 1), selectedImage.substring(selectedImage.lastIndexOf("/") + 1), "Wrong image");
        clickOn("#PriestOfVengeance");
        selectedImage = controller.getCharacterSelected().getImage().getUrl();
        Assertions.assertEquals(pathSly.substring(pathSly.lastIndexOf("/") + 1), selectedImage.substring(selectedImage.lastIndexOf("/") + 1), "Wrong image");
        clickOn("#JinjerJink");
        selectedImage = controller.getCharacterSelected().getImage().getUrl();
        Assertions.assertEquals(pathSol.substring(pathSol.lastIndexOf("/") + 1), selectedImage.substring(selectedImage.lastIndexOf("/") + 1), "Wrong image");
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
