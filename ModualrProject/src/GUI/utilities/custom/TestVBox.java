/**
 * @author CHINEDU Oguejiofor
 *Mar 8, 2018
 * 12:24:38 PM
 */
package GUI.utilities.custom;

import java.util.Arrays;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class TestVBox extends VBox
{

    private Button button;
    private InputPair[] pairs;

    public TestVBox(Button btn, InputPair... inputPairs){
	super();

	button = btn;
	setPairs(inputPairs);
	setPadding(new Insets(5));
	for (int i = 0; i < inputPairs.length; i++){
	    getChildren().add(inputPairs[i]);
	
	}getChildren().add(button);

    }

    public String getValue(String string) {
	try{
	    InputPair result = Arrays.stream(getPairs()).filter(pair -> pair.getName().equals(string)).findFirst()
		    .get();
	    return result.getText();
	}
	catch (Exception e) {
	    return "";
	}

    }

    public InputPair[] getPairs(){
	return pairs;
    }

    public void setPairs(InputPair[] pairs){
	this.pairs = pairs;
    }

}
