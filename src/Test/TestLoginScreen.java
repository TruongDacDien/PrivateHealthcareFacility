package Test;



import controllers.CurrentUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TestLoginScreen extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/LoginScreen.fxml"));

            Scene scene = new Scene(root);
           
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Private Heathcare Facility");
            primaryStage.getIcons().add(new Image("/images/hospital-building.png"));
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}