package ru.kbles.dekar.tirneri;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


import org.apache.http.client.methods.HttpGet;



/**
 * Created by 1 on 21.10.2015.
 */
public class JSONSender {
    final private static String mPostVar = "json";
    final private static String mUrl = "http://snowtalk.ru/mirror/";


    private static String formJson(int id, double altitude, double longitude, double accuracy ) {
        JSONObject o = new JSONObject();
        int hash = -1;
        try {
            o.put("id", id);
            o.put("time", System.currentTimeMillis() / 1000);
            o.put("alt", altitude);
            o.put("long", longitude);
            o.put("acc", accuracy);
            o.put("hash", hash);
        }catch (JSONException e)
        {
            return "";
        }
        return o.toString();
    }

    private static void sendJson(final String ds) {
        Thread t = new Thread() {
            public void run() {
                try {
                    DefaultHttpClient hc = new DefaultHttpClient();
                    HttpPost http = new HttpPost(mUrl);
                    List<NameValuePair> pairs = new ArrayList<NameValuePair>();
                    pairs.add(new BasicNameValuePair(mPostVar, ds));
                    http.setEntity(new UrlEncodedFormEntity(pairs));
                    String r = http.getRequestLine().toString();
                    HttpResponse rsp = hc.execute(http);
                }
                catch (Exception e)
                {
                    String re = e.toString();
                }
            }
        };

        t.start();
    }
    public static void sendPoint(int id, double altitude, double longitude, double accuracy ) {
        String json=formJson(id,  altitude,  longitude,  accuracy );
        sendJson(json);
    }

    /*
    void send(final String data) {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                trueSend(data);
            }
        });
        myThread.start();
    }
    void trueSend (String data) {
        try {
            URL url;
            URLConnection urlConn;
            DataOutputStream printout;
            DataInputStream input;
            url = new URL("http://snowtalk.ru/mirror/?json={%22ack%22%3A%22GO%22}");
            urlConn = url.openConnection();
            //urlConn.setRequestMethod("POST");
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Host", "snowtalk.ru");
            urlConn.connect();
            //Create JSONObject here
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("ID", "25");
            jsonParam.put("description", "Real");
            jsonParam.put("enable", "true");
            // Send POST output.
            printout = new DataOutputStream(urlConn.getOutputStream());
            printout.write(URLEncoder.encode(jsonParam.toString(), "UTF-8").getBytes());
            printout.flush();
            printout.close();
        }
        catch (Exception e)
        {
            String s = e.toString();
        }
    }*/
}
