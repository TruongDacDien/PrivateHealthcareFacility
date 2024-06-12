package controllers;

import java.io.File;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.ResourceBundle;

import Data_Access_Object.*;
import javafx.fxml.Initializable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.*;

public class SubcinicalResultController implements Initializable {
	@FXML
	private MFXButton addBtn;

	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXButton deleteBtn;

	@FXML
	private MFXButton okBtn;

	@FXML
	private MFXButton openBtn;

	@FXML
	private Label patient_address;

	@FXML
	private Label patient_birth;

	@FXML
	private Label patient_gender;

	@FXML
	private Label patient_name;

	@FXML
	private Label patient_phone;

	@FXML
	private AnchorPane scenePane;

	@FXML
	private TextArea sub_advice;

	@FXML
	private TextArea sub_conclusion;

	@FXML
	private Label sub_date;

	@FXML
	private TextArea sub_description;

	@FXML
	private Label sub_diagnosis;

	@FXML
	private Label sub_doctor;

	@FXML
	private Label sub_name;

	@FXML
	private MFXTableView<Anh> table_Photo;

	@FXML
	private ImageView imageView;

	private ObservableList<Anh> anhs = null;

	Stage window;
	private AlertMessage alertMessage = new AlertMessage();
	private String path = null;
	private String filepath = null;
	private Date ngayXN;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setupScreen();
		setupButton();
	}

	// Load data from the database
	public void loadTable() {
		String sql = "MaKQXN = " + CurrentMedicalForm.ketquaxetnghiemId + " AND Status = " + true;
		anhs = DAO_Anh.getInstance().selectByCondition2(sql);
		table_Photo.setItems(anhs);
		table_Photo.autosize();
		table_Photo.autosizeColumns();
		table_Photo.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() {
		anhs.clear();
		loadTable();
	}

	@SuppressWarnings("unchecked")
	public void setupScreen() {

		if (CurrentPatient.patientid != 0 && CurrentMedicalForm.xetnghiemid != 0) {
			if (CurrentMedicalForm.medicalformStatus.equals("Completed")) {
				addBtn.setDisable(true);
				deleteBtn.setDisable(true);
				sub_description.setEditable(false);
				sub_conclusion.setEditable(false);
				sub_advice.setEditable(false);
			}

			BenhNhan benhNhan = new BenhNhan(CurrentPatient.patientid, null, null, null, null, null, null, true);
			BenhNhan temp1 = DAO_BenhNhan.getInstance().seclectById(benhNhan);
			patient_name.setText(temp1.getHoTen());
			patient_gender.setText(temp1.getGioiTinh());
			patient_birth.setText(temp1.getNgaySinh().toString());
			patient_phone.setText(temp1.getSDT());
			patient_address.setText(temp1.getDiaChi());

			XetNghiem nghiem = new XetNghiem(CurrentMedicalForm.xetnghiemid, 0, 0, 0, null, null, null, null, true);
			XetNghiem temp2 = DAO_XetNghiem.getInstance().seclectById(nghiem);
			sub_diagnosis.setText(temp2.getChuanDoan());
			sub_name.setText(temp2.getTenXN());

			BacSi bacSi = new BacSi(temp2.getMaBSXN(), null, null, null, null, null, null, 0, null, true);
			BacSi temp3 = DAO_BacSi.getInstance().seclectById(bacSi);
			sub_doctor.setText(temp3.getHoTen());

			String sql = "MaXN = " + CurrentMedicalForm.xetnghiemid;
			KetQuaXetNghiem temp4 = DAO_KetQuaXetNghiem.getInstance().selectByCondition2(sql);
			
			// Định dạng ngày theo kiểu "March 17, 2024"
            
            sub_date.setText(temp4.getNgayXN().toString());
            
			sub_description.setText(temp4.getMoTa());
			sub_conclusion.setText(temp4.getKetLuan());
			sub_advice.setText(temp4.getLoiKhuyen());
			CurrentMedicalForm.ketquaxetnghiemId = temp4.getMaKQXN();
			ngayXN = temp4.getNgayXN();

			// Initialize columns
			MFXTableColumn<Anh> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(Anh::getMaAnh));
			MFXTableColumn<Anh> filepathColumn = new MFXTableColumn<>("File Path", true,
					Comparator.comparing(Anh::getDuongDan));

			// Set row cell factories
			idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Anh::getMaAnh));
			filepathColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Anh::getDuongDan));

			// Add columns to the table
			table_Photo.getTableColumns().addAll(idColumn, filepathColumn);

			// Add filters to the table
			table_Photo.getFilters().add(new IntegerFilter<>("Id", Anh::getMaAnh));

			loadTable();

			// select row
			table_Photo.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
				if (newValue != null) {
					newValue.values().stream().findFirst().ifPresent(p -> {
						CurrentMedicalForm.maanhId = p.getMaAnh();
						filepath = p.getDuongDan();
					});
				}
			});
		}

	}

	private void openFileDialog() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image File");
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg", "*.gif", "*.svg"));

		File file = fileChooser.showOpenDialog(window);
		if (file != null) {
			path = file.getAbsolutePath().replace("\\", "\\\\");
		}
	}

	private void displayImage(File file) {
	    if (file != null) {
	        if (Desktop.isDesktopSupported()) {
	            Desktop desktop = Desktop.getDesktop();
	            try {
	                desktop.open(file);
	            } catch (IOException e) {
	                e.printStackTrace();   
	            }
	        } else {
	           return;
	        }
	    }
	}

	public void setupButton() {
		addBtn.setOnMouseClicked(event -> {
			openFileDialog();
			if (path != null) {
				Anh anh = new Anh(0, CurrentMedicalForm.ketquaxetnghiemId, path,true);
				DAO_Anh.getInstance().Add(anh);
				reloadTable();
				alertMessage.successMessage("New object added successfully!");
			}
			path = null;
		});

		openBtn.setOnMouseClicked(event -> {
			if (filepath == null) {
				alertMessage.errorMessage("Please select the file you want to open!");
				return;
			} else {
				File file = new File(filepath);
				if (file.exists()) {
					displayImage(file);
				} else {
					alertMessage.errorMessage("File does not exist!");
				}
			}
		});
		
		deleteBtn.setOnMouseClicked(event->{
			if(CurrentMedicalForm.maanhId == 0) {
				alertMessage.errorMessage("Please select the object you want to delete!");
				return;
			}else {
				Anh anh = new Anh(CurrentMedicalForm.maanhId, 0, filepath, false);
				DAO_Anh.getInstance().UpdateStatus(anh);
				reloadTable();
				alertMessage.successMessage("Deleted object successfully!");
			}
		});

		okBtn.setOnMouseClicked(event -> {
			if (sub_advice.getText().isEmpty() || sub_description.getText().isEmpty()
					|| sub_conclusion.getText().isEmpty()) {
				alertMessage.errorMessage("Please fill all blank fields!");
				return;
			}
			KetQuaXetNghiem ketQuaXetNghiem = new KetQuaXetNghiem(CurrentMedicalForm.ketquaxetnghiemId,
					CurrentMedicalForm.xetnghiemid, sub_description.getText(), sub_conclusion.getText(),
					sub_advice.getText(), ngayXN);
			DAO_KetQuaXetNghiem.getInstance().Update(ketQuaXetNghiem);
			alertMessage.successMessage("Information changed successfully!");
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});

		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});
	}
}
