
package controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

import Data_Access_Object.DAO_BacSi;
import Data_Access_Object.DAO_LoaiXetNghiem;
import Data_Access_Object.DAO_Role;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.*;

public class SubcinicalScreenController implements Initializable {
	Stage window;
	private boolean canChange;
	private int timeChange;
	private AlertMessage alertMessage = new AlertMessage();

	private ObservableList<LoaiXetNghiem> subList = null;

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
	private AnchorPane scenePane;

	@FXML
	private Label sub_id;

	@FXML
	private TextArea sub_instruc;

	@FXML
	private MFXTextField sub_name;

	@FXML
	private MFXTextField sub_price;

	@FXML
	private MFXButton subclinicalBtn;

	@FXML
	private ImageView subclinicalImg;

	@FXML
	private MFXTableView<LoaiXetNghiem> tableSub;

	@FXML
	private MFXButton userBtn;

	@FXML
	private ImageView userImg;
	
	@FXML
	private MFXComboBox<String> sub_status;
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

		
		sub_status.setItems(statusList);
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
		subList = DAO_LoaiXetNghiem.getInstance().selectAll();
		tableSub.setItems(subList);
		tableSub.autosize();
		tableSub.autosizeColumns();
		tableSub.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() {
		subList.clear();
		loadTable();
	}

	@SuppressWarnings("unchecked")
	public void setupTable() {
		// Initialize columns
		MFXTableColumn<LoaiXetNghiem> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(LoaiXetNghiem::getMaLoaiXN));
		MFXTableColumn<LoaiXetNghiem> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(LoaiXetNghiem::getTenLoaiXN));
		MFXTableColumn<LoaiXetNghiem> priceColumn = new MFXTableColumn<>("Price", true,Comparator.comparing(LoaiXetNghiem::getChiPhiXN));
		MFXTableColumn<LoaiXetNghiem> instrucColumn = new MFXTableColumn<>("Instructions", true,Comparator.comparing(LoaiXetNghiem::getMoTaXN));
		MFXTableColumn<LoaiXetNghiem> statusColumn = new MFXTableColumn<>("Active?", true,Comparator.comparing(LoaiXetNghiem::isStatus));

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(LoaiXetNghiem::getMaLoaiXN));
		nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(LoaiXetNghiem::getTenLoaiXN));
		priceColumn.setRowCellFactory(col -> new MFXTableRowCell<>(LoaiXetNghiem::getChiPhiXN));
		instrucColumn.setRowCellFactory(col -> new MFXTableRowCell<>(LoaiXetNghiem::getMoTaXN));
		statusColumn.setRowCellFactory(col -> new MFXTableRowCell<>(LoaiXetNghiem::isStatus));
		
		// Add columns to the table
		tableSub.getTableColumns().addAll(idColumn, nameColumn, priceColumn, instrucColumn,statusColumn);

		// Add filters to the table
		tableSub.getFilters().add(new IntegerFilter<>("Id", LoaiXetNghiem::getMaLoaiXN));
		tableSub.getFilters().add(new StringFilter<>("Name", LoaiXetNghiem::getTenLoaiXN));
		tableSub.getFilters().add(new StringFilter<>("Instruction", LoaiXetNghiem::getMoTaXN));
		tableSub.getFilters().add(new BooleanFilter<>("Status", LoaiXetNghiem::isStatus));

		loadTable();

		// select row
		
		tableSub.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					sub_id.setText(String.valueOf(p.getMaLoaiXN()));
					sub_name.setText(p.getTenLoaiXN());
					sub_price.setText(String.valueOf(p.getChiPhiXN()));
					sub_instruc.setText(p.getMoTaXN());
					
					if(p.isStatus()) {
						sub_status.setText(statusList.get(0));
					}
					else {
						sub_status.setText(statusList.get(1));
					}
				});
			}
		});
	}

	public void setupButton() {
		editBtn.setOnMouseClicked(event -> {
			if (sub_id.getText().isEmpty()) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
			if (sub_name.getText().isEmpty() || sub_price.getText().isEmpty()|| sub_instruc.getText().isEmpty()) {
				alertMessage.errorMessage("Please fill all blank fields!");
				return;
			} else {
				double N_1 = Double.parseDouble(sub_price.getText());
				BigDecimal n1 = new BigDecimal(N_1); 
				Boolean status = true;
				if(sub_status.getText().equals("Active")) {
					
				}
				else status = false;
				LoaiXetNghiem loaiXetNghiem = new LoaiXetNghiem(Integer.parseInt(sub_id.getText()), sub_name.getText(), n1, sub_instruc.getText(),status); 
				DAO_LoaiXetNghiem.getInstance().Update(loaiXetNghiem);
				reloadTable();
				alertMessage.successMessage("Information changed successfully!");
			}
		});

		addBtn.setOnMouseClicked(event -> {
			double N_1 = 0.0;
			BigDecimal n1 = new BigDecimal(N_1);
			LoaiXetNghiem LoaiXetNghiem1 = new LoaiXetNghiem(0, "...", n1, "...",true);
			DAO_LoaiXetNghiem.getInstance().Add(LoaiXetNghiem1);
			reloadTable();
			LoaiXetNghiem p = subList.getLast();
			sub_id.setText(String.valueOf(p.getMaLoaiXN()));
			sub_name.setText(p.getTenLoaiXN());
			sub_price.setText(String.valueOf(p.getChiPhiXN()));
			sub_instruc.setText(p.getMoTaXN());
			sub_status.setText(statusList.get(0));
			alertMessage.successMessage("New object added successfully!");
		});

		
		
		
	}
}
