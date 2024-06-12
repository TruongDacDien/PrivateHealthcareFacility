

package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Data_Access_Object.DAO_BaoCaoDoanhSo;
import Data_Access_Object.DAO_Role;
import Data_Access_Object.DAO_Thuoc;
import io.github.palexdev.materialfx.beans.BiPredicateBean;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterPane;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import models.BaoCaoDoanhSo;
import models.Role;
import models.Thuoc;


public class ReportScreenController implements Initializable  {
	Stage window;
	private boolean canChange ;
	private int timeChange;
	
	@FXML
	private AnchorPane darkPane,detailMenuPane,menuPane;
	
	@FXML 
	private ImageView menuImg ; 
	
	@FXML
	private ImageView userImg;
	
	@FXML 
	private MFXButton addBtn,editBtn;
	
	@FXML
    private MFXButton excelBtn;
	
	@FXML
	Dialog<ButtonType> dialog = new Dialog<>();
	
	@FXML
	private MFXButton homepageBtn,admissionBtn,pateintBtn,
	medicineBtn,subclinicalBtn,doctorBtn,userBtn,reportBtn;
	
	@FXML
	private MFXTableView<BaoCaoDoanhSo> sale_table;
	
	
	private ObservableList<BaoCaoDoanhSo> saleList = null;
	
	
	@FXML
	private MFXButton detailBtn;
	
;

	
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
	setupTabChange ();
    
    
        
    }
public void loadTable() {
	saleList = DAO_BaoCaoDoanhSo.getInstance().selectAll();
	sale_table.setItems(saleList);
	sale_table.autosize();
	sale_table.autosizeColumns();
	sale_table.autosizeColumnsOnInitialization();
}

// Reload screen
public void reloadTable() {
	saleList.clear();
	loadTable();
}

	private void setupTabChange() {
		
		homepageBtn.setOnAction(event -> {
			window = (Stage)menuImg.getScene().getWindow();
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
			window = (Stage)menuImg.getScene().getWindow();
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
			window = (Stage)menuImg.getScene().getWindow();
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
			window = (Stage)menuImg.getScene().getWindow();
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
			window = (Stage)menuImg.getScene().getWindow();
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
			window = (Stage)menuImg.getScene().getWindow();
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
			window = (Stage)menuImg.getScene().getWindow();
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
			window = (Stage)menuImg.getScene().getWindow();
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


	    javafx.animation.FadeTransition fadeTransition=new javafx.animation.FadeTransition(Duration.seconds(0.39),darkPane);
	    fadeTransition.setFromValue(1);
	    fadeTransition.setToValue(0);
	    fadeTransition.play();

	    if(isQuick.quick == true) {
	    javafx.animation.TranslateTransition translateTransition=new javafx.animation.TranslateTransition(Duration.seconds(0.01),detailMenuPane);
	    translateTransition.setByX(-600);
	    translateTransition.play();
	    isQuick.quick = false;
	    }
	    else {
	    javafx.animation.TranslateTransition translateTransition=new javafx.animation.TranslateTransition(Duration.seconds(0.39),detailMenuPane);
	    translateTransition.setByX(-600);
	    translateTransition.play();
	    }

	   
	    
	    canChange = true;
	    menuPane.setOnMouseEntered(event -> {
	    	timeChange += 1;
	    	if(canChange&& timeChange ==1) {
	    		menuImg.setVisible(false);
	    		darkPane.setVisible(true);
	        
	        javafx.animation.FadeTransition fadeTransition1=new javafx.animation.FadeTransition(Duration.seconds(0.39),darkPane);
	        //fadeTransition1.setFromValue(0);
	        //fadeTransition1.setToValue(0.1);
	        fadeTransition1.play();
	        
	        

	        javafx.animation.TranslateTransition translateTransition1=new javafx.animation.TranslateTransition(Duration.seconds(0.01),detailMenuPane);
	        translateTransition1.setByX(+600);
	        translateTransition1.setOnFinished(event1 -> {
	            canChange = false;
	            
	            
	        });
	        translateTransition1.play();
	        
	        
	        
	        
	        
	       
	    	}
	        
	    });
	    detailMenuPane.setOnMouseExited(event -> {
	    	
	    	if(!canChange && event.getX()> 100) {
	    		
	        javafx.animation.FadeTransition fadeTransition1=new javafx.animation.FadeTransition(Duration.seconds(0.39),darkPane);
	        //fadeTransition1.setFromValue(0.15);
	        //fadeTransition1.setToValue(0);
	        fadeTransition1.play();

	        fadeTransition1.setOnFinished(event1 -> {
	        	darkPane.setVisible(false);
	        });


	        javafx.animation.TranslateTransition translateTransition1=new javafx.animation.TranslateTransition(Duration.seconds(0.39),detailMenuPane);
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
	public void setupTable() {
		MFXTableColumn<BaoCaoDoanhSo> idColumn = new MFXTableColumn<>("Id", true, Comparator.comparing(BaoCaoDoanhSo::getMaBCDoanhSo));
		MFXTableColumn<BaoCaoDoanhSo> dateColumn = new MFXTableColumn<>("Date", true, Comparator.comparing(BaoCaoDoanhSo::getNgayLapDoanhSo));
		MFXTableColumn<BaoCaoDoanhSo> moneyColumn = new MFXTableColumn<>("Money", true, Comparator.comparing(BaoCaoDoanhSo::getDoanhSo));
		MFXTableColumn<BaoCaoDoanhSo> numberOfFormColumn = new MFXTableColumn<>("Number of form", true, Comparator.comparing(BaoCaoDoanhSo::getSoLuongPhieuKham));
		

		// Set row cell factories
		idColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BaoCaoDoanhSo::getMaBCDoanhSo));
		dateColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BaoCaoDoanhSo::getNgayLapDoanhSo));
		moneyColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BaoCaoDoanhSo::getDoanhSo));
		numberOfFormColumn.setRowCellFactory(col -> new MFXTableRowCell<>(BaoCaoDoanhSo::getSoLuongPhieuKham));
		

		// Add columns to the table
		sale_table.getTableColumns().addAll(idColumn, dateColumn,moneyColumn,numberOfFormColumn);

		// Add filters to the table
		sale_table.getFilters().add(new IntegerFilter<>("Id", BaoCaoDoanhSo::getMaBCDoanhSo));
		sale_table.getFilters().add(new DateFilter<>("Date(YYYY-MM-dd)", BaoCaoDoanhSo::getNgayLapDoanhSo));
		
		sale_table.getFilters().add(new IntegerFilter<>("Number of form", BaoCaoDoanhSo::getSoLuongPhieuKham));
		

		loadTable();
		sale_table.getSelectionModel().selectionProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.values().stream().findFirst().ifPresent(p -> {
					CurrentSalesReport.MaBCDoanhSo = p.getMaBCDoanhSo();
				});
			}
		});
		
		AlertMessage alertMessage = new AlertMessage();
		detailBtn.setOnAction(event -> {
			if (event.getSource() == detailBtn) {
				if (CurrentSalesReport.MaBCDoanhSo == 0) {
					
					alertMessage.errorMessage("Please select the object you want to change information!");
					return;
				}
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/SaleReportDialog.fxml"));
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
						CurrentSalesReport.MaBCDoanhSo=0;
					}
				} catch (Exception e) {
					alertMessage.errorMessage("Can't load the scene!");
					e.printStackTrace();
					return;
				}
			}
		});
		
		excelBtn.setOnAction(event -> {
		    FileChooser fileChooser = new FileChooser();
		    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
		    File file = fileChooser.showSaveDialog(excelBtn.getScene().getWindow());
		    if (file != null) {
		    	try (Workbook workbook = new XSSFWorkbook()) {
                    Sheet sheet = workbook.createSheet("Sales Report");

                    // Add an empty row at the top
                    sheet.createRow(0);

                    // Create the title row "Sales Report"
                    Row titleRow = sheet.createRow(1);
                    Cell titleCell = titleRow.createCell(1);
                    titleCell.setCellValue("Sales Report");

                    // Merge cells for the title row
                    CellRangeAddress titleRegion = new CellRangeAddress(1, 1, 1, 4);
                    sheet.addMergedRegion(titleRegion);

                    CellStyle titleStyle = workbook.createCellStyle();
                    Font titleFont = workbook.createFont();
                    titleFont.setBold(true);
                    titleFont.setFontHeightInPoints((short) 16);
                    titleStyle.setFont(titleFont);
                    titleStyle.setAlignment(HorizontalAlignment.CENTER);
                    titleStyle.setBorderBottom(BorderStyle.THIN);
                    titleStyle.setBorderTop(BorderStyle.THIN);
                    titleStyle.setBorderLeft(BorderStyle.THIN);
                    titleStyle.setBorderRight(BorderStyle.THIN);

                    titleCell.setCellStyle(titleStyle); // Apply the style to the cell
                    setBordersToMergedCells(sheet, titleRegion, titleStyle);// Apply borders to merged cells

                    // Create the date row
                    Row dateRow = sheet.createRow(2);
                    Cell dateCell = dateRow.createCell(1);
                    dateCell.setCellValue("Report Date: " + LocalDate.now().getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear());

                    // Merge cells for the date row
                    CellRangeAddress dateRegion = new CellRangeAddress(2, 2, 1, 4);
                    sheet.addMergedRegion(dateRegion);

                    CellStyle dateStyle = workbook.createCellStyle();
                    Font dateFont = workbook.createFont();
                    dateFont.setBold(true);
                    dateFont.setFontHeightInPoints((short) 14);
                    dateStyle.setFont(dateFont);
                    dateStyle.setAlignment(HorizontalAlignment.CENTER);
                    dateStyle.setBorderBottom(BorderStyle.THIN);
                    dateStyle.setBorderTop(BorderStyle.THIN);
                    dateStyle.setBorderLeft(BorderStyle.THIN);
                    dateStyle.setBorderRight(BorderStyle.THIN);

                    dateCell.setCellStyle(dateStyle); // Apply the style to the cell           
                    setBordersToMergedCells(sheet, dateRegion, dateStyle); // Apply borders to merged cells
		           
		            // Create the header row
		            Row headerRow = sheet.createRow(3);
		            CellStyle headerStyle = workbook.createCellStyle();
		            Font headerFont = workbook.createFont();
		            headerFont.setFontHeightInPoints((short) 14);
		            headerFont.setBold(true);
		            headerStyle.setFont(headerFont);
		            headerStyle.setBorderBottom(BorderStyle.THIN);
		            headerStyle.setBorderTop(BorderStyle.THIN);
		            headerStyle.setBorderLeft(BorderStyle.THIN);
		            headerStyle.setBorderRight(BorderStyle.THIN);

		            String[] headers = {"ID", "Date", "Money", "Number of form"};
		            for (int i = 0; i < headers.length; i++) {
		                Cell cell = headerRow.createCell(i + 1);
		                cell.setCellValue(headers[i]);
		                cell.setCellStyle(headerStyle);
		            }

		            // Add data to the table and apply border to all cells
		            CellStyle cellStyle = workbook.createCellStyle();
		            Font cellFont = workbook.createFont();
		            cellFont.setFontHeightInPoints((short) 14);
		            cellStyle.setFont(cellFont);
		            cellStyle.setBorderBottom(BorderStyle.THIN);
		            cellStyle.setBorderTop(BorderStyle.THIN);
		            cellStyle.setBorderLeft(BorderStyle.THIN);
		            cellStyle.setBorderRight(BorderStyle.THIN);

		            int rowIndex = 4; // Start from the fourth row
		            for (BaoCaoDoanhSo report : saleList) {
		                Row row = sheet.createRow(rowIndex++);
		                Cell cell0 = row.createCell(1);
		                cell0.setCellValue(report.getMaBCDoanhSo());
		                cell0.setCellStyle(cellStyle);

		                Cell cell1 = row.createCell(2);
		                cell1.setCellValue(report.getNgayLapDoanhSo().toString());
		                cell1.setCellStyle(cellStyle);

		                Cell cell2 = row.createCell(3);
		                cell2.setCellValue(report.getDoanhSo().doubleValue());
		                cell2.setCellStyle(cellStyle);

		                Cell cell3 = row.createCell(4);
		                cell3.setCellValue(report.getSoLuongPhieuKham());
		                cell3.setCellStyle(cellStyle);
		            }

		            for (int i = 1; i <= headers.length; i++) {
		                sheet.autoSizeColumn(i);
		            }

		            try (FileOutputStream fos = new FileOutputStream(file)) {
		                workbook.write(fos);
		                alertMessage.successMessage(
		                        "Export Successful\n" + "Data exported successfully to " + file.getAbsolutePath());
		            }

		            Desktop.getDesktop().open(file);

		        } catch (Exception e) {
		            alertMessage.errorMessage(
		                    "Export Error\n" + "An error occurred while exporting data: " + e.getMessage());
		            e.printStackTrace();
		        }
		    }
		});
	}
	
	private static void setBordersToMergedCells(Sheet sheet, CellRangeAddress region, CellStyle style) {
	    RegionUtil.setBorderTop(style.getBorderTop(), region, sheet);
	    RegionUtil.setBorderRight(style.getBorderRight(), region, sheet);
	    RegionUtil.setBorderBottom(style.getBorderBottom(), region, sheet);
	    RegionUtil.setBorderLeft(style.getBorderLeft(), region, sheet);
	}
	
}

                                      


