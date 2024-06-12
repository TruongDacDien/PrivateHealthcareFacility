
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

import Data_Access_Object.DAO_BacSi;
import Data_Access_Object.DAO_BenhNhan;
import Data_Access_Object.DAO_Role;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.BenhNhan;
import models.Role;

public class PatientScreenController implements Initializable {
	Stage window;
	private boolean canChange;
	private int timeChange;
	private AlertMessage alertMessage = new AlertMessage();

	private ObservableList<BenhNhan> patientList = null;

	@FXML
	private MFXTableView<BenhNhan> tablePatient;

	@FXML
	private AnchorPane darkPane, detailMenuPane, menuPane, scenePane;

	@FXML
	private ImageView menuImg;
	
	@FXML
	private ImageView userImg;

	@FXML
	private MFXButton addBtn, detailBtn;

	@FXML

	Dialog<ButtonType> dialog = new Dialog<>();

	@FXML
	private MFXButton homepageBtn, admissionBtn, pateintBtn, medicineBtn, subclinicalBtn, doctorBtn, userBtn,
			reportBtn;;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
Role role = DAO_Role.getInstance().selectByCondition2(" User_Id = " + CurrentUser.userId);
		
		
		
		if(!role.isRole1()) admissionBtn.setDisable(true);
		
		if(!role.isRole2()) pateintBtn.setDisable(true);
		
		if(!role.isRole3()) medicineBtn.setDisable(true);
		
		if(!role.isRole4()) subclinicalBtn.setDisable(true);
		
		if(!role.isRole5()) doctorBtn.setDisable(true);
		
		if(!role.isRole6()) userBtn.setDisable(true);
		
		if(!role.isRole7()) reportBtn.setDisable(true);
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
		patientList = DAO_BenhNhan.getInstance().selectAll();
		tablePatient.setItems(patientList);
		tablePatient.autosize();
		tablePatient.autosizeColumns();
		tablePatient.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() {
		patientList.clear();
		loadTable();
	}

	@SuppressWarnings("unchecked")
	public void setupTable() {
		// Initialize columns
		MFXTableColumn<BenhNhan> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(BenhNhan::getMaBN));
		MFXTableColumn<BenhNhan> nameColumn = new MFXTableColumn<>("Name", true,
				Comparator.comparing(BenhNhan::getHoTen));
		MFXTableColumn<BenhNhan> birthColumn = new MFXTableColumn<>("Birthday", true,
				Comparator.comparing(BenhNhan::getNgaySinh));
		MFXTableColumn<BenhNhan> genderColumn = new MFXTableColumn<>("Gender", true,
				Comparator.comparing(BenhNhan::getGioiTinh));
		MFXTableColumn<BenhNhan> addressColumn = new MFXTableColumn<>("Address", true,
				Comparator.comparing(BenhNhan::getDiaChi));
		MFXTableColumn<BenhNhan> phoneNumberColumn = new MFXTableColumn<>("Phone Number", true,
				Comparator.comparing(BenhNhan::getSDT));
		
		
		MFXTableColumn<BenhNhan> statusColumn = new MFXTableColumn<>("Active?", true,
				Comparator.comparing(BenhNhan::getStatus));

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getMaBN));
		nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getHoTen));
		birthColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getNgaySinh));
		genderColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getGioiTinh));
		addressColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getDiaChi));
		phoneNumberColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getSDT));
		
			
		statusColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getStatus));
		// Add columns to the table
		tablePatient.getTableColumns().addAll(idColumn, nameColumn, birthColumn, genderColumn,
				addressColumn, phoneNumberColumn, statusColumn);
		
		

		// Add filters to the table
		tablePatient.getFilters().add(new IntegerFilter<>("Id", BenhNhan::getMaBN));
		tablePatient.getFilters().add(new StringFilter<>("Name", BenhNhan::getHoTen));
		tablePatient.getFilters().add(new StringFilter<>("Gender", BenhNhan::getGioiTinh));
		tablePatient.getFilters().add(new StringFilter<>("Address", BenhNhan::getDiaChi));
		tablePatient.getFilters().add(new StringFilter<>("Phone Number", BenhNhan::getSDT));
		tablePatient.getFilters().add(new DateFilter<>("Birthday(YYYY-MM-dd)", BenhNhan::getNgaySinh));
		tablePatient.getFilters().add(new BooleanFilter<>("Status", BenhNhan::getStatus));
		
		for(int i =0 ;i< tablePatient.getTableColumns().size();i++) {
			tablePatient.getTableColumns().get(i).setMaxWidth(10);
		}

		loadTable();

		// select row
		tablePatient.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					CurrentPatient.patientid = p.getMaBN();
				});
			}
		});
	}

	public void setupButton() {
		detailBtn.setOnAction(event -> {
			if (event.getSource() == detailBtn) {
				if (CurrentPatient.patientid == 0) {
					alertMessage.errorMessage("Please select the object you want to change information!");
					return;
				}
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PatientDetailDialog.fxml"));
					Parent root = (Parent) loader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("Private Heathcare Facility");
					stage.getIcons().add(new Image("/images/hospital-building.png"));
					stage.showAndWait();
					if (!stage.isShowing()) {
						reloadTable();
						CurrentPatient.patientid=0;
					}
				} catch (Exception e) {
					alertMessage.errorMessage("Can't load the scene!");
					return;
				}
			}
		});

		addBtn.setOnAction(event -> {
			if (event.getSource() == addBtn) {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddPatientDialog.fxml"));
					Parent root = (Parent) loader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root));
					stage.initModality(Modality.APPLICATION_MODAL);
					stage.setTitle("Private Heathcare Facility");
					stage.getIcons().add(new Image("/images/hospital-building.png"));
					stage.showAndWait();
					if (!stage.isShowing()) {
						reloadTable();
					}
				} catch (Exception e) {
					alertMessage.errorMessage("Can't load the scene!");
					return;
				}
			}
		});
	}
}
