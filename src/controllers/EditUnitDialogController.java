package controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import Data_Access_Object.DAO_BacSi;
import Data_Access_Object.DAO_DVT;
import Data_Access_Object.DAO_Thuoc;
import Database.JDBCUtil;
import models.*;
import javafx.fxml.Initializable;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.BooleanFilter;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EditUnitDialogController implements Initializable {

	@FXML
	private MFXButton addBtn;

	@FXML
	private MFXButton cancelBtn;

	@FXML
	private MFXComboBox<String> status;
	ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");

	@FXML
	private MFXButton editBtn;

	@FXML
	private AnchorPane scenePane;

	@FXML
	private MFXTableView<DVT> tableUnit;

	@FXML
	private MFXTextField unit_id;

	@FXML
	private MFXTextField unit_name;
	
	private Stage window;
	private ObservableList<DVT> unitList = null;
	private AlertMessage alertMessage = new AlertMessage();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setupButton();
		setupTable();
	}

	public void loadTable() {
		unitList = DAO_DVT.getInstance().selectAll();
		tableUnit.setItems(unitList);
		tableUnit.autosize();
		tableUnit.autosizeColumns();
		tableUnit.autosizeColumnsOnInitialization();
	}

	// Reload screen
	public void reloadTable() {
		unitList.clear();
		loadTable();
	}

	@SuppressWarnings("unchecked")
	public void setupTable() {
		status.setItems(statusList);
		// Initialize columns
		MFXTableColumn<DVT> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(DVT::getMa_DVT));
		MFXTableColumn<DVT> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(DVT::getTen_DVT));
		MFXTableColumn<DVT> statusColumn = new MFXTableColumn<>("Active?", true, Comparator.comparing(DVT::getStatus));
		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(DVT::getMa_DVT));
		nameColumn.setRowCellFactory(col -> new MFXTableRowCell<>(DVT::getTen_DVT));
		statusColumn.setRowCellFactory(col -> new MFXTableRowCell<>(DVT::getStatus));
		// Add columns to the table
		tableUnit.getTableColumns().addAll(idColumn, nameColumn,statusColumn);

		// Add filters to the table
		tableUnit.getFilters().add(new IntegerFilter<>("Id", DVT::getMa_DVT));
		tableUnit.getFilters().add(new StringFilter<>("Name", DVT::getTen_DVT));
		tableUnit.getFilters().add(new BooleanFilter<>("Active?", DVT::getStatus));

		loadTable();

		// select row
		unit_id.setEditable(false);
		tableUnit.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					unit_id.setText(String.valueOf(p.getMa_DVT()));
					unit_name.setText(p.getTen_DVT());
					
					if(p.getStatus()) {
						status.setText(statusList.get(0));
					}
					else {
						status.setText(statusList.get(1));
					}
				});
			}
		});
	}

	public void setupButton() {
		
		editBtn.setOnMouseClicked(event -> {
			if (unit_id.getText().isEmpty()) {
				alertMessage.errorMessage("Please select the object you want to change information!");
				return;
			}
			if (unit_name.getText().isEmpty()) {
				alertMessage.errorMessage("Please fill all blank fields!");
				return;
			} else {	
				Boolean status2 = true;
				if(status.getText().equals("Active")) {
					
				}
				else status2 = false;
				DVT dvt = new DVT(Integer.parseInt(unit_id.getText()), unit_name.getText(), status2);
				DAO_DVT.getInstance().Update(dvt);
				reloadTable(); 
				alertMessage.successMessage("Information changed successfully!");
			}
		});

		addBtn.setOnMouseClicked(event -> {
			DVT dvt = new DVT(0, null, true);
			DAO_DVT.getInstance().Add(dvt);
			reloadTable();
			DVT a = DAO_DVT.getInstance().selectAll().getLast();
			unit_id.setText(String.valueOf(a.getMa_DVT()));
			unit_name.setText(a.getTen_DVT());
			
			
			status.setText(statusList.get(0));
			alertMessage.successMessage("New object added successfully!");
		});

		
		
		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) scenePane.getScene().getWindow();
			window.close();
		});
	}
}
