package controllers;


import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_BenhNhan;
import Data_Access_Object.DAO_ChiTietToaThuoc;
import Data_Access_Object.DAO_Thuoc;
import Database.JDBCUtil;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class AddMedicineDialogController implements Initializable {
	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXTextField medicine_dosage;

	@FXML
	private MFXTextField medicine_duration;

	@FXML
	private MFXComboBox<Thuoc> medicine_name;

	@FXML
	private MFXTextField medicine_quantity;

	@FXML
	private MFXButton okBtn;

	@FXML
	private AnchorPane scenePane;
	
	@FXML
	private Label unitLabel;

	private AlertMessage alertMessage = new AlertMessage();
	private Stage window;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public void setupButton() {
		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});

		okBtn.setOnMouseClicked(event -> {
			if (CurrentMedicalForm.chiTietToaThuocId == 0) {
				if (medicine_name.getText().isEmpty() || medicine_quantity.getText().isEmpty()
						|| medicine_dosage.getText().isEmpty() || medicine_duration.getText().isEmpty()) {
					alertMessage.errorMessage("Please fill all blank fields!");
					return;
				} else {
					int maThuoc = 0;
					String tenThuoc = null;
					int indexOfDash = medicine_name.getText().indexOf('-');
					if (indexOfDash != -1) {
						maThuoc = Integer.parseInt(medicine_name.getText().substring(0, indexOfDash).trim());
						tenThuoc = medicine_name.getText().substring(indexOfDash + 1).trim();
					}
					LocalDate slDate = LocalDate.of(2012, 04, 21);
					Date b = Date.valueOf(slDate);
					double N_1 = 0.0;
					BigDecimal n1 = new BigDecimal(N_1);
					Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, null, 0, n1, b, b,true);
					BigDecimal tienThuoc = DAO_Thuoc.getInstance().seclectById(thuoc).getDonGia();
					Double soluong = Double.parseDouble(medicine_quantity.getText());
					
					int soLương2 = Integer.parseInt(medicine_quantity.getText());
					int tonKho = DAO_Thuoc.getInstance().seclectById(thuoc).getSoLuong();
					
					if(soLương2 > tonKho) {
						alertMessage.errorMessage("Exceeds available stock quantity! ");
						return;
					}
					
					
					
					
					
					
					BigDecimal soLuong = BigDecimal.valueOf(soluong);
					BigDecimal sum = soLuong.multiply(tienThuoc);
					ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc(0, CurrentMedicalForm.toaThuocId, maThuoc,
							tenThuoc, Integer.parseInt(medicine_quantity.getText()), medicine_duration.getText(),
							medicine_dosage.getText(), sum,true);
					DAO_ChiTietToaThuoc.getInstance().Add(chiTietToaThuoc);
					alertMessage.successMessage("New object added successfully!");
				}
			}
			else {
				if (medicine_name.getText().isEmpty() || medicine_quantity.getText().isEmpty()
						|| medicine_dosage.getText().isEmpty() || medicine_duration.getText().isEmpty()) {
					alertMessage.errorMessage("Please fill all blank fields!");
					return;
				} else {
					int maThuoc = 0;
					String tenThuoc = null;
					int indexOfDash = medicine_name.getText().indexOf('-');
					if (indexOfDash != -1) {
						maThuoc = Integer.parseInt(medicine_name.getText().substring(0, indexOfDash).trim());
						tenThuoc = medicine_name.getText().substring(indexOfDash + 1).trim();
					}
					LocalDate slDate = LocalDate.of(2012, 04, 21);
					Date b = Date.valueOf(slDate);
					double N_1 = 0.0;
					BigDecimal n1 = new BigDecimal(N_1);
					Thuoc thuoc = new Thuoc(maThuoc, tenThuoc, null, 0, n1, b, b,true);
					BigDecimal tienThuoc = DAO_Thuoc.getInstance().seclectById(thuoc).getDonGia();
					Double soluong = Double.parseDouble(medicine_quantity.getText());
					BigDecimal soLuong = BigDecimal.valueOf(soluong);
					BigDecimal sum = soLuong.multiply(tienThuoc);
					ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc(CurrentMedicalForm.chiTietToaThuocId, CurrentMedicalForm.toaThuocId, maThuoc,
							tenThuoc, Integer.parseInt(medicine_quantity.getText()), medicine_duration.getText(),
							medicine_dosage.getText(), sum,true);
					DAO_ChiTietToaThuoc.getInstance().Update(chiTietToaThuoc);
					alertMessage.successMessage("Information changed successfully!");
				}
			}
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});
	}

	public void setupScreen() {
		connection = JDBCUtil.getConnection();
		String query = "SELECT MaThuoc, TenThuoc,DonVi FROM THUOC WHERE Status = "+ true;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			List<Thuoc> thuocs = new ArrayList<Thuoc>();
			while (resultSet.next()) {
				int MaThuoc = resultSet.getInt("MaThuoc");
				String TenThuoc = resultSet.getString("TenThuoc");
				String DonVi = resultSet.getString("DonVi");
				
				LocalDate time = LocalDate.now();
				Date timeNow = Date.valueOf(time);
				double N_1 = 0.0;
				BigDecimal n1 = new BigDecimal(N_1);
				thuocs.add(new Thuoc(MaThuoc, TenThuoc, DonVi, 0, n1, timeNow, timeNow,true));
			}
			
			ObservableList<ChiTietToaThuoc> chiTiet = DAO_ChiTietToaThuoc.getInstance().selectByCondition2(" MaToa = "	+ CurrentMedicalForm.toaThuocId + " and Status = " + true );
			
			List<Thuoc> itemsToRemove = new ArrayList<>();
		    for (int i = 0; i < chiTiet.size(); i++) {
		        for (Thuoc it : thuocs) {
		            if (it.getMaThuoc() == chiTiet.get(i).getMaThuoc()) {
		                itemsToRemove.add(it);
		            }
		        }
		    }
		    thuocs.removeAll(itemsToRemove);
			
			
			
			
			
			
			medicine_name.setItems(FXCollections.observableArrayList(thuocs));
		} catch (SQLException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		medicine_name.setOnAction(event -> {
            Thuoc selectedValue = medicine_name.getSelectionModel().getSelectedItem();
            unitLabel.setText(selectedValue.getDonVi());
            
            
        });
		
		
		if (CurrentMedicalForm.chiTietToaThuocId != 0) {
			ChiTietToaThuoc chiTietToaThuoc = new ChiTietToaThuoc(CurrentMedicalForm.chiTietToaThuocId, 0, 0, null, 0,
					null, null, null,true);
			ChiTietToaThuoc now = DAO_ChiTietToaThuoc.getInstance().seclectById(chiTietToaThuoc);
			medicine_name.setText(String.valueOf(now.getMaThuoc() + " - " + now.getTenThuoc()));
			medicine_quantity.setText(String.valueOf(now.getSoLuong()));
			medicine_dosage.setText(now.getLieuLuong());
			medicine_duration.setText(now.getKhoangThoiGian());
			unitLabel.setText(now.getDonVi());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setupScreen();
		setupButton();
	}

}
