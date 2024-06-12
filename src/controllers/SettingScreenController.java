package controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_BacSi;
import Data_Access_Object.DAO_ChiTietToaThuoc;
import Data_Access_Object.DAO_LichHen;
import Data_Access_Object.DAO_Thuoc;
import Database.JDBCUtil;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import models.*;

public class SettingScreenController implements Initializable {

	

	

	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXButton saveBtn;

	@FXML
	private MFXTextField fee,maximum;

	private AlertMessage alertMessage = new AlertMessage();
	private Stage window;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setupButton();
		setupScreen();
	}

	public void setupScreen() {
		
		BigDecimal giaKham = null;
		int benhNhanToiDa = 0;
		
		try {
            Connection con = JDBCUtil.getConnection();
            Statement st = con.createStatement();
            String sql = "SELECT * FROM RANGBUOC";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                
            	giaKham = rs.getBigDecimal("GiaKham");
            	benhNhanToiDa = rs.getInt("SoLuongBenhNhanToiDaTrongNgay");
            	
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
			NumberStringConverter converter = new NumberStringConverter();
        
        
        
        
        fee.setText(String.valueOf(giaKham));
		maximum.setText(String.valueOf(benhNhanToiDa));
	}

	public void setupButton() {

		saveBtn.setOnMouseClicked(event -> {
			if (fee.getText().isEmpty() || maximum.getText().isEmpty()
					) {
				alertMessage.errorMessage("Please fill all blank fields!");
				return;
			} else {
				try {
					Connection con = JDBCUtil.getConnection();
		            Statement st = con.createStatement();
		            String sql = "UPDATE RANGBUOC SET GiaKham = " + new BigDecimal(fee.getText()) +
		                         " , SoLuongBenhNhanToiDaTrongNgay = " + Integer.parseInt(maximum.getText());
		            int result = st.executeUpdate(sql);
		            System.out.println("Bạn đã thực thi: " + sql);
		            JDBCUtil.closeConnection(con);
		            alertMessage.successMessage("Saved!");
		        } catch (Exception e) {
		            e.printStackTrace();
		            alertMessage.errorMessage("Incorrect format entry!");
		        }
		       
			}
		});

		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});
	}
}
