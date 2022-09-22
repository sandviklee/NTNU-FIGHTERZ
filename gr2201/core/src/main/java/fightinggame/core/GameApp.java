package fightinggame.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class GameApp extends Application  {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("NTNU Fighterz");
		primaryStage.setScene(new Scene(FXMLLoader.load(GameApp.class.getResource("LogIn.fxml"))));
		primaryStage.show();
	}
}
