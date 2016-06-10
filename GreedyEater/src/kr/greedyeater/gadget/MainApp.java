package kr.greedyeater.gadget;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import kr.greedyeater.gadget.model.ProxySetting;
import kr.greedyeater.gadget.view.ProxyLayoutController;
import kr.greedyeater.gadget.view.ProxySettingDialogController;

public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	private TabPane mainTabPane;
	private ProxySetting mainProxy;
	// Loading Image 
	public static final String SPLASH_IMAGE =
			"file:resources/images/Logo2.png";
	
	private Pane splashLayout;
	private ProgressBar loadProgress;
	private Label progressText;
	private static final int SPLASH_WIDTH = 676;
	private static final int SPLASH_HEIGHT = 227;
	/**
	 * start 메서드 실행 전에 실행되는 메서드
	 */
	@Override
	public void init() throws Exception {
		
		ImageView splash = new ImageView(new Image(SPLASH_IMAGE));
		splash.setFitHeight(SPLASH_HEIGHT - 20);
		splash.setFitWidth(SPLASH_WIDTH- 20);
		loadProgress = new ProgressBar();
		
		loadProgress.setPrefWidth(SPLASH_WIDTH- 20);
		
		progressText = new Label("샘플 프로그레스 바");
		progressText.setAlignment(Pos.CENTER);
		
		splashLayout = new VBox();
		
		splashLayout.getChildren().addAll(splash, loadProgress, progressText);
		splashLayout.setStyle(
				"-fx-padding: 5;"
				+"-fx-background-color: cornsilk;"
				+"-fx-border-width: 5;"
				//+"-fx-border-color: linear-gradient();"
		);
		
		splashLayout.setEffect(new DropShadow());
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		final Task<ObservableList<String>> friendTask = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> foundFriends =
                        FXCollections.<String>observableArrayList();
                // UI 화면 로드 전 주변환경 체크 용도로 사용
                // 예: 파이썬 설치 여부 체크, 윈도우 환경 체크, 프록시 설정 여부 체크 등등
                ObservableList<String> availableFriends =
                        FXCollections.observableArrayList(
                                "환경 설정 체크"
                        );
                
                updateMessage("초기 환경 세팅 중 입니다. . . .");
                for (int i = 0; i < availableFriends.size(); i++) {
                    Thread.sleep(400);
                    updateProgress(i + 1, availableFriends.size());
                    String nextFriend = availableFriends.get(i);
                    foundFriends.add(nextFriend);
                    updateMessage("초기 환경 점검 중 ... " + nextFriend);
                }
                Thread.sleep(400);
                updateMessage("모든 환경 구성이 완료되었습니다.");
                
                return foundFriends;
            }
        };
        
        showSplash(primaryStage,
        		friendTask,
        		() -> showMainStage());
        
        new Thread(friendTask).start();
	}
    private void showSplash(
    		final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) {
    	// Task 객체내의 message , progress 프로퍼티와 progress
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
            	// task 의 작업이 종료되면 FadeOut 되면서 사라지는 효과를 연출하기 위해서 
            	// 아래의 코드가 필요한 것으로 보인다. 
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.
        });
        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
        
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
			//this.tabLayout = tablayout;
			this.rootLayout.setCenter(tablayout);
			this.mainTabPane = (TabPane) tablayout.lookup("#mainTabPane");
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private void showMainStage(){
		this.primaryStage = new Stage();
		// Initialize RootLayout and RootLayoutController 
		initRootLayout();
		// Initialize TabLayout and TabLayoutController
		initTabLayout();
		// Initialize ProxyLayout
		initProxyLayout();
	}
	private void initProxyLayout(){
		try{
			FXMLLoader Loader = new FXMLLoader();
			Loader.setLocation(MainApp.class.getResource("view/ProxyLayout.fxml"));
			AnchorPane pane = Loader.load();
			addTabPane(pane, "Proxy");
			ProxyLayoutController controller = Loader.getController();
			controller.setMainApp(this);
			
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	private Pane loadFXMLLayout(String pathTofxml) throws IOException {
		FXMLLoader Loader = new FXMLLoader();
		Loader.setLocation(MainApp.class.getResource(pathTofxml));
		return (Pane)Loader.load();
	}
	private void addTabPane(Pane pane, final String title){
		Tab tab = new Tab();
		tab.setContent(pane);
		tab.setText(title);
		this.mainTabPane.getTabs().add(tab);
	}
	
    public boolean showProxySettingDialog(ProxySetting proxy) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ProxySettingDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Proxy");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Set the person into the controller.
            ProxySettingDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProxySetting(proxy);
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
	public static void main(String[] args) {
		launch(args);
	}
	
    public interface InitCompletionHandler {
        void complete();
    }
}
