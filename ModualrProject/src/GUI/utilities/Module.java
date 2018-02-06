package GUI.utilities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Module {
	private final SimpleIntegerProperty noofUnits;
	private final SimpleStringProperty moduleName;
	
	public Module(Integer noofUnits, String moduleName) {
		super();
		this.noofUnits = new SimpleIntegerProperty(noofUnits);
		this.moduleName =new SimpleStringProperty(moduleName);
	}
	public Integer getNoofUnits() {
		return noofUnits.get();
	}
	public String getModuleName() {
		return moduleName.get();
	}
	
}//end class
