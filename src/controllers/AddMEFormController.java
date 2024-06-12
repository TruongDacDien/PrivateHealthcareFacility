package controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_BenhNhan;
import Data_Access_Object.DAO_PhieuKham;
import Data_Access_Object.DAO_ToaThuoc;
import Database.JDBCUtil;
import javafx.fxml.Initializable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;

public class AddMEFormController implements Initializable {

	@FXML
	private MFXButton addPatientBtn;

	@FXML
	private MFXButton cancelBtn;

	@FXML
	private TextArea examination_symptoms;

	@FXML
	private MFXButton okBtn;

	@FXML
	private AnchorPane scenePane;

	@FXML
	private MFXButton selectPatient;

	@FXML
	private MFXTableView<BenhNhan> tablePatient;

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private int totalDoctor = 0;

	private AlertMessage alertMessage = new AlertMessage();
	private ObservableList<BenhNhan> patientList = null;
	private Stage window;
	private boolean isPatientSelected = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setupScreen();
		setupButton();
	}

	// Load data from the database
	public void loadTable() {
		patientList = DAO_BenhNhan.getInstance().selectByCondition2("Status=" + true);
		tablePatient.setItems(patientList);
		
	}

	// Reload screen
	public void reloadTable() {
		patientList.clear();
		loadTable();
	}

	public int RandomDoctor() {
		connection = JDBCUtil.getConnection();
		String query = "SELECT COUNT(*) AS total FROM BACSI";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				totalDoctor = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}
		Random generator = new Random();
		int value = generator.nextInt((totalDoctor - 1) + 1) + 1;
		return value;
	}

	@SuppressWarnings("unchecked")
	public void setupScreen() {
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
		
		
		MFXTableColumn<BenhNhan> statusColumn = new MFXTableColumn<>("Status", true,
				Comparator.comparing(BenhNhan::getStatus_Str));

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getMaBN));
		nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getHoTen));
		birthColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getNgaySinh));
		genderColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getGioiTinh));
		addressColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getDiaChi));
		phoneNumberColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getSDT));
		
			
		statusColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BenhNhan::getStatus_Str));
		
		idColumn.setMaxWidth(50);
		idColumn.setMinWidth(50);
		// Add columns to the table
		tablePatient.getTableColumns().addAll(idColumn, nameColumn, birthColumn, genderColumn,
				addressColumn, phoneNumberColumn, statusColumn);
		
		

		// Add filters to the table
		tablePatient.getFilters().add(new IntegerFilter<>("Id", BenhNhan::getMaBN));
		tablePatient.getFilters().add(new StringFilter<>("Name", BenhNhan::getHoTen));
		tablePatient.getFilters().add(new StringFilter<>("Gender", BenhNhan::getGioiTinh));
		tablePatient.getFilters().add(new StringFilter<>("Address", BenhNhan::getDiaChi));
		tablePatient.getFilters().add(new StringFilter<>("Phone Number", BenhNhan::getSDT));
		
		tablePatient.getFilters().add(new StringFilter<>("Status", BenhNhan::getStatus_Str));
		
		
		

		loadTable();

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
		selectPatient.setOnMouseClicked(event -> {
			if (CurrentPatient.patientid == 0) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			} else {
				isPatientSelected = true;
				alertMessage.successMessage("Selected patient number " + CurrentPatient.patientid);
			}
		});

		addPatientBtn.setOnMouseClicked(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddPatientDialog.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Private Heathcare Facility");
				stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.setResizable(false);
				stage.showAndWait();
				if (!stage.isShowing()) {
					CurrentPatient.patientid = 0;
					isPatientSelected = false;
					reloadTable();
				}
			} catch (Exception e) {
				alertMessage.errorMessage("Can't load the scene!");
			}
		});

		okBtn.setOnMouseClicked(event -> {
			if (!isPatientSelected) {
				alertMessage.errorMessage("Please select a patient before proceeding!");
				return;
			}
			LocalDate time = LocalDate.now();
			Date timeNow = Date.valueOf(time);
			BigDecimal tienkham = BigDecimal.ZERO;
			
			

			
			
			
			try {
	            Connection con = JDBCUtil.getConnection();
	            Statement st = con.createStatement();
	            String sql = "SELECT * FROM RANGBUOC";
	            System.out.println(sql);
	            ResultSet rs = st.executeQuery(sql);
	            if (rs.next()) {
	                
	            	
	            	tienkham = rs.getBigDecimal("GiaKham");
	            	
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			
			PhieuKham phieuKham = new PhieuKham(0, CurrentUser.userId, CurrentPatient.patientid, RandomDoctor(), timeNow,
					"Uncompleted", "", examination_symptoms.getText(), "", tienkham);
			int newId = DAO_PhieuKham.getInstance().AddAndGetID(phieuKham);
			ToaThuoc toaThuoc = new ToaThuoc(0, newId, timeNow, null);
			DAO_ToaThuoc.getInstance().Add(toaThuoc);
			CurrentPatient.patientid = 0;
			alertMessage.successMessage("New object added successfully!");
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});

		cancelBtn.setOnMouseClicked(event -> {
			CurrentPatient.patientid = 0;
			isPatientSelected = false;
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});
	}
}
