package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Stage stg;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stg = primaryStage;
			primaryStage.setResizable(false);
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/com/Driving_School/view/LogIn.fxml"));
			Scene scene = new Scene(root,1200,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			com.Driving_School.model.MySQLConnect.connectDb();
			primaryStage.setTitle("Al-Aqsa Driving School - Log In");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	  public void changeScene(String fxml) throws IOException {
	        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
	        stg.getScene().setRoot(pane);
	    }

	public static void main(String[] args) {
		launch(args);
	}
}
