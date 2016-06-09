package kr.greedyeater.gadget;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private AnchorPane tabLayout;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		// Initialize RootLayout and RootLayoutController 
		initRootLayout();
		// Initialize TabLayout and TabLayoutController
		initTabLayout();
	}
	
	private void initRootLayout(){
		// FXML 파일을 로드하기 위해 FXLMLoader 인스턴스를 생성한다.
		try{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
		BorderPane rootlayout = (BorderPane) loader.load();
		this.rootLayout = rootlayout;
		
		Scene scene = new Scene(rootlayout);
		this.primaryStage.setScene(scene);
		this.primaryStage.setTitle("Gadget");
		
		// 아이콘을 추가하는 소스라인을 추가
		this.primaryStage.getIcons().add(new Image("file:resources/images/icon-small.png"));
		
		this.primaryStage.show();
		
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	private void initTabLayout(){
		try{
			FXMLLoader Loader = new FXMLLoader();
			Loader.setLocation(MainApp.class.getResource("view/TabLayout.fxml"));
			AnchorPane tablayout = (AnchorPane) Loader.load();
			
			this.rootLayout.setCenter(tablayout);
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
