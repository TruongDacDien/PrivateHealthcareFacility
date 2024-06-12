package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_Role;
import Database.JDBCUtil;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Role;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class LoginController implements Initializable {
	@FXML
	private MFXButton loginBtn;
	
	@FXML
	private AnchorPane login_screen;
	
	@FXML
	private MFXTextField user_name;

	@FXML
	private MFXPasswordField user_password;
	
	@FXML
	private Label recoveryL;
	
	

	Stage window;

	private AlertMessage alertMessage = new AlertMessage();
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	@FXML
	private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login();
            
        }
    }
	public void setupButton() {
		
		login_screen.setOnKeyPressed(this::handleKeyPressed);
		recoveryL.setOnMouseClicked(event->{
			if (event.getSource() == recoveryL) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/RecoveryAccountDialog.fxml"));
					Parent root = (Parent) loader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("Private Heathcare Facility");
					stage.getIcons().add(new Image("/images/hospital-building.png"));
					stage.setResizable(false);
					stage.showAndWait();
					
				} catch (Exception e) {
					alertMessage.errorMessage("Can't load the scene!");
					e.printStackTrace();
					return;
				}
			}
		});
		
		
		
		
		loginBtn.setOnMouseClicked(evnt -> {
			login();
		});
		
	}
	private void login() {
		if (user_name.getText().isEmpty() || user_password.getText().isEmpty()) {
			alertMessage.errorMessage("Incorrect Username/Password");
			return;
		} else {
			String sql = "SELECT * FROM user WHERE UserName = '"+user_name.getText()+"' AND Password = '"+user_password.getText()+"'";
			connection = JDBCUtil.getConnection();
			try {
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					CurrentUser.userId = resultSet.getInt("User_Id");
					
					CurrentUser.userName = resultSet.getString("UserName");
					CurrentUser.userPassword = resultSet.getString("Password");
					CurrentUser.userEmail = resultSet.getString("Email");
					CurrentUser.userName = resultSet.getString("Name");
					CurrentUser.userAddress = resultSet.getString("Address");
					CurrentUser.userBirth = resultSet.getDate("BirthDate");
					CurrentUser.userSDT = resultSet.getString("SDT");
					CurrentUser.userGender = resultSet.getString("Gender");
					alertMessage.successMessage("Login Successfully!");
					window = (Stage) login_screen.getScene().getWindow();
					window.close();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainScreen.fxml"));
					Parent root = (Parent) loader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.setTitle("Private Heathcare Facility");
					stage.getIcons().add(new Image("/images/hospital-building.png"));
					stage.showAndWait();
				} else {
					alertMessage.errorMessage("Incorrect Username/Password!");
				}
				
				
				

			} catch (Exception e) {
			    e.printStackTrace();
			    alertMessage.errorMessage("An error occurred while trying to load the MainScreen.fxml");
			}

		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupButton();
	}

}
