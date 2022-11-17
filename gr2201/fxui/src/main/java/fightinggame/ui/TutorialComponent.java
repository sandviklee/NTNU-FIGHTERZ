package fightinggame.ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

/**
 * This class is only meant to help controllers, and has a method to pop up the tutorial component.
 */
public class TutorialComponent {
    /**
     * When run, pops up an alert with tutorial and waits until player has clicked OK before removing.
     */
    public void popupTutorial(){
        // Create alert and set title
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Welcome to NTNU Fighterz!");
        alert.setHeaderText("Welcome to NTNU Fighterz!");
        
        // get tutorial
        //TODO: Fix persistence
        String tutorial = "";
        try {
            File tutorialText = new File("../fxui/src/main/resources/fightinggame/ui/Tutorial.txt");
            tutorial = readFromFile(tutorialText);
        } catch (Exception e) {
            System.out.println(e);
        }

        // Add a text area to the dialog pane of the alert        
        TextArea area = new TextArea(tutorial);
        area.setId("TutorialContent");
        alert.getDialogPane().setContent(area);

        // Style the text area
        String cssPath = getClass().getResource("css/popup.css").toString();
        area.setWrapText(true);
        area.setEditable(false);
        area.getStylesheets().add(cssPath);
        area.getStyleClass().add("textarea");

        // Style the dialog pane
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(cssPath);
        dialogPane.getStyleClass().add("popup");
        dialogPane.setPrefSize(1280, 720);;

        alert.showAndWait();
    }

    //TODO: fix persistence thing
    private static String readFromFile(File userFile) throws IOException {		
        String usersInfo = "";
        if (userFile.exists()){
            Scanner userFileReader = new Scanner(userFile);

            while(userFileReader.hasNextLine()) {
                String line = userFileReader.nextLine();
                usersInfo += line + "\n";
            }
            userFileReader.close();

        }
        return usersInfo;
    }
}
