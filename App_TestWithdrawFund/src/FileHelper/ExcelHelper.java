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
import dataRequest.DataInput;
import dataRequest.DataInputLevel2;
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

    public DataSheet ReadTestCaseFileFromSheet(String fileName, String sheetName, MyDataHash myDataHash, String rawData) {
        try {
            File excel = new File(fileName);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheet(sheetName);
            Iterator<Row> itr = sheet.iterator();
            DataSheet dataSheet = new DataSheet();
            ArrayList<RowDataFromFile> datas = new ArrayList<RowDataFromFile>();
            ArrayList<DataHash> dataHash = new ArrayList<>();
            int colmnDataStart = 0, colmnDataStop = 0, numReal = 0;
            ArrayList<NameDynamic> nameDynamic = new ArrayList<NameDynamic>();
            ArrayList<DataInput> listDataInput = new ArrayList<>();
            ArrayList<DataInputLevel2> dataInputLevel2 = new ArrayList<>();
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
                            Row row1 = sheet.getRow(1);
                            Row row2 = sheet.getRow(2);
                            Row row3 = sheet.getRow(3);
                            Row row4 = sheet.getRow(4);
                            Cell cellColmn;
                            Cell cellColmn2;
                            int numColmn = colmnDataStart;
                            while (numColmn <= colmnDataStop) {
                                cellColmn = row1.getCell(numColmn);
                                String temp = GetValueStringFromCell(cellColmn);
                                cellColmn2 = row2.getCell(numColmn);
                                NameDynamic nameDy = CutStrGetNameDynamic(GetValueStringFromCell(cellColmn2));
                                if (nameDy.getIsDyn().equals("1")){      // Check Data is change when run Thread
                                    nameDynamic.add(nameDy);
                                }
                                // Add to list save data api
                                listDataInput.add(new DataInput(temp, nameDy.getName()));
                                DataHash dataHt = myDataHash.CheckNameDataIsHash(sheetName, nameDy.getName());
                                if (dataHt != null) {
                                    dataHt.setNumColumn(numColmn);
                                    dataHash.add(dataHt);
                                }
                                if (temp.equals("Object")) {         // Exist object group datas name
                                    ArrayList<DataInput> listDataIputLevel2 = new ArrayList<>();
                                    cellColmn = row3.getCell(numColmn);
                                    cellColmn2 = row4.getCell(numColmn);
                                    String tempT = GetValueStringFromCell(cellColmn);
                                    if(!tempT.equals("")){
                                        while (!GetValueStringFromCell(cellColmn).equals("")) {
                                            nameDy = CutStrGetNameDynamic(GetValueStringFromCell(cellColmn2));
                                            if (nameDy.getIsDyn().equals("1")){      // Check Data is change when run Thread
                                                nameDynamic.add(nameDy);
                                            }
                                            dataHt = myDataHash.CheckNameDataIsHash(sheetName, nameDy.getName());
                                            if (dataHt != null) {
                                                dataHt.setNumColumn(numColmn);
                                                dataHash.add(dataHt);
                                            }
                                            listDataIputLevel2.add(new DataInput(GetValueStringFromCell(cellColmn), nameDy.getName()));
                                            numColmn++;
                                            cellColmn = row3.getCell(numColmn);
                                            cellColmn2 = row4.getCell(numColmn);
                                        }
                                        numColmn--;
                                        dataInputLevel2.add(new DataInputLevel2(listDataIputLevel2));
                                    }else{
                                        dataInputLevel2.add(new DataInputLevel2(listDataIputLevel2));
                                    }
                                }
                                numColmn++;
                            }
                            Gson gson = new Gson();
                            System.out.println(gson.toJson(listDataInput));
                            System.out.println(gson.toJson(dataHash));
                        }
                        break;
                    }
                    case Cell.CELL_TYPE_NUMERIC: {
//                        System.out.println(cell.getNumericCellValue());
                        if (cell.getNumericCellValue() > 0) {
                            dataRow.setId(row.getRowNum());
                            String isSecutiry = "no";
                            int arrIndex = 0;
                            int arrIndexReq = 0;        // Object con
                            int arrIndexRow = 0;
                            while (cellIterator.hasNext()) {
                                Cell cell1 = cellIterator.next();
                                if ((cell1.getColumnIndex() >= colmnDataStart) && (cell1.getColumnIndex() < colmnDataStop)) {
                                    if (listDataInput.get(arrIndex).getType().equals("Object")) {
                                        JsonObject jObj = new JsonObject();
                                        int i = 0;
                                        int size = dataInputLevel2.get(arrIndexReq).getListDataIputLevel2().size();
                                        while (i < size) {
                                            if (dataInputLevel2.get(arrIndexReq).getListDataIputLevel2().get(i).getType().equals("String")) {
                                                String value = GetValueStringFromCell(cell1);
                                                if (!dataHash.isEmpty()) {
                                                    for (DataHash dataH : dataHash) {
                                                        if (dataH.getNumColumn() == cell1.getColumnIndex()) {
                                                            value = EncryptHelper.EncryptData(value, dataH.getAlgorithm(), dataH.getKey(), dataH.getIv());
                                                        }
                                                    }
                                                }
                                                jObj.addProperty(dataInputLevel2.get(arrIndexReq).getListDataIputLevel2().get(i).getName(), value);
                                            } else if (dataInputLevel2.get(arrIndexReq).getListDataIputLevel2().get(i).getType().equals("Integer")) {
                                                int value = GetValueIntegerFromCell(cell1);
                                                jObj.addProperty(dataInputLevel2.get(arrIndexReq).getListDataIputLevel2().get(i).getName(), value);
                                            } else if (dataInputLevel2.get(arrIndexReq).getListDataIputLevel2().get(i).getType().equals("Object")) {
                                                String value = GetValueStringFromCell(cell1);
                                                Gson gson = new Gson();
                                                JsonObject obj = gson.fromJson(value, JsonObject.class);
                                                jObj.add(dataInputLevel2.get(arrIndexReq).getListDataIputLevel2().get(i).getName(), obj);
                                            }
                                            i++;
                                            if (i < size) {
                                                cell1 = cellIterator.next();
                                            }
                                        }
                                        arrIndexReq++;
                                        jObjReq.add(listDataInput.get(arrIndex).getName(), jObj);
                                    } else if (listDataInput.get(arrIndex).getType().equals("String")) {
                                        String value = GetValueStringFromCell(cell1);
                                        if (!dataHash.isEmpty()) {
                                            for (DataHash dataH : dataHash) {
                                                if (dataH.getNumColumn() == cell1.getColumnIndex()) {
                                                    value = EncryptHelper.EncryptData(value, dataH.getAlgorithm(), dataH.getKey(), dataH.getIv());
                                                }
                                            }
                                        }
                                        jObjReq.addProperty(listDataInput.get(arrIndex).getName(), value);
                                    } else if (listDataInput.get(arrIndex).getType().equals("Integer")) {
                                        int value = GetValueIntegerFromCell(cell1);
                                        jObjReq.addProperty(listDataInput.get(arrIndex).getName(), value);
                                    }
                                    arrIndex++;
                                } else if (cell1.getColumnIndex() == colmnDataStop) {
                                    isSecutiry = GetValueStringFromCell(cell1);
                                    dataRow.setNameAlgorithm(isSecutiry);
                                } else if (cell1.getColumnIndex() > colmnDataStop) {
                                    if (arrIndexRow == 0) {
                                        dataRow.setThread(GetValueIntegerFromCell(cell1));
                                    } else if (arrIndexRow == 1) {
                                        dataRow.setResultExpect(GetValueStringFromCell(cell1));
                                    }
                                    arrIndexRow++;
                                }
                            }
//                            System.out.println("data: " + jObj.toString());
//                            System.out.println("data Req: " + jObjReq.toString());
                            String[] arrR = rawData.split(",");
                            String rawDataNew = "";
                            char a = '"';
                            for (String str : arrR) {
                                if (str.charAt(0) == a) {
                                    String value = str.substring(1, str.length() - 1);
                                    rawDataNew += value;
                                } else {
                                    JsonElement je = jObjReq.get(str);
                                    if (je.isJsonObject()) {
                                        String value = je.toString();
                                        rawDataNew += value;
                                    } else {
                                        String value = je.getAsString();
                                        rawDataNew += value;
                                    }
                                }
                            }
                            String[] arr = isSecutiry.split("-");
                            if (arr[0].equals("chksum")) {
                                String chksum = CheckSumInquireCard.createCheckSum(isSecutiry, rawDataNew);
//                                System.out.println("chksum: " + chksum);
                                jObjReq.addProperty(listDataInput.get(arrIndex).getName(), chksum);
                            } else if (arr[0].equals("signature")) {
                                String signature = RSASHA1Signature.getSignature(isSecutiry, rawDataNew);
//                                System.out.println("signature: " + signature);
                                jObjReq.addProperty(listDataInput.get(arrIndex).getName(), signature);
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
            dataSheet.setListDataInput(listDataInput);
            dataSheet.setDataInputLevel2(dataInputLevel2);
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
        try {
            NameDynamic nameD = new NameDynamic();
            String[] arr = str.split("-");
            nameD.setName(arr[0]);
            nameD.setIsDyn(arr[1]);
            return nameD;
        } catch (Exception ex) {
            return new NameDynamic(str, "");
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

                Cell cell = row.getCell(0);
                if((cell != null) && (!GetValueStringFromCell(cell).equals(""))){
                    dataUrl.setNameSheet(GetValueStringFromCell(cell));
                    cell = row.getCell(1);
                    dataUrl.setUrl(GetValueStringFromCell(cell));
                    cell = row.getCell(2);
                    dataUrl.setAcceptType(GetValueStringFromCell(cell));
                    cell = row.getCell(3);
                    dataUrl.setContentType(GetValueStringFromCell(cell));
                    cell = row.getCell(4);
                    dataUrl.setRawData(GetValueStringFromCell(cell));

                    dataUrls.add(dataUrl);
                }
//                Gson gson = new Gson();
//                System.out.println("url: " + gson.toJson(dataUrl));
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
                if (cell != null) {
                    dataH.setIv(GetValueStringFromCell(cell));
                }
                dataHahs.add(dataH);
            }
            myDataHash.setDataHashs(dataHahs);
            return myDataHash;
        } catch (Throwable t) {
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
            } else if (str.equals("hash_data")) {
                myDataHash = ReadNameHash(fileName, "hash_data");
            } else {
                DataTestCaseFull dataTestCaseFull = new DataTestCaseFull();
                String rawData = GetRawDataFromSheet(urls, str);
                DataSheet dataSheet = ReadTestCaseFileFromSheet(fileName, str, myDataHash, rawData);
//                Gson gson = new Gson();
//                System.out.println("data sheet: " + gson.toJson(dataSheet));
                DataURL dataURL = GetUrlValueFromUrls(urls, str);

                dataTestCaseFull.setDatas(dataSheet);
                dataTestCaseFull.setDataURL(dataURL);

                dataFull.add(dataTestCaseFull);
            }
        }
        dataFulls.setDataFulls(dataFull);
        return dataFulls;
    }

    private String GetRawDataFromSheet(URLs urls, String nameSheet) {
        for (DataURL dataUrl : urls.getUrls()) {
            if (dataUrl.getNameSheet().equals(nameSheet)) {
                return dataUrl.getRawData();
            }
        }
        return "";
    }

    private Object GetValueFromCell(Cell cell, String typeName) {
        if (typeName.equals("String")) {
            return cell.getStringCellValue();
        } else if (typeName.equals("Integer")) {
            Double d = cell.getNumericCellValue();
            return Integer.toString(d.intValue());
        }
        return null;
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

    private DataURL GetUrlValueFromUrls(URLs urls, String sheetName) {
        for (DataURL dataUrl : urls.getUrls()) {
            if (dataUrl.getNameSheet().equals(sheetName)) {
                return dataUrl;
            }
        }
        return new DataURL();
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
                        String code = caller.CallAPIForURL(dataFull.getDataURL(), dataRow);
//                        String code = "0";
                        dataRow.setResultReal(code);
                        dataFull.getDatas().getDatas().set(j, dataRow);
                    } else {
                        JsonObject jObj = dataRow.getData();
                        ThreadResult threadResult = new ThreadResult();
                        ArrayList<MyThread> threads = new ArrayList<MyThread>();
                        CyclicBarrier gate = new CyclicBarrier(dataRow.getThread());
                        for (int k = 0; k < dataRow.getThread(); k++) {
                            JsonObject jObjNew = CreateJsonOnject(dataFull.getDatas().getListDataInput(), dataFull.getDatas().getDataInputLevel2(), jObj, gson, dataFull.getDatas().getNameDynamic(), k, dataRow.getNameAlgorithm(), dataFull.getDataURL().getRawData());
                            System.out.println("data new: " + jObjNew.toString());
                            MyThread myThread = new MyThread(jObjNew, dataFull.getDataUrl().getUrl(), gate, dataFull.getDataURL().getAcceptType(), dataFull.getDataURL().getContentType());
                            gate = myThread.getGate();
                            threads.add(myThread);
                        }
                        ////
                        for (MyThread thread : threads) {
                            thread.start();
                        }
                        int nameT = dataRow.getId() - 5 + 1;
                        String nameofSheet = dataFull.getDataUrl().getNameSheet() + "_" + Integer.toString(nameT);
                        threadResult.setNameSheet(nameofSheet);
                        // Get Result After call API
                        for (MyThread thread : threads) {
                            String code = thread.getCode();
                            while ((code == null) || (code.equals(""))) {
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

    private JsonObject CreateJsonOnject(ArrayList<DataInput> listDataInput, ArrayList<DataInputLevel2> dataInputLevel2, JsonObject jObjOld, Gson gson, ArrayList<NameDynamic> nameDys, int k, String nameAlgro, String rawData) throws NoSuchAlgorithmException, UnsupportedEncodingException, SignatureException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, Exception {
        JsonObject jObjNew = new JsonObject();
        int countObject = 0;
        for (DataInput dataI : listDataInput) {
            JsonElement je = jObjOld.get(dataI.getName());
            if (je != null) {
                if (dataI.getType().equals("String")) {
                    String value = je.getAsString();
                    if (!nameDys.isEmpty()) {
                        for (NameDynamic nameDy : nameDys) {
                            if (dataI.getName().equals(nameDy.getName())) {
                                value = new String(value + k);
                            }
                        }
                    }
                    jObjNew.addProperty(dataI.getName(), value);
                } else if (dataI.getType().equals("Integer")) {
                    int value = je.getAsInt();
                    jObjNew.addProperty(dataI.getName(), value);
                } else if (dataI.getType().equals("Object")) {
                    JsonObject jsonChild = gson.fromJson(je.toString(), JsonObject.class);
                    JsonObject jsonChildNew = new JsonObject();
                    DataInputLevel2 dataIL2 = dataInputLevel2.get(countObject);
                    for (DataInput dataI2 : dataIL2.getListDataIputLevel2()) {
                        if (dataI2.getType().equals("String")) {
                            je = jsonChild.get(dataI2.getName());
                            if (je != null) {
                                String value = je.getAsString();
                                if (!nameDys.isEmpty()) {
                                    for (NameDynamic nameDy : nameDys) {
                                        if (dataI2.getName().equals(nameDy.getName())) {
                                            value = new String(value + k);
                                        }
                                    }
                                }
                                jsonChildNew.addProperty(dataI2.getName(), value);
                            }
                        } else if (dataI2.getType().equals("Integer")) {
                            je = jsonChild.get(dataI2.getName());
                            if (je != null) {
                                int value = je.getAsInt();
                                jsonChildNew.addProperty(dataI2.getName(), value);
                            }
                        } else if (dataI2.getType().equals("Object")) {
                            je = jsonChild.get(dataI2.getName());
                            if (je != null) {
                                JsonObject value = je.getAsJsonObject();
                                jsonChildNew.add(dataI2.getName(), value);
                            }
                        }
                    }
                    jObjNew.add(dataI.getName(), jsonChildNew);
                    countObject++;
                }
            }
        }
        // Raw Data
        String[] arr = rawData.split(",");
        String rawDataNew = "";
        char a = '"';
        for (String str : arr) {
            if (str.charAt(0) == a) {
                String value = str.substring(1, str.length() - 1);
                rawDataNew += value;
            } else {
                JsonElement je = jObjNew.get(str);
                if (je.isJsonObject()) {
                    String value = je.toString();
                    rawDataNew += value;
                } else {
                    String value = je.getAsString();
                    rawDataNew += value;
                }
            }
        }
        String[] arrS = nameAlgro.split("-");
        if (arrS[0].equals("chksum")) {
            String chksum = CheckSumInquireCard.createCheckSum(nameAlgro, rawDataNew);
                                System.out.println("chksum: " + chksum);
            jObjNew.addProperty(listDataInput.get(listDataInput.size()-1).getName(), chksum);
        } else if (arrS[0].equals("signature")) {
            String signature = RSASHA1Signature.getSignature(nameAlgro, rawDataNew);
                                System.out.println("signature: " + signature);
            jObjNew.addProperty(listDataInput.get(listDataInput.size()-1).getName(), signature);
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
                if (dataRow.getResultReal() != null) {
                    cell.setCellValue(dataRow.getResultReal());
                }
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
        System.out.println("Writed result test case!");
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
            cell.setCellValue("Response Time (ms)");
            //
            for (int i = 0; i < threadR.getThreads().size(); i++) {
                Row row1 = sheet.createRow(i + 1);
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
