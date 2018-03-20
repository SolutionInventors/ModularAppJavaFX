package GUI.utilities;

import javafx.beans.property.SimpleStringProperty;

public class CertificateTableGUI {
	private final SimpleStringProperty certName;
	private final SimpleStringProperty noIssused;
	
	public CertificateTableGUI(String certName, String noIssused) {
		super();
		this.certName = new SimpleStringProperty(certName);
		this.noIssused =new SimpleStringProperty(noIssused);
	}
	public String getCertName() {
		return certName.get();
	}
	public String getNoIssused() {
		return noIssused.get();
	}
	
}//end class
