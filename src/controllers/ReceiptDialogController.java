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
import Data_Access_Object.DAO_BaoCaoDoanhSo;
import Data_Access_Object.DAO_BenhNhan;
import Data_Access_Object.DAO_ChiTietToaThuoc;
import Data_Access_Object.DAO_HoaDonPhieuKham;
import Data_Access_Object.DAO_PhieuKham;
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
import io.github.palexdev.materialfx.filter.BooleanFilter;
import io.github.palexdev.materialfx.filter.DoubleFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
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

public class ReceiptDialogController implements Initializable {

	
	

	

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
	private MFXTextField date_receipt,medicalId,isPaid,sum;
	
	@FXML
	private MFXButton cancelBtn,receiveBtn;
	
	@FXML
	private MFXTableView<HoaDon> tableReceipt;

	private ObservableList<HoaDon> hoaDons = null;
	private Stage window;
	BigDecimal sum2;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setupScreen();
		setupButton();
	}

	

	public void reloadTable() {
		
	}

	@SuppressWarnings("unchecked")
	public void setupScreen() {
		
		
		
		
		
		

		

		

		if ( CurrentMedicalForm.medicalformId != 0) {
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
			isPaid.setEditable(false);
			date_receipt.setEditable(false);
			medicalId.setEditable(false);
			DAO_HoaDonPhieuKham hdpka  = new DAO_HoaDonPhieuKham();
			String sql2 = "MaPK = " + CurrentMedicalForm.medicalformId ;
			
			
			HoaDonPhieuKham hd = hdpka.getInstance().selectByCondition2(sql2);
			medicalId.setText(String.valueOf(hd.getMaPK()));
			if(hd.getIsPaid() == true) {
				isPaid.setText("Yes");
				date_receipt.setText(hd.getNgayBan().toString()	);
			}
			else {
				isPaid.setText("No");
				date_receipt.setText(Date.valueOf(LocalDate.now()).toString());
			}
			
			if(hd.getIsPaid() == true) {
				receiveBtn.setDisable(true);
			}
			hoaDons =FXCollections.observableArrayList();
			
			
			
			
			hoaDons.add(new HoaDon(1,"Doctor's consultation fee", temp.getTienKham()));
			
			
			DAO_ToaThuoc tt= new DAO_ToaThuoc();
			ToaThuoc toaThuoc = tt.getInstance().selectByCondition2("MaPK = " +CurrentMedicalForm.medicalformId);
			
			hoaDons.add(new HoaDon(2, "Prescription", toaThuoc.getTongTien()));
			
			DAO_XetNghiem xn = new DAO_XetNghiem();
			ArrayList<XetNghiem> xetNghiems = xn.getInstance().selectByCondition("MaPK = " +CurrentMedicalForm.medicalformId + " and status = true");
			for(int i = 0;i<xetNghiems.size();i++) {
				hoaDons.add(new HoaDon(3+i,xetNghiems.get(i).getTenXN(),xetNghiems.get(i).getChiPhiXN()));
			}
			sum2 = new BigDecimal(0);
			for (HoaDon hoaDon : hoaDons) {
				sum2 = sum2.add(hoaDon.getPrice());
			}
			
			sum.setText(sum2.toString());
			MFXTableColumn<HoaDon> numberColumn = new MFXTableColumn<>("Number", true, Comparator.comparing(HoaDon::getNumber));
			MFXTableColumn<HoaDon> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(HoaDon::getName));
			MFXTableColumn<HoaDon> priceColumn = new MFXTableColumn<>("Price", true, Comparator.comparing(HoaDon::getPrice));
			

			// Set row cell factories
			numberColumn.setRowCellFactory(col -> new MFXTableRowCell<>(HoaDon::getNumber));
			nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(HoaDon::getName));
			priceColumn.setRowCellFactory(col -> new MFXTableRowCell<>(HoaDon::getPrice));
			

			// Add columns to the table
			tableReceipt.getTableColumns().addAll(numberColumn,nameColumn,priceColumn);

			// Add filters to the table
			tableReceipt.getFilters().add(new IntegerFilter<>("Number", HoaDon::getNumber));
			tableReceipt.getFilters().add(new StringFilter<>("Name", HoaDon::getName));
			
			tableReceipt.setItems(hoaDons);
			
			
		}
	}

	public void setupButton() {
		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});
		
		receiveBtn.setOnMouseClicked(event -> {
			DAO_HoaDonPhieuKham hdpka  = new DAO_HoaDonPhieuKham();
			String sql2 = "MaPK = " + CurrentMedicalForm.medicalformId ;
			HoaDonPhieuKham hd = hdpka.getInstance().selectByCondition2(sql2);
			
			HoaDonPhieuKham hdd = new HoaDonPhieuKham(hd.getMaHD(), CurrentMedicalForm.medicalformId, Date.valueOf(LocalDate.now()), sum2, true);
			Date currentDate = hdd.getNgayBan();
			 
			
	        
		    
			
			DAO_HoaDonPhieuKham.getInstance().Update(hdd);
			receiveBtn.setDisable(true);
		});

		
	}
	
	
}
