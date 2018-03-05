package GUI.controller;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

import database.bean.ModuleRegister;
import database.managers.ModuleRegisterManager;
import exception.InvalidAdminException;
import javafx.event.ActionEvent;

public class VerifyStudentController {
	@FXML
	private Button btnRegister;

	// Event Listener on Button[#btnRegister].onAction
	@FXML
	private void registerStudent(ActionEvent event) {
	    Stage window = new Stage(); 
	 	window.initModality(Modality.WINDOW_MODAL);
	        window.setTitle("Student Info");

	        //GridPane with 10px padding around edge
	        GridPane grid = new GridPane();
	        grid.setPadding(new Insets(10, 10, 10, 10));
	        grid.setVgap(8);
	        grid.setHgap(10);

	        //Name Label - constrains use (child, column, row)
	        Label nameLabel = new Label("StudentID:");
	        GridPane.setConstraints(nameLabel, 0, 0);

	        //Name Input
	        TextField txtstudentID = new TextField();
	        GridPane.setConstraints(txtstudentID, 1, 0);

	        //Password Label
	        Label moduleLabel = new Label("Class:");
	        GridPane.setConstraints(moduleLabel, 0, 1);

	        //Password Input
	        TextField txtClass = new TextField();
	       // passInput.setPromptText("password");
	        GridPane.setConstraints(txtClass, 1, 1);
	        
	      //Password Label
	        Label lblPhoneno = new Label("Phone no:");
	        GridPane.setConstraints(lblPhoneno, 0, 2);
	        
	      //Phone
	        TextField txtPhoneno = new TextField();
	       // passInput.setPromptText("password");
	        GridPane.setConstraints(txtClass, 1, 2);
	        
	        //Login
	        Button registerButton = new Button("Register");
	        GridPane.setConstraints(registerButton, 1, 4);
	        
	        registerButton.setOnMouseClicked(e->{
	            try
		    {
			ModuleRegisterManager.registerForModule(new ModuleRegister(txtstudentID.getText(),txtClass.getText()));
		    }
		    catch (SQLException | InvalidAdminException e1)
		    {
			e1.printStackTrace();
		    }
	            
	            System.out.println(txtstudentID.getText() + "was successfully registered for " + txtClass.getText());
	        });
	        //Add everything to grid
	        grid.getChildren().addAll(nameLabel, txtstudentID, moduleLabel, txtClass, txtPhoneno,lblPhoneno,registerButton);

	        Scene scene = new Scene(grid, 300, 200);
	        window.setScene(scene);
	        window.show();
	}
}
