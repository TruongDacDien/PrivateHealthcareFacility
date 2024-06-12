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

import Data_Access_Object.DAO_BacSi;
import Data_Access_Object.DAO_ChiTietToaThuoc;
import Data_Access_Object.DAO_KetQuaXetNghiem;
import Data_Access_Object.DAO_LoaiXetNghiem;
import Data_Access_Object.DAO_Thuoc;
import Data_Access_Object.DAO_XetNghiem;
import Database.JDBCUtil;
import javafx.fxml.Initializable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.*;

public class AddSubclinicalDialogController implements Initializable {

	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXButton okBtn;

	@FXML
	private AnchorPane scenePane;

	@FXML
	private MFXTextField sub_diagnosis;

	@FXML
	private MFXComboBox<BacSi> sub_doctor;

	@FXML
	private MFXComboBox<LoaiXetNghiem> sub_name;

	private AlertMessage alertMessage = new AlertMessage();
	private Stage window;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setupScreen();
		setupButton();
	}

	public void setupScreen() {
		connection = JDBCUtil.getConnection();
		
		String query1 = "SELECT MaLoaiXN, TenLoaiXN FROM LOAIXETNGHIEM WHERE Status = "+ true;
		try {
			preparedStatement = connection.prepareStatement(query1);
			resultSet = preparedStatement.executeQuery();
			List<LoaiXetNghiem> loaiXetNghiems = new ArrayList<LoaiXetNghiem>();
			
			
			while (resultSet.next()) {
				int MaLoaiXN = resultSet.getInt("MaLoaiXN");
				String TenLoaiXN = resultSet.getString("TenLoaiXN");
				double N_1 = 0.0;
				BigDecimal n1 = new BigDecimal(N_1);
				loaiXetNghiems.add(new LoaiXetNghiem(MaLoaiXN, TenLoaiXN, n1, null,true));
			}
			sub_name.setItems(FXCollections.observableArrayList(loaiXetNghiems));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String query2 = "SELECT MaBS, HoTen FROM BACSI WHERE Status = "+ true;
		try {
			preparedStatement = connection.prepareStatement(query2);
			resultSet = preparedStatement.executeQuery();
			List<BacSi> bacSis = new ArrayList<BacSi>();
			while (resultSet.next()) {
				int MaBS = resultSet.getInt("MaBS");
				String HoTen = resultSet.getString("HoTen");
				bacSis.add(new BacSi(MaBS, HoTen, null, null, null, null, null, 0, null,true));
			}
			sub_doctor.setItems(FXCollections.observableArrayList(bacSis));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setupButton() {
		okBtn.setOnMouseClicked(event -> {
			if (sub_name.getText().isEmpty() || sub_doctor.getText().isEmpty() || sub_diagnosis.getText().isEmpty()) {
				alertMessage.errorMessage("Please fill all blank fields!");
				return;
			} else {
				int maLoaiXN = 0;
				int maBSXN = 0;
				String tenXN = null;
				int indexOfDash = sub_name.getText().indexOf('-');
				if (indexOfDash != -1) {
					maLoaiXN = Integer.parseInt(sub_name.getText().substring(0, indexOfDash).trim());
					tenXN = sub_name.getText().substring(indexOfDash + 1).trim();
				}
				int indexOfDash_ = sub_doctor.getText().indexOf('-');
				if (indexOfDash_ != -1) {
					maBSXN = Integer.parseInt(sub_doctor.getText().substring(0, indexOfDash_).trim());
				}
				double N_1 = 0.0;
				BigDecimal n1 = new BigDecimal(N_1);
				LoaiXetNghiem loaiXetNghiem = new LoaiXetNghiem(maLoaiXN, null, n1, null,true);
				BigDecimal chiphiXN = DAO_LoaiXetNghiem.getInstance().seclectById(loaiXetNghiem).getChiPhiXN();
				XetNghiem xetNghiem = new XetNghiem(0,CurrentMedicalForm.medicalformId,maLoaiXN,maBSXN,tenXN,null,sub_diagnosis.getText(),chiphiXN,true);
				int newId = DAO_XetNghiem.getInstance().AddAndGetID(xetNghiem);
				LocalDate time = LocalDate.now();
				Date now = Date.valueOf(time);
				KetQuaXetNghiem ketQuaXetNghiem = new KetQuaXetNghiem(0, newId, "", "", "", now);
				DAO_KetQuaXetNghiem.getInstance().Add(ketQuaXetNghiem);
				alertMessage.successMessage("New object added successfully!");
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
