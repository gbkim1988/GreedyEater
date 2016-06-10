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
		// Model ���		
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
			// ��ư�� ���� �� �ش� ��ƾ�� �����Ѵ�.
			//System.out.println("Pressed!");
			proxyToggle.setText("Deativate");
			//�ʱ�ȭ ��Ű��
			GlobalProxyJNI.init();
			// �켱 deactivate �� ��Ű��
			GlobalProxyJNI.deactivateGlobalProxy();
			// ������ �ּҸ� ���ؼ� 
			GlobalProxyJNI.activateGlobalProxy(currentProxySetting.getText());
		}else{
			// ��ư�� ���� �� �ش� ��ƾ�� �����Ѵ�.
			System.out.println("Released!");

			// �ʱ�ȭ ��Ű�� 
			GlobalProxyJNI.init();
			// �켱 deactivate �� ��Ű��
			GlobalProxyJNI.deactivateGlobalProxy();
		}
	}

}
