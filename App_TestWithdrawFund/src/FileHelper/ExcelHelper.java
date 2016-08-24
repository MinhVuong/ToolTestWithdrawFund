/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHelper;

import callAPIHelper.CallApiHelper;
import callAPIHelper.ResultApi;
import security.CheckSumInquireCard;
import security.RSASHA1Signature;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dataHash.DataHash;
import dataHash.MyDataHash;
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
import thread.ThreadResult;

/**
 *
 * @author CPU01661-local
 */
public class ExcelHelper {
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

    public DataSheet ReadTestCaseFileFromSheet(String fileName, String sheetName, MyDataHash myDataHash) {
        try {
            File excel = new File(fileName);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheet(sheetName);
            Iterator<Row> itr = sheet.iterator();
//            SaveData saveData = new SaveData();
            DataSheet dataSheet = new DataSheet();
            ArrayList<RowDataFromFile> datas = new ArrayList<RowDataFromFile>();
            ArrayList<DataHash> dataHash = new ArrayList<>();
            int colmnDataStart = 0, colmnDataStop = 0, colmnDataReqStart = 0, numReal = 0;
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
                                    
                                    nameDataObject.add(nameObj);
                                    DataHash dataHt = myDataHash.CheckNameDataIsHash(sheetName, nameObj);
                                    if(dataHt != null){
                                        dataHt.setNumColumn(row2.getCell(temp).getColumnIndex());
                                        dataHash.add(dataHt);
                                    }
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
                                    if(!dataHash.isEmpty()){
                                        String valueHash = GetValueStringFromCell(cell1);
                                        for(DataHash dataH : dataHash){
                                            if(dataH.getNumColumn() == cell1.getColumnIndex())
                                                valueHash = EncryptHelper.EncryptData(valueHash, dataH.getAlgorithm(), dataH.getKey(), dataH.getIv());
                                        }
                                        jObj.addProperty(nameDataObject.get(arrIndex), valueHash);
                                    }else{
                                        jObj.addProperty(nameDataObject.get(arrIndex), GetValueStringFromCell(cell1));
                                    }
                                    arrIndex++;
                                } else if (cell1.getColumnIndex() > colmnDataReqStart && cell1.getColumnIndex() <= colmnDataStop) {
                                    if (cell1.getColumnIndex() == colmnDataStop) {
                                        isSecutiry = GetValueStringFromCell(cell1);
                                        dataRow.setNameAlgorithm(isSecutiry);
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

                            String[] arr = isSecutiry.split("-");
                            if (arr[0].equals("chksum")) {
                                String chksum = CheckSumInquireCard.createCheckSum(isSecutiry,callerValue, "a", jObj);
//                                System.out.println("chksum: " + chksum);
                                jObjReq.addProperty("chksum", chksum);
                            } else if (arr[0].equals("signature")) {
                                String signature = RSASHA1Signature.getSignature(isSecutiry, callerValue, "a", jObj);
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
    
     public MyDataHash ReadNameHash(String fileName, String sheetName) {
         try {
            File excel = new File(fileName);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheet(sheetName);
            Iterator<Row> itr = sheet.iterator();
            Row row = itr.next();
            MyDataHash myDataHash = new MyDataHash();
            ArrayList<DataHash> dataHahs = new ArrayList<>();
            while (itr.hasNext()) {
                row = itr.next();
                DataHash dataH = new DataHash();
                Cell cell = row.getCell(0);
                dataH.setNameApi(GetValueStringFromCell(cell));
                cell = row.getCell(1);
                dataH.setNameData(GetValueStringFromCell(cell));
                cell = row.getCell(2);
                dataH.setAlgorithm(GetValueStringFromCell(cell));
                cell = row.getCell(3);
                dataH.setKey(GetValueStringFromCell(cell));
                cell = row.getCell(4);
                if(cell != null)
                    dataH.setIv(GetValueStringFromCell(cell));
                dataHahs.add(dataH);
            }
            myDataHash.setDataHashs(dataHahs);
            return myDataHash;
         }catch (Throwable t) {
            System.out.println("Throwsable: " + t.getMessage());
            return new MyDataHash();
        }
     }

    public DataTestCaseFullList ReadDataFromFileExcel(String fileName) {
        DataTestCaseFullList dataFulls = new DataTestCaseFullList();
        ArrayList<DataTestCaseFull> dataFull = new ArrayList<DataTestCaseFull>();
        SheetsOfFile sheetsOfFile = ReadSheetsOfFile(fileName);
        URLs urls = new URLs();
        MyDataHash myDataHash = new MyDataHash();
        for (String str : sheetsOfFile.getSheets()) {
            if (str.equals("url")) {
                urls = ReadURLS(fileName, "url");
            }else if(str.equals("hash_data")){
                myDataHash = ReadNameHash(fileName, "hash_data");
            } else{
                DataTestCaseFull dataTestCaseFull = new DataTestCaseFull();
                DataSheet dataSheet = ReadTestCaseFileFromSheet(fileName, str, myDataHash);
                Gson gson = new Gson();
                System.out.println("data sheet: " + gson.toJson(dataSheet));
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

    public ResultApi RunTest(DataTestCaseFullList data) {
        try {
            CallApiHelper caller = new CallApiHelper();
            Gson gson = new Gson();
            ResultApi resultApi = new ResultApi();
            ArrayList<ThreadResult> threadAll = new ArrayList<ThreadResult>();
            for (int i = 0; i < data.getDataFulls().size(); i++) {
                DataTestCaseFull dataFull = data.getDataFulls().get(i);
                for (int j = 0; j < dataFull.getDatas().getDatas().size(); j++) {
                    RowDataFromFile dataRow = dataFull.getDatas().getDatas().get(j);
                    if (dataRow.getThread() == 1) {
                        String code = caller.CallAPIForURL(dataFull.getDataURL().getUrl(), dataRow);
//                        String code = "0";
                        dataRow.setResultReal(code);
                        dataFull.getDatas().getDatas().set(j, dataRow);
                    } else {
                        JsonObject jObj = dataRow.getData();
                        ThreadResult threadResult = new ThreadResult();
                        ArrayList<MyThread> threads = new ArrayList<MyThread>();
                        CyclicBarrier gate = new CyclicBarrier(dataRow.getThread());
                        for (int k = 0; k < dataRow.getThread(); k++) {
                            JsonObject jObjNew = CreateJsonOnject(dataFull.getDatas().getNameRequest(), jObj, gson, dataFull.getDatas().getNameDynamic(), k, dataRow.getNameAlgorithm());
                            System.out.println("data new: " + jObjNew.toString());
                            MyThread myThread = new MyThread(jObjNew, dataFull.getDataUrl().getUrl(), gate);
                            gate = myThread.getGate();
                            threads.add(myThread);
                        }
                        ////
                        for (MyThread thread : threads) {
                            thread.start();
                        }
                        int nameT = dataRow.getId() - 3 + 1;
                        String nameofSheet = dataFull.getDataUrl().getNameSheet() + "_" + Integer.toString(nameT);
                        threadResult.setNameSheet(nameofSheet);
                        // Get Result After call API

                        for (MyThread thread : threads) {
                            String code = thread.getCode();
                            while ((code==null) || (code.equals(""))) {
                                Thread.sleep(100);
                                code = thread.getCode();
                            }
                        }
                        threadResult.setThreads(threads);
                        threadAll.add(threadResult);
                    }
                }
                data.getDataFulls().set(i, dataFull);
            }
            resultApi.setData(data);
            resultApi.setThreads(threadAll);
            return resultApi;
        } catch (Exception t) {
            System.out.println("Throwable RunTest " + t.getMessage());
            return new ResultApi();
        }
    }

    private JsonObject CreateJsonObjectData(JsonObject old, ArrayList<NameDynamic> nameDys, int i) {
        JsonObject jObj = new JsonObject();
        for (NameDynamic nameDy : nameDys) {
            if (nameDy.getIsDyn().equals("1")) {
                String value = new String(old.get(nameDy.getName()).getAsString() + i);
                jObj.addProperty(nameDy.getName(), value);
            } else {

                jObj.addProperty(nameDy.getName(), old.get(nameDy.getName()).getAsString());
            }
        }
        return jObj;
    }

    private JsonObject CreateJsonOnject(ArrayList<String> nameRequest, JsonObject jObjOld, Gson gson, ArrayList<NameDynamic> nameDys, int k, String nameAlgro) throws NoSuchAlgorithmException, UnsupportedEncodingException, SignatureException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, Exception {
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
            String checksum = CheckSumInquireCard.createCheckSum(nameAlgro, calleerCode, "a", jObjTemp);
            jObjNew.addProperty("chksum", checksum);
        } else if (security == 2) {
            String signature = RSASHA1Signature.getSignature(nameAlgro, calleerCode, "a", jObjTemp);
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
            for (RowDataFromFile dataRow : data.getDatas().getDatas()) {
                Row row = sheet.getRow(dataRow.getId());
                Cell cell = row.getCell(dataRow.getNumReal());
                if (cell == null) {
                    cell = row.createCell(dataRow.getNumReal());
                }
                if(dataRow.getResultReal() != null)
                    cell.setCellValue(Integer.parseInt(dataRow.getResultReal()));
            }
            fis.close();

            FileOutputStream fos = new FileOutputStream(new File(fileName));
            book.write(fos);
            fos.close();
        } catch (Exception t) {
            System.out.println("Throwable WriteResultTestCaseForSheet " + t.getMessage());
        }
    }

    public void WriteResult(String fileName, ResultApi resultApi) {
        WriteResultTestCase(fileName, resultApi.getData());

        CreateFileResultThread("result_threads.xlsx");       // Create File Result
        for (ThreadResult threadR : resultApi.getThreads()) {
            CreateSheetResult(threadR);
        }
    }

    private void CreateFileResultThread(String fileName) {
        try {
            File excel = new File(fileName);
            if (excel.exists()) {
                excel.delete();
            }
            FileOutputStream fos = new FileOutputStream(new File(fileName));
            XSSFWorkbook book = new XSSFWorkbook();
            XSSFSheet sheet = book.createSheet("sheet1");
            book.write(fos);
            fos.close();
        } catch (Exception t) {
            System.out.println("Throwable CreateFileResultThread " + t.getMessage());
        }
    }

    private void CreateSheetResult(ThreadResult threadR) {
        try {
            File excel = new File("result_threads.xlsx");
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.createSheet(threadR.getNameSheet());
            // Create Tile Tile
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("ID");
            cell = row.createCell(1);
            cell.setCellValue("Code Result");
            cell = row.createCell(2);
            cell.setCellValue("Time (ms)");
            //
            for (int i = 0; i < threadR.getThreads().size(); i++) {
                Row row1 = sheet.createRow(i+1);
                Cell cell1 = row1.createCell(0);
                cell1.setCellValue(i + 1);
                
                cell1 = row1.createCell(1);
                cell1.setCellValue(threadR.getThreads().get(i).getCode());
                
                cell1 = row1.createCell(2);
                cell1.setCellValue(threadR.getThreads().get(i).getTime());
                
            }
            fis.close();
            FileOutputStream fos = new FileOutputStream(new File("result_threads.xlsx"));
            book.write(fos);
            fos.close();
        } catch (Exception t) {
            System.out.println("Throwable CreateSheetResult " + t.getMessage());
        }
    }
}
