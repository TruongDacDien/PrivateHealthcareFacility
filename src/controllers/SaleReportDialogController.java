package controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;
import java.util.ResourceBundle;

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
import Data_Access_Object.DAO_BenhNhan;
import Data_Access_Object.DAO_HoaDonPhieuKham;
import Data_Access_Object.DAO_LichHen;
import Data_Access_Object.DAO_PhieuKham;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
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
import javafx.fxml.FXMLLoader;

public class SaleReportDialogController implements Initializable {

	
	@FXML
	private MFXTextField dateTV;

	@FXML
	private MFXButton cancelBtn;
	
	@FXML
    private MFXButton excelBtn;

	private AlertMessage alertMessage = new AlertMessage();
	 
	@FXML
	private MFXTableView<ChiTietBaoCaoThang>  reportTable;
	
	
	
	
	private ObservableList<ChiTietBaoCaoThang> ctbcts = FXCollections.observableArrayList();
	
	private Stage window;
	MathContext mc = new MathContext(5, RoundingMode.HALF_UP);
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setupScreen();
		setupButton();
		setupTable();
	}

	private void setupTable() {
		BaoCaoDoanhSo bc = DAO_BaoCaoDoanhSo.getInstance().selectByCondition2(" MaBCDoanhSo = " + CurrentSalesReport.MaBCDoanhSo );
		dateTV.setText(String.valueOf(bc.getNgayLapDoanhSo()));
		
		
		
		ArrayList<HoaDonPhieuKham> hdpks =  DAO_HoaDonPhieuKham.getInstance().selectByCondition(" isPaid = true ");
		
		LocalDate a = LocalDate.of(bc.getNgayLapDoanhSo().getYear()+ 1900,bc.getNgayLapDoanhSo().getMonth()+1,bc.getNgayLapDoanhSo().getDate());
	    
	    
	 
		
		for(int j = 1;j<=a.getDayOfMonth();j++) {
			BigDecimal doanhSo = new BigDecimal(0);
		    int soPhieuKham = 0;
			int y = 0;
			for(int i =0;i<hdpks.size();i++) {
				 
				 
					
			        
			    
				
				
				
					
			
			if(hdpks.get(i).getNgayBan().getYear() == bc.getNgayLapDoanhSo().getYear() && hdpks.get(i).getNgayBan().getMonth() == bc.getNgayLapDoanhSo().getMonth() && hdpks.get(i).getNgayBan().getDate()==j) {
					doanhSo = doanhSo.add(hdpks.get(i).getTongTien());
					soPhieuKham ++;
					y = 1;
			}
						
						
					
					
					
				}
		if(y==1) {
			ctbcts.add(new ChiTietBaoCaoThang(Date.valueOf(LocalDate.of(bc.getNgayLapDoanhSo().getYear()+1900, bc.getNgayLapDoanhSo().getMonth()+1,j)),soPhieuKham,doanhSo,doanhSo.divide( bc.getDoanhSo(),mc).floatValue() ));
		}
		else {
			ctbcts.add(new ChiTietBaoCaoThang(Date.valueOf(LocalDate.of(bc.getNgayLapDoanhSo().getYear()+1900, bc.getNgayLapDoanhSo().getMonth()+1,j)),0,new BigDecimal(0),0));
		}
		}
		
		MFXTableColumn<ChiTietBaoCaoThang> dateColumn = new MFXTableColumn<>("Date", true, Comparator.comparing(ChiTietBaoCaoThang::getNgayBC));
		MFXTableColumn<ChiTietBaoCaoThang> numberOfFormColumn = new MFXTableColumn<>("Number of form", true, Comparator.comparing(ChiTietBaoCaoThang::getSoBenhNhan));
		MFXTableColumn<ChiTietBaoCaoThang> doanhSoColumn = new MFXTableColumn<>("Sales", true, Comparator.comparing(ChiTietBaoCaoThang::getDoanhThu));
		MFXTableColumn<ChiTietBaoCaoThang> tiLeColumn = new MFXTableColumn<>("Rate", true, Comparator.comparing(ChiTietBaoCaoThang::getTiLe));
		
		

		// Set row cell factories
		dateColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietBaoCaoThang::getNgayBC));
		numberOfFormColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietBaoCaoThang::getSoBenhNhan));
		doanhSoColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietBaoCaoThang::getDoanhThu));
		tiLeColumn.setRowCellFactory(col -> new MFXTableRowCell<>(ChiTietBaoCaoThang::getTiLe));
		
		
		reportTable.getFilters().add(new DateFilter<ChiTietBaoCaoThang>("Date", ChiTietBaoCaoThang::getNgayBC));
		// Add columns to the table
		reportTable.getTableColumns().addAll(dateColumn,numberOfFormColumn,doanhSoColumn,tiLeColumn);

		// Add filters to the table
		
		reportTable.setItems(ctbcts);
		
	}

	private void setupButton() {
		// TODO Auto-generated method stub
		cancelBtn.setOnMouseClicked(event -> {
			window = (Stage) cancelBtn.getScene().getWindow();
			window.close();
		});

		excelBtn.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
			File file = fileChooser.showSaveDialog(excelBtn.getScene().getWindow());
			if (file != null) {
				try (Workbook workbook = new XSSFWorkbook()) {
					Sheet sheet = workbook.createSheet("Monthly Revenue Report");

					// Add an empty row at the top
					sheet.createRow(0);

					// Create the title row "Revenue Report"
					Row titleRow = sheet.createRow(1);
					Cell titleCell = titleRow.createCell(1);
					BaoCaoDoanhSo bc = DAO_BaoCaoDoanhSo.getInstance()
							.selectByCondition2(" MaBCDoanhSo = " + CurrentSalesReport.MaBCDoanhSo);
					titleCell.setCellValue("Revenue Report - " + bc.getNgayLapDoanhSo().toLocalDate().getMonth()
							.getDisplayName(TextStyle.FULL, Locale.ENGLISH));

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
					dateCell.setCellValue("Report Date: " + LocalDate.now().getDayOfMonth() + "/"
							+ LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear());

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

					String[] headers = { "Date", "Number of form", "Sales", "Rate" };
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
					for (ChiTietBaoCaoThang report : ctbcts) {
						Row row = sheet.createRow(rowIndex++);
						Cell cell0 = row.createCell(1);
						cell0.setCellValue(report.getNgayBC().toString());
						cell0.setCellStyle(cellStyle);

						Cell cell1 = row.createCell(2);
						cell1.setCellValue(report.getSoBenhNhan());
						cell1.setCellStyle(cellStyle);

						Cell cell2 = row.createCell(3);
						cell2.setCellValue(report.getDoanhThu().doubleValue());
						cell2.setCellStyle(cellStyle);

						Cell cell3 = row.createCell(4);
						cell3.setCellValue(report.getTiLe());
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

	public void setupScreen() {
		
	}

	// Load data from the database
	
}
