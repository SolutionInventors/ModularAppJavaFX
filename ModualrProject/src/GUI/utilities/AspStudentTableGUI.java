package GUI.utilities;

import javafx.beans.property.SimpleStringProperty;

public class AspStudentTableGUI {
	private final SimpleStringProperty sn;
	private final SimpleStringProperty firstName;
	private final SimpleStringProperty lastName;
	
	public AspStudentTableGUI(String sn, String firstname, String lastname ) {
		super();
		this.sn = new SimpleStringProperty(sn);
		this.firstName =new SimpleStringProperty(firstname);
		this.lastName = new SimpleStringProperty(lastname);
	}
	public String getSn() {
		return sn.get();
	}
	public String getFirstName() {
		return firstName.get();
	}
	public String getLastName() {
		return lastName.get();
	}
	
}//end class
