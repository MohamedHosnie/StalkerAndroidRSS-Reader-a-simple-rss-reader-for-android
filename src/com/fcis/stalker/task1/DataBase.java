package com.fcis.stalker.task1;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase {

	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_URL = "link";

	private static final String DATABASE_NAME = "Test";
	private static final String DATABASE_TABLE = "rsstable";
	private static final int DATABASE_VERSION = 1;

	private Dbhandler handler;
	private final Context BetaContext;
	private SQLiteDatabase BetaDataBase;

	private static class Dbhandler extends SQLiteOpenHelper {

		public Dbhandler(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
					+ " TEXT NOT NULL, " + KEY_URL + " TEXT NOT NULL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);

		}

	}

	public DataBase(Context c) {

		BetaContext = c;

	}

	public DataBase Open() throws SQLException {
		handler = new Dbhandler(BetaContext);
		BetaDataBase = handler.getWritableDatabase();
		return this;
	}

	public void Close() {
		handler.close();
	}

	public void CreateEntry(String temp_name, String temp_url) {
		// TODO Auto-generated method stub
		ContentValues Values = new ContentValues();
		Values.put(KEY_NAME, temp_name);
		Values.put(KEY_URL, temp_url);
		BetaDataBase.insert(DATABASE_TABLE, null, Values);
	}

	public List<String> getID() {

		String[] columns = new String[] { KEY_ID, KEY_NAME, KEY_URL };
		Cursor c = BetaDataBase.query(DATABASE_TABLE, columns, null, null,
				null, null, null);
		List<String> s_List = new ArrayList<String>();
		int iID = c.getColumnIndex(KEY_ID);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			String temp = c.getString(iID);
			s_List.add(temp);

		}
		c.close();
		return s_List;
	}

	public void updateEntry(long lRow, String name, String url) {
		// TODO Auto-generated method stub
		ContentValues cvupdate = new ContentValues();
		cvupdate.put(KEY_NAME, name);
		cvupdate.put(KEY_URL, url);
		BetaDataBase
				.update(DATABASE_TABLE, cvupdate, KEY_ID + "=" + lRow, null);

	}

	public void deleteEntry(long lRow1) {
		// TODO Auto-generated method stub
		BetaDataBase.delete(DATABASE_TABLE, KEY_ID + "=" + lRow1, null);
	}

	public List<String> getSiteData() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ID, KEY_NAME, KEY_URL };
		Cursor c = BetaDataBase.query(DATABASE_TABLE, columns, null, null,
				null, null, null);
		List<String> s_List = new ArrayList<String>();

		int iName = c.getColumnIndex(KEY_NAME);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			String temp = c.getString(iName);
			s_List.add(temp);
		}
		c.close();
		return s_List;
	}

	public List<String> getLinkData() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_ID, KEY_NAME, KEY_URL };
		Cursor c = BetaDataBase.query(DATABASE_TABLE, columns, null, null,
				null, null, null);
		List<String> s_List = new ArrayList<String>();

		int IURl = c.getColumnIndex(KEY_URL);

		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			String temp = c.getString(IURl);
			s_List.add(temp);
		}
		c.close();
		return s_List;
	}
}
