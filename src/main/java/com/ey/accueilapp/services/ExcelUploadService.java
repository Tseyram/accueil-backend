package com.ey.accueilapp.services;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.ey.accueilapp.dtos.CulteDTO;
import com.ey.accueilapp.dtos.PhysicalEventDTO;
import com.ey.accueilapp.enums.TypeEvent;

public class ExcelUploadService {
    // MultipartFile file = new MockMultipartFile("stats.xlsx",
    // new FileInputStream(new
    // File("src\\main\\resources\\static\\STATISTIQUES_ACCUEIL_ICC_METZ.xlsx")));

    public static boolean isValid(MultipartFile file) {
        System.out.println(file.getContentType());
        return Objects.equals(file.getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<PhysicalEventDTO> getEventDatFromExcelFor2023(InputStream inputStream) {
        List<PhysicalEventDTO> eventsFromExcel = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheet("ANNEE 2023");

            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                PhysicalEventDTO event = new PhysicalEventDTO();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 2 -> event.setDate(
                                cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                        case 3 -> event.setName(cell.getStringCellValue());
                        case 4 -> event.setNombreHommes((int) cell.getNumericCellValue());
                        case 5 -> event.setNombreFemmes((int) cell.getNumericCellValue());
                        case 6 -> event.setNombreEnfants((int) cell.getNumericCellValue());
                        case 7 -> event.setNombreAdultes((int) cell.getNumericCellValue());
                        case 8 -> event.setNombreParticipants((int) cell.getNumericCellValue());
                        case 9 -> {
                            if (event.getName().contains("culte")) {
                                ((CulteDTO) event).setNouveauxHommes((int) cell.getNumericCellValue());
                            } else
                                continue;
                        }
                        case 10 -> {
                            if (event.getName().contains("culte")) {
                                ((CulteDTO) event).setNouveauxFemmes((int) cell.getNumericCellValue());
                            } else
                                continue;
                        }
                        case 12 -> event.setConnexions((int) cell.getNumericCellValue());
                        case 13 -> {
                            if (event.getName().contains("culte")) {
                                ((CulteDTO) event).setHommesIntercession((int) cell.getNumericCellValue());
                            } else
                                continue;
                        }
                        case 14 -> {
                            if (event.getName().contains("culte")) {
                                ((CulteDTO) event).setFemmesIntercession((int) cell.getNumericCellValue());
                            } else
                                continue;
                        }
                        case 15 -> {
                            if (event.getName().contains("culte")) {
                                ((CulteDTO) event).setEnfantsIntercession((int) cell.getNumericCellValue());
                            } else
                                continue;
                        }
                        case 17 -> {
                            if (event.getName().contains("culte")) {
                                ((CulteDTO) event).setEnfantsMIJ((int) cell.getNumericCellValue());
                            } else
                                continue;
                        }
                        case 18 -> {
                            if (event.getName().contains("culte")) {
                                ((CulteDTO) event).setModeratrice(cell.getStringCellValue());
                            } else
                                continue;
                        }
                        case 19 -> {
                            if (event.getName().contains("culte")) {
                                ((CulteDTO) event).setConducteurIntercession(cell.getStringCellValue());
                            } else
                                continue;
                        }
                        case 20 -> event.setConducteur(cell.getStringCellValue());
                        case 21 -> event.setTitre(cell.getStringCellValue());
                        case 22 -> event.setDebut(cell.getLocalDateTimeCellValue().toLocalTime());
                        case 23 -> event.setFin(cell.getLocalDateTimeCellValue().toLocalTime());

                        default -> {
                            continue;
                        }

                    }
                    cellIndex++;
                }
                event.setTypeEvent(attributeType(event));
                eventsFromExcel.add(event);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return eventsFromExcel;
    }

    private static TypeEvent attributeType(PhysicalEventDTO event) {
        if (event.getName().contains("culte")) {
            return TypeEvent.CULTE;
        } else if (event.getName().contains("TPA")) {
            return TypeEvent.TPA;
        } else if (event.getName().contains("24H")) {
            return TypeEvent.CORPORATE;
        } else if (event.getName().contains("48H")) {
            return TypeEvent.CORPORATE;
        } else if (event.getName().contains("21 JOURS")) {
            return TypeEvent.CORPORATE;
        } else if (event.getName().contains("BIENVENUE")) {
            return TypeEvent.BIENVENUE;
        } else if (event.getName().contains("HOMMES")) {
            return TypeEvent.MHI;
        } else if (event.getName().contains("FEMMES")) {
            return TypeEvent.MFI;
        } else if (event.getName().contains("JEUNES")) {
            return TypeEvent.MJI;
        } else if (event.getName().contains("MIJ")) {
            return TypeEvent.MIJ;
        } else if (event.getName().contains("STAR")) {
            return TypeEvent.STAR;
        } else if (event.getName().contains("BAPTEME")) {
            return TypeEvent.BAPTEME;
        } else if (event.getName().contains("MCEP")) {
            return TypeEvent.MCEP;
        } else if (event.getName().contains("SEMINAIRE")) {
            return TypeEvent.SEMINAIRE;
        } else {
            return TypeEvent.AUTRE;
        }

    }

}