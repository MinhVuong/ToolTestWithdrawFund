/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package callAPIHelper;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author CPU01661-local
 */
public class CallApiHelper {
    Gson gson = new Gson();
    
//    private DataResponseInquireCard CallApiInquireCard(DataPostInquireCard data) throws UnsupportedEncodingException, IOException{
//        String POST_URL = "http://10.30.17.21:8076/inquireCard";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(POST_URL);
//        httpPost.setHeader("Accept", "text/plain");
//        httpPost.setHeader("Content-type", "text/plain");
//        httpPost.setEntity(new StringEntity(gson.toJson(data)));
//        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
//        if (httpResponse.getStatusLine().getStatusCode() == 200) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = reader.readLine()) != null) {
//                response.append(inputLine);
//            }
//            reader.close();
//            DataResponseInquireCard dataRes = new DataResponseInquireCard();
//            dataRes = gson.fromJson(response.toString(), DataResponseInquireCard.class);
//             
//            System.out.println("code: " + dataRes.getCode());
////            System.out.println("API InquireCard TID_True: Content: " + response.toString());
//            return dataRes;
//        } else {
//            System.out.println("API Fail status code = " + httpResponse.getStatusLine().getStatusCode());
//            return new DataResponseInquireCard();
//        }
//    }
//    
//    public void RunTestApiInquireCard(SaveDataPostInquiredCard saveData) throws IOException{
//        ArrayList<DataRowInquireCard> datas = saveData.getDatas();
//        for(int i=0; i<datas.size(); i++){
//            DataRowInquireCard dataRow = datas.get(i);
//            if(dataRow.getThreadNumber() == 1){
//                DataResponseInquireCard dataRes = CallApiInquireCard(dataRow.getData());
//                dataRow.setCodeReal(dataRes.getCode());
//                datas.set(i, dataRow);
//            }else{
//            
//            }
//        }
//    }
//    
//    private DataResponseWithdrawFunds CallApiWithdrawFunds(DataPostInquireCard dataInquireCard, DataPostWithdrawFunds dataWithdrawFunds) throws IOException{
//        DataResponseInquireCard respInquireCard = CallApiInquireCard(dataInquireCard);
//        String POST_URL = "http://10.30.17.21:8076/withdrawFunds";
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(POST_URL);
//        httpPost.setHeader("Accept", "text/plain");
//        httpPost.setHeader("Content-type", "text/plain");
//        httpPost.setEntity(new StringEntity(gson.toJson(dataWithdrawFunds)));
//        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
//        if (httpResponse.getStatusLine().getStatusCode() == 200) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
//            String inputLine;
//            StringBuffer response = new StringBuffer();
//            while ((inputLine = reader.readLine()) != null) {
//                response.append(inputLine);
//            }
//            reader.close();
//            DataResponseWithdrawFunds dataRes = new DataResponseWithdrawFunds();
//            dataRes = gson.fromJson(response.toString(), DataResponseWithdrawFunds.class);
//             
//            System.out.println("code: " + dataRes.getCode());
////            System.out.println("API InquireCard TID_True: Content: " + response.toString());
//            return dataRes;
//        } else {
//            System.out.println("API Fail status code = " + httpResponse.getStatusLine().getStatusCode());
//            return new DataResponseWithdrawFunds();
//        }
//    }
//
//    public void RunTestApiWithdrawFunds(SaveDataWithdrawFunds saveData) throws IOException{
//        ArrayList<DataRowWithdrawFunds> datas = saveData.getDatas();
//        for(int i=0; i<datas.size(); i++){
//            DataRowWithdrawFunds dataRow = datas.get(i);
//            if(dataRow.getThreadNumber() == 1){
//                DataResponseWithdrawFunds dataResp = CallApiWithdrawFunds(dataRow.getDataPostInquireCard(), dataRow.getDataWithdrawFunds());
//                dataRow.setCodeReal(dataResp.getCode());
//                datas.set(i, dataRow);
//            }else{
//            }
//        }
//    }
}
