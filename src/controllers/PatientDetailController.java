package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_BenhNhan;
import Data_Access_Object.DAO_LichHen;
import Data_Access_Object.DAO_PhieuKham;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class PatientDetailController implements Initializable {

	@FXML
	private MFXButton addAppointmentBtn;

	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXButton deleteAppointmentBtn;



	@FXML
	private MFXButton detailBtn;

	@FXML
	private MFXButton editAppointmentBtn;

	@FXML
	private MFXButton editPatientBtn;

	@FXML
	private MFXComboBox<String> patient_address;

	@FXML
	private MFXDatePicker patient_birth;

	@FXML
	private MFXComboBox<String> patient_gender;

	@FXML
	private MFXTextField patient_id;

	@FXML
	private MFXTextField patient_name;

	@FXML
	private MFXTextField patient_phone;

	@FXML
	private AnchorPane scenePane;

	@FXML
	private MFXTableView<LichHen> tableAppointment;

	@FXML
	private MFXTableView<PhieuKham> tableMedicalExamination;
	
	@FXML
	private MFXComboBox<String> pateint_status;

	private AlertMessage alertMessage = new AlertMessage();
	private Stage window;
	private ObservableList<PhieuKham> examinationList = null;
	private ObservableList<LichHen> appointmentList = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupScreen();
		setupButton();
		setupTable();
	}

	public void setupScreen() {
		patient_id.setEditable(false);
		ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");
		patient_gender.setItems(genderList);
		
		ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
		pateint_status.setItems(statusList);
		
		
		BenhNhan benhNhan = new BenhNhan(CurrentPatient.patientid, null, null, null, null, null, null,true);
		BenhNhan temp = DAO_BenhNhan.getInstance().seclectById(benhNhan);
		patient_id.setText(String.valueOf(temp.getMaBN()));
		patient_name.setText(temp.getHoTen());
		patient_gender.setText(temp.getGioiTinh());
		
		if(temp.getStatus()) {
			pateint_status.setText(statusList.get(0));
		}
		else {
			pateint_status.setText(statusList.get(1));
		}
	
		patient_birth.setValue(temp.getNgaySinh().toLocalDate());
		patient_phone.setText(temp.getSDT());
		patient_address.setText(temp.getDiaChi());
		CurrentPatient.patientid = Integer.parseInt(patient_id.getText());
	}

	// Load data from the database
	public void loadExaminationTable() {
		String sql = "MaBN = " + CurrentPatient.patientid + " AND TrangThai = " + "'Completed'";
		examinationList = DAO_PhieuKham.getInstance().selectByCondition2(sql);
		tableMedicalExamination.setItems(examinationList);
		tableMedicalExamination.autosize();
		tableMedicalExamination.autosizeColumns();
		tableMedicalExamination.autosizeColumnsOnInitialization();
	}

	public void loadAppointmentTable() {
		String sql = "MaBN = " + CurrentPatient.patientid + " AND Status = " + true;
		appointmentList = DAO_LichHen.getInstance().selectByCondition2(sql);
		tableAppointment.setItems(appointmentList);
		tableAppointment.autosize();
		tableAppointment.autosizeColumns();
		tableAppointment.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() {
		examinationList.clear();
		loadExaminationTable();
		appointmentList.clear();
		loadAppointmentTable();
	}

	@SuppressWarnings("unchecked")
	public void setupTable() {
		// Initialize columns
		MFXTableColumn<PhieuKham> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(PhieuKham::getMaPK));
		MFXTableColumn<PhieuKham> idPatientColumn = new MFXTableColumn<>("Id Patient", true,
				Comparator.comparing(PhieuKham::getMaBN));
		MFXTableColumn<PhieuKham> idDoctorColumn = new MFXTableColumn<>("Id Doctor", true,
				Comparator.comparing(PhieuKham::getMaBS));
		MFXTableColumn<PhieuKham> dateColumn = new MFXTableColumn<>("Date", true,
				Comparator.comparing(PhieuKham::getNgayLap));
		MFXTableColumn<PhieuKham> statusColumn = new MFXTableColumn<>("Active?", true,
				Comparator.comparing(PhieuKham::getTrangThai));

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getMaPK));
		idPatientColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getMaBN));
		idDoctorColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getMaBS));
		dateColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getNgayLap));
		statusColumn.setRowCellFactory(col -> new MFXTableRowCell<>(PhieuKham::getTrangThai));

		// Add columns to the table
		tableMedicalExamination.getTableColumns().addAll(idColumn, idPatientColumn, idDoctorColumn, dateColumn,
				statusColumn);

		// Add filters to the table
		tableMedicalExamination.getFilters().add(new IntegerFilter<>("Id", PhieuKham::getMaPK));
		tableMedicalExamination.getFilters().add(new IntegerFilter<>("Id Patient", PhieuKham::getMaBN));
		tableMedicalExamination.getFilters().add(new IntegerFilter<>("Id Doctor", PhieuKham::getMaBS));
		tableMedicalExamination.getFilters().add(new StringFilter<>("Active?", PhieuKham::getTrangThai));

		loadExaminationTable();

		// select row
		tableMedicalExamination.getSelectionModel().selectionProperty()
				.addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						newValue.values().stream().findFirst().ifPresent(p -> {
							CurrentMedicalForm.medicalformId = p.getMaPK();
							CurrentMedicalForm.medicalformStatus = p.getTrangThai();
							CurrentPatient.patientid = p.getMaBN();
							CurrentDoctor.doctorid = p.getMaBS();
						});
					}
				});

		// Initialize columns
		MFXTableColumn<LichHen> idLHColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(LichHen::getMaLH));
		MFXTableColumn<LichHen> idPatientLHColumn = new MFXTableColumn<>("Id Patient", true,
				Comparator.comparing(LichHen::getMaBN));
		MFXTableColumn<LichHen> idDoctorLHColumn = new MFXTableColumn<>("Id Doctor", true,
				Comparator.comparing(LichHen::getMaBS));
		MFXTableColumn<LichHen> dateLHColumn = new MFXTableColumn<>("Date", true,
				Comparator.comparing(LichHen::getThoiGianHen));

		// Set row cell factories
		idLHColumn.setRowCellFactory(col -> new MFXTableRowCell<>(LichHen::getMaLH));
		idPatientLHColumn.setRowCellFactory(col -> new MFXTableRowCell<>(LichHen::getMaBN));
		idDoctorLHColumn.setRowCellFactory(col -> new MFXTableRowCell<>(LichHen::getMaBS));
		dateLHColumn.setRowCellFactory(col -> new MFXTableRowCell<>(LichHen::getThoiGianHen));

		// Add columns to the table
		tableAppointment.getTableColumns().addAll(idLHColumn, idPatientLHColumn, idDoctorLHColumn, dateLHColumn);

		// Add filters to the table
		tableAppointment.getFilters().add(new IntegerFilter<>("Id", LichHen::getMaLH));
		tableAppointment.getFilters().add(new IntegerFilter<>("Id Patient", LichHen::getMaBN));
		tableAppointment.getFilters().add(new IntegerFilter<>("Id Doctor", LichHen::getMaBS));
		tableAppointment.getFilters().add(new DateFilter<>("Date(YYYY-MM-dd)", LichHen::lichHen ));
		loadAppointmentTable();

		// select row
		tableAppointment.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					CurrentPatient.appointmentId = p.getMaLH();
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
				CurrentMedicalForm.canDetail = false;
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MedicalExaminationForm.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Private Heathcare Facility");
				stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.showAndWait();
				if (!stage.isShowing()) {
					
					CurrentMedicalForm.medicalformId = 0;
					CurrentMedicalForm.medicalformStatus = null;
					
					CurrentDoctor.doctorid = 0;
				}
			} catch (Exception e) {
				alertMessage.errorMessage("Can't load the scene!");
				return;
			}
		});

		editPatientBtn.setOnMouseClicked(event -> {
			if (patient_name.getText().isEmpty() || patient_birth.getText().isEmpty()
					|| patient_gender.getText().isEmpty() || patient_phone.getText().isEmpty()
					|| patient_address.getText().isEmpty() || pateint_status.getText().isEmpty() ) {
				alertMessage.errorMessage("Please fill all blank fields!");
				return;
			} else {
				LocalDate slDate = patient_birth.getValue();
				Date birth = Date.valueOf(slDate);
				
				Boolean status = null ;
				if(pateint_status.getText().equals("Active") ) {
					status = true;
				}
				else status = false;
				
				BenhNhan benhnhan = new BenhNhan(Integer.parseInt(patient_id.getText()), patient_name.getText(), birth,
						patient_gender.getText(), patient_address.getText(), patient_phone.getText(), null,status);
				
				DAO_BenhNhan.getInstance().Update(benhnhan);
				alertMessage.successMessage("Information changed successfully!");
			}
		});
		
		addAppointmentBtn.setOnMouseClicked(event ->{
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddApointmentDialog.fxml"));
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
		});
		
		editAppointmentBtn.setOnMouseClicked(event ->{
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddApointmentDialog.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Private Heathcare Facility");
				stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.showAndWait();
				if (!stage.isShowing()) {
					reloadTable();
					CurrentPatient.appointmentId = 0;
				}
			} catch (Exception e) {
				alertMessage.errorMessage("Can't load the scene!");
				return;
			}
		});
		
		deleteAppointmentBtn.setOnMouseClicked(event ->{
			if(CurrentPatient.appointmentId ==0) {
				alertMessage.errorMessage("Please select the object you want to delete!");
				return;
			}else {
				LichHen lichHen = new LichHen(CurrentPatient.appointmentId, 0, 0, null,null,false);
				DAO_LichHen.getInstance().UpdateStatus(lichHen);
				CurrentPatient.appointmentId = 0;
				reloadTable();
				alertMessage.successMessage("Deleted object successfully!");
			}
		});
		
		

		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});
	}
}
