package fightinggame.ui;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        Assertions.assertEquals("javafx.scene.image.Image@f0c8a99", controller.getCharacterSelected().getImage().toString());
        clickOn("#Sly");
        Assertions.assertEquals("javafx.scene.image.Image@76a4ebf2", controller.getCharacterSelected().getImage().toString());
        clickOn("#Sol");
        Assertions.assertEquals("javafx.scene.image.Image@53fe15ff", controller.getCharacterSelected().getImage().toString());
    }

   
}
