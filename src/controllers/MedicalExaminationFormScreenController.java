
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
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
import Data_Access_Object.DAO_PhieuKham;
import Data_Access_Object.DAO_Role;
import Data_Access_Object.DAO_ToaThuoc;
import Database.JDBCUtil;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableRow;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;

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
import models.PhieuKham;
import models.Role;
import models.ToaThuoc;

public class MedicalExaminationFormScreenController implements Initializable {
	Stage window;
	private boolean canChange;
	private int timeChange;
	private AlertMessage alertMessage = new AlertMessage();
	private ObservableList<PhieuKham> examinationList = null;

	@FXML
	private MFXButton addBtn;

	@FXML
	private MFXButton admissionBtn;

	@FXML
	private ImageView admissionImg;

	@FXML
	private ImageView contactImg;

	@FXML
	private AnchorPane darkPane;

	@FXML
	private MFXButton detailBtn;

	@FXML
	private AnchorPane detailMenuPane;

	@FXML
	private MFXButton doctorBtn;

	@FXML
	private ImageView doctorImg;

	@FXML
	private ImageView homeImage;

	@FXML
	private MFXButton homepageBtn;

	@FXML
	private MFXButton medicineBtn;

	@FXML
	private ImageView medicineImg;

	@FXML
	private ImageView menuImg;

	@FXML
	private ImageView menuImg1;

	@FXML
	private AnchorPane menuPane;

	@FXML
	private AnchorPane pane4;

	@FXML
	private MFXButton pateintBtn;

	@FXML
	private ImageView pateintImg;

	@FXML
	private MFXButton reportBtn,settingBtn;

	@FXML
	private ImageView reportImg;

	@FXML
	private MFXButton subclinicalBtn;

	@FXML
	private ImageView subclinicalImg;

	@FXML
	private MFXTableView<PhieuKham> table;

	@FXML
	private MFXButton userBtn;

	@FXML
	private ImageView userImg;
	
	@FXML 
	private MFXToggleButton switchListToggle;
	
	

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
		if(switchListToggle.getText().equals("All")) {
		examinationList = DAO_PhieuKham.getInstance().selectAll();
		table.setItems(examinationList);
		table.autosize();
		table.autosizeColumns();
		table.autosizeColumnsOnInitialization();
		}
		else {
			examinationList = DAO_PhieuKham.getInstance().selectAll();
			ObservableList<PhieuKham> examinationList2 =  DAO_PhieuKham.getInstance().selectAll();
			examinationList2.clear();
			
			
			
			Date today = Date.valueOf(LocalDate.now());
			for(int i =0;i<examinationList.size();i++) {
				if(examinationList.get(i).getNgayLap().equals(today)  ) {
					examinationList2.add(examinationList.get(i));
				}
			}
			table.setItems(examinationList2);
			table.autosize();
			table.autosizeColumns();
			table.autosizeColumnsOnInitialization();
		}
	}

	// Reload screen
	public void reloadTable() {
		examinationList.clear();
		loadTable();
	}

	@SuppressWarnings("unchecked")
	public void setupTable() {
		switchListToggle.setOnAction(event -> {
		    if (switchListToggle.isSelected()) {
		        switchListToggle.setText("All");
		        
		    } else {
		        switchListToggle.setText("Today");
		        
		    }
		    reloadTable();
		});
		
		// Initialize columns
		MFXTableColumn<PhieuKham> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(PhieuKham::getMaPK));
		MFXTableColumn<PhieuKham> idPatientColumn = new MFXTableColumn<>("Id Patient", true,
				Comparator.comparing(PhieuKham::getMaBN));
		MFXTableColumn<PhieuKham> idDoctorColumn = new MFXTableColumn<>("Id Doctor", true,
				Comparator.comparing(PhieuKham::getMaBS));
		MFXTableColumn<PhieuKham> importerDoctorColumn = new MFXTableColumn<>("Importer", true,
				Comparator.comparing(PhieuKham::getMaBS));
		MFXTableColumn<PhieuKham> dateColumn = new MFXTableColumn<>("Date", true,
				Comparator.comparing(PhieuKham::getNgayLap));
		MFXTableColumn<PhieuKham> statusColumn = new MFXTableColumn<>("Status", true,
				Comparator.comparing(PhieuKham::getTrangThai));

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getMaPK));
		idPatientColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getMaBN));
		idDoctorColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getMaBS));
		importerDoctorColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getId_User));
		dateColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getNgayLap));
		statusColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getTrangThai));

		// Add columns to the table
		table.getTableColumns().addAll(idColumn, idPatientColumn, idDoctorColumn,importerDoctorColumn, dateColumn,
				statusColumn);
	

		// Add filters to the table
		table.getFilters().add(new IntegerFilter<>("Id",PhieuKham::getMaPK));
		table.getFilters().add(new IntegerFilter<>("Id Patient", PhieuKham::getMaBN));
		table.getFilters().add(new IntegerFilter<>("Id Doctor", PhieuKham::getMaBS));
		table.getFilters().add(new IntegerFilter<>("Importer", PhieuKham::getId_User));
		table.getFilters().add(new DateFilter<>("Date(YYYY-MM-dd)",PhieuKham::getNgayLap));
		
		table.getFilters().add(new StringFilter<>("Status", PhieuKham::getTrangThai));
		
		
		
		loadTable();

		// select row
		table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					CurrentMedicalForm.medicalformId = p.getMaPK();
					CurrentMedicalForm.medicalformStatus = p.getTrangThai();
					CurrentPatient.patientid = p.getMaBN();
					CurrentDoctor.doctorid = p.getMaBS();
				});
			}
		});
		
		
	}

	public void setupButton() {
		detailBtn.setOnMouseClicked(event -> {
			if (CurrentPatient.patientid == 0 || CurrentDoctor.doctorid == 0 || CurrentMedicalForm.medicalformId == 0) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MedicalExaminationForm.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false); 
				stage.setTitle("Private Heathcare Facility");
                stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.showAndWait();
				if (!stage.isShowing()) {
					reloadTable();
					CurrentMedicalForm.medicalformId = 0;
					CurrentMedicalForm.medicalformStatus = null;
					CurrentPatient.patientid = 0;
					CurrentDoctor.doctorid = 0;
				}
			} catch (Exception e) {
				alertMessage.errorMessage("Can't load the scene!");
				return;
			}
		});

		addBtn.setOnMouseClicked(event -> {
			Date dateNow = Date.valueOf(LocalDate.now());
			ArrayList<PhieuKham> pks = DAO_PhieuKham.getInstance().selectByCondition(" NgayLap = '" + dateNow + "'" );
			
			int benhNhanToiDa = 0;
			
			try {
	            Connection con = JDBCUtil.getConnection();
	            Statement st = con.createStatement();
	            String sql = "SELECT * FROM RANGBUOC";
	            System.out.println(sql);
	            ResultSet rs = st.executeQuery(sql);
	            if (rs.next()) {
	                
	            	
	            	benhNhanToiDa = rs.getInt("SoLuongBenhNhanToiDaTrongNgay");
	            	
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			if(pks.size() +1 > benhNhanToiDa) {
				alertMessage.errorMessage("The maximum number of patients for the day has been reached");
				return;
			}
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddMEForm.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setResizable(false); 
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Private Heathcare Facility");
                stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.showAndWait();
				if (!stage.isShowing()) {
					reloadTable();
				}
			} catch (Exception e) {
				e.printStackTrace();
				alertMessage.errorMessage("Can't load the scene!");
				return;
			}
		});
		
		settingBtn.setOnMouseClicked(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SettingScreen.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setResizable(false); 
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Private Heathcare Facility");
                stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.showAndWait();
				if (!stage.isShowing()) {
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				alertMessage.errorMessage("Can't load the scene!");
				return;
			}
		});
	}
}
