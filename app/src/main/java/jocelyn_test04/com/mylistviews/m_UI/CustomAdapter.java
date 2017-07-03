package jocelyn_test04.com.mylistviews.m_UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


import jocelyn_test04.com.mylistviews.R;
import jocelyn_test04.com.mylistviews.m_DataObj.Userinfo;
import jocelyn_test04.com.mylistviews.ui.RoundImageView;

/**
 * Created by Jocelyn on 7/12/2016.
 */

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Userinfo> userList;

    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList<Userinfo> userList) {
        this.c = c;
        this.userList = userList;

        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.row,viewGroup,false);
        }

        TextView tvId = (TextView) convertView.findViewById(R.id.tvId);
        TextView tvFname = (TextView) convertView.findViewById(R.id.tvFname);
        TextView tvLname = (TextView) convertView.findViewById(R.id.tvLname);
        TextView tvUname = (TextView) convertView.findViewById(R.id.tvUname);
        TextView tvEmail = (TextView) convertView.findViewById(R.id.tvEmail);

        RoundImageView imgView = (RoundImageView) convertView.findViewById(R.id.ivImg);

        Userinfo user = userList.get(position);
        tvId.setText("ID:"+user.getId()+"");
        tvFname.setText("First Name:"+user.getFirstName()+"");
        tvLname.setText("Last Name:"+user.getLastName()+"");
        tvUname.setText("User Name:"+user.getUserName()+"");
        tvEmail.setText("Email:"+user.getEmail()+"");
        //img
        String imgPath = "http://192.168.1.4"+user.getImgUrl()+"";
        PicassoClient.downloadImage(c, imgPath, imgView);

        return convertView;
    }
}
