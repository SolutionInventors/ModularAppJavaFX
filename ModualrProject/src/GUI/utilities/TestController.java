package GUI.utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TestController
{

    // Event Listener on Button.onAction
    @FXML
    public void btnClicked(ActionEvent event)
    {
	int no = 2;
	Label[] lab = new Label[no];
	TextField fid[];
	for (int i = 0; i < no; i++)
	{
	    lab[i] = new Label("message" + i);
	    lab[i] = new Label();
	}

	String str[] =
	{
		"ab", "ads"
	};
    }

}
