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
 * AddListenerDialog.fxml �� controller 
 * ��Ʈ��ũ �������̽��� ����� �о�� �ش� ����� TableView�� ǥ���Ѵ�.
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
	 * ������
	 */
	public AddListenerDialogController(){}
	
	/**
	 * �ʱ�ȭ �ܰ迡�� ���������� �̴��� �������̽��� �о�´�.
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
