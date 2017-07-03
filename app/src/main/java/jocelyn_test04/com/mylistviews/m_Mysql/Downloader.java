package jocelyn_test04.com.mylistviews.m_Mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by Jocelyn on 7/12/2016.
 */

public class Downloader extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAddress;
    ListView lv;

    ProgressDialog pd;

    public Downloader(Context c, String urlAddress, ListView lv) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
        pd.setTitle("Download");
        pd.setMessage("Downloading...Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        pd.dismiss();
        if(jsonData == null){
            Toast.makeText(c,"Unsuccessful,No data Retrieved",Toast.LENGTH_SHORT).show();
        }else {
            //todo parse data
            DataParser dataParser = new DataParser(c, jsonData, lv);
            dataParser.execute();
        }
    }

    private String downloadData(){

        HttpURLConnection con = Connector.connect(urlAddress);
        if(con == null) return null;

        try {
            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer json_sb = new StringBuffer();
            while ((line = br.readLine()) != null)
            {
                json_sb.append(line + "\n");
            }

            br.close();
            is.close();
            return json_sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
