
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

import Data_Access_Object.DAO_BacSi;
import Data_Access_Object.DAO_Role;
import Database.JDBCUtil;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableRow;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.BooleanFilter;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.i18n.I18N;
import io.github.palexdev.materialfx.utils.FXCollectors;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.BacSi;
import models.Role;
import io.github.palexdev.materialfx.utils.others.observables.When;

public class DoctorScreenController implements Initializable {
	Stage window;
	private boolean canChange;
	private int timeChange;
	private AlertMessage alertMessage = new AlertMessage();

	private ObservableList<BacSi> doctorList = null;

	@FXML
	private AnchorPane darkPane, detailMenuPane, menuPane;

	@FXML
	private ImageView menuImg;

	@FXML
	private MFXTextField  doctor_name, doctor_address, doctor_phone, doctor_email, doctor_spec, doctor_exper;

	@FXML
	Label doctor_id;
	@FXML
	private MFXComboBox<String> doctor_gender;

	@FXML
	private MFXButton addBtn, editBtn;

	@FXML
	Dialog<ButtonType> dialog = new Dialog<>();

	@FXML
	private MFXTableView<BacSi> tableDoctor;

	@FXML
	private MFXButton homepageBtn, admissionBtn, pateintBtn, medicineBtn, subclinicalBtn, doctorBtn, userBtn, reportBtn;
	
	@FXML
	private ImageView userImg;
	
	@FXML
	private MFXComboBox<String> doctor_status;
	ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupScreen();
		setupTable();
		setupTabChange();
		setupButton();
	}

	private void setupTabChange() {
		
		
		homepageBtn.setOnAction(event -> {
			window = (Stage) menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MainScreen.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Scene scene = new Scene(root);
			window.setTitle("Private Heathcare Facility");
			window.getIcons().add(new Image("/images/hospital-building.png"));
			window.setScene(scene);

		});

		admissionBtn.setOnAction(event -> {
			window = (Stage) menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MedicalExaminationFormScreen.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Scene scene = new Scene(root);
			window.setTitle("Private Heathcare Facility");
			window.getIcons().add(new Image("/images/hospital-building.png"));
			window.setScene(scene);

		});
		pateintBtn.setOnAction(event -> {
			window = (Stage) menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PatientScreen.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Scene scene = new Scene(root);
			window.setTitle("Private Heathcare Facility");
			window.getIcons().add(new Image("/images/hospital-building.png"));
			window.setScene(scene);

		});
		medicineBtn.setOnAction(event -> {
			window = (Stage) menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MedicineScreen.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Scene scene = new Scene(root);
			window.setTitle("Private Heathcare Facility");
			window.getIcons().add(new Image("/images/hospital-building.png"));
			window.setScene(scene);

		});
		subclinicalBtn.setOnAction(event -> {
			window = (Stage) menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SubcinicalScreen.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Scene scene = new Scene(root);
			window.setTitle("Private Heathcare Facility");
			window.getIcons().add(new Image("/images/hospital-building.png"));
			window.setScene(scene);

		});
		doctorBtn.setOnAction(event -> {
			window = (Stage) menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DoctorScreen.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Scene scene = new Scene(root);
			window.setTitle("Private Heathcare Facility");
			window.getIcons().add(new Image("/images/hospital-building.png"));
			window.setScene(scene);

		});
		userBtn.setOnAction(event -> {
			window = (Stage) menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/StaffScreen.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Scene scene = new Scene(root);
			window.setTitle("Private Heathcare Facility");
			window.getIcons().add(new Image("/images/hospital-building.png"));
			window.setScene(scene);

		});
		reportBtn.setOnAction(event -> {
			window = (Stage) menuImg.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReportScreen.fxml"));
			Parent root = null;
			try {
				root = loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Scene scene = new Scene(root);
			window.setTitle("Private Heathcare Facility");
			window.getIcons().add(new Image("/images/hospital-building.png"));
			window.setScene(scene);

		});

	}

	public void setupScreen() {

		darkPane.setVisible(false);

		javafx.animation.FadeTransition fadeTransition = new javafx.animation.FadeTransition(Duration.seconds(0.39),
				darkPane);
		fadeTransition.setFromValue(1);
		fadeTransition.setToValue(0);
		fadeTransition.play();

		javafx.animation.TranslateTransition translateTransition = new javafx.animation.TranslateTransition(
				Duration.seconds(0.39), detailMenuPane);
		translateTransition.setByX(-600);
		translateTransition.play();
		doctor_status.setItems(statusList);
		canChange = true;
		menuPane.setOnMouseEntered(event -> {
			timeChange += 1;
			if (canChange && timeChange == 1) {
				menuImg.setVisible(false);
				darkPane.setVisible(true);

				javafx.animation.FadeTransition fadeTransition1 = new javafx.animation.FadeTransition(
						Duration.seconds(0.39), darkPane);
				// fadeTransition1.setFromValue(0);
				// fadeTransition1.setToValue(0.1);
				fadeTransition1.play();

				javafx.animation.TranslateTransition translateTransition1 = new javafx.animation.TranslateTransition(
						Duration.seconds(0.01), detailMenuPane);
				translateTransition1.setByX(+600);
				translateTransition1.setOnFinished(event1 -> {
					canChange = false;

				});
				translateTransition1.play();

			}

		});
		detailMenuPane.setOnMouseExited(event -> {

			if (!canChange && event.getX() > 100) {

				javafx.animation.FadeTransition fadeTransition1 = new javafx.animation.FadeTransition(
						Duration.seconds(0.39), darkPane);
				// fadeTransition1.setFromValue(0.15);
				// fadeTransition1.setToValue(0);
				fadeTransition1.play();

				fadeTransition1.setOnFinished(event1 -> {
					darkPane.setVisible(false);
				});

				javafx.animation.TranslateTransition translateTransition1 = new javafx.animation.TranslateTransition(
						Duration.seconds(0.39), detailMenuPane);
				translateTransition1.setByX(-600);
				translateTransition1.setOnFinished(event1 -> {
					canChange = true;
					timeChange = 0;
					menuImg.setVisible(true);

				});
				translateTransition1.play();
			}
		});

	}

	// Load data from the database
	public void loadTable() {
Role role = DAO_Role.getInstance().selectByCondition2(" User_Id = " + CurrentUser.userId);
		
		
		
		if(!role.isRole1()) admissionBtn.setDisable(true);
		
		if(!role.isRole2()) pateintBtn.setDisable(true);
		
		if(!role.isRole3()) medicineBtn.setDisable(true);
		
		if(!role.isRole4()) subclinicalBtn.setDisable(true);
		
		if(!role.isRole5()) doctorBtn.setDisable(true);
		
		if(!role.isRole6()) userBtn.setDisable(true);
		
		if(!role.isRole7()) reportBtn.setDisable(true);
		doctorList = DAO_BacSi.getInstance().selectAll();
		tableDoctor.setItems(doctorList);
		tableDoctor.autosize();
		tableDoctor.autosizeColumns();
		tableDoctor.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() {	
		doctorList.clear();
		loadTable();
	}

	@SuppressWarnings("unchecked")
	public void setupTable() {
		ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");
		doctor_gender.setItems(genderList);
		// Initialize columns
		MFXTableColumn<BacSi> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(BacSi::getMaBS));
		MFXTableColumn<BacSi> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(BacSi::getHoTen));
		MFXTableColumn<BacSi> specializeColumn = new MFXTableColumn<>("Specialize", true,Comparator.comparing(BacSi::getChuyenMon));
		MFXTableColumn<BacSi> addressColumn = new MFXTableColumn<>("Address", true,Comparator.comparing(BacSi::getDiaChi));
		MFXTableColumn<BacSi> phoneNumberColumn = new MFXTableColumn<>("Phone Number", true,Comparator.comparing(BacSi::getSDT));
		MFXTableColumn<BacSi> genderColumn = new MFXTableColumn<>("Gender", true,Comparator.comparing(BacSi::getGioiTinh));
		MFXTableColumn<BacSi> emailColumn = new MFXTableColumn<>("Email", true, Comparator.comparing(BacSi::getEmail));
		MFXTableColumn<BacSi> yearsOfExperienceColumn = new MFXTableColumn<>("Years Of Experience", true,Comparator.comparing(BacSi::getNamKN));
		MFXTableColumn<BacSi> statusColumn = new MFXTableColumn<>("Active?", true,Comparator.comparing(BacSi::getStatus));

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BacSi::getMaBS));
		nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BacSi::getHoTen));
		specializeColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BacSi::getChuyenMon));
		addressColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BacSi::getDiaChi));
		phoneNumberColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BacSi::getSDT));
		genderColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BacSi::getGioiTinh));
		emailColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BacSi::getEmail));
		yearsOfExperienceColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BacSi::getNamKN));
		statusColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BacSi::getStatus));

		
		// Add columns to the table
		tableDoctor.getTableColumns().addAll(idColumn, nameColumn, specializeColumn,   genderColumn,  yearsOfExperienceColumn,statusColumn);

		// Add filters to the table
		tableDoctor.getFilters().addAll(new IntegerFilter<>("Id", BacSi::getMaBS),
				new StringFilter<>("Name", BacSi::getHoTen), new StringFilter<>("Specialize", BacSi::getChuyenMon),
				
				new StringFilter<>("Gender", BacSi::getGioiTinh), new StringFilter<>("Email", BacSi::getEmail),
				new IntegerFilter<>("Years Of Experience", BacSi::getNamKN),
				new BooleanFilter<>("Status", BacSi::getStatus));
		
		loadTable();
		
		// select row
		
		tableDoctor.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					

					doctor_id.setText(String.valueOf(p.getMaBS()));
					doctor_name.setText(p.getHoTen());
					doctor_spec.setText(p.getChuyenMon());
					doctor_address.setText(p.getDiaChi());
					doctor_phone.setText(p.getSDT());
					doctor_gender.setText(p.getGioiTinh());
					doctor_email.setText(p.getEmail());	
					doctor_exper.setText(String.valueOf(p.getNamKN()));
					if(p.getStatus()) {
						doctor_status.setText(statusList.get(0));
					}
					else {
						doctor_status.setText(statusList.get(1));
					}
				});
			}
		});
	}

	public void setupButton() {
		editBtn.setOnMouseClicked(event -> {
			if (doctor_id.getText().isEmpty()) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
			if (doctor_name.getText().isEmpty() || doctor_gender.getText().isEmpty()
					|| doctor_address.getText().isEmpty() || doctor_phone.getText().isEmpty()
					|| doctor_email.getText().isEmpty() || doctor_spec.getText().isEmpty()
					|| doctor_exper.getText().isEmpty()) {
				alertMessage.errorMessage("Please fill all blank fields!");
				return;
			} else {
				boolean status = true;
				if(doctor_status.getText().equals("Active")) {
					
				}
				else status = false;
				BacSi bacsi = new BacSi(Integer.parseInt(doctor_id.getText()), doctor_name.getText(),
						doctor_spec.getText(), doctor_address.getText(), doctor_phone.getText(),
						doctor_gender.getText(), doctor_email.getText(), Integer.parseInt(doctor_exper.getText()), null,status);
				DAO_BacSi.getInstance().Update(bacsi);
				reloadTable(); 
				alertMessage.successMessage("Information changed successfully!");
			}
		});

		addBtn.setOnMouseClicked(event -> {
			BacSi bacsi = new BacSi(0, "...", "...","...", "...", "...", "...",0, "...",true);
			DAO_BacSi.getInstance().Add(bacsi);
			reloadTable();
			BacSi p = doctorList.getLast()	;
			doctor_id.setText(String.valueOf(p.getMaBS()));
			doctor_name.setText(p.getHoTen());
			doctor_spec.setText(p.getChuyenMon());
			doctor_address.setText(p.getDiaChi());
			doctor_phone.setText(p.getSDT());
			doctor_gender.setText(p.getGioiTinh());
			doctor_email.setText(p.getEmail());	
			doctor_exper.setText(String.valueOf(p.getNamKN()));
			doctor_status.setText(statusList.get(0));
			alertMessage.successMessage("New object added successfully!");
		});

		
	}
}
