package com.fcis.stalker.task1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fcis.stalker.R;

public class EditSite extends Activity implements OnClickListener {

	Button SaveChanges, Delete;
	EditText SiteName, RssUrl;
	String name, site, tempid;
	long id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_site);
		Bundle gotBasket = getIntent().getExtras();
		name = gotBasket.getString("Name");
		site = gotBasket.getString("Site");
		tempid = gotBasket.getString("ID");
		id = Long.parseLong(tempid);
		SaveChanges = (Button) findViewById(R.id.edit_save_changes_button);
		Delete = (Button) findViewById(R.id.edit_delete_button);
		SiteName = (EditText) findViewById(R.id.edit_site_name);
		RssUrl = (EditText) findViewById(R.id.edit_rss_url);
		SiteName.setText(name);
		RssUrl.setText(site);

		SaveChanges.setOnClickListener(this);
		Delete.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.edit_save_changes_button:
			boolean check = true;
			try {
				String temp_name = SiteName.getText().toString();
				String temp_url = RssUrl.getText().toString();

				DataBase temp_db = new DataBase(EditSite.this);
				temp_db.Open();
				temp_db.updateEntry(id, temp_name, temp_url);
				temp_db.Close();
			} catch (Exception e) {
				check = false;

			} finally {
				if (check == true) {
					Toast.makeText(this, "Changes Has Been Saved",
							Toast.LENGTH_LONG).show();
					Intent i = new Intent(EditSite.this, MainActivity.class);
					startActivity(i);
				} else
					Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.edit_delete_button:
			boolean check3 = true;
			try {

				DataBase temp_db = new DataBase(EditSite.this);
				temp_db.Open();
				temp_db.deleteEntry(id);
				temp_db.Close();
			} catch (Exception e) {
				check3 = false;

			} finally {
				if (check3 == true) {
					Toast.makeText(this, "Site has Been Deleted",
							Toast.LENGTH_LONG).show();
					Intent i = new Intent(EditSite.this, MainActivity.class);
					startActivity(i);
				} else
					Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
			}
			break;
		}

	}

}
