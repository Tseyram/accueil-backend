package com.ey.accueilapp.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import com.ey.accueilapp.dtos.CulteDTO;
import com.ey.accueilapp.mappers.EventMapper;
import com.ey.accueilapp.repositories.PhysicalEventRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.ey.accueilapp.dtos.PhysicalEventDTO;
import com.ey.accueilapp.enums.TypeEvent;

public class ExcelUploadService {

    public static boolean isValid(MultipartFile file) {
        System.out.println(file.getContentType());
        return Objects.equals(file.getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<PhysicalEventDTO> getEventDatFromExcelFor2023() {
        List<PhysicalEventDTO> eventsFromExcel = new ArrayList<>();
        String fileLocation = "src\\main\\resources\\static\\STATISTIQUES_ACCUEIL_ICC_METZ.xlsx";
        try (FileInputStream file = new FileInputStream(new File(fileLocation))) {
            try (XSSFWorkbook workbook = new XSSFWorkbook(file)) {
                XSSFSheet sheet = workbook.getSheetAt(0);

                int rowIndex = 0;
                for (Row row : sheet) {
                    if(rowIndex==162) {
                    break;
                    }
                    //System.out.println("row"+rowIndex+"row");
                    if (rowIndex == 0) {
                        rowIndex++;
                        continue;
                    }
                    rowIndex++;
                    Iterator<Cell> cellIterator = row.iterator();
                    int cellIndex = 0;
                    PhysicalEventDTO event = new PhysicalEventDTO();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        System.out.println((cellIndex+"colomn"+cell.getColumnIndex()+"type"+cell.getCellType()+"value"+cell.toString()));
                        if(cell.getColumnIndex()==23){
                            break;
                        }

                        switch (cell.getColumnIndex()) {
                        case 2 -> {if (cell.getCellType()!=CellType.BLANK) {
                            event.setDate(cell.getLocalDateTimeCellValue().toLocalDate());
                            System.out.println("ok"+2);}
                        else
                            continue;}
                        case 3 -> {if (cell.getCellType()!=CellType.BLANK){event.setName(cell.getStringCellValue());
                            System.out.println("ok"+3);
                            if (event.getName().contains("CULTE")) {
                                event=changePhysicalToCulte(event);
                            }}
                        else
                            continue;}
                        case 4 ->{if (cell.getCellType()!=CellType.BLANK) {event.setNombreHommes((int) cell.getNumericCellValue());
                            System.out.println("ok"+4);}
                            else
                                continue;}
                        case 5 -> {if (cell.getCellType()!=CellType.BLANK){event.setNombreFemmes((int) cell.getNumericCellValue());
                            System.out.println("ok"+5);}
                        else
                            continue;}
                        case 6 -> {if (cell.getCellType()!=CellType.BLANK){event.setNombreEnfants((int) cell.getNumericCellValue());
                            System.out.println("ok"+6);}
                            else
                                continue;}
                        case 7 -> {if (cell.getCellType()!=CellType.BLANK){event.setNombreAdultes((int) cell.getNumericCellValue());
                            System.out.println("ok"+7);}
                            else
                                continue;}
                        case 8 -> {if (cell.getCellType()!=CellType.BLANK){event.setNombreParticipants((int) cell.getNumericCellValue());
                            System.out.println("ok"+8);}
                            else
                                continue;}
                        case 9 -> {
                       if (event.getName().contains("CULTE")) {
                        ((CulteDTO) event).setNouveauxHommes((int) cell.getNumericCellValue());
                           System.out.println("ok"+9);
                        } else
                        {continue;}
                            }
                        case 10 -> {
                        if (event.getName().contains("CULTE")) {
                        ((CulteDTO) event).setNouveauxFemmes((int) cell.getNumericCellValue());
                           System.out.println("ok"+10);
                        } else
                        continue;

                        }
                        case 12 -> {event.setConnexions((int) cell.getNumericCellValue());
                            System.out.println("ok"+12);}

                        case 13 -> {
                        if (event.getName().contains("CULTE")) {
                        ((CulteDTO) event).setHommesIntercession((int) cell.getNumericCellValue());
                            System.out.println("ok"+13);
                        } else
                        continue;

                        }
                        case 14 -> {
                        if (event.getName().contains("CULTE")) {
                        ((CulteDTO) event).setFemmesIntercession((int) cell.getNumericCellValue());
                         } else
                        continue;
                            System.out.println("ok"+14);
                         }
                        case 15 -> {
                        if (event.getName().contains("CULTE")) {
                        ((CulteDTO) event).setEnfantsIntercession((int) cell.getNumericCellValue());
                        } else
                        continue;
                            System.out.println("ok"+15);
                        }
                        case 17 -> {
                        if (event.getName().contains("CULTE")) {
                        ((CulteDTO) event).setEnfantsMIJ((int) cell.getNumericCellValue());
                        } else
                        continue;
                            System.out.println("ok"+17);
                        }
                        case 18 -> {
                        if (event.getName().contains("CULTE")) {
                        ((CulteDTO) event).setModeratrice(cell.getStringCellValue());
                        } else
                        continue;
                            System.out.println("ok"+18);
                        }
                        case 19 -> {
                        if (event.getName().contains("CULTE")) {
                        ((CulteDTO) event).setConducteurIntercession(cell.getStringCellValue());
                        } else
                        continue;
                            System.out.println("ok"+19);
                        }

                            case 20 -> {event.setConducteur(cell.getStringCellValue());
                            System.out.println("ok"+20);}
                        case 21 ->{ event.setTitre(cell.getStringCellValue());
                            System.out.println("ok"+21);}

                        case 22 -> {event.setDebut(LocalTime.from(cell.getLocalDateTimeCellValue()));
                            System.out.println("ok"+22);}
                        case 23 -> {event.setFin(LocalTime.from(cell.getLocalDateTimeCellValue()));
                            System.out.println("ok"+23);}


                        default -> {
                            System.out.println("ok");
                        continue;
                        }

                        }

                    }
                    event.setTypeEvent(attributeType(event));
                    eventsFromExcel.add(event);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("1, The file is not a valid excel file");

        }
        return eventsFromExcel;
    }
    public static CulteDTO changePhysicalToCulte(PhysicalEventDTO event){
        CulteDTO culte= new CulteDTO();
        culte.setName(event.getName());
        culte.setDate(event.getDate());
        culte.setDebut(event.getDebut());
        culte.setFin(event.getFin());
        culte.setConducteur(event.getConducteur());
        culte.setId(event.getId());
        culte.setConnexions(event.getConnexions());
        culte.setNombreHommes(event.getNombreHommes());
        culte.setNombreFemmes(event.getNombreFemmes());
        culte.setNombreEnfants(event.getNombreEnfants());
        culte.setTitre(event.getTitre());
        culte.setTypeEvent(event.getTypeEvent());
        return culte;
    }
    private static TypeEvent attributeType(PhysicalEventDTO event) {
        if (event.getName().contains("CULTE")) {
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

    public static void main(String[] args) {
        try {
            System.out.println("first");
            List<PhysicalEventDTO> events = ExcelUploadService.getEventDatFromExcelFor2023();

        } catch (Exception e) {
            throw new IllegalArgumentException("The file is not a valid excel file");
        }

    }

}