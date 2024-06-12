
package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import Data_Access_Object.DAO_Role;
import Data_Access_Object.DAO_Thuoc;
import Database.JDBCUtil;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.BooleanFilter;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.BacSi;
import models.DVT;
import models.Role;
import models.Thuoc;

public class MedicineScreenController implements Initializable {
	Stage window;
	private boolean canChange;
	private int timeChange;
	private AlertMessage alertMessage = new AlertMessage();

	private ObservableList<Thuoc> medicineList = null;

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
	private AnchorPane detailMenuPane;

	@FXML
	private MFXButton doctorBtn;

	@FXML
	private ImageView doctorImg;

	@FXML
	private MFXButton editBtn;

	@FXML
	private ImageView homeImage;

	@FXML
	private MFXButton homepageBtn;

	@FXML
	private MFXButton medicineBtn;

	@FXML
	private ImageView medicineImg;

	@FXML
	private Label medicine_id;

	@FXML
	private MFXTextField medicine_name;

	@FXML
	private MFXTextField medicine_price;

	@FXML
	private MFXTextField medicine_quantity;

	@FXML
	private MFXComboBox<DVT> medicine_unit;

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
	private MFXTableView<Thuoc> tableMedicine;

	@FXML
	private MFXButton userBtn;

	@FXML
	private ImageView userImg;

	@FXML
	private MFXButton editUnitBtn;
	
	@FXML
	private MFXComboBox<String> medicine_status;
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
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
		
		connection = JDBCUtil.getConnection();
		String query = "SELECT Ma_DVT, Ten_DVT FROM DVT WHERE Status = "+ true;
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			List<DVT> dvts = new ArrayList<DVT>();
			while (resultSet.next()) {
				int Ma_DVT = resultSet.getInt("Ma_DVT");
				String Ten_DVT = resultSet.getString("Ten_DVT");
				dvts.add(new DVT(Ma_DVT, Ten_DVT, true));
			}
			medicine_unit.setItems(FXCollections.observableArrayList(dvts));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

		
		
		medicine_status.setItems(statusList);
		
		
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
		medicineList = DAO_Thuoc.getInstance().selectAll();
		tableMedicine.setItems(medicineList);
		tableMedicine.autosize();
		tableMedicine.autosizeColumns();
		tableMedicine.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() {
		medicineList.clear();
		loadTable();
	}

	@SuppressWarnings("unchecked")
	public void setupTable() {
		// Initialize columns
		MFXTableColumn<Thuoc> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(Thuoc::getMaThuoc));
		MFXTableColumn<Thuoc> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Thuoc::getTenThuoc));
		MFXTableColumn<Thuoc> unitColumn = new MFXTableColumn<>("Unit", true, Comparator.comparing(Thuoc::getDonVi));
		MFXTableColumn<Thuoc> quantityColumn = new MFXTableColumn<>("Quantity", true,
				Comparator.comparing(Thuoc::getSoLuong));
		MFXTableColumn<Thuoc> priceColumn = new MFXTableColumn<>("Price", true, Comparator.comparing(Thuoc::getDonGia));
		MFXTableColumn<Thuoc> statusColumn = new MFXTableColumn<>("Active?", true, Comparator.comparing(Thuoc::getStatus));

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Thuoc::getMaThuoc));
		nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Thuoc::getTenThuoc));
		unitColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Thuoc::getDonVi));
		quantityColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Thuoc::getSoLuong));
		priceColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Thuoc::getDonGia));
		statusColumn.setRowCellFactory(col -> new MFXTableRowCell<>(Thuoc::getStatus));

		// Add columns to the table
		tableMedicine.getTableColumns().addAll(idColumn, nameColumn, quantityColumn, unitColumn, priceColumn,statusColumn);

		// Add filters to the table
		tableMedicine.getFilters().add(new IntegerFilter<>("Id", Thuoc::getMaThuoc));
		tableMedicine.getFilters().add(new StringFilter<>("Name", Thuoc::getTenThuoc));
		tableMedicine.getFilters().add(new StringFilter<>("Unit", Thuoc::getDonVi));
		tableMedicine.getFilters().add(new IntegerFilter<>("Quantity", Thuoc::getSoLuong));
		tableMedicine.getFilters().add(new BooleanFilter<>("Status", Thuoc::getStatus));

		loadTable();

		// select row
		
		tableMedicine.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					medicine_unit.setText(p.getDonVi());
					medicine_id.setText(String.valueOf(p.getMaThuoc()));
					medicine_name.setText(p.getTenThuoc());
					medicine_unit.setText(p.getDonVi());
					medicine_quantity.setText(String.valueOf(p.getSoLuong()));
					medicine_price.setText(String.valueOf(p.getDonGia()));
					
					if(p.getStatus()) {
						medicine_status.setText(statusList.get(0));
					}
					else {
						medicine_status.setText(statusList.get(1));
					}
				});
			}
		});
	}

	public void setupButton() {
		editBtn.setOnMouseClicked(event -> {
			if (medicine_id.getText().isEmpty()) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
			if (medicine_name.getText().isEmpty() || medicine_unit.getText().isEmpty()
					|| medicine_price.getText().isEmpty()) {
				alertMessage.errorMessage("Please fill all blank fields!");
				return;
			} else {
				LocalDate slDate = LocalDate.of(2012, 04, 21);
				Date b = Date.valueOf(slDate);
				double N_1 = Double.parseDouble(medicine_price.getText());
				BigDecimal n1 = new BigDecimal(N_1);
				Boolean status = true;
				if(medicine_status.getText().equals("Active")) {
					
				}
				else status = false;
				
				Thuoc thuoc = new Thuoc(Integer.parseInt(medicine_id.getText()), medicine_name.getText(),
						medicine_unit.getText(), Integer.parseInt(medicine_quantity.getText()), n1, b, b, status);
				DAO_Thuoc.getInstance().Update(thuoc);
				reloadTable();
				alertMessage.successMessage("Information changed successfully!");
			}
		});

		addBtn.setOnMouseClicked(event -> {
			LocalDate slDate = LocalDate.of(2012, 04, 21);
			Date b = Date.valueOf(slDate);
			double N_1 = 0.0;
			BigDecimal n1 = new BigDecimal(N_1);
			Thuoc thuoc = new Thuoc(0, "...", "...", 0, n1, b, b, true);
			DAO_Thuoc.getInstance().Add(thuoc);
			
			
			reloadTable();
			Thuoc p = medicineList.getLast();
			medicine_unit.setText(p.getDonVi());
			medicine_id.setText(String.valueOf(p.getMaThuoc()));
			medicine_name.setText(p.getTenThuoc());
			medicine_unit.setText(p.getDonVi());
			medicine_quantity.setText(String.valueOf(p.getSoLuong()));
			medicine_price.setText(String.valueOf(p.getDonGia()));
			
				medicine_status.setText(statusList.get(0));
			
				
			alertMessage.successMessage("New object added successfully!");
		});

		
		
		editUnitBtn.setOnMouseClicked(event -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/EditUnitDialog.fxml"));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setTitle("Private Heathcare Facility");
				stage.setResizable(false);
				stage.getIcons().add(new Image("/images/hospital-building.png"));
				stage.showAndWait();
				if (!stage.isShowing()) {
					reloadTable();
				}
			} catch (Exception e) {
				alertMessage.errorMessage("Can't load the scene!");
			}
		});
	}
}
