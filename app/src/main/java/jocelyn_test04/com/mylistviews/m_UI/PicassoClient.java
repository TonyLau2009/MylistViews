package jocelyn_test04.com.mylistviews.m_UI;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import jocelyn_test04.com.mylistviews.R;

/**
 * Created by Jocelyn on 7/12/2016.
 */

public class PicassoClient {

    public static void downloadImage(Context c, String imgUrl, ImageView img)
    {
        if(imgUrl.length() > 0 && imgUrl != null)
        {
            Picasso.with(c).load(imgUrl).placeholder(R.drawable.bb).into(img);
        }else {
            Picasso.with(c).load(R.drawable.bb).into(img);
        }
    }
}
