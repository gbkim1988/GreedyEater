package kr.greedyeater.gadget.view;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import kr.greedyeater.gadget.model.ListenerSetting;
/**
 * AddListenerDialog.fxml 의 controller 
 * 네트워크 인터페이스의 목록을 읽어와 해당 목록을 TableView에 표시한다.
 * @author gun88
 *
 */
public class AddListenerDialogController {
	@FXML
	TableView<ListenerSetting> listenerTable;
	@FXML
	TableColumn<ListenerSetting, String> runningColumn;
	@FXML
	TableColumn<ListenerSetting, String> hostColumn;
	@FXML
	TableColumn<ListenerSetting, String> portColumn;
	@FXML
	TableColumn<ListenerSetting, Boolean> activeColumn;
	
	/**
	 * 생성자
	 */
	public AddListenerDialogController(){}
	
	/**
	 * 초기화 단계에서 윈도우즈의 이더넷 인터페이스를 읽어온다.
	 */
	@FXML
	private void initialize(){
		try{
			Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets))
			{
		        System.out.printf("Display name: %s\n", netint.getDisplayName());
		        System.out.printf("Name: %s\n", netint.getName());
		        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
		        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
		        	System.out.printf("InetAddress: %s\n", inetAddress);
		        }
		        System.out.printf("\n");
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
