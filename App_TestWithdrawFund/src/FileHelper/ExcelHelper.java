/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHelper;

import callAPIHelper.CallApiHelper;
import checksum.CheckSumInquireCard;
import com.google.gson.Gson;
import dataInquireCard.DataInquireCardFull;
import dataInquireCard.DataPostInquireCard;
import dataInquireCard.DataRowInquireCard;
import dataInquireCard.SaveDataPostInquiredCard;
import encrypt.EncryptHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author CPU01661-local
 */
public class ExcelHelper {

    public SaveDataPostInquiredCard ReadFile(String filename) {
        try {
            File excel = new File(filename);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheet("api_inquireCard");
            Iterator<Row> itr = sheet.iterator();
            SaveDataPostInquiredCard saveData = new SaveDataPostInquiredCard();
            ArrayList<DataRowInquireCard> datas = new ArrayList<DataRowInquireCard>();
            while (itr.hasNext()) {
                DataRowInquireCard dataRow = new DataRowInquireCard();
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING: {
                        System.out.println(cell.getStringCellValue());
                        break;
                    }
                    case Cell.CELL_TYPE_NUMERIC: {
                        Double d = new Double(row.getRowNum());
                        System.out.println(row.getRowNum());
                        dataRow.setId(row.getRowNum());
                        //Xu ly de lay noi dung Test
                        int valueR = (int) cell.getNumericCellValue();
                        CheckSumInquireCard chkSumH = new CheckSumInquireCard();
                        DataPostInquireCard data = new DataPostInquireCard();
                        DataInquireCardFull dataI = new DataInquireCardFull();
                        if (valueR > 0) {
                            while (cellIterator.hasNext()) {
                                cell = cellIterator.next();

                                switch (cell.getColumnIndex()) {
                                    case 4: {                    // Caller
                                        data.setCaller(cell.getStringCellValue());
                                        chkSumH.setCaller(cell.getStringCellValue());
                                        break;
                                    }
                                    case 5: {                    // Tid
                                        switch (cell.getCellType()) {
                                            case Cell.CELL_TYPE_STRING: {
//                                                System.out.println(cell.getStringCellValue());
                                                dataI.setTid(cell.getStringCellValue());
                                                break;
                                            }
                                            case Cell.CELL_TYPE_NUMERIC: {
                                                d = new Double(cell.getNumericCellValue());
                                                dataI.setTid(Integer.toString(d.intValue()));
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                    case 6: {                    // Tn
                                        switch (cell.getCellType()) {
                                            case Cell.CELL_TYPE_STRING: {
//                                                System.out.println(cell.getStringCellValue());
                                                String encrypt = EncryptHelper.AES256(cell.getStringCellValue());
                                                 dataI.setTn(encrypt);
                                                break;
                                            }
                                            case Cell.CELL_TYPE_NUMERIC: {
                                                d = new Double(cell.getNumericCellValue());
                                                String encrypt = EncryptHelper.AES256(d.toString());
                                                dataI.setTn(encrypt);
                                                break;
                                            }
                                        }
                                        
                                        break;
                                    }
                                    case 7: {                    // Amt
                                        switch (cell.getCellType()) {
                                            case Cell.CELL_TYPE_STRING: {
//                                                System.out.println(cell.getStringCellValue());
                                                dataI.setAmt(cell.getStringCellValue());
                                                break;
                                            }
                                            case Cell.CELL_TYPE_NUMERIC: {
                                                d = new Double(cell.getNumericCellValue());
                                                dataI.setAmt(Integer.toString(d.intValue()));
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                    case 8: {                    // Thread
                                        switch (cell.getCellType()) {
                                            case Cell.CELL_TYPE_STRING: {
//                                                System.out.println(cell.getStringCellValue());
                                                String str = cell.getStringCellValue();
                                                dataRow.setThreadNumber(Integer.parseInt(str));
                                                break;
                                            }
                                            case Cell.CELL_TYPE_NUMERIC: {
                                                d = new Double(cell.getNumericCellValue());
                                                dataRow.setThreadNumber(d.intValue());
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                    case 9: {                    // Expect
                                        switch (cell.getCellType()) {
                                            case Cell.CELL_TYPE_STRING: {
//                                                System.out.println(cell.getStringCellValue());
                                                String str = cell.getStringCellValue();
                                                dataRow.setCodeExpect(Integer.parseInt(str));
                                                break;
                                            }
                                            case Cell.CELL_TYPE_NUMERIC: {
                                                d = new Double(cell.getNumericCellValue());
                                                dataRow.setCodeExpect(d.intValue());
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }
//                            Gson gson = new Gson();
//                            System.out.println("dataI: " + gson.toJson(dataI));
                            chkSumH.setData(dataI);
                            data.setData(dataI);
                            data.setChksum(chkSumH.createCheckSum());
//                            System.out.println("data: " + gson.toJson(data));
                            dataRow.setData(data);
                        }
                        if (valueR > 0) {
                            datas.add(dataRow);
                        }
                        break;
                    }

                }

            }
            saveData.setDatas(datas);
            fis.close();
            return saveData;
        } catch (Throwable t) {
            System.out.println("Throwsable: " + t.getMessage());
            return new SaveDataPostInquiredCard();
        }
    }

    public void WriteResultApiInquireCardToFile(String filename, SaveDataPostInquiredCard saveData) {
        try {
            File excel = new File(filename);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheet("api_inquireCard");
            for(DataRowInquireCard dataRow : saveData.getDatas()){
                Row row = sheet.getRow(dataRow.getId());
                Cell cell = row.getCell(10);
                cell.setCellValue(dataRow.getCodeReal());
            }
            fis.close();
            FileOutputStream fos = new FileOutputStream(new File("test.xlsx"));
            book.write(fos);
            fos.close();
        } catch (Exception t) {
            System.out.println("Throwable WriteResultApiInquireCardToFile " + t.getMessage());
        }
    }
}
