package controllers;

import java.io.IOException;
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
import java.util.List;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_BacSi;
import Data_Access_Object.DAO_BenhNhan;
import Data_Access_Object.DAO_ChiTietToaThuoc;
import Data_Access_Object.DAO_HoaDonPhieuKham;
import Data_Access_Object.DAO_PhieuKham;
import Data_Access_Object.DAO_Role;
import Data_Access_Object.DAO_Thuoc;
import Data_Access_Object.DAO_ToaThuoc;
import Data_Access_Object.DAO_XetNghiem;
import Database.JDBCUtil;
import javafx.fxml.Initializable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;

public class MedicalExaminationFormController implements Initializable {

	@FXML
	private MFXButton add_MedicineBtn;

	@FXML
	private MFXButton add_SubclinicalBtn;

	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXButton delete_MedicineBtn;

	@FXML
	private MFXButton delete_SubclinicalBtn;

	@FXML
	private MFXButton edit_MedicineBtn;

	@FXML
	private MFXTableView<ChiTietToaThuoc> examination_MedicineTable;

	@FXML
	private MFXTableView<XetNghiem> examination_SubclinicalTable;

	@FXML
	private TextArea examination_advice;

	@FXML
	private MFXTextField examination_date;
	
	

	@FXML
	private TextArea examination_diagnosis;

	@FXML
	private MFXComboBox<BacSi> examination_doctor;

	@FXML
	private MFXTextField examination_id;

	@FXML
	private MFXComboBox<String> examination_state;

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
	private MFXButton receipt_Btn;

	@FXML
	private MFXButton result_SubclinicalBtn;

	@FXML
	private AnchorPane scenePane;

	@FXML
	private MFXButton detailPatientBtn;

	@FXML
	private MFXButton saveBtn;

	@FXML
	private MFXComboBox<BenhNhan> patient_selectPatient;

	private ObservableList<ChiTietToaThuoc> chiTietToaThuocs = null;
	private ObservableList<XetNghiem> xetNghiems = null;
	private AlertMessage alertMessage = new AlertMessage();
	private Stage window;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
Role role = DAO_Role.getInstance().selectByCondition2(" User_Id = " + CurrentUser.userId);
		
		
		
		
		if(!role.isRole2()) detailPatientBtn.setVisible(false);
		
		setupScreen();
		setupButton();
	}

	public void loadMedicineTable() {
		String sql2 = "MaToa = " + CurrentMedicalForm.toaThuocId + " AND Status = " + true;
		chiTietToaThuocs = DAO_ChiTietToaThuoc.getInstance().selectByCondition2(sql2);
		examination_MedicineTable.setItems(chiTietToaThuocs);
		examination_MedicineTable.autosize();
		examination_MedicineTable.autosizeColumns();
		examination_MedicineTable.autosizeColumnsOnInitialization();
	}

	public void loadSubTable() {
		String sql1 = "MaPK = " + CurrentMedicalForm.medicalformId + " AND Status = " + true;
		xetNghiems = DAO_XetNghiem.getInstance().selectByCondition2(sql1);
		examination_SubclinicalTable.setItems(xetNghiems);
		examination_SubclinicalTable.autosize();
		examination_SubclinicalTable.autosizeColumns();
		examination_SubclinicalTable.autosizeColumnsOnInitialization();
	}

	public void reloadTable() {
		chiTietToaThuocs.clear();
		loadMedicineTable();
		xetNghiems.clear();
		loadSubTable();
	}

	@SuppressWarnings("unchecked")
	public void setupScreen() {
		
		if(CurrentMedicalForm.canDetail == false) {
			detailPatientBtn.setVisible(false);
			CurrentMedicalForm.canDetail = true;
		}
		examination_id.setEditable(false);
		examination_date.setEditable(false);
		ObservableList<String> stateList = FXCollections.observableArrayList("Completed", "Uncompleted");
		examination_state.setItems(stateList);

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
			examination_doctor.setItems(FXCollections.observableArrayList(bacSis));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String query2 = "SELECT MaBN, HoTen FROM BENHNHAN WHERE Status = "+ true;
		try {
			preparedStatement = connection.prepareStatement(query2);
			resultSet = preparedStatement.executeQuery();
			List<BenhNhan> benhNhans = new ArrayList<BenhNhan>();
			while (resultSet.next()) {
				int MaBN = resultSet.getInt("MaBN");
				String HoTen = resultSet.getString("HoTen");
				LocalDate time = LocalDate.now();
				Date b = Date.valueOf(time);
				benhNhans.add(new BenhNhan(MaBN, HoTen, b, null, null, null, null,true));
			}
			patient_selectPatient.setItems(FXCollections.observableArrayList(benhNhans));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String selectId = "MaPK = " + CurrentMedicalForm.medicalformId;
		CurrentMedicalForm.toaThuocId = DAO_ToaThuoc.getInstance().selectByCondition2(selectId).getMaToa();

		if (CurrentMedicalForm.medicalformStatus.equals("Completed")) {
			saveBtn.setDisable(true);
			patient_selectPatient.setDisable(true);
			examination_date.setEditable(false);
			examination_doctor.setDisable(true);
			examination_state.setDisable(true);
			examination_diagnosis.setEditable(false);
			examination_advice.setEditable(false);
			add_MedicineBtn.setDisable(true);
			edit_MedicineBtn.setDisable(true);
			delete_MedicineBtn.setDisable(true);
			add_SubclinicalBtn.setDisable(true);
			delete_SubclinicalBtn.setDisable(true);
		}

		if (CurrentPatient.patientid != 0 && CurrentDoctor.doctorid != 0 && CurrentMedicalForm.medicalformId != 0) {
			LocalDate slDate = LocalDate.of(2012, 04, 21);
			Date b = Date.valueOf(slDate);
			double N_1 = 14.00;
			BigDecimal n1 = new BigDecimal(N_1);
			PhieuKham kham = new PhieuKham(CurrentMedicalForm.medicalformId, 0, 0, 0, b, null, null, null, null, n1);
			PhieuKham temp = DAO_PhieuKham.getInstance().seclectById(kham);
			examination_id.setText(String.valueOf(temp.getMaPK()));
			examination_date.setText(temp.getNgayLap().toString());
			examination_state.setText(temp.getTrangThai());
			examination_diagnosis.setText(temp.getChuanDoan());
			examination_advice.setText(temp.getLoiKhuyen());

			BenhNhan benhNhan = new BenhNhan(temp.getMaBN(), null, null, null, null, null, null,true);
			BenhNhan temp1 = DAO_BenhNhan.getInstance().seclectById(benhNhan);
			
			
			patient_name.setText(temp1.getHoTen());
			patient_gender.setText(temp1.getGioiTinh());
			patient_birth.setText(temp1.getNgaySinh().toString());
			patient_phone.setText(temp1.getSDT());
			patient_address.setText(temp1.getDiaChi());
			patient_selectPatient.setText(String.valueOf(temp1.getMaBN() + " - " + temp1.getHoTen()));

			BacSi bacSi = new BacSi(temp.getMaBS(), null, null, null, null, null, null, 0, null,true);
			BacSi temp2 = DAO_BacSi.getInstance().seclectById(bacSi);
			examination_doctor.setText(String.valueOf(temp2.getMaBS() + " - " + temp2.getHoTen()));

			// Load medicine table
			MFXTableColumn<ChiTietToaThuoc> idColumn = new MFXTableColumn<>("Id", true,
					Comparator.comparing(ChiTietToaThuoc::getMaChiTietToa));
			MFXTableColumn<ChiTietToaThuoc> nameColumn = new MFXTableColumn<>("Name", true,
					Comparator.comparing(ChiTietToaThuoc::getTenThuoc));
			MFXTableColumn<ChiTietToaThuoc> quantityColumn = new MFXTableColumn<>("Quantity", true,
					Comparator.comparing(ChiTietToaThuoc::getSoLuong));
			MFXTableColumn<ChiTietToaThuoc> unitColumn = new MFXTableColumn<>("Unit", true,
					Comparator.comparing(ChiTietToaThuoc::getDonVi));
			MFXTableColumn<ChiTietToaThuoc> dosageColumn = new MFXTableColumn<>("Dosage", true,
					Comparator.comparing(ChiTietToaThuoc::getLieuLuong));
			MFXTableColumn<ChiTietToaThuoc> durationColumn = new MFXTableColumn<>("Duration", true,
					Comparator.comparing(ChiTietToaThuoc::getKhoangThoiGian));
			MFXTableColumn<ChiTietToaThuoc> priceColumn = new MFXTableColumn<>("Price", true,
					Comparator.comparing(ChiTietToaThuoc::getTien));

			idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietToaThuoc::getMaChiTietToa));
			nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietToaThuoc::getTenThuoc));
			quantityColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietToaThuoc::getSoLuong));
			unitColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietToaThuoc::getDonVi));
			dosageColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietToaThuoc::getLieuLuong));
			durationColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietToaThuoc::getKhoangThoiGian));
			priceColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietToaThuoc::getTien));

			examination_MedicineTable.getTableColumns().addAll(idColumn, nameColumn, quantityColumn,unitColumn, dosageColumn,
					durationColumn, priceColumn);

			examination_MedicineTable.getSelectionModel().selectionProperty()
					.addListener((observable, oldValue, newValue) -> {
						if (newValue != null) {
							newValue.values().stream().findFirst().ifPresent(p -> {
								CurrentMedicalForm.chiTietToaThuocId = p.getMaChiTietToa();
							});
						}
					});
			loadMedicineTable();

			// Load sub table
			MFXTableColumn<XetNghiem> idXNColumn = new MFXTableColumn<>("Id", true,
					Comparator.comparing(XetNghiem::getMaXN));
			MFXTableColumn<XetNghiem> idDoctorXNColumn = new MFXTableColumn<>("Id Doctor", true,
					Comparator.comparing(XetNghiem::getMaBSXN));
			MFXTableColumn<XetNghiem> nameXNColumn = new MFXTableColumn<>("Name", true,
					Comparator.comparing(XetNghiem::getTenXN));
			MFXTableColumn<XetNghiem> diagnosisXNColumn = new MFXTableColumn<>("Diagnosis", true,
					Comparator.comparing(XetNghiem::getChuanDoan));
			
			MFXTableColumn<XetNghiem> priceXNColumn = new MFXTableColumn<>("Price", true,
					Comparator.comparing(XetNghiem::getChiPhiXN));

			idXNColumn.setRowCellFactory(col -> new MFXTableRowCell<>(XetNghiem::getMaXN));
			idDoctorXNColumn.setRowCellFactory(col -> new MFXTableRowCell<>(XetNghiem::getMaBSXN));
			nameXNColumn.setRowCellFactory(col -> new MFXTableRowCell<>(XetNghiem::getTenXN));
			diagnosisXNColumn.setRowCellFactory(col -> new MFXTableRowCell<>(XetNghiem::getChuanDoan));
			
			priceXNColumn.setRowCellFactory(col -> new MFXTableRowCell<>(XetNghiem::getChiPhiXN));

			examination_SubclinicalTable.getTableColumns().addAll(idXNColumn, idDoctorXNColumn, nameXNColumn,
					diagnosisXNColumn, priceXNColumn);

			examination_SubclinicalTable.getSelectionModel().selectionProperty()
					.addListener((observable, oldValue, newValue) -> {
						if (newValue != null) {
							newValue.values().stream().findFirst().ifPresent(p -> {
								CurrentMedicalForm.xetnghiemid = p.getMaXN();
								CurrentDoctor.bacsixetnghiemid = p.getMaBSXN();
							});
						}
					});
			loadSubTable();
		}
	}

	public void setupButton() {
		add_MedicineBtn.setOnMouseClicked(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddMedicineDialog.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				
				stage.setTitle("Private Heathcare Facility");
				stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.showAndWait();
				if (!stage.isShowing()){
					reloadTable();
					CurrentMedicalForm.chiTietToaThuocId = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		edit_MedicineBtn.setOnMouseClicked(event -> {
			if (CurrentMedicalForm.chiTietToaThuocId == 0) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddMedicineDialog.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.showAndWait();
				if (!stage.isShowing()) {
					reloadTable();
					CurrentMedicalForm.chiTietToaThuocId = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		delete_MedicineBtn.setOnMouseClicked(event -> {
			if (CurrentMedicalForm.chiTietToaThuocId == 0) {
				alertMessage.errorMessage("Please select the object you want to delete!");
				return;
			} else {
				ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc(CurrentMedicalForm.chiTietToaThuocId, 0, 0, null,
						0, null, null, null,false);
				DAO_ChiTietToaThuoc.getInstance().UpdateStatus(chiTietToaThuoc);
				reloadTable();
				CurrentMedicalForm.chiTietToaThuocId = 0;
				alertMessage.successMessage("Deleted object successfully!");
			}

		});

		add_SubclinicalBtn.setOnMouseClicked(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AddSubclinicalDialog.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Private Heathcare Facility");
				stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.showAndWait();
				if (!stage.isShowing()) {
					reloadTable();
					CurrentDoctor.bacsixetnghiemid = 0;
				}
			} catch (Exception e) {
				return;
			}
		});

		result_SubclinicalBtn.setOnMouseClicked(event -> {
			if (CurrentMedicalForm.xetnghiemid == 0 || CurrentDoctor.bacsixetnghiemid == 0) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SubcinicalResult.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Private Heathcare Facility");
				stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.showAndWait();
				if (!stage.isShowing()) {
					reloadTable();
					CurrentDoctor.bacsixetnghiemid = 0;
					CurrentMedicalForm.xetnghiemid = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		});

		delete_SubclinicalBtn.setOnMouseClicked(event -> {
			if (CurrentMedicalForm.xetnghiemid == 0 || CurrentDoctor.bacsixetnghiemid == 0) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			} else {
				double N_1 = 0.0;
				BigDecimal n1 = new BigDecimal(N_1);
				XetNghiem nghiem = new XetNghiem(CurrentMedicalForm.xetnghiemid, 0, 0, 0, null, null, null, n1,false);
				DAO_XetNghiem.getInstance().UpdateStatus(nghiem);
				reloadTable();
				CurrentDoctor.bacsixetnghiemid = 0;
				CurrentMedicalForm.xetnghiemid = 0;
				alertMessage.successMessage("Deleted object successfully!");
			}
		});

		saveBtn.setOnMouseClicked(event -> {
			if(examination_state.getText().equals("Completed")) {
				
				
			
			ObservableList<ChiTietToaThuoc> chiTiet = DAO_ChiTietToaThuoc.getInstance().selectByCondition2(" MaToa = "	+ CurrentMedicalForm.toaThuocId + " and Status = " + true );
			for(int i = 0;i< chiTiet.size();i++) {
				LocalDate slDate = LocalDate.of(2012, 04, 21);
				Date b = Date.valueOf(slDate);
				double N_1 = 0.0;
				BigDecimal n1 = new BigDecimal(N_1);
				Thuoc thuoc = new Thuoc(chiTiet.get(i).getMaThuoc(), "", null, 0, n1, b, b,true);
				int tonKho = DAO_Thuoc.getInstance().seclectById(thuoc).getSoLuong();
				if(chiTiet.get(i).getSoLuong() > tonKho) {
					alertMessage.errorMessage(chiTiet.get(i).getTenThuoc()+ " exceeds available stock quantity! ");
					return;
				}
			}
			for(int i = 0;i< chiTiet.size();i++) {
				LocalDate slDate = LocalDate.of(2012, 04, 21);
				Date b = Date.valueOf(slDate);
				double N_1 = 0.0;
				BigDecimal n1 = new BigDecimal(N_1);
				Thuoc thuoc = new Thuoc(chiTiet.get(i).getMaThuoc(), "", null, 0, n1, b, b,true);
				Thuoc thuoc2 = DAO_Thuoc.getInstance().seclectById(thuoc);
				thuoc2.setSoLuong(thuoc2.getSoLuong()- chiTiet.get(i).getSoLuong());
				DAO_Thuoc.getInstance().Update(thuoc2);
			
			}
			saveBtn.setDisable(true);
			}
			
			
			
		    int maBN = 0;
		    int maBS = 0;
		    int indexOfDash = patient_selectPatient.getText().indexOf('-');
		    if (indexOfDash != -1) {
		        maBN = Integer.parseInt(patient_selectPatient.getText().substring(0, indexOfDash).trim());
		    }
		    int indexOfDash_ = examination_doctor.getText().indexOf('-');
		    if (indexOfDash_ != -1) {
		        maBS = Integer.parseInt(examination_doctor.getText().substring(0, indexOfDash_).trim());
		    }
		    LocalDate slDate = LocalDate.of(2012, 04, 21);
			Date b = Date.valueOf(slDate);
			double N_1 = 14.00;
			BigDecimal n1 = new BigDecimal(N_1);
		    PhieuKham kham0 = new PhieuKham(CurrentMedicalForm.medicalformId, 0, 0, 0, b, null, null, null, null, n1);
			PhieuKham temp = DAO_PhieuKham.getInstance().seclectById(kham0);
		   
		    Date date = temp.getNgayLap();
		    BigDecimal sumPhieuKham = BigDecimal.ZERO;
		    BigDecimal sumToaThuoc = BigDecimal.ZERO;
		    // Calculate sum from CHITIETOATHUOC table
		    String query1 = "SELECT Tien FROM CHITIETOATHUOC WHERE MaToa = " + CurrentMedicalForm.toaThuocId + " AND Status = "+ true;
		    try {
		        preparedStatement = connection.prepareStatement(query1);
		        resultSet = preparedStatement.executeQuery();
		        while (resultSet.next()) {
		            sumPhieuKham = sumPhieuKham.add(resultSet.getBigDecimal("Tien"));
		            sumToaThuoc = sumToaThuoc.add(resultSet.getBigDecimal("Tien"));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    // Calculate sum from XETNGHIEM table
		    String query2 = "SELECT ChiPhiXN FROM XETNGHIEM WHERE MaPK = " + CurrentMedicalForm.medicalformId+ " AND Status = "+ true;
		    try {
		        preparedStatement = connection.prepareStatement(query2);
		        resultSet = preparedStatement.executeQuery();
		        while (resultSet.next()) {
		            sumPhieuKham = sumPhieuKham.add(resultSet.getBigDecimal("ChiPhiXN"));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		    ToaThuoc thuoc = new ToaThuoc(CurrentMedicalForm.toaThuocId, CurrentMedicalForm.medicalformId, date, sumToaThuoc);
		    DAO_ToaThuoc.getInstance().Update(thuoc);
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
			
		    PhieuKham kham = new PhieuKham(CurrentMedicalForm.medicalformId, CurrentUser.userId, maBN, maBS, date, 
		                                   examination_state.getText(), examination_advice.getText(), null,examination_diagnosis.getText(), tienkham);
		    DAO_PhieuKham.getInstance().Update(kham);
		    CurrentMedicalForm.medicalformStatus = examination_state.getText();
		    alertMessage.successMessage("Information changed successfully!");
		    
		    
		});

		receipt_Btn.setOnMouseClicked(event->{
			if(CurrentMedicalForm.medicalformStatus.equals("Completed")) {
			try {
				
				DAO_HoaDonPhieuKham hdpka  = new DAO_HoaDonPhieuKham();
				String sql2 = "MaPK = " + CurrentMedicalForm.medicalformId ;
				HoaDonPhieuKham hd = hdpka.getInstance().selectByCondition2(sql2);
				if(hd.getMaHD() == -1) {
					System.out.print("lsjflsjlflsdf");
					double N_1 = 14.00;
					BigDecimal n1 = new BigDecimal(N_1);
					 LocalDate slDate = LocalDate.of(2012, 04, 21);
						Date b = Date.valueOf(slDate);
					HoaDonPhieuKham hdd = new HoaDonPhieuKham(0, CurrentMedicalForm.medicalformId, b, n1, false);
					DAO_HoaDonPhieuKham.getInstance().Add(hdd);
				}
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ReceiptDialog.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Private Heathcare Facility");
				stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.setResizable(false);
				stage.showAndWait();
				if (!stage.isShowing()) {
					reloadTable();
					CurrentPatient.patientid=0;
				}
			} catch (Exception e) {
				alertMessage.errorMessage("Can't load the scene!");
				e.printStackTrace();
				return;
			}
			}
			else {
				alertMessage.errorMessage("The form must be completed!");
			}
		});
		
		detailPatientBtn.setOnMouseClicked(event->{
			try {
				OldDetailMedicalForm.canDetail = CurrentMedicalForm.canDetail;
				OldDetailMedicalForm.chiTietToaThuocId = CurrentMedicalForm.chiTietToaThuocId;
				OldDetailMedicalForm.ketquaxetnghiemId = CurrentMedicalForm.ketquaxetnghiemId;
				OldDetailMedicalForm.maanhId = CurrentMedicalForm.maanhId;
				OldDetailMedicalForm.medicalformId = CurrentMedicalForm.medicalformId;
				OldDetailMedicalForm.toaThuocId = CurrentMedicalForm.toaThuocId;
				OldDetailMedicalForm.xetnghiemid = CurrentMedicalForm.xetnghiemid;
				OldDetailMedicalForm.medicalformStatus = CurrentMedicalForm.medicalformStatus;
				
				OldPateint.appointmentId = CurrentPatient.appointmentId;
				OldPateint.patientid = CurrentPatient.patientid;
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/PatientDetailDialog.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setResizable(false);
				stage.setTitle("Private Heathcare Facility");
				stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.showAndWait();
				if (!stage.isShowing()) {
					
					CurrentMedicalForm.canDetail = OldDetailMedicalForm.canDetail;
					CurrentMedicalForm.chiTietToaThuocId = OldDetailMedicalForm.chiTietToaThuocId;
					CurrentMedicalForm.ketquaxetnghiemId = OldDetailMedicalForm.ketquaxetnghiemId;
					CurrentMedicalForm.maanhId = OldDetailMedicalForm.maanhId;
					CurrentMedicalForm.medicalformId = OldDetailMedicalForm.medicalformId;
					CurrentMedicalForm.toaThuocId = OldDetailMedicalForm.toaThuocId;
					CurrentMedicalForm.xetnghiemid = OldDetailMedicalForm.xetnghiemid;
					CurrentMedicalForm.medicalformStatus = OldDetailMedicalForm.medicalformStatus;
					
					CurrentPatient.appointmentId = OldPateint.appointmentId;
					CurrentPatient.patientid = OldPateint.patientid;
					//reloadPateint();
					LocalDate slDate = LocalDate.of(2012, 04, 21);
					Date b = Date.valueOf(slDate);
					double N_1 = 14.00;
					BigDecimal n1 = new BigDecimal(N_1);
					PhieuKham kham = new PhieuKham(CurrentMedicalForm.medicalformId, 0, 0, 0, b, null, null, null, null, n1);
					PhieuKham temp = DAO_PhieuKham.getInstance().seclectById(kham);
					
					BenhNhan benhNhan = new BenhNhan(temp.getMaBN(), null, null, null, null, null, null,true);
					BenhNhan temp1 = DAO_BenhNhan.getInstance().seclectById(benhNhan);
					
					
					patient_name.setText(temp1.getHoTen());
					patient_gender.setText(temp1.getGioiTinh());
					patient_birth.setText(temp1.getNgaySinh().toString());
					patient_phone.setText(temp1.getSDT());
					patient_address.setText(temp1.getDiaChi());
					patient_selectPatient.setText(String.valueOf(temp1.getMaBN() + " - " + temp1.getHoTen()));
					
				}
			} catch (Exception e) {
				alertMessage.errorMessage("Can't load the scene!");
				e.printStackTrace();
				return;
			}
		});

		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});
	}
	
	void reloadPateint() {
		LocalDate slDate = LocalDate.of(2012, 04, 21);
		Date b = Date.valueOf(slDate);
		double N_1 = 14.00;
		BigDecimal n1 = new BigDecimal(N_1);
		PhieuKham kham = new PhieuKham(CurrentMedicalForm.medicalformId, 0, 0, 0, b, null, null, null, null, n1);
		PhieuKham temp = DAO_PhieuKham.getInstance().seclectById(kham);
		

		BenhNhan benhNhan = new BenhNhan(temp.getMaBN(), null, null, null, null, null, null,true);
		BenhNhan temp1 = DAO_BenhNhan.getInstance().seclectById(benhNhan);
		
		
		patient_name.setText(temp1.getHoTen());
		patient_gender.setText(temp1.getGioiTinh());
		patient_birth.setText(temp1.getNgaySinh().toString());
		patient_phone.setText(temp1.getSDT());
		patient_address.setText(temp1.getDiaChi());
		patient_selectPatient.setText(String.valueOf(temp1.getMaBN() + " - " + temp1.getHoTen()));
	}
}
