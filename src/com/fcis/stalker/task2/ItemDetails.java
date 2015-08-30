package com.fcis.stalker.task2;

import java.io.IOException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fcis.stalker.R;

public class ItemDetails extends Activity {

	TextView tvTitle, tvDate, tvDescription;
	ImageView ivImage;
	Button bLink;
	String details[];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_details);
		initialize();

		Bundle gotBasket = getIntent().getExtras();
		details = gotBasket.getStringArray("details");

		tvTitle.setText(details[1]);
		tvDescription.setText(Html.fromHtml(details[2]));
		URL url = null;
		Bitmap bmp = null;
		try {
			url = new URL(details[4]);
			bmp = BitmapFactory.decodeStream(url.openConnection()
					.getInputStream());
			ivImage.setImageBitmap(bmp);
		} catch (IOException e) {
			try {
				URL temp = new URL(
						"https://s3-us-west-2.amazonaws.com/droplr.storage/previews/2HWE.preview_small.png?AWSAccessKeyId=AKIAJSVQN3Z4K7MT5U2A&Expires=1390821060&Signature=ANkSFXtaR24U9NGEEk7nXqr7UE8%3D");
				bmp = BitmapFactory.decodeStream(temp.openConnection()
						.getInputStream());
				ivImage.setImageBitmap(bmp);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		tvDate.setText(details[3]);

		bLink.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				String link = details[0];
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(link));
				startActivity(intent);
			}
		});
	}

	private void initialize() {
		// TODO Auto-generated method stub
		tvTitle = (TextView) findViewById(R.id.tvTitle);
		tvDescription = (TextView) findViewById(R.id.tvDescription);
		tvDate = (TextView) findViewById(R.id.tvDate);
		ivImage = (ImageView) findViewById(R.id.ivImage);
		bLink = (Button) findViewById(R.id.bLink);
	}

}