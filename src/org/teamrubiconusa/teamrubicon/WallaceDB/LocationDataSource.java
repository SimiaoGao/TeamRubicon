package org.teamrubiconusa.teamrubicon.WallaceDB;

import java.util.ArrayList;
import java.util.List;

import org.teamrubiconusa.teamrubicon.WallaceModels.Event;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LocationDataSource {

	  // Database fields
	  private SQLiteDatabase database;
	  private LocationMySqlHelper dbHelper;
	  private String[] allColumns = { LocationMySqlHelper.COLUMN_ID,
		      LocationMySqlHelper.COLUMN_NAME, LocationMySqlHelper.COLUMN_LOCATION};

	  public LocationDataSource(Context context) {
	    dbHelper = new LocationMySqlHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }
	  
	  //
	  // Create a note from a Event
	  //
	  public Event createEvent(Event event){
		    ContentValues values = new ContentValues();
		    values.put(LocationMySqlHelper.COLUMN_NAME, event.getEventName().toString());
		    values.put(LocationMySqlHelper.COLUMN_LOCATION, event.getEventLocation().toString());
		    long insertId = database.insert(LocationMySqlHelper.TABLE_EVENT, null,
		        values);
		    Cursor cursor = database.query(LocationMySqlHelper.TABLE_EVENT,
		        allColumns, LocationMySqlHelper.COLUMN_ID + " = " + insertId, null,
		        null, null, null);
		    cursor.moveToFirst();
		    Event newNote = cursorToEvent(cursor);
		    cursor.close();
		    return newNote;
	  }

	  public void deleteEvent(Event note) {
		    long id = note.getId();
		    database.delete(LocationMySqlHelper.TABLE_EVENT, LocationMySqlHelper.COLUMN_ID
		        + " = " + id, null);
		    System.out.println("Note deleted with id: " + id);
		  }
	  
	  public void deleteAllEvents() {
		    database.delete(LocationMySqlHelper.TABLE_EVENT, null, null);		  
	  }

	  public List<Event> getAllEvents() {
	    List<Event> notes = new ArrayList<Event>();
	    Cursor cursor = database.query(LocationMySqlHelper.TABLE_EVENT,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Event note = cursorToEvent(cursor);
	      notes.add(note);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return notes;
	  }

	  private Event cursorToEvent(Cursor cursor) {
	    Event event = new Event();
	    event.setId(cursor.getLong(0));
	    event.setEventName(cursor.getString(1));
	    event.setEventLocation(cursor.getString(2));


	    return event;
	  }
	  
	  public boolean updateEvent(long rowId, String eventName, 
	  String eventLocation) 
	  {
	      ContentValues values = new ContentValues();
	      values.put(LocationMySqlHelper.COLUMN_NAME, eventName);
	      values.put(LocationMySqlHelper.COLUMN_LOCATION, eventLocation);
	      return database.update(LocationMySqlHelper.TABLE_EVENT, values, 
	    		  LocationMySqlHelper.COLUMN_ID + "=" + rowId, null) > 0;
	  }
	} 
