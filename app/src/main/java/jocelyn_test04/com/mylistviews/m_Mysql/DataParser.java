package jocelyn_test04.com.mylistviews.m_Mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jocelyn_test04.com.mylistviews.m_DataObj.Userinfo;
import jocelyn_test04.com.mylistviews.m_UI.CustomAdapter;

/**
 * Created by Jocelyn on 7/12/2016.
 */

public class DataParser extends AsyncTask<Void, Void, Integer> {

    Context c;
    String jsonData;
    ListView lv;

    ArrayList<Userinfo> userList = new ArrayList<>();

    ProgressDialog pd;

    public DataParser(Context c, String jsonData, ListView lv) {
        this.c = c;
        this.jsonData = jsonData;
        this.lv = lv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(c);
            pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parseJson();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        pd.dismiss();

        if(result == 0){
            Toast.makeText(c,"Unable To Parse",Toast.LENGTH_SHORT).show();
        }else {
            //BING DATA TO LISTVIEW
            CustomAdapter ca = new CustomAdapter(c, userList);
            lv.setAdapter(ca);
        }
    }

    private int parseJson(){

        try {
            JSONArray jsa = new JSONArray(jsonData);
            JSONObject jo = null;

            userList.clear();
            Userinfo ui;
            for(int i = 0; i<jsa.length(); i++)
            {
                jo = jsa.getJSONObject(i);

                int id = jo.getInt("0");
                String firsName = jo.getString("1");
                String lastName = jo.getString("2");
                String userName = jo.getString("3");
                String email = jo.getString("4");
                String imgUrl = jo.getString("5");

                ui = new Userinfo();
                ui.setId(id);
                ui.setFirstName(firsName);
                ui.setLastName(lastName);
                ui.setUserName(userName);
                ui.setEmail(email);
                ui.setImgUrl(imgUrl);

                userList.add(ui);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
