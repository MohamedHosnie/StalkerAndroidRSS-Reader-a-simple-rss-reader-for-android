package com.fcis.stalker.task1;

import java.util.List;
import com.fcis.stalker.R;
import com.fcis.stalker.task2.ItemsList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	ListView list;
	List<String> Sites, Links, IDS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			DataBase db = new DataBase(MainActivity.this);
			db.Open();
			Sites = db.getSiteData();
			Links = db.getLinkData();
			IDS = db.getID();
			db.Close();
			list = (ListView) findViewById(R.id.listView1);
			if (Sites.isEmpty()) {
				Intent isEmpty = new Intent(MainActivity.this, AddSite.class);
				startActivity(isEmpty);
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, Sites);
			list.setAdapter(adapter);
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                public boolean onItemLongClick(AdapterView<?> arg0, View v,
                        int pos, long arg3) {
                    // TODO Auto-generated method stub
                     
                	Bundle Basket = new Bundle();
    				Basket.putString("Name", (String) Sites.get(pos));
    				Basket.putString("Site", (String) Links.get(pos));
    				Basket.putString("ID", (String) IDS.get(pos));
    				Intent i = new Intent(MainActivity.this, EditSite.class);
    				i.putExtras(Basket);
    				startActivity(i);

                   
                    return true;
                }
    }); 
			list.setOnItemClickListener(this);

		} catch (Exception e) {
			Toast.makeText(this, "ERROR ", Toast.LENGTH_LONG).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.aboutus:
			Dialog About = new Dialog(this);
			About.setTitle("About Us");
			TextView v = new TextView(this);
			v.setText("This App is this in Beta Test so Please be Patient with it ");
			About.setContentView(v);
			About.show();
			break;
		case R.id.feedback:
			Intent r = new Intent(MainActivity.this, FeedBack.class);
			startActivity(r);
			break;
		case R.id.newwebsite:
			Intent i = new Intent(MainActivity.this, AddSite.class);
			startActivity(i);
			break;
		case R.id.user_manual:
			Intent o = new Intent(MainActivity.this, User_manual.class);
			startActivity(o);
			break;
		}
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int pos, long arg3) {
		// TODO Auto-generated method stub
		
			String rssLink = (String) Links.get(pos);
			Bundle Basket = new Bundle();
			Basket.putString("link", rssLink);
			Intent i = new Intent(MainActivity.this, ItemsList.class);
			i.putExtras(Basket);
			startActivity(i);
		
	}

	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		DataBase db = new DataBase(MainActivity.this);
		db.Open();
		Sites = db.getSiteData();
		Links = db.getLinkData();
		IDS = db.getID();
		db.Close();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, Sites);
		list.setAdapter(adapter);
	}

}
