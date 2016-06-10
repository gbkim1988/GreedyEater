package kr.greedyeater.gadget.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProxySetting {
    private final StringProperty ProxyAddress;
    private final StringProperty ProxyPort;
    
    /**
     * Default constructor
     */
    public ProxySetting(){
    	this(null, null);
    }
    
    public ProxySetting(String address, String port )
    {
    	this.ProxyAddress = new SimpleStringProperty();
    	this.ProxyPort = new SimpleStringProperty();
    	
    	this.ProxyAddress.set(address);
    	this.ProxyPort.set(port);
    }
    
    public String getProxyAddress(){
    	return this.ProxyAddress.get();
    }
    
    public void setProxyAddress(String address){
    	this.ProxyAddress.set(address);
    }
    
    public StringProperty ProxyAddressPorperty(){
    	return this.ProxyAddress;
    }
    
    public String getProxyPort(){
    	return this.ProxyPort.get();
    }
    
    public void setProxyPort(String port){
    	this.ProxyPort.set(port);
    }
    
    public StringProperty ProxyPortProperty(){
    	return this.ProxyPort;
    }
}
