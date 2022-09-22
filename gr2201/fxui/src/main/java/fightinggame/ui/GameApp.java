package fightinggame.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class GameApp extends Application  {
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("NTNU Fighterz");
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("LogIn.fxml"))));
		primaryStage.setMaximized(false);
		primaryStage.show();
	}
}
