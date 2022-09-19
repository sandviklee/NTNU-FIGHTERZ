public class GameApp {
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
