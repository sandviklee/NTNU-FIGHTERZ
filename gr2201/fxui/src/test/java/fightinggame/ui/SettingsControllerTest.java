package fightinggame.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;


public class SettingsControllerTest extends ApplicationTest{
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
        root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private Parent getRoot () {
        return root;
    }

    private void setRoot(Parent root){
        this.root = root;
    }

    private void click(String label){
        clickOn(LabeledMatchers.hasText(label));
    }

    private String getLabelText(String label){
        return ((Label) getRoot().lookup(label)).getText();
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

    @Test
    private void testChangePassword(){
        clickOn("#usernameField").write("s");
        clickOn("#passwordField").write("s");
        click("Login");

        clickOn("#settings");
        setRoot(getCurrentRootById("settingsRoot"));

        clickOn("#passwordField").write("ss");
        clickOn("#confirmPasswordField").write("ss");
        clickOn("#changePasswordButton");
        assertEquals("Changed password!", getLabelText("#feedback"), "Werent able to change passwords");

        clickOn("#passwordField").write("s");
        clickOn("#confirmPasswordField").write("s");
        clickOn("#changePasswordButton");

        clickOn("#passwordField").write("!");
        clickOn("#confirmPasswordField").write("!");
        clickOn("#changePasswordButton");
        assertEquals("Invalid password, only use letter and numbers.", getLabelText("#feedback"), "You should not be able to change passwords");
    }

    @Test
    private void testDeleteUser(){
        clickOn("#usernameField").write("s");
        clickOn("#passwordField").write("s");
        click("Login");

        clickOn("#settings");
        setRoot(getCurrentRootById("settingsRoot"));

        clickOn("#deleteUser");
        assertEquals("To delete your user, type 'DELETE' in the field bellow", getLabelText("#feedback"));
    }

}
