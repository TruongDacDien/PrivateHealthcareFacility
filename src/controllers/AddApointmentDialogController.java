package controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_BacSi;
import Data_Access_Object.DAO_ChiTietToaThuoc;
import Data_Access_Object.DAO_LichHen;
import Data_Access_Object.DAO_Thuoc;
import Database.JDBCUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.*;

public class AddApointmentDialogController implements Initializable {

	@FXML
	private MFXDatePicker appoint_date;

	@FXML
	private MFXComboBox<BacSi> appoint_doctor;

	@FXML
	private MFXComboBox<String> appoint_state;

	@FXML
	private MFXComboBox<LocalTime> appoint_time;

	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXButton okBtn;

	@FXML
	private AnchorPane scenePane;

	private AlertMessage alertMessage = new AlertMessage();
	private Stage window;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setupButton();
		setupScreen();
	}

	public void setupScreen() {
		for (int hour = 0; hour < 24; hour++) {
			appoint_time.getItems().add(LocalTime.of(hour, 0));
		}
		ObservableList<String> stateList = FXCollections.observableArrayList("Completed", "Uncompleted");
		appoint_state.setItems(stateList);
		appoint_state.setText("Uncompleted");
		connection = JDBCUtil.getConnection();
		String query1 = "SELECT MaBS, HoTen FROM BACSI WHERE Status = "+ true;
		try {
			preparedStatement = connection.prepareStatement(query1);
			resultSet = preparedStatement.executeQuery();
			List<BacSi> bacSis = new ArrayList<BacSi>();
			while (resultSet.next()) {
				int MaBS = resultSet.getInt("MaBS");
				String HoTen = resultSet.getString("HoTen");
				bacSis.add(new BacSi(MaBS, HoTen, null, null, null, null, null, 0, null,true));
			}
			appoint_doctor.setItems(FXCollections.observableArrayList(bacSis));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (CurrentPatient.appointmentId != 0) {
			LichHen lichHen = new LichHen(CurrentPatient.appointmentId, 0, 0, null, null,true);
			LichHen temp = DAO_LichHen.getInstance().seclectById(lichHen);
			
			if (temp.getTrangThai().equals("Completed")) {
				appoint_doctor.setDisable(true);
				appoint_date.setDisable(true);
				appoint_time.setDisable(true);
				appoint_state.setDisable(true);
			}
			
			appoint_date.setText(Date.valueOf(temp.getThoiGianHen().toLocalDateTime().toLocalDate()).toString());
			appoint_date.setValue(temp.getThoiGianHen().toLocalDateTime().toLocalDate());
			appoint_time.setText(LocalTime.of(temp.getThoiGianHen().toLocalDateTime().getHour(), 0).toString());
			appoint_time.setValue(temp.getThoiGianHen().toLocalDateTime().toLocalTime());
			appoint_state.setValue(temp.getTrangThai());

			BacSi bacSi = new BacSi(temp.getMaBS(), null, null, null, null, null, null, 0, null,true);
			BacSi temp1 = DAO_BacSi.getInstance().seclectById(bacSi);
			appoint_doctor.setText(String.valueOf(temp1.getMaBS() + " - " + temp1.getHoTen()));
		}
	}

	public void setupButton() {

		okBtn.setOnMouseClicked(event -> {
			if (CurrentPatient.appointmentId == 0) {
				if (appoint_doctor.getText().isEmpty() || appoint_date.getText().isEmpty()
						|| appoint_time.getText().isEmpty() || appoint_state.getText().isEmpty()) {
					alertMessage.errorMessage("Please fill all blank fields!");
					return;
				} else {
					int maBS = 0;
					int indexOfDash = appoint_doctor.getText().indexOf('-');
					if (indexOfDash != -1) {
						maBS = Integer.parseInt(appoint_doctor.getText().substring(0, indexOfDash).trim());
					}
					LocalDate localDate = appoint_date.getValue();
		            LocalTime localTime = appoint_time.getValue();
		            LocalDateTime appointmentDateTime = LocalDateTime.of(localDate, localTime);
		            Timestamp timestamp = Timestamp.valueOf(appointmentDateTime);
		            LichHen lichHen = new LichHen(0, maBS, CurrentPatient.patientid, timestamp, appoint_state.getText(),true);
		            DAO_LichHen.getInstance().Add(lichHen);
		            System.out.print(CurrentPatient.patientid);
					alertMessage.successMessage("New object added successfully!");
				}
			} else {
				if (appoint_doctor.getText().isEmpty() || appoint_date.getText().isEmpty()
						|| appoint_time.getText().isEmpty() || appoint_state.getText().isEmpty()) {
					alertMessage.errorMessage("Please fill all blank fields!");
					return;
				} else {
					int maBS = 0;
					int indexOfDash = appoint_doctor.getText().indexOf('-');
					if (indexOfDash != -1) {
						maBS = Integer.parseInt(appoint_doctor.getText().substring(0, indexOfDash).trim());
					}
					System.out.print(appoint_date.getValue());
					LocalDate localDate = appoint_date.getValue();
		            LocalTime localTime = appoint_time.getValue();
		            
		            LocalDateTime appointmentDateTime = LocalDateTime.of(localDate, localTime);
		            Timestamp timestamp = Timestamp.valueOf(appointmentDateTime);
		            LichHen lichHen = new LichHen(CurrentPatient.appointmentId, maBS, CurrentPatient.patientid, timestamp, appoint_state.getText(),true);
					DAO_LichHen.getInstance().Update(lichHen);
		            alertMessage.successMessage("Information changed successfully!");
				}
			}
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});

		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});
	}
}
