package com.fcis.stalker.task1;

import com.fcis.stalker.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSite extends Activity implements OnClickListener {

	Button add;
	EditText name, url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_site);
		add = (Button) findViewById(R.id.add_new_site_button);
		name = (EditText) findViewById(R.id.add_site_name);
		url = (EditText) findViewById(R.id.add_RSS_URL);
		add.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add_new_site_button:

			boolean check = true;

			try {
				String Temp_name = name.getText().toString();
				String Temp_url = url.getText().toString();
				DataBase db = new DataBase(AddSite.this);
				db.Open();
				db.CreateEntry(Temp_name, Temp_url);
				db.Close();
			} catch (Exception e) {
				check = false;
				Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG)
						.show();
			} finally {
				if (check == true) {
					Toast.makeText(getBaseContext(), "Site has been Added",
							Toast.LENGTH_LONG).show();
					Intent i = new Intent(AddSite.this, MainActivity.class);
					startActivity(i);

				}
			}
			break;
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
