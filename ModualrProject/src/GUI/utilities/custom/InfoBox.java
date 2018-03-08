/**
 * @author CHINEDU Oguejiofor
 *Mar 8, 2018
 * 11:02:09 AM
 */
package GUI.utilities.custom;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InfoBox
{
    public static void display(int numofitems, Label label1[], TextField txtBox[], String[] labelName, String title)
    {
	Stage window = new Stage();
	window.initModality(Modality.WINDOW_MODAL);
	window.setTitle(title);

	// GridPane with 10px padding around edge
	GridPane grid = new GridPane();
	grid.setPadding(new Insets(10, 10, 10, 10));
	grid.setVgap(8);
	grid.setHgap(10);

	for (int i = 0; i < numofitems; i++)
	{
	    label1[i] = new Label(labelName[i]);
	    GridPane.setConstraints(label1[i], i, 0);

	    // Name Input
	    txtBox[i] = new TextField();
	    GridPane.setConstraints(txtBox[i], i, 1);
	    grid.getChildren().addAll(label1[i], txtBox[i]);
	}
	
		Scene scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show();
	/*// Login
	Button registerButton = new Button("Register");
	GridPane.setConstraints(registerButton, 1, 2);*/

    }
}
