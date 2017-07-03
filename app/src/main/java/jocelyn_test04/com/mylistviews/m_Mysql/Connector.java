package jocelyn_test04.com.mylistviews.m_Mysql;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jocelyn on 7/12/2016.
 */

public class Connector {

    public static HttpURLConnection connect(String urlAddress){

        try {
            URL url = new URL(urlAddress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //conn prop
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(20000);
            conn.setDoInput(true);

            return conn;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
