/**
 * @author CHINEDU Oguejiofor
 *Mar 8, 2018
 * 12:30:33 PM
 */
package GUI.utilities.custom;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class InputPair extends GridPane{
 // GridPane with 10px padding around edge
    private Label label; 
    private TextField txt;
    
    public String getText() {
        return txt.getText();
    }
    
    public String getName() {
	return label.getText();
    }
    public InputPair(String labelName) {
	label = new Label (labelName);
	label.setWrapText(true);
	txt = new TextField();
	
	ColumnConstraints column1 = new ColumnConstraints(110); //comp name

	getColumnConstraints().addAll(column1);

	setPadding(new Insets(10, 10, 10, 10));
 	setVgap(8);
 	setHgap(10);
 	GridPane.setConstraints(label, 0, 0);
 	GridPane.setConstraints(txt, 1,0);
 	getChildren().addAll(label,txt);
    
    
    
    }//end constructor
    
    
}
