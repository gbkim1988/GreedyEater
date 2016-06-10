package kr.greedyeater.gadget.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HTTPRecord {
	private final IntegerProperty No;
	private final StringProperty Host;
	
	
	/**
	 * 
	 */
	public HTTPRecord() {
		this(null, null);
	}
	/**
	 * 
	 * @param no
	 * @param Host
	 */
	public HTTPRecord(Integer no, String Host){
		this.No = new SimpleIntegerProperty(no);
		this.Host = new SimpleStringProperty(Host);
	}
}
