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

    static Workbook workbook = new XSSFWorkbook();

    public static void writeBikeDetailsToExcel(List<String> names,
                                               List<String> prices,
                                               List<String> dates)
            throws IOException {

        Sheet sheet1 = workbook.createSheet("HondaBikesUnder4Lakhs");

        Row header = sheet1.createRow(0);
        header.createCell(0).setCellValue("Bike Name");
        header.createCell(1).setCellValue("Bike Price");
        header.createCell(2).setCellValue("Launch Date");

        for (int i = 0; i < names.size(); i++) {
            Row row = sheet1.createRow(i + 1);
            row.createCell(0).setCellValue(names.get(i));
            row.createCell(1).setCellValue(prices.get(i));
            row.createCell(2).setCellValue(dates.get(i));
        }

        FileOutputStream fos = new FileOutputStream(
                System.getProperty("user.dir")
                        + "/src/test/resources/testdata/Vehicle.xlsx"
        );

        workbook.write(fos);
        workbook.close();

        System.out.println("Vehicle.xlsx created successfully");
    }

    public static void writeCarDetailsToExcel(List<String> carDetails)
            throws IOException {

        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/testdata/Vehicle.xlsx";

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("Vehicle.xlsx not found");
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

        System.out.println("Cars Sheet Added Successfully");
    }
}