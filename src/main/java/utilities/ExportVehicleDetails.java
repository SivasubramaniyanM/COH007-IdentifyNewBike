package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportVehicleDetails {
    private static final String FILE_PATH =
            System.getProperty("user.dir")
                    + "/src/test/resources/testdata/Vehicle.xlsx";
    private static final String SHEET_NAME = "HondaBikes";

    public static void writeHondaBikeDetails(List<String[]> bikeData) throws IOException {
        File file = new File(FILE_PATH);
        Workbook workbook;
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } else {
            workbook = new XSSFWorkbook();
        }
        Sheet sheet = workbook.getSheet(SHEET_NAME);
        if (sheet != null) {
            workbook.removeSheetAt(workbook.getSheetIndex(sheet));
        }
        sheet = workbook.createSheet(SHEET_NAME);
        int rowNum = 0;
        Row titleRow = sheet.createRow(rowNum++);
        titleRow.createCell(0).setCellValue("ALL HONDA BIKE DETAILS");
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Bike Name");
        headerRow.createCell(1).setCellValue("Bike Price");
        headerRow.createCell(2).setCellValue("Launch Date");
        for (String[] bike : bikeData) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(bike[0]);
            if (bike.length > 1) {
                row.createCell(1).setCellValue(bike[1]);
            }
            if (bike.length > 2) {
                row.createCell(2).setCellValue(bike[2]);
            }
        }
        FileOutputStream fos = new FileOutputStream(FILE_PATH);
        workbook.write(fos);
        fos.close();
        workbook.close();
        Log.info("All Honda Bike Details written to Excel");
    }
    public static void writeBikeDetailsToExcel(List<String> names,
                                               List<String> prices,
                                               List<String> dates)
            throws IOException {
        File file = new File(FILE_PATH);
        Workbook workbook;
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } else {
            workbook = new XSSFWorkbook();
        }
        Sheet sheet = workbook.getSheet(SHEET_NAME);
        if (sheet == null) {
            sheet = workbook.createSheet(SHEET_NAME);
        }
        int rowNum = sheet.getLastRowNum();
        if (rowNum > 0) {
            rowNum += 3;
        }
        Row titleRow = sheet.createRow(rowNum++);
        titleRow.createCell(0).setCellValue("HONDA BIKES UNDER 4 LAKHS");
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Bike Name");
        headerRow.createCell(1).setCellValue("Bike Price");
        headerRow.createCell(2).setCellValue("Launch Date");
        for (int i = 0; i < names.size(); i++) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(names.get(i));
            if (i < prices.size()) {
                row.createCell(1).setCellValue(prices.get(i));
            }
            if (i < dates.size()) {
                row.createCell(2).setCellValue(dates.get(i));
            }
        }
        FileOutputStream fos = new FileOutputStream(FILE_PATH);
        workbook.write(fos);
        fos.close();
        workbook.close();
        Log.info("Honda Bikes Under 4 Lakhs written to Excel");
    }
    public static void writeCarDetailsToExcel(List<String> carDetails)
            throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            Log.error("Vehicle.xlsx not found");
            return;
        }
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet("CarsUnder4Lakh");
        if (sheet != null) {
            workbook.removeSheetAt(workbook.getSheetIndex(sheet));
        }
        sheet = workbook.createSheet("CarsUnder4Lakh");
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Car Name");
        header.createCell(1).setCellValue("Price");
        int rowNum = 1;
        for (String car : carDetails) {
            String[] data = car.split("->");
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(data[0].trim());
            if (data.length > 1) {
                row.createCell(1).setCellValue(data[1].trim());
            }
        }
        fis.close();
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();
        workbook.close();
        Log.info("Cars Sheet Added Successfully");
    }
    public static void writeHyundaiPopularCars(List<String[]> carData)
            throws IOException {
        File file = new File(FILE_PATH);
        Workbook workbook;
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } else {
            workbook = new XSSFWorkbook();
        }
        Sheet sheet = workbook.getSheet("HyundaiPopularCars");
        if (sheet != null) {
            workbook.removeSheetAt(workbook.getSheetIndex(sheet));
        }
        sheet = workbook.createSheet("HyundaiPopularCars");
        int rowNum = 0;
        Row titleRow = sheet.createRow(rowNum++);
        titleRow.createCell(0).setCellValue("HYUNDAI POPULAR CARS - CHENNAI");
        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Car Name");
        headerRow.createCell(1).setCellValue("Car Price");
        for (String[] car : carData) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(car[0]);
            if (car.length > 1) {
                row.createCell(1).setCellValue(car[1]);
            }
        }
        FileOutputStream fos = new FileOutputStream(FILE_PATH);
        workbook.write(fos);
        fos.close();
        workbook.close();
        Log.info("Hyundai Popular Cars written to Excel");
    }
}