package com.rssfeed.activity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import org.json.JSONObject;
import com.rssfeed.R;
import com.rssfeed.adapter.RssReaderListAdapter;
import com.rssfeed.helper.SortingOrder;
import com.rssfeed.helper.ReverseOrder;
import com.rssfeed.helper.RssFeedStructure;

import com.rssfeed.helper.XmlHandler;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class RssFeedReaderActivity extends Activity {
    /** Called when the activity is first created. */
	
	ListView _rssFeedListView;
	List<JSONObject> jobs ;
	 List<RssFeedStructure> rssStr ;
	private RssReaderListAdapter _adapter;
	String sorti = "";
	String mode = "";
	 Button sort_Btn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rssfeedreaderactivity);
       _rssFeedListView = (ListView)findViewById(R.id.rssfeed_listview);
       sort_Btn = (Button)findViewById(R.id.sort);
       sort_Btn.setText("Change Sorting Mode");
       sort_Btn.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(sorti.equalsIgnoreCase("")){
				 sorti = "sort";
			}
			if(sorti.equalsIgnoreCase("sort")){
		     sorti = "sort";
		     sort_Btn.setText("Change Reverse Mode");
			 RssFeedTask rssTask = new RssFeedTask();
		     rssTask.execute();
			}
			else if(sorti.equalsIgnoreCase("reverse")){
				 sorti = "reverse";
				 sort_Btn.setText("Change Normal Mode");
				 RssFeedTask rssTask = new RssFeedTask();
			     rssTask.execute();
			}
			else if(sorti.equalsIgnoreCase("normal")){
				sort_Btn.setText("Change Sorting Mode");
				 RssFeedTask rssTask = new RssFeedTask();
			     rssTask.execute();
			}
		}
	});
       RssFeedTask rssTask = new RssFeedTask();
       rssTask.execute();
    }
    private class RssFeedTask extends AsyncTask<String, Void, String> {
		// private String Content;
		private ProgressDialog Dialog;
		String response = "";

		@Override
		protected void onPreExecute() {
			Dialog = new ProgressDialog(RssFeedReaderActivity.this);
			Dialog.setMessage("Rss Loading...");
			Dialog.show();
		
		}

		@Override
		protected String doInBackground(String... urls) {
			  try {
				  //String feed = "http://feeds.nytimes.com/nyt/rss/HomePage";
				  
				  String feed = "http://feeds.feedburner.com/iamvijayakumar/androidtutorial";
				  XmlHandler rh = new XmlHandler();
				  rssStr = rh.getLatestArticles(feed);  
			        } catch (Exception e) {
			        }
			return response;

		}

		@Override
		protected void onPostExecute(String result) {
			  if(sorti.equalsIgnoreCase("sort")){
				  sorti = "reverse";
			     Collections.sort(rssStr, new SortingOrder());
			     
			  }else if(sorti.equalsIgnoreCase("reverse")){
				  sorti = "normal";
				  Comparator comp = Collections.reverseOrder();
				  Collections.sort(rssStr, new ReverseOrder());
			  }else{
				  sorti = "";
			  }
			  if(rssStr != null){
			    _adapter = new RssReaderListAdapter(RssFeedReaderActivity.this,rssStr);
		        _rssFeedListView.setAdapter(_adapter);
			  }
		        Dialog.dismiss();
		}
	}
  
}