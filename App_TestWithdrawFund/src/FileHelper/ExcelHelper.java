/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHelper;

import callAPIHelper.CallApiHelper;
import checksum.CheckSumInquireCard;
import checksum.RSASHA1Signature;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dataRequest.DataTestCaseFull;
import dataRequest.DataTestCaseFullList;
import dataRequest.RowDataFromFile;
import dataRequest.SaveData;
import dataRequest.SheetsOfFile;
import dataURL.DataURL;
import dataURL.URLs;
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

//    public SaveDataPostInquiredCard ReadFileAPIInquireCard(String filename) {
//        try {
//            File excel = new File(filename);
//            FileInputStream fis = new FileInputStream(excel);
//            XSSFWorkbook book = new XSSFWorkbook(fis);
//            XSSFSheet sheet = book.getSheet("api_inquireCard");
//            Iterator<Row> itr = sheet.iterator();
//            SaveDataPostInquiredCard saveData = new SaveDataPostInquiredCard();
//            ArrayList<DataRowInquireCard> datas = new ArrayList<DataRowInquireCard>();
//            while (itr.hasNext()) {
//                DataRowInquireCard dataRow = new DataRowInquireCard();
//                Row row = itr.next();
//                Iterator<Cell> cellIterator = row.cellIterator();
//                Cell cell = cellIterator.next();
//                switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING: {
//                        System.out.println(cell.getStringCellValue());
//                        break;
//                    }
//                    case Cell.CELL_TYPE_NUMERIC: {
//                        Double d = new Double(row.getRowNum());
//                        System.out.println(row.getRowNum());
//                        dataRow.setId(row.getRowNum());
//                        //Xu ly de lay noi dung Test
//                        int valueR = (int) cell.getNumericCellValue();
//                        CheckSumInquireCard chkSumH = new CheckSumInquireCard();
//                        DataPostInquireCard data = new DataPostInquireCard();
//                        DataInquireCardFull dataI = new DataInquireCardFull();
//                        if (valueR > 0) {
//                            while (cellIterator.hasNext()) {
//                                cell = cellIterator.next();
//
//                                switch (cell.getColumnIndex()) {
//                                    case 4: {                    // Caller
//                                        data.setCaller(cell.getStringCellValue());
//                                        chkSumH.setCaller(cell.getStringCellValue());
//                                        break;
//                                    }
//                                    case 5: {                    // Tid
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
////                                                System.out.println(cell.getStringCellValue());
//                                                dataI.setTid(cell.getStringCellValue());
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                dataI.setTid(Integer.toString(d.intValue()));
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    }
//                                    case 6: {                    // Tn
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
////                                                System.out.println(cell.getStringCellValue());
//                                                String encrypt = EncryptHelper.AES256(cell.getStringCellValue());
//                                                dataI.setTn(encrypt);
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                String encrypt = EncryptHelper.AES256(d.toString());
//                                                dataI.setTn(encrypt);
//                                                break;
//                                            }
//                                        }
//
//                                        break;
//                                    }
//                                    case 7: {                    // Amt
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
////                                                System.out.println(cell.getStringCellValue());
//                                                dataI.setAmt(cell.getStringCellValue());
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                dataI.setAmt(Integer.toString(d.intValue()));
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    }
//                                    case 8: {                    // Thread
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
////                                                System.out.println(cell.getStringCellValue());
//                                                String str = cell.getStringCellValue();
//                                                dataRow.setThreadNumber(Integer.parseInt(str));
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                dataRow.setThreadNumber(d.intValue());
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    }
//                                    case 9: {                    // Expect
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
////                                                System.out.println(cell.getStringCellValue());
//                                                String str = cell.getStringCellValue();
//                                                dataRow.setCodeExpect(Integer.parseInt(str));
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                dataRow.setCodeExpect(d.intValue());
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    }
//                                }
//                            }
////                            Gson gson = new Gson();
////                            System.out.println("dataI: " + gson.toJson(dataI));
//                            chkSumH.setData(dataI);
//                            data.setData(dataI);
//                            data.setChksum(chkSumH.createCheckSum());
////                            System.out.println("data: " + gson.toJson(data));
//                            dataRow.setData(data);
//                        }
//                        if (valueR > 0) {
//                            datas.add(dataRow);
//                        }
//                        break;
//                    }
//
//                }
//
//            }
//            saveData.setDatas(datas);
//            fis.close();
//            return saveData;
//        } catch (Throwable t) {
//            System.out.println("Throwsable: " + t.getMessage());
//            return new SaveDataPostInquiredCard();
//        }
//    }
//
//    public void WriteResultApiInquireCardToFile(String filename, SaveDataPostInquiredCard saveData) {
//        try {
//            File excel = new File(filename);
//            FileInputStream fis = new FileInputStream(excel);
//            XSSFWorkbook book = new XSSFWorkbook(fis);
//            XSSFSheet sheet = book.getSheet("api_inquireCard");
//            for (DataRowInquireCard dataRow : saveData.getDatas()) {
//                Row row = sheet.getRow(dataRow.getId());
//                Cell cell = row.getCell(10);
//                cell.setCellValue(dataRow.getCodeReal());
//            }
//            fis.close();
//            FileOutputStream fos = new FileOutputStream(new File("test.xlsx"));
//            book.write(fos);
//            fos.close();
//        } catch (Exception t) {
//            System.out.println("Throwable WriteResultApiInquireCardToFile " + t.getMessage());
//        }
//    }
//
//    public SaveDataWithdrawFunds ReadFileAPIWithdrawCard(String filename) {
//        try {
//            File excel = new File(filename);
//            FileInputStream fis = new FileInputStream(excel);
//            XSSFWorkbook book = new XSSFWorkbook(fis);
//            XSSFSheet sheet = book.getSheet("api_withdraw");
//            Iterator<Row> itr = sheet.iterator();
//            SaveDataWithdrawFunds saveData = new SaveDataWithdrawFunds();
//            ArrayList<DataRowWithdrawFunds> datas = new ArrayList<DataRowWithdrawFunds>();
//            while (itr.hasNext()) {
//                Row row = itr.next();
//                Iterator<Cell> cellIterator = row.cellIterator();
//                Cell cell = cellIterator.next();
//                switch (cell.getCellType()) {
//                    case Cell.CELL_TYPE_STRING: {
//                        System.out.println(cell.getStringCellValue());
//                        break;
//                    }
//                    case Cell.CELL_TYPE_NUMERIC: {
//                        Double d = new Double(row.getRowNum());
////                        System.out.println(row.getRowNum());
////                        dataRow.setId(row.getRowNum());
//                        //Xu ly de lay noi dung Test
//                        int valueR = (int) cell.getNumericCellValue();
////                        DataPostInquireCard data = new DataPostInquireCard();
//                        if (valueR > 0) {
//                            
//                            DataRowWithdrawFunds dataRow = new DataRowWithdrawFunds();
//                            DataPostInquireCard dataPostInquireCard = new DataPostInquireCard();
//                            CheckSumInquireCard chkSum = new CheckSumInquireCard();
//                            DataInquireCardFull dataInquireCard = new DataInquireCardFull();
//                            DataPostWithdrawFunds dataPostWithdrawFunds = new DataPostWithdrawFunds();
//                            RSASHA1Signature sHASignature = new RSASHA1Signature();
//                            DataWithdrawFundsFull dataWithdrawFunds = new DataWithdrawFundsFull();
//
//                            dataRow.setId(row.getRowNum());
//                            while (cellIterator.hasNext()) {
//                                cell = cellIterator.next();
//
//                                switch (cell.getColumnIndex()) {
//                                    case 4: {                    // Caller
//                                        dataPostInquireCard.setCaller(cell.getStringCellValue());
//                                        chkSum.setCaller(cell.getStringCellValue());
//                                        dataPostWithdrawFunds.setCaller(cell.getStringCellValue());
//                                        sHASignature.setCaller(cell.getStringCellValue());
//                                        break;
//                                    }
//                                    case 5: {                    // Tid
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
//                                                dataInquireCard.setTid(cell.getStringCellValue());
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                dataInquireCard.setTid(Integer.toString(d.intValue()));
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    }
//                                    case 6: {                    // Tn
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
//                                                String encrypt = EncryptHelper.AES256(cell.getStringCellValue());
//                                                dataInquireCard.setTn(encrypt);
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                String encrypt = EncryptHelper.AES256(d.toString());
//                                                dataInquireCard.setTn(encrypt);
//                                                break;
//                                            }
//                                        }
//
//                                        break;
//                                    }
//                                    case 7: {                    // Amt
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
//                                                dataInquireCard.setAmt(cell.getStringCellValue());
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                dataInquireCard.setAmt(Integer.toString(d.intValue()));
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    }
//                                    case 8: {                    // Tid
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
//                                                dataWithdrawFunds.setTid(cell.getStringCellValue());
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                dataWithdrawFunds.setTid(Integer.toString(d.intValue()));
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    }
//                                    case 9: {                    // Amt
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
//                                                dataWithdrawFunds.setAmt(cell.getStringCellValue());
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                dataWithdrawFunds.setAmt(Integer.toString(d.intValue()));
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    }
//                                    case 10: {                    // Thread
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
//                                                dataRow.setThreadNumber(Integer.parseInt(cell.getStringCellValue()));
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                dataRow.setThreadNumber(d.intValue());
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    }
//                                    case 11: {                    // Expect
//                                        switch (cell.getCellType()) {
//                                            case Cell.CELL_TYPE_STRING: {
////                                                System.out.println(cell.getStringCellValue());
//                                                String str = cell.getStringCellValue();
//                                                dataRow.setCodeExpect(Integer.parseInt(str));
//                                                break;
//                                            }
//                                            case Cell.CELL_TYPE_NUMERIC: {
//                                                d = new Double(cell.getNumericCellValue());
//                                                dataRow.setCodeExpect(d.intValue());
//                                                break;
//                                            }
//                                        }
//                                        break;
//                                    }
//                                }
//                            }
//                            Gson gson = new Gson();
////                            System.out.println("dataI: " + gson.toJson(dataI));
//
//                            dataPostInquireCard.setData(dataInquireCard);
//                            chkSum.setData(dataInquireCard);
//                            dataPostInquireCard.setChksum(chkSum.createCheckSum());
//                            dataRow.setDataPostInquireCard(dataPostInquireCard);
//
//                            dataPostWithdrawFunds.setData(dataWithdrawFunds);
//                            sHASignature.setData(dataWithdrawFunds);
//                            dataPostWithdrawFunds.setSignature(sHASignature.getSignature());
//                            dataRow.setDataWithdrawFunds(dataPostWithdrawFunds);
//                            datas.add(dataRow);
//
//                            System.out.println("Data InquireCard: " + gson.toJson(dataPostInquireCard));
//                            System.out.println("Data WithdrawFunds: " + gson.toJson(dataPostWithdrawFunds));
//
//                        }
//                        break;
//                    }
//
//                }
//
//            }
//            saveData.setDatas(datas);
//            fis.close();
//            return saveData;
//        } catch (Throwable t) {
//            System.out.println("Throwsable: " + t.getMessage());
//            return new SaveDataWithdrawFunds();
//        }
//    }
//
//    public void WriteResultApiWithdrawFunds(String filename, SaveDataWithdrawFunds saveData) {
//        try {
//            File excel = new File(filename);
//            FileInputStream fis = new FileInputStream(excel);
//            XSSFWorkbook book = new XSSFWorkbook(fis);
//            XSSFSheet sheet = book.getSheet("api_withdraw");
//            for (DataRowWithdrawFunds dataRow : saveData.getDatas()) {
//                Row row = sheet.getRow(dataRow.getId());
//                Cell cell = row.getCell(12);
//                cell.setCellValue(dataRow.getCodeReal());
//            }
//            fis.close();
//            FileOutputStream fos = new FileOutputStream(new File("test.xlsx"));
//            book.write(fos);
//            fos.close();
//        } catch (Exception t) {
//            System.out.println("Throwable WriteResultApiWithdrawFunds " + t.getMessage());
//        }
//    }
    //// Start again
    public SheetsOfFile ReadSheetsOfFile(String fileName) {
        try {
            SheetsOfFile sheetsOfFile = new SheetsOfFile();
            ArrayList<String> sheets = new ArrayList<String>();
            File excel = new File(fileName);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            int size = book.getNumberOfSheets();
            if (size > 1) {

                for (int i = 0; i < size; i++) {
                    XSSFSheet sheet = book.getSheetAt(i);
                    String str = sheet.getSheetName();
                    sheets.add(str);
                }
            }
            sheetsOfFile.setSheets(sheets);
            return sheetsOfFile;
        } catch (Throwable t) {
            System.out.println("Throwsable: " + t.getMessage());
            return new SheetsOfFile();
        }
    }

    public SaveData ReadTestCaseFileFromSheet(String fileName, String sheetName) {
        try {
            File excel = new File(fileName);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheet(sheetName);
            Iterator<Row> itr = sheet.iterator();
            SaveData saveData = new SaveData();
            ArrayList<RowDataFromFile> datas = new ArrayList<RowDataFromFile>();
            int colmnDataStart = 0, colmnDataStop = 0, colmnDataReqStart = 0;
            ArrayList<String> nameDataObject = new ArrayList<String>();
            ArrayList<String> nameDataRequest = new ArrayList<String>();
            JsonObject jObjReq = new JsonObject();
            while (itr.hasNext()) {
                RowDataFromFile dataRow = new RowDataFromFile();

                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING: {
                        String str = cell.getStringCellValue();
                        if (str.equals("STT")) {
                            while (cellIterator.hasNext()) {
                                Cell cell1 = cellIterator.next();
                                switch (cell1.getCellType()) {
                                    case Cell.CELL_TYPE_STRING: {
//                                        System.out.println(cell1.getStringCellValue());
                                        if (cell1.getStringCellValue().equals("Data Request")) {
                                            colmnDataStart = cell1.getColumnIndex();
                                        }
                                        if (cell1.getStringCellValue().equals("Threads")) {
                                            colmnDataStop = cell1.getColumnIndex() - 1;
                                        }
                                        break;
                                    }
                                    case Cell.CELL_TYPE_NUMERIC: {
                                        System.out.println(cell1.getNumericCellValue());
                                        break;
                                    }
                                }
                            }
//                            System.out.println("Data Colmn Start: " + colmnDataStart);
//                            System.out.println("Data Colmn Stop: " + colmnDataStop);
                            Row row1 = sheet.getRow(1);
                            Row row2 = sheet.getRow(2);
                            String caller = row1.getCell(colmnDataStart).getStringCellValue();
                            nameDataRequest.add(caller);
//                            System.out.println("Caller: " + caller);
                            int temp = 5;
//                            System.out.println("temp: " + temp);
                            while (temp <= colmnDataStop) {
                                String nameObj = row2.getCell(temp).getStringCellValue();
//                                System.out.println("NameObj: " + nameObj);
                                if (nameObj.equals("")) {
                                    colmnDataReqStart = temp - 1;
                                    while (temp <= colmnDataStop) {
//                                        colmnDataReqStart = temp-1;
//                                        System.out.println("ID Start: " + colmnDataReqStart);
                                        nameDataRequest.add(row1.getCell(temp).getStringCellValue());
                                        temp++;
                                    }
                                } else {
                                    nameDataObject.add(nameObj);
                                }
                                temp++;
                            }
//                            System.out.println("data name: " + nameDataObject.toString());
                        }
                        break;
                    }
                    case Cell.CELL_TYPE_NUMERIC: {
//                        System.out.println(cell.getNumericCellValue());
                        if (cell.getNumericCellValue() > 0) {
                            Double di = cell.getNumericCellValue();
                            dataRow.setId(di.intValue());
                            JsonObject jObj = new JsonObject();
                            int isChkSumOrSignature = 0;
                            int arrIndex = 0;
                            int arrIndexReq = 1;
                            int arrIndexRow = 0;
                            while (cellIterator.hasNext()) {
                                Cell cell1 = cellIterator.next();
                                if (cell1.getColumnIndex() == colmnDataStart) {
                                    jObjReq.addProperty(nameDataRequest.get(0), GetValueStringFromCell(cell1));
                                } else if (cell1.getColumnIndex() > colmnDataStart && cell1.getColumnIndex() <= colmnDataReqStart) {
                                    jObj.addProperty(nameDataObject.get(arrIndex), GetValueStringFromCell(cell1));
                                    arrIndex++;
                                } else if (cell1.getColumnIndex() > colmnDataReqStart && cell1.getColumnIndex() <= colmnDataStop) {
                                    if (cell1.getColumnIndex() == colmnDataStop) {
                                        isChkSumOrSignature = GetValueIntegerFromCell(cell1);
                                    } else {
                                        jObjReq.addProperty(nameDataRequest.get(arrIndexReq), GetValueStringFromCell(cell1));
                                    }
                                    arrIndexReq++;
                                } else if (cell1.getColumnIndex() > colmnDataStop) {
                                    if (arrIndexRow == 0) {
                                        dataRow.setThread(GetValueIntegerFromCell(cell1));
                                    } else {
                                        dataRow.setResultExpect(GetValueStringFromCell(cell1));
                                    }
                                    arrIndexRow++;
                                }
                            }
                            jObjReq.add("data", jObj);
//                            System.out.println("data: " + jObj.toString());

                            if (isChkSumOrSignature == 1) {
                                jObjReq.addProperty("chksum", "check sum");
                            } else if (isChkSumOrSignature == 2) {
                                jObjReq.addProperty("signature", "signature");
                            }
                            System.out.println("data Request: " + jObjReq.toString());
                            dataRow.setData(jObjReq);
                            Gson gson = new Gson();
                            System.out.println("data row: " + gson.toJson(dataRow));
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
            return new SaveData();
        }
    }

    public URLs ReadURLS(String fileName, String sheetName) {
        try {
            File excel = new File(fileName);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheet(sheetName);
            Iterator<Row> itr = sheet.iterator();
            URLs urls = new URLs();
            ArrayList<DataURL> dataUrls = new ArrayList<DataURL>();
            Row row = itr.next();
            while (itr.hasNext()) {
                row = itr.next();
                DataURL dataUrl = new DataURL();

                Cell cell_N = row.getCell(0);
                dataUrl.setNameSheet(GetValueStringFromCell(cell_N));
                Cell cell_U = row.getCell(1);
                dataUrl.setUrl(GetValueStringFromCell(cell_U));

                dataUrls.add(dataUrl);
            }
            urls.setUrls(dataUrls);
            return urls;
        } catch (Throwable t) {
            System.out.println("Throwsable: " + t.getMessage());
            return new URLs();
        }
    }

    public DataTestCaseFullList ReadDataFromFileExcel(String fileName) {
        DataTestCaseFullList dataFulls = new DataTestCaseFullList();
        ArrayList<DataTestCaseFull> dataFull = new ArrayList<DataTestCaseFull>();
        SheetsOfFile sheetsOfFile = ReadSheetsOfFile(fileName);
        URLs urls = new URLs();
        for (String str : sheetsOfFile.getSheets()) {
            if (str.equals("url")) {
                urls = ReadURLS(fileName, "url");
            } else {
                DataTestCaseFull dataTestCaseFull = new DataTestCaseFull();
                SaveData saveData = ReadTestCaseFileFromSheet(fileName, str);
                String urlValue = GetUrlValueFromUrls(urls, str);

                dataTestCaseFull.setDatas(saveData.getDatas());
                dataTestCaseFull.setUrl(urlValue);

                dataFull.add(dataTestCaseFull);
            }
        }
        dataFulls.setDataFulls(dataFull);
        return dataFulls;
    }

    private String GetValueStringFromCell(Cell cell) {
        String str = "";
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING: {
                str = cell.getStringCellValue();
                break;
            }
            case Cell.CELL_TYPE_NUMERIC: {
                Double d = cell.getNumericCellValue();
                str = Integer.toString(d.intValue());
                break;
            }
        }
        return str;
    }

    private int GetValueIntegerFromCell(Cell cell) {
        int result = -1;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING: {
                result = Integer.parseInt(cell.getStringCellValue());
                break;
            }
            case Cell.CELL_TYPE_NUMERIC: {
                Double d = cell.getNumericCellValue();
                result = d.intValue();
                break;
            }
        }
        return result;
    }

    private String GetUrlValueFromUrls(URLs urls, String sheetName) {
        String str = "";
        for (DataURL dataUrl : urls.getUrls()) {
            if (dataUrl.getNameSheet().equals(sheetName)) {
                return dataUrl.getUrl();
            }
        }
        return str;
    }
}
