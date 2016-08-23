/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author CPU01661-local
 */
public class MyThread extends Thread {

    private JsonObject obj;
    private String url;
    private String code;
    private CyclicBarrier gate;

    public MyThread(JsonObject obj, String url, CyclicBarrier gate) {
        this.obj = obj;
        this.url = url;
        this.gate = gate;
    }

    public void run() {
        try {
            gate.await();
            System.out.println("Run : " + obj.toString());
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "text/plain");
            httpPost.setHeader("Content-type", "text/plain");
            httpPost.setEntity(new StringEntity(obj.toString()));
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();
                JsonObject jresp = new JsonObject();
                Gson gson = new Gson();
                jresp = gson.fromJson(response.toString(), JsonObject.class);
                JsonElement je = jresp.get("code");
                code = je.getAsString();
                System.out.println("Thread - data resp: " + jresp.toString());
            } else {
                System.out.println("API Fail status code = " + httpResponse.getStatusLine().getStatusCode());
//            return new DataResponseWithdrawFunds();/
                code = "-1";
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public String getCode() {
        return code;
    }

    public CyclicBarrier getGate() {
        return gate;
    }


}