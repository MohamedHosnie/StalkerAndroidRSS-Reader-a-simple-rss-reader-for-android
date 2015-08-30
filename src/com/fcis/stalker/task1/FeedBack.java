package com.fcis.stalker.task1;

import com.fcis.stalker.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FeedBack extends Activity {
	Button send;
	EditText message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		send = (Button) findViewById(R.id.feedback_send);
		message = (EditText) findViewById(R.id.feedback_text);
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String s_message = message.getText().toString();
				String email[] = { "mohamedfathy234@gmail.com" };
				Intent sent_email = new Intent(
						android.content.Intent.ACTION_SEND);
				sent_email.putExtra(android.content.Intent.EXTRA_EMAIL, email);
				sent_email.putExtra(android.content.Intent.EXTRA_SUBJECT,
						"Feedback");
				sent_email.setType("plain/text");
				sent_email.putExtra(android.content.Intent.EXTRA_TEXT,
						s_message);
				startActivity(sent_email);

			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	    finish();
	}
}
