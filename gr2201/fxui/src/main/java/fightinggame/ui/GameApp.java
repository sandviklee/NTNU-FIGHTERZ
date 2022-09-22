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
		Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setX(bounds.getMinX());
		primaryStage.setY(bounds.getMinY());
		primaryStage.setWidth(bounds.getWidth());
		primaryStage.setHeight(bounds.getHeight());
		primaryStage.setMaxWidth(bounds.getWidth() * 2);
		primaryStage.show();
	}
}
