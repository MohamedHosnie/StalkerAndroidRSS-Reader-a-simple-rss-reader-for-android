package com.fcis.stalker.task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fcis.stalker.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ItemsList extends Activity {

	private ProgressDialog loadingDialog;
	ArrayList<HashMap<String, String>> mapItemsList = new ArrayList<HashMap<String, String>>();
	List<FeedItem> itemsList = new ArrayList<FeedItem>();
	ListView lvItemsList;
	RSSReader reader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.items_list);
		lvItemsList = (ListView) findViewById(R.id.lvItemsList);

		Bundle gotBasket2 = getIntent().getExtras();
		String rssLink = gotBasket2.getString("link");
		new LoadItems().execute(rssLink);
		lvItemsList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int pos, long l) {
				// TODO Auto-generated method stub
				new LoadDetails().execute(pos);
			}
		});
	}

	class LoadItems extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			loadingDialog = new ProgressDialog(ItemsList.this);
			loadingDialog.setMessage("Loading recent topics...");
			loadingDialog.setIndeterminate(false);
			loadingDialog.setCancelable(true);
			loadingDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				String rssLink = params[0];
				reader = new RSSReader(rssLink);
				itemsList = reader.getItems();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				String error = e.toString();
				Dialog dialog = new Dialog(ItemsList.this);
				dialog.setTitle(error);
				TextView tvError = new TextView(ItemsList.this);
				tvError.setText("failure");
				dialog.setContentView(tvError);
				dialog.show();
			}

			for (FeedItem feedItem : itemsList) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("title", feedItem.getTitle());
				map.put("link", feedItem.getLink());
				String date = feedItem.getDate();
				date = date.substring(0, 26);
				map.put("pubDate", date);
				mapItemsList.add(map);
			}

			runOnUiThread(new Runnable() {
				public void run() {
					ListAdapter adapter = new SimpleAdapter(ItemsList.this,
							mapItemsList, R.layout.list_style, new String[] {
									"link", "title", "pubDate" }, new int[] {
									R.id.page_url, R.id.title, R.id.pub_date });

					lvItemsList.setAdapter(adapter);
				}
			});

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			loadingDialog.dismiss();
		}
	}

	class LoadDetails extends AsyncTask<Integer, Integer, Intent> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			loadingDialog = new ProgressDialog(ItemsList.this);
			loadingDialog.setMessage("Loading topic's details...");
			loadingDialog.setIndeterminate(false);
			loadingDialog.setCancelable(false);
			loadingDialog.show();
		}

		@Override
		protected Intent doInBackground(Integer... params) {
			// TODO Auto-generated method stub

			int pos = params[0];
			Intent intent = new Intent(ItemsList.this, ItemDetails.class);
			try {
				String date = reader.getItems().get(pos).getDate();
				date = date.substring(0, 26);
				String itemDetails[] =
					{
						reader.getItems().get(pos).getLink(), 
						reader.getItems().get(pos).getTitle(),
						reader.getItems().get(pos).getDescription(), 
						date,
						reader.getItems().get(pos).getImageLink()
					};

				Bundle basket = new Bundle();
				basket.putStringArray("details", itemDetails);
				intent.putExtras(basket);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				String error = e.toString();
				Dialog dialog = new Dialog(ItemsList.this);
				dialog.setTitle(error);
				TextView tvError = new TextView(ItemsList.this);
				tvError.setText("failure");
				dialog.setContentView(tvError);
				dialog.show();
			}

			return intent;
		}

		@Override
		protected void onPostExecute(Intent result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Intent intent = result;
			startActivity(intent);
			loadingDialog.dismiss();
		}
	}
}