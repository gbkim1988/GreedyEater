package kr.greedyeater.gadget.view;

import kr.greedyeater.gadget.MainApp;

public class RootLayoutController {
	private MainApp mainApp;
	
	/**
	 * Default Constructor
	 */
	public RootLayoutController(){ }
	/**
	 * MainApp À» µî·Ï
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;		
	}
}
