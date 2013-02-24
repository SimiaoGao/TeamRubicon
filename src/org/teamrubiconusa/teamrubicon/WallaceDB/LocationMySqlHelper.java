package org.teamrubiconusa.teamrubicon.WallaceDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LocationMySqlHelper extends SQLiteOpenHelper {

	  public static final String TABLE_EVENT = "event";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_NAME = "name";
	  public static final String COLUMN_LOCATION = "location";

	  private static final String DATABASE_NAME = "notes.db";
	  private static final int DATABASE_VERSION = 4;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_EVENT + "(" 
		  + COLUMN_ID + " integer primary key autoincrement, " 
	      + COLUMN_NAME + " text not null, " 
	      + COLUMN_LOCATION + " text not null);";

	  public LocationMySqlHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(LocationMySqlHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
	    onCreate(db);
	  }

	} 