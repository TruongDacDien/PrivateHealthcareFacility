package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

import Data_Access_Object.DAO_BaoCaoDoanhSo;
import Data_Access_Object.DAO_HoaDonPhieuKham;
import Data_Access_Object.DAO_LichHen;
import Data_Access_Object.DAO_Role;
import Database.JDBCUtil;
import Insert.HoaDonPhieuKham_Insert;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
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
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.BacSi;
import models.BaoCaoDoanhSo;
import models.HoaDonPhieuKham;
import models.LichHen;
import models.Role;

public class MainScreenController implements Initializable {
	Stage window;
	private boolean canChange;
	private int timeChange;
	private AlertMessage alertMessage = new AlertMessage();
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@FXML
	private MFXButton admissionBtn;

	@FXML
	private ImageView admissionImg;

	@FXML
	private ImageView contactImg;

	@FXML
	private AnchorPane darkPane;

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
	private MFXButton logoutBtn;

	@FXML
	private AnchorPane main_screen;

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
	private MFXButton reportBtn;

	@FXML
	private ImageView reportImg;

	@FXML
	private MFXButton subclinicalBtn;

	@FXML
	private ImageView subclinicalImg;

	@FXML
	private Label total_doctor = new Label();

	
	@FXML
	private Label revenue;
	@FXML
	private Label total_patient = new Label();

	@FXML
	private Label total_typemedicine = new Label();

	@FXML
	private MFXButton userBtn;

	@FXML
	private ImageView userImg;

	@FXML
	private ImageView user_image;

	@FXML
	private Label user_name = new Label();

	@FXML
	private Label user_position = new Label();

	@FXML
	private MFXTableView<LichHen> table_apppointmet;
	private ObservableList<LichHen> appointmentList = null;
	
	CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
	
	@FXML
	BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

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
		TotalDoctor();
		TotalTypeMedicine();
		TotalPatient();
		setUpReport();
		revenue();
	}

	private void setUpReport() {
		ArrayList<HoaDonPhieuKham> hdpks =  DAO_HoaDonPhieuKham.getInstance().selectByCondition(" isPaid = true ");
		for(int i =0;i<hdpks.size();i++) {
			 Date currentDate = hdpks.get(i).getNgayBan();
			 
				
		        
		    LocalDate a = LocalDate.of(currentDate.getYear()+ 1900,currentDate.getMonth()+1,currentDate.getDate());
		    
		    LocalDate b = LocalDate.of(currentDate.getYear()+ 1900,currentDate.getMonth()+1,a.lengthOfMonth());
		    
			Date date = Date.valueOf(b);
			
			BaoCaoDoanhSo tempt= DAO_BaoCaoDoanhSo.getInstance().selectByCondition2(" NgayLapDoanhSo = '" +date +"'");
			if(tempt.getSoLuongPhieuKham()== -1 && a.getMonth()!= LocalDate.now().getMonth()) {
				BigDecimal doanhSo = new BigDecimal(0);
				int soPhieuKham = 0;
				for(int j =0;j<hdpks.size();j++) {
					
					if(hdpks.get(j).getNgayBan().getMonth() == date.getMonth() && hdpks.get(j).getNgayBan().getYear() == date.getYear()) {
						doanhSo = doanhSo.add(hdpks.get(j).getTongTien());
						soPhieuKham++;
					}
					
					
				}
				System.out.print(doanhSo);
				BaoCaoDoanhSo bc = new BaoCaoDoanhSo(0,date,doanhSo,soPhieuKham);
				DAO_BaoCaoDoanhSo.getInstance().Add(bc);
			}
			
		}
		
	}

	public void TotalDoctor() {
		connection = JDBCUtil.getConnection();
		String query = "SELECT COUNT(*) AS total FROM BACSI";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int total = resultSet.getInt("total");
				total_doctor.setText(String.valueOf(total));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}
	}

	public void revenue() {
		ArrayList<HoaDonPhieuKham> hdpks =  DAO_HoaDonPhieuKham.getInstance().selectByCondition(" isPaid = true ");
		
				BigDecimal doanhSo = new BigDecimal(0);
				Date date = Date.valueOf(LocalDate.now());
				for(int j =0;j<hdpks.size();j++) {
					
					if(hdpks.get(j).getNgayBan().getMonth() == date.getMonth() && hdpks.get(j).getNgayBan().getYear() == date.getYear()) {
						doanhSo = doanhSo.add(hdpks.get(j).getTongTien());
						
					}
					
					
				}
				
				revenue.setText(doanhSo.toString());
			
			
		
	}
	public void TotalTypeMedicine() {
		connection = JDBCUtil.getConnection();
		String query = "SELECT COUNT(*) AS total FROM THUOC";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int total = resultSet.getInt("total");
				total_typemedicine.setText(String.valueOf(total));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
		}
	}

	public void TotalPatient() {
		ArrayList<HoaDonPhieuKham> hdpks =  DAO_HoaDonPhieuKham.getInstance().selectByCondition(" isPaid = true ");
		
		int tongBN = 0;
		Date date = Date.valueOf(LocalDate.now());
		for(int j =0;j<hdpks.size();j++) {
			
			if(hdpks.get(j).getNgayBan().getMonth() == date.getMonth() && hdpks.get(j).getNgayBan().getYear() == date.getYear()) {
				tongBN++;
				
			}
			
			
		}
		
		total_patient.setText(tongBN + "");
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

		user_name.setText("Name: " + CurrentUser.userName);
		user_name.autosize();
		Role role = DAO_Role.getInstance().selectByCondition2(" User_Id = " + CurrentUser.userId);
		user_position.setText("Position: " +role.getPosition());
		user_position.autosize();

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

	public void loadAppointmentTable() {
		LocalDate currentDate = LocalDate.now();
		int year = currentDate.getYear();
		int month = currentDate.getMonthValue();
		int day = currentDate.getDayOfMonth();

		String sql = "Status =" + true + " AND YEAR(ThoiGianHen) = " + year + " AND MONTH(ThoiGianHen) = " + month
				+ " AND DAY(ThoiGianHen) = " + day;

		appointmentList = DAO_LichHen.getInstance().selectByCondition2(sql);
		table_apppointmet.setItems(appointmentList);
		table_apppointmet.autosize();
		table_apppointmet.autosizeColumns();
		table_apppointmet.autosizeColumnsOnInitialization();
	}

	@SuppressWarnings("unchecked")
	public void setupTable() {
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
		table_apppointmet.getTableColumns().addAll(idLHColumn, idPatientLHColumn, idDoctorLHColumn, dateLHColumn);

		// Add filters to the table
		table_apppointmet.getFilters().add(new IntegerFilter<>("Id", LichHen::getMaLH));
		table_apppointmet.getFilters().add(new IntegerFilter<>("Id Patient", LichHen::getMaBN));
		table_apppointmet.getFilters().add(new IntegerFilter<>("Id Doctor", LichHen::getMaBS));

		loadAppointmentTable();
		
		
		

       
        XYChart.Series<String, Number> doanhThuSeries = new XYChart.Series<>();
        doanhThuSeries.setName("Sales");

        XYChart.Series<String, Number> soPhieuKhamSeries = new XYChart.Series<>();
        soPhieuKhamSeries.setName("Number of form");

        
        
        

        
       
        
        ObservableList<BaoCaoDoanhSo> bc = DAO_BaoCaoDoanhSo.getInstance().selectAll();
        LocalDate tempt = LocalDate.now();
        int year = tempt.getYear();
        for(int i = 1;i<= 12 ;i++) {
        	int y = 0;
        	for(int j  = 0;j<bc.size();j++) {
        		
        		if(bc.get(j).getNgayLapDoanhSo().getMonth()+1 == i && bc.get(j).getNgayLapDoanhSo().getYear()+1900 == year) {
        			doanhThuSeries.getData().add(new XYChart.Data<>( ""+ i, bc.get(j).getDoanhSo()));
        	        
        	        y = 1;
        		}
        	}
        	if(y == 0) {
        		doanhThuSeries.getData().add(new XYChart.Data<>(  ""+i, 0));
    	        
        	}
        }
        
        
       
        
        

        
        barChart.getData().addAll(doanhThuSeries);
	}

	public void setupButton() {
		logoutBtn.setOnMouseClicked(event -> {
			try {
				alertMessage.successMessage("Log out successfully!");
				window = (Stage) main_screen.getScene().getWindow();
				window.close();
				CurrentUser.Reset();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/LoginScreen.fxml"));
				Parent root = loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.setTitle("Private Heathcare Facility");
				stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.show();
			} catch (Exception e) {
				e.printStackTrace(); // In ra stack trace để biết chi tiết lỗi
				alertMessage.errorMessage("Logout failed!");
			}
		});
	}
}
