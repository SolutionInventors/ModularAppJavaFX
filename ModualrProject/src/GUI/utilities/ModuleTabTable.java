package GUI.utilities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ModuleTabTable {
	private final SimpleIntegerProperty noofUnits;
	private final SimpleStringProperty moduleName;
	private final SimpleStringProperty dateCreated;
	private final SimpleStringProperty amount;
	
	public ModuleTabTable(Integer noofUnits, String moduleName,String dateCreated, String amount) {
		super();
		this.noofUnits = new SimpleIntegerProperty(noofUnits);
		this.moduleName =new SimpleStringProperty(moduleName);
		this.dateCreated =new SimpleStringProperty(dateCreated);
		this.amount =new SimpleStringProperty(amount);
	}
	public Integer getNoofUnits() {
		return noofUnits.get();
	}
	public String getModuleName() {
		return moduleName.get();
	}
	public String getDateCreated() {
		return dateCreated.get();
	}
	public String getAmount() {
		return amount.get();
	}
	
}//end class
