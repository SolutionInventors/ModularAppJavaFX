package GUI.utilities;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TestGUI extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			//Parent root = FXMLLoader.load(getClass().getResource("/GUI/views/Module.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/GUI/views/CombineTabs.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Student");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
