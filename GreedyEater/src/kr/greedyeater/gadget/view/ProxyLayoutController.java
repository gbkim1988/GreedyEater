package kr.greedyeater.gadget.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import kr.greedyeater.gadget.MainApp;
import kr.greedyeater.gadget.model.HTTPRecord;
import kr.greedyeater.gadget.model.ProxySetting;
import kr.greedyeater.gadget.util.GlobalProxyJNI;

public class ProxyLayoutController {
	@FXML 
	private ToggleButton proxyToggle;
	
	@FXML
	private TableView<HTTPRecord> httpTable;
	
	@FXML
	private TableColumn<HTTPRecord, Integer> noColumn;
	
	@FXML
	private TableColumn<HTTPRecord, String> hostColumn;
	
	@FXML
	private Label currentProxySetting;
	
	private MainApp mainApp;
	
	private ProxySetting proxySetting;
	
	/**
	 *  The constructor.
	 */
	public ProxyLayoutController(){
		
	}
	
	@FXML
	private void initialize(){
		proxyToggle.setDisable(true);
	}
	
	public void setMainApp(MainApp mainApp){
		
		this.mainApp = mainApp;
		System.out.println(this.mainApp.toString());
		// Model 등록		
	}
	
	@FXML
	private void handleProxySetting(){
		ProxySetting proxy = new ProxySetting();
		boolean isOk = this.mainApp.showProxySettingDialog(proxy);
		
		if (isOk){
			this.proxySetting = proxy;
			currentProxySetting.setText( proxy.getProxyAddress() + ":"  +proxy.getProxyPort());
			proxyToggle.setDisable(false);
		}
	}
	
	@FXML
	private void hanldeToggleButton(){
		
		if (proxyToggle.isSelected()){
			// 버튼을 누를 때 해당 루틴이 동작한다.
			//System.out.println("Pressed!");
			proxyToggle.setText("Deativate");
			//초기화 시키고
			GlobalProxyJNI.init();
			// 우선 deactivate 를 시키고
			GlobalProxyJNI.deactivateGlobalProxy();
			// 지정된 주소를 통해서 
			GlobalProxyJNI.activateGlobalProxy(currentProxySetting.getText());
		}else{
			// 버튼을 누를 때 해당 루틴이 동작한다.
			System.out.println("Released!");

			// 초기화 시키고 
			GlobalProxyJNI.init();
			// 우선 deactivate 를 시키고
			GlobalProxyJNI.deactivateGlobalProxy();
		}
	}

}
