// package com.ey.accueilapp.services;

// import java.io.IOException;
// import java.io.InputStream;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Objects;

// import javax.swing.text.html.HTMLDocument.Iterator;

// import org.apache.poi.ss.usermodel.Cell;
// import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.xssf.usermodel.XSSFSheet;
// import org.apache.poi.xssf.usermodel.XSSFWorkbook;
// import org.springframework.web.multipart.MultipartFile;

// import com.ey.accueilapp.entities.Event;

// public class ExcelUploadService {
// public static boolean isValid(MultipartFile file) {
// return Objects.equals(file.getContentType(),
// "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
// }

// public static List<Event> getEventDatFromExcel(InputStream inputStream) {
// List<Event> eventsFromExcel = new ArrayList<>();
// try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
// XSSFSheet sheet = workbook.getSheet("JANVIER2023");

// int rowIndex = 0;
// for (Row row : sheet) {
// if (rowIndex == 0) {
// rowIndex++;
// continue;
// }
// // Iterator<Cell> cellIterator = row.iterator();
// // int cellIndex = 0;
// // Event event= new Event();
// // while (cellIterator.hasnext()) {
// // Cell cell = cellIterator.next();
// // switch (cellIndex) {
// // case 2 -> event.setDate(cell.getDateCellValue());
// // case 3 -> event.setName(cell.getStringCellValue());
// // case 4-> event.setNombreHommes((int)cell.getNumericCellValue());
// // case 5-> event.setNombreFemmes((int)cell.getNumericCellValue());
// // case 6-> event.setNombreEnfants((int)cell.getNumericCellValue());
// // case 8
// // case 9
// // case 11
// // case 19->event.setConducteur(cell.getStringCellValue());
// // case 20->event.setTitre(cell.getStringCellValue());
// // case 21->event.setDebut(cell.getDateCellValue());
// // case 22->event.setFin(cell.getDateCellValue());

// // default->continue;

// }

// }
// // }
// // } catch (IOException e) {
// // // TODO Auto-generated catch block
// // e.printStackTrace();
// // }
// return eventsFromExcel;
// }
// }