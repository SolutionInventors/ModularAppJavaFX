package GUI.utilities;

import javafx.beans.property.SimpleStringProperty;

public class StudentTableGUI {
	private final SimpleStringProperty id;
	private final SimpleStringProperty firstName;
	private final SimpleStringProperty lastName;
	private final SimpleStringProperty className;
	
	public StudentTableGUI(String id, String firstname, String lastname,String className) {
		super();
		this.id = new SimpleStringProperty(id);
		this.firstName =new SimpleStringProperty(firstname);
		this.lastName = new SimpleStringProperty(lastname);
		this.className = new SimpleStringProperty(className);
	}
	public String getId() {
		return id.get();
	}
	public String getFirstName() {
		return firstName.get();
	}
	public String getLastName() {
		return lastName.get();
	}
	public String getClassName() {
		return className.get();
	}
	
}//end class
