/**
 * @author CHINEDU Oguejiofor
 *Mar 8, 2018
 * 12:30:33 PM
 */
package GUI.utilities.custom;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	txt = new TextField();
	
	setPadding(new Insets(10, 10, 10, 10));
 	setVgap(8);
 	setHgap(10);
 	GridPane.setConstraints(label, 0, 0);
 	GridPane.setConstraints(txt, 0, 1);
 	getChildren().addAll(label,txt);
    }
    
}
