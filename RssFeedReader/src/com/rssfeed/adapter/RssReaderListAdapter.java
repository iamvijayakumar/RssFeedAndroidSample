package com.rssfeed.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.rssfeed.R;
import com.rssfeed.helper.RssFeedStructure;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RssReaderListAdapter extends ArrayAdapter<RssFeedStructure> {
	List<RssFeedStructure> imageAndTexts1 =null;
public RssReaderListAdapter(Activity activity, List<RssFeedStructure> imageAndTexts) {
super(activity, 0, imageAndTexts);
imageAndTexts1 = imageAndTexts;
}


@Override
public View getView(int position, View convertView, ViewGroup parent) {

Activity activity = (Activity) getContext();
LayoutInflater inflater = activity.getLayoutInflater();


View rowView = inflater.inflate(R.layout.rssfeedadapter_layout, null);
TextView textView = (TextView) rowView.findViewById(R.id.feed_text);
TextView timeFeedText = (TextView) rowView.findViewById(R.id.feed_updatetime);
ImageView imageView = (ImageView) rowView.findViewById(R.id.feed_image);
        try {
        	
        	Log.d("rssfeed", "imageAndTexts1.get(position).getImgLink() :: " +imageAndTexts1.get(position).getImgLink() +" :: " +imageAndTexts1.get(position).getTitle());
        	textView.setText(imageAndTexts1.get(position).getTitle());
        	SpannableString content = new SpannableString(imageAndTexts1.get(position).getPubDate());
        	 content.setSpan(new UnderlineSpan(), 0, 13, 0);

        	timeFeedText.setText(content);
        	if(imageAndTexts1.get(position).getImgLink() !=null){
        		
       
        	URL feedImage= new URL(imageAndTexts1.get(position).getImgLink().toString());
        	if(!feedImage.toString().equalsIgnoreCase("null")){
        		HttpURLConnection conn= (HttpURLConnection)feedImage.openConnection();
            	InputStream is = conn.getInputStream();
            	Bitmap img = BitmapFactory.decodeStream(is);
            	imageView.setImageBitmap(img);
        	}
        	 else{
             	imageView.setBackgroundResource(R.drawable.im);
             }
        		}
       
        	
        } catch (MalformedURLException e) {
       
        }
        catch (IOException e) {
        
        }

return rowView;

}

}