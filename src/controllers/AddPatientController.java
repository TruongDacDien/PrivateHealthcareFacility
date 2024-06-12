package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_BenhNhan;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.BenhNhan;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class AddPatientController implements Initializable {
	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXButton okBtn;

	@FXML
	private MFXTextField patient_address;

	@FXML
	private MFXDatePicker patient_birth;

	@FXML
	private MFXComboBox<String> patient_gender;

	@FXML
	private MFXTextField patient_name;

	@FXML
	private MFXTextField patient_phone;

	@FXML
	private AnchorPane scenePane;

	Stage window;
	private AlertMessage alertMessage = new AlertMessage();

	public void setupButton() {
		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});

		okBtn.setOnMouseClicked(event -> {
			if (patient_name.getText().isEmpty() || patient_birth.getText().isEmpty() || patient_gender.getText().isEmpty()
					|| patient_phone.getText().isEmpty() || patient_address.getText().isEmpty()) {
				alertMessage.errorMessage("Please fill all blank fields!");
				return;
			}else {
				LocalDate slDate = patient_birth.getValue();
				Date birth = Date.valueOf(slDate);
				BenhNhan benhnhan = new BenhNhan(0, patient_name.getText(), birth, patient_gender.getText(),
						patient_address.getText(), patient_phone.getText(), "",true);
				DAO_BenhNhan.getInstance().Add(benhnhan);
				alertMessage.successMessage("New object added successfully!");
			}
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");
		patient_gender.setItems(genderList);

		setupButton();
	}

}
