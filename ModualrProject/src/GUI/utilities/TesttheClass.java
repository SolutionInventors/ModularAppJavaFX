/**
 * @author CHINEDU Oguejiofor
 *Mar 8, 2018
 * 12:59:34 PM
 */
package GUI.utilities;

import GUI.utilities.custom.InputPair;
import GUI.utilities.custom.TestVBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TesttheClass extends Application
{

    Button button;
    

    public static void main(String[] args)
    {
	launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception  {
	primaryStage.setTitle("Title of the Window");
	InputPair p1 = new InputPair("StudentID:");
	InputPair p2 = new InputPair("Module Name:");
	
	InputPair[] pairs = {
		new InputPair("StudentID:"),
		new InputPair("Module Name:"),
	};
	
	
	button = new Button();
	button.setText("Click me");
	TestVBox layout = new TestVBox(button,p2,p1);
	
	Scene scene = new Scene(layout, 300, 250);
	primaryStage.setScene(scene);
	primaryStage.show();
    }

}
