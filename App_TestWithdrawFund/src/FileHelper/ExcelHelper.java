/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHelper;

import callAPIHelper.CallApiHelper;
import security.CheckSumInquireCard;
import security.RSASHA1Signature;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dataRequest.DataSheet;
import dataRequest.DataTestCaseFull;
import dataRequest.DataTestCaseFullList;
import dataRequest.NameDynamic;
import dataRequest.RowDataFromFile;
import dataRequest.SaveData;
import dataRequest.SheetsOfFile;
import dataURL.DataURL;
import dataURL.URLs;
import encrypt.EncryptHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import thread.MyThread;

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

    public DataSheet ReadTestCaseFileFromSheet(String fileName, String sheetName) {
        try {
            File excel = new File(fileName);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheet(sheetName);
            Iterator<Row> itr = sheet.iterator();
//            SaveData saveData = new SaveData();
            DataSheet dataSheet = new DataSheet();
            ArrayList<RowDataFromFile> datas = new ArrayList<RowDataFromFile>();
            int colmnDataStart = 0, colmnDataStop = 0, colmnDataReqStart = 0, numReal = 0;
            int colmTn = 0;
            ArrayList<String> nameDataObject = new ArrayList<String>();
            ArrayList<String> nameDataRequest = new ArrayList<String>();
            ArrayList<NameDynamic> nameDynamic = new ArrayList<NameDynamic>();
            while (itr.hasNext()) {
                RowDataFromFile dataRow = new RowDataFromFile();
                JsonObject jObjReq = new JsonObject();
                String caller = "";

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
                                        if (cell1.getStringCellValue().equals("Result Real")) {
//                                            System.out.println("Colmn Reail: " + cell1.getColumnIndex());
                                            numReal = cell1.getColumnIndex();
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
                            NameDynamic nameD = CutStrGetNameDynamic(row1.getCell(colmnDataStart).getStringCellValue());
                            caller = nameD.getName();
                            nameDataRequest.add(caller);

//                            System.out.println("Caller: " + caller);
                            int temp = colmnDataStart + 1;
//                            System.out.println("temp: " + temp);
                            while (temp <= colmnDataStop) {

                                if (row2.getCell(temp).getStringCellValue().equals("")) {
                                    colmnDataReqStart = temp - 1;
                                    while (temp <= colmnDataStop) {
//                                        colmnDataReqStart = temp-1;
//                                        System.out.println("ID Start: " + colmnDataReqStart);
                                        nameDataRequest.add(row1.getCell(temp).getStringCellValue());
                                        temp++;
                                    }
                                } else {
                                    NameDynamic nameDy = CutStrGetNameDynamic(row2.getCell(temp).getStringCellValue());
                                    String nameObj = nameDy.getName();
                                    nameDynamic.add(nameDy);
                                    //                                System.out.println("NameObj: " + nameObj);
                                    if (nameObj.equals("tn")) {
                                        colmTn = temp;
                                    }
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
                            dataRow.setId(row.getRowNum());
                            JsonObject jObj = new JsonObject();
                            String isSecutiry = "no";
                            int arrIndex = 0;
                            int arrIndexReq = 1;
                            int arrIndexRow = 0;
                            String callerValue = "";
                            while (cellIterator.hasNext()) {
                                Cell cell1 = cellIterator.next();
                                if (cell1.getColumnIndex() == colmnDataStart) {
                                    jObjReq.addProperty(nameDataRequest.get(0), GetValueStringFromCell(cell1));
                                    callerValue = GetValueStringFromCell(cell1);
                                } else if (cell1.getColumnIndex() > colmnDataStart && cell1.getColumnIndex() <= colmnDataReqStart) {
                                    if (colmTn == cell1.getColumnIndex()) {
                                        String encrypt = EncryptHelper.AES256(GetValueStringFromCell(cell1));
                                        jObj.addProperty(nameDataObject.get(arrIndex), encrypt);
                                    } else {
                                        jObj.addProperty(nameDataObject.get(arrIndex), GetValueStringFromCell(cell1));
                                    }
                                    arrIndex++;
                                } else if (cell1.getColumnIndex() > colmnDataReqStart && cell1.getColumnIndex() <= colmnDataStop) {
                                    if (cell1.getColumnIndex() == colmnDataStop) {
                                        isSecutiry = GetValueStringFromCell(cell1);
                                    } else {
                                        jObjReq.addProperty(nameDataRequest.get(arrIndexReq), GetValueStringFromCell(cell1));
                                    }
                                    arrIndexReq++;
                                } else if (cell1.getColumnIndex() > colmnDataStop) {
                                    if (arrIndexRow == 0) {
                                        dataRow.setThread(GetValueIntegerFromCell(cell1));
                                    } else if (arrIndexRow == 1) {
//                                        System.out.println("code: " + GetValueStringFromCell(cell1));
                                        dataRow.setResultExpect(GetValueStringFromCell(cell1));
                                    }
                                    arrIndexRow++;
                                }
                            }
                            jObjReq.add("data", jObj);
//                            System.out.println("data: " + jObj.toString());

                            if (isSecutiry.equals("chksum")) {
                                String chksum = CheckSumInquireCard.createCheckSum(callerValue, "a", jObj);
//                                System.out.println("chksum: " + chksum);
                                jObjReq.addProperty("chksum", chksum);
                            } else if (isSecutiry.equals("signature")) {
                                String signature = RSASHA1Signature.getSignature(callerValue, "a", jObj);
//                                System.out.println("signature: " + signature);
                                jObjReq.addProperty("signature", signature);
                            }
//                            System.out.println("data Request: " + jObjReq.toString());
                            dataRow.setData(jObjReq);
                            dataRow.setNumReal(numReal);
                            Gson gson = new Gson();
                            System.out.println("data row: " + gson.toJson(dataRow));

                            datas.add(dataRow);

                        }
                        break;
                    }
                }
            }
            dataSheet.setDatas(datas);
            dataSheet.setNameDynamic(nameDynamic);
            dataSheet.setNameRequest(nameDataRequest);
            Gson gson = new Gson();
//            System.out.println("save data: " + gson.toJson(datas));
            fis.close();
            return dataSheet;
        } catch (Throwable t) {
            System.out.println("Throwsable: " + t.getMessage());
            return new DataSheet();
        }
    }

    private NameDynamic CutStrGetNameDynamic(String str) {
        NameDynamic nameD = new NameDynamic();
        String[] arr = str.split("-");
        nameD.setName(arr[0]);
        nameD.setIsDyn(arr[1]);
        return nameD;
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
                DataSheet dataSheet = ReadTestCaseFileFromSheet(fileName, str);

                String urlValue = GetUrlValueFromUrls(urls, str);

                dataTestCaseFull.setDatas(dataSheet);
                dataTestCaseFull.setDataURL(new DataURL(str, urlValue));

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

    public DataTestCaseFullList RunTest(DataTestCaseFullList data) {
        try {
            CallApiHelper caller = new CallApiHelper();
            Gson gson = new Gson();
            for (int i = 0; i < data.getDataFulls().size(); i++) {
                DataTestCaseFull dataFull = data.getDataFulls().get(i);
                for (int j = 0; j < dataFull.getDatas().getDatas().size(); j++) {
                    RowDataFromFile dataRow = dataFull.getDatas().getDatas().get(j);
                    if (dataRow.getThread() == 1) {
                        String code = caller.CallAPIForURL(dataFull.getDataURL().getUrl(), dataRow);
//                        String code = "0";
                        dataRow.setResultReal(code);
                        dataFull.getDatas().getDatas().set(i, dataRow);
                    } else {
                        JsonObject jObj = dataRow.getData();
                        ArrayList<MyThread> threads = new ArrayList<MyThread>();
                        CyclicBarrier gate = new CyclicBarrier(dataRow.getThread());
                        for (int k = 0; k < dataRow.getThread(); k++) {
                            JsonObject jObjNew =  CreateJsonOnject(dataFull.getDatas().getNameRequest(), jObj, gson, dataFull.getDatas().getNameDynamic(), k);
                            System.out.println("data new: " + jObjNew.toString());
                            MyThread myThread = new MyThread(jObjNew, dataFull.getDataUrl().getUrl(), gate);
                            gate = myThread.getGate();
                            threads.add(myThread);
                        }
                        
                        
                        ////
                        for (MyThread thread : threads) {
                            thread.start();
                        }
//                        gate.await();
                    }
                }
                data.getDataFulls().set(i, dataFull);
            }
            return data;
        } catch (Exception t) {
            System.out.println("Throwable RunTest " + t.getMessage());
            return data;
        }
    }

    private JsonObject CreateJsonObjectData(JsonObject old, ArrayList<NameDynamic> nameDys, int i) {
        JsonObject jObj = new JsonObject();
        for (NameDynamic nameDy : nameDys) {
            if (nameDy.getIsDyn().equals("1")) {
                String value = old.get(nameDy.getName()).toString();
                Long l = System.currentTimeMillis() + i;
                value = Long.toString(l);
                jObj.addProperty(nameDy.getName(), value);
            } else {

                jObj.addProperty(nameDy.getName(), old.get(nameDy.getName()).getAsString());
            }
        }
        return jObj;
    }

    private JsonObject CreateJsonOnject(ArrayList<String> nameRequest, JsonObject jObjOld, Gson gson, ArrayList<NameDynamic> nameDys, int k) throws NoSuchAlgorithmException, UnsupportedEncodingException, SignatureException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, Exception {
        JsonObject jObjNew = new JsonObject();
        int security = 0;
        JsonObject jObjTemp = new JsonObject();
        String calleerCode = "";

        JsonElement je = jObjOld.get("data");
        JsonObject dataChksum = gson.fromJson(je.toString(), JsonObject.class);
        jObjTemp = CreateJsonObjectData(dataChksum, nameDys, k);
        jObjNew.add("data", jObjTemp);
        for (String str : nameRequest) {

            if (str.equals("security")) {
                JsonElement je2 = jObjOld.get("signature");
                if (je2 == null) {
                    je2 = jObjOld.get("chksum");
                    security = 1;
                } else {
                    security = 2;
                }
            } else if (str.equals("caller")) {
                calleerCode = jObjOld.get(str).getAsString();
                jObjNew.addProperty(str, jObjOld.get(str).getAsString());
            } else {
                jObjNew.addProperty(str, jObjOld.get(str).getAsString());
            }
        }
        if (security == 1) {
            String checksum = CheckSumInquireCard.createCheckSum(calleerCode, "a", jObjTemp);
            jObjNew.addProperty("chksum", checksum);
        } else if (security == 2) {
            String signature = RSASHA1Signature.getSignature(calleerCode, "a", jObjTemp);
            jObjNew.addProperty("signature", signature);
        }
        return jObjNew;
    }

    public void WriteResultTestCase(String fileName, DataTestCaseFullList data) {
        try {
            for (DataTestCaseFull dataFull : data.getDataFulls()) {
                WriteResultTestCaseForSheet(fileName, dataFull.getDataURL().getNameSheet(), dataFull);
            }
        } catch (Exception t) {
            System.out.println("Throwable WriteResultTestCase " + t.getMessage());
        }
    }

    private void WriteResultTestCaseForSheet(String fileName, String sheetName, DataTestCaseFull data) {
        try {
            File excel = new File(fileName);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheet(sheetName);
//            for (RowDataFromFile dataRow : data.getDatas()) {
//                Row row = sheet.getRow(dataRow.getId());
//                Cell cell = row.getCell(dataRow.getNumReal());
//                if (cell == null) {
//                    cell = row.createCell(dataRow.getNumReal());
//                }
//
//                cell.setCellValue(Integer.parseInt(dataRow.getResultReal()));
//            }
            fis.close();

            FileOutputStream fos = new FileOutputStream(new File(fileName));
            book.write(fos);
            fos.close();
        } catch (Exception t) {
            System.out.println("Throwable WriteResultTestCaseForSheet " + t.getMessage());
        }
    }
}
