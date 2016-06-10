package kr.greedyeater.gadget.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import kr.greedyeater.gadget.model.ProxySetting;
import kr.greedyeater.gadget.util.IPAddressValidator;


public class ProxySettingDialogController {

	@FXML
	private TextField proxyField;
	
	@FXML
	private TextField portField;
	
	private ProxySetting proxy;
	Stage dialogStage;
	boolean isOkClicked;
	/**
	 * Default Constructor.
	 */
	public ProxySettingDialogController(){
		
	}
	
	public void setDialogStage(Stage stage){
		this.dialogStage = stage;
	}
	
	public void setProxySetting(ProxySetting proxy){
		this.proxy = proxy;
	}
	
	@FXML
	private void handleOkButton(){
		if (isValidated()){
			this.proxy.setProxyAddress(proxyField.getText());
			this.proxy.setProxyPort(portField.getText());
			this.dialogStage.close();
			this.isOkClicked = true;
		}
	}
	
	@FXML
	private void handleCancelButton(){
		this.dialogStage.close();
		this.isOkClicked = false;
	}
	
	public boolean isOkClicked(){
		return this.isOkClicked;
	}
	
	public boolean isValidated(){
        String errorMessage = "";
        IPAddressValidator ipAddressValidator = new IPAddressValidator();
        if ( proxyField.getText() == null || proxyField.getText().length() == 0 ){
        	errorMessage += "No valid IP Address!\n";

        }else if (!ipAddressValidator.validate(proxyField.getText())){
    		errorMessage += "No valid IP Address!\n";
    	}

        if ( portField.getText() == null || portField.getText().length() == 0 ){
        	errorMessage += "No valid Port Number!\n";
        }else{
        	try{
		    	Integer portNumber = Integer.parseInt(portField.getText());
		    	if ( ! (0 < portNumber && portNumber < 65535 ) ){
		    		errorMessage += "No valid Port Number!\n";
		    	}
        	}catch(NumberFormatException e){
        		errorMessage += "No valid Port Number!\n";
        	}
        }


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
		
	}
	
}
