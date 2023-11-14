// package fightinggame.ui;

// import java.io.IOException;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// import org.testfx.framework.junit5.ApplicationTest;
// import org.testfx.matcher.control.LabeledMatchers;

// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import javafx.stage.Window;
// import javafx.scene.control.Label;

// public class MainMenuControllerTest extends ApplicationTest{
//     private MainMenuController controller;
//     private Parent root;

//     @Override
//     public void start(Stage stage) throws IOException {
//         FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainMenu.fxml"));
//         root = loader.load();
//         controller = loader.getController();
//         stage.setScene(new Scene(root));
//         stage.show();
//     }

//     private Parent getRoot () {
//         return root;
//     }

//     private void click(String lable){
//         clickOn(LabeledMatchers.hasText(lable));
//     }

//     private String getLableText(String label){
//         return ((Label) getRoot().lookup(label)).getText();
//     }

//     @Test
//     public void testSwichToSingleplayerSelection(){
//         click("PLAY SINGLEPLAYER");
//         Assertions.assertNotNull(getCurrentRootById("characterSelectSingleRoot"));
//     }

//     // @Test
//     // public void testSwichToCharacterInformationMenu(){
//     //     click("CHARACTER INFO");
//     //     Assertions.assertNotNull(getCurrentRootById("characterInformationMenuRoot"));
//     // }

//     private Parent getCurrentRootById(String id){
//         for (Window window: Window.getWindows()){
//             if (window.isShowing() && window instanceof Stage){
//                 if (window.getScene().getRoot().getId().equals(id)){
//                     return window.getScene().getRoot();
//                 }
//             }
//         }
//         return null;
//     }




// }
