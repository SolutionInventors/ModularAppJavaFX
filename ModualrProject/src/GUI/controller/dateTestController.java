package GUI.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;




public class dateTestController {
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField showdate;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public void initialize() {
	    System.out.println("I ran");
	    datePicker.setValue(LOCAL_DATE("01-01-1990"));
	    datePicker.setConverter(new StringConverter<LocalDate>() {
		
		@Override
		public LocalDate fromString(String arg0)
		{
		    if(arg0 != null && !arg0.trim().isEmpty()) {
			return LocalDate.parse(arg0, formatter);
		    }
		    return null;
		}

		@Override
		public String toString(LocalDate t)
		{
		    if (t!=null) {
			return formatter.format(t);
		    }
		    return null;
		}
		
	    });
	}
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}

	
	// Event Listener on DatePicker[#datePicker].onAction
	@FXML
	public void selectedDate(ActionEvent event) {
	    System.out.println(formatter.format(datePicker.getValue()));
	    showdate.setText(formatter.format(datePicker.getValue()));
	}
}
