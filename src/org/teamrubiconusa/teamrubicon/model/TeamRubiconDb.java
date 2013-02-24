package org.teamrubiconusa.teamrubicon.model;

import org.teamrubiconusa.teamrubicon.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQuery;
import android.util.Log;

public class TeamRubiconDb extends SQLiteOpenHelper {
	/** The name of the database file on the file system */
	private static final String DATABASE_NAME = "teamrubicon.db";
	/** The version of the database that this class understands. */
	private static final int DATABASE_VERSION = 1;
	/** Keep track of context so that we can load SQL from string resources */
	private final Context mContext;

	public TeamRubiconDb(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.mContext = context;
	}

	public static class WarehousesCursor extends SQLiteCursor {
		/** The query for this cursor */
		private static final String QUERY = "SELECT _id, name, location FROM warehouses";

		/** Cursor constructor */
		WarehousesCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
			super(db, driver, editTable, query);
		}

		/** Private factory class necessary for rawQueryWithFactory() call */
		private static class Factory implements SQLiteDatabase.CursorFactory {

			@Override
			public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
				return new WarehousesCursor(db, driver, editTable, query);
			}
		}

		/* Accessor functions -- one per database column */
		public int getColId() {
			return getInt(getColumnIndexOrThrow("_id"));
		}

		public String getColName() {
			return getString(getColumnIndexOrThrow("name"));
		}

		public String getColLocation() {
			return getString(getColumnIndexOrThrow("location"));
		}
	}
	
	public static class ItemsCursor extends SQLiteCursor {
		/** The query for this cursor */
		private static final String QUERY = "SELECT _id, name, condition FROM items";

		/** Cursor constructor */
		ItemsCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
			super(db, driver, editTable, query);
		}

		/** Private factory class necessary for rawQueryWithFactory() call */
		private static class Factory implements SQLiteDatabase.CursorFactory {

			@Override
			public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
				return new ItemsCursor(db, driver, editTable, query);
			}
		}

		/* Accessor functions -- one per database column */
		public int getColId() {
			return getInt(getColumnIndexOrThrow("_id"));
		}

		public String getColName() {
			return getString(getColumnIndexOrThrow("name"));
		}

		public String getColCondition() {
			return getString(getColumnIndexOrThrow("condition"));
		}
	}

	public static class InactiveCursor extends SQLiteCursor {
		/** The query for this cursor */
		private static final String QUERY = "SELECT warehouse, item, amount FROM inactive";

		/** Cursor constructor */
		InactiveCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
			super(db, driver, editTable, query);
		}

		/** Private factory class necessary for rawQueryWithFactory() call */
		private static class Factory implements SQLiteDatabase.CursorFactory {
			
			@Override
			public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
				return new InactiveCursor(db, driver, editTable, query);
			}
		}

		/* Accessor functions -- one per database column */

		/** foreign key column */
		public int getColWarehouse() {
			return getInt(getColumnIndexOrThrow("warehouse"));
		}

		/** foreign key column */
		public int getColItem() {
			return getInt(getColumnIndexOrThrow("item"));
		}
		
		public int getColAmount() {
			return getInt(getColumnIndexOrThrow("amount"));
		}
	}
	
	public static class ActiveCursor extends SQLiteCursor {
		/** The query for this cursor */
		private static final String QUERY = "SELECT warehouse, item, amount, time FROM active";

		/** Cursor constructor */
		ActiveCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
			super(db, driver, editTable, query);
		}

		/** Private factory class necessary for rawQueryWithFactory() call */
		private static class Factory implements SQLiteDatabase.CursorFactory {
			
			@Override
			public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
				return new ActiveCursor(db, driver, editTable, query);
			}
		}

		/* Accessor functions -- one per database column */

		/** foreign key column */
		public int getColWarehouse() {
			return getInt(getColumnIndexOrThrow("warehouse"));
		}

		/** foreign key column */
		public int getColItem() {
			return getInt(getColumnIndexOrThrow("item"));
		}
		
		public int getColAmount() {
			return getInt(getColumnIndexOrThrow("amount"));
		}
		
		public String getColTime() {
			return getString(getColumnIndexOrThrow("time"));
		}
	}
	
	public static class PersonsCursor extends SQLiteCursor {
		/** The query for this cursor */
		private static final String QUERY = "SELECT _id, name, role, phone FROM persons";

		/** Cursor constructor */
		PersonsCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
			super(db, driver, editTable, query);
		}

		/** Private factory class necessary for rawQueryWithFactory() call */
		private static class Factory implements SQLiteDatabase.CursorFactory {

			@Override
			public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
				return new PersonsCursor(db, driver, editTable, query);
			}
		}

		/* Accessor functions -- one per database column */
		public int getColId() {
			return getInt(getColumnIndexOrThrow("_id"));
		}

		public String getColName() {
			return getString(getColumnIndexOrThrow("name"));
		}

		public String getColRole() {
			return getString(getColumnIndexOrThrow("role"));
		}
		
		public String getColPhone() {
			return getString(getColumnIndexOrThrow("phone"));
		}
	}
	
	public static class LentCursor extends SQLiteCursor {
		/** The query for this cursor */
		private static final String QUERY = "SELECT person, item, amount, time FROM lent";

		/** Cursor constructor */
		LentCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
			super(db, driver, editTable, query);
		}

		/** Private factory class necessary for rawQueryWithFactory() call */
		private static class Factory implements SQLiteDatabase.CursorFactory {
			
			@Override
			public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
				return new LentCursor(db, driver, editTable, query);
			}
		}

		/* Accessor functions -- one per database column */

		/** foreign key column */
		public int getColPerson() {
			return getInt(getColumnIndexOrThrow("person"));
		}

		/** foreign key column */
		public int getColItem() {
			return getInt(getColumnIndexOrThrow("item"));
		}
		
		public int getColAmount() {
			return getInt(getColumnIndexOrThrow("amount"));
		}
		
		public String getColTime() {
			return getString(getColumnIndexOrThrow("time"));
		}
	}
	
	/**
	 * Execute all of the SQL statements in the String[] array
	 * 
	 * @param db
	 *            The database on which to execute the statements
	 * @param sql
	 *            An array of SQL statements to execute
	 */
	private void execMultipleSQL(SQLiteDatabase db, String[] sql) {
		for (String s : sql) {
			if (s.trim().length() > 0) {
				db.execSQL(s);
			}
		}
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String[] sql = mContext.getString(R.string.smsAutoResponder_onCreate).split("\n");
		db.beginTransaction();
		try {
			// Create tables & test data
			execMultipleSQL(db, sql);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("Error creating tables and debug data", e.toString());
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	
	/**
	 * Warehouse
	 * @param id
	 * @param name
	 * @param location
	 */
	public void addWarehouse(int id, String name, String location) {
		ContentValues map = new ContentValues();
		map.put("_id", id);
		map.put("name", name);
		map.put("location", location);
		try {
			getWritableDatabase().insert("warehouses", null, map);
		} catch (SQLException e) {
			Log.e("Error writing new warehouse", e.toString());
		}
	}
	
	public void deleteWarehouse(int id) {
		String[] whereArgs = new String[] { Integer.toString(id) };
		try {
			getWritableDatabase().delete("warehouses", "_id=?", whereArgs);
		} catch (SQLException e) {
			Log.e("Error deleteing warehouse", e.toString());
		}
	}
	
	public int getWarehousesCount() {

		Cursor c = null;
		try {
			c = getReadableDatabase().rawQuery("SELECT count(*) FROM warehouses", null);
			if (0 >= c.getCount()) {
				return 0;
			}
			c.moveToFirst();
			return c.getInt(0);
		} finally {
			if (null != c) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	public WarehousesCursor getWarehouses() {
		String sql = WarehousesCursor.QUERY;
		SQLiteDatabase d = getReadableDatabase();
		WarehousesCursor c = (WarehousesCursor) d.rawQueryWithFactory(new WarehousesCursor.Factory(), sql, null, null);
		c.moveToFirst();
		return c;
	}
	
	
	/**
	 * Item
	 * @param id
	 * @param name
	 * @param condition
	 */
	public void addItem(int id, String name, String condition) {
		ContentValues map = new ContentValues();
		map.put("_id", id);
		map.put("name", name);
		map.put("condition", condition);
		try {
			getWritableDatabase().insert("items", null, map);
		} catch (SQLException e) {
			Log.e("Error writing new item", e.toString());
		}
	}
	
	public void deleteItem(int id) {
		String[] whereArgs = new String[] { Integer.toString(id) };
		try {
			getWritableDatabase().delete("items", "_id=?", whereArgs);
		} catch (SQLException e) {
			Log.e("Error deleteing item", e.toString());
		}
	}
	
	public int getItemsCount() {

		Cursor c = null;
		try {
			c = getReadableDatabase().rawQuery("SELECT count(*) FROM items", null);
			if (0 >= c.getCount()) {
				return 0;
			}
			c.moveToFirst();
			return c.getInt(0);
		} finally {
			if (null != c) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	public ItemsCursor getItems() {
		String sql = WarehousesCursor.QUERY;
		SQLiteDatabase d = getReadableDatabase();
		ItemsCursor c = (ItemsCursor) d.rawQueryWithFactory(new ItemsCursor.Factory(), sql, null, null);
		c.moveToFirst();
		return c;
	}
	
	
	/**
	 * Inactive
	 * @param id
	 * @param name
	 * @param condition
	 */
	public void addInactive(int warehouse, int item, int amount) {
		ContentValues map = new ContentValues();
		map.put("warehouse", warehouse);
		map.put("item", item);
		map.put("amount", amount);
		try {
			getWritableDatabase().insert("inactive", null, map);
		} catch (SQLException e) {
			Log.e("Error writing new inactive", e.toString());
		}
	}
	
	public void deleteInactive(int warehouse, int item) {
		String[] whereArgs = new String[] { Integer.toString(warehouse), Integer.toString(item) };
		try {
			getWritableDatabase().delete("inactive", "warehouse=? AND item=?", whereArgs);
		} catch (SQLException e) {
			Log.e("Error deleteing inactive", e.toString());
		}
	}
	
	public int getInactiveCount() {

		Cursor c = null;
		try {
			c = getReadableDatabase().rawQuery("SELECT count(*) FROM inactive", null);
			if (0 >= c.getCount()) {
				return 0;
			}
			c.moveToFirst();
			return c.getInt(0);
		} finally {
			if (null != c) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	public InactiveCursor getInactive() {
		String sql = WarehousesCursor.QUERY;
		SQLiteDatabase d = getReadableDatabase();
		InactiveCursor c = (InactiveCursor) d.rawQueryWithFactory(new InactiveCursor.Factory(), sql, null, null);
		c.moveToFirst();
		return c;
	}
	
	/**
	 * Active
	 * @param id
	 * @param name
	 * @param condition
	 */
	public void addActive(int warehouse, int item, int amount, String time) {
		ContentValues map = new ContentValues();
		map.put("warehouse", warehouse);
		map.put("item", item);
		map.put("amount", amount);
		map.put("time", time);
		try {
			getWritableDatabase().insert("active", null, map);
		} catch (SQLException e) {
		Log.e("Error writing new active", e.toString());
		}
	}
	
	public void deleteActive(int warehouse, int item) {
		String[] whereArgs = new String[] { Integer.toString(warehouse), Integer.toString(item) };
		try {
			getWritableDatabase().delete("active", "warehouse=? AND item=?", whereArgs);
		} catch (SQLException e) {
			Log.e("Error deleteing active", e.toString());
		}
	}
	
	public int getActiveCount() {

		Cursor c = null;
		try {
			c = getReadableDatabase().rawQuery("SELECT count(*) FROM active", null);
			if (0 >= c.getCount()) {
				return 0;
			}
			c.moveToFirst();
			return c.getInt(0);
		} finally {
			if (null != c) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	public ActiveCursor getActive() {
		String sql = WarehousesCursor.QUERY;
		SQLiteDatabase d = getReadableDatabase();
		ActiveCursor c = (ActiveCursor) d.rawQueryWithFactory(new ActiveCursor.Factory(), sql, null, null);
		c.moveToFirst();
		return c;
	}
	
	/**
	 * Person
	 * @param id
	 * @param name
	 * @param condition
	 */
	public void addPerson(int id, String name, String role, String phone) {
		ContentValues map = new ContentValues();
		map.put("id", id);
		map.put("name", name);
		map.put("role", role);
		map.put("phone", phone);
		try {
			getWritableDatabase().insert("persons", null, map);
		} catch (SQLException e) {
		Log.e("Error writing new person", e.toString());
		}
	}
	
	public void deletePerson(int id) {
		String[] whereArgs = new String[] { Integer.toString(id) };
		try {
			getWritableDatabase().delete("persons", "_id=?", whereArgs);
		} catch (SQLException e) {
			Log.e("Error deleteing person", e.toString());
		}
	}
	
	public int getPersonCount() {

		Cursor c = null;
		try {
			c = getReadableDatabase().rawQuery("SELECT count(*) FROM person", null);
			if (0 >= c.getCount()) {
				return 0;
			}
			c.moveToFirst();
			return c.getInt(0);
		} finally {
			if (null != c) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	public PersonsCursor getPerson() {
		String sql = WarehousesCursor.QUERY;
		SQLiteDatabase d = getReadableDatabase();
		PersonsCursor c = (PersonsCursor) d.rawQueryWithFactory(new PersonsCursor.Factory(), sql, null, null);
		c.moveToFirst();
		return c;
	}
	
	
	/**
	 * Lent
	 * @param id
	 * @param name
	 * @param condition
	 */
	public void addLent(int person, int item, int amount, String time) {
		ContentValues map = new ContentValues();
		map.put("person", person);
		map.put("item", item);
		map.put("amount", amount);
		map.put("time", time);
		try {
			getWritableDatabase().insert("lent", null, map);
		} catch (SQLException e) {
		Log.e("Error writing new lent", e.toString());
		}
	}
	
	public void deleteLent(int person, int item, String time) {
		String[] whereArgs = new String[] { Integer.toString(person), Integer.toString(item), time };
		try {
			getWritableDatabase().delete("lent", "person=? AND item=? AND time=?", whereArgs);
		} catch (SQLException e) {
			Log.e("Error deleteing lent", e.toString());
		}
	}
	
	public int getLentCount() {

		Cursor c = null;
		try {
			c = getReadableDatabase().rawQuery("SELECT count(*) FROM lent", null);
			if (0 >= c.getCount()) {
				return 0;
			}
			c.moveToFirst();
			return c.getInt(0);
		} finally {
			if (null != c) {
				try {
					c.close();
				} catch (SQLException e) {
				}
			}
		}
	}
	
	public LentCursor getLent() {
		String sql = WarehousesCursor.QUERY;
		SQLiteDatabase d = getReadableDatabase();
		LentCursor c = (LentCursor) d.rawQueryWithFactory(new LentCursor.Factory(), sql, null, null);
		c.moveToFirst();
		return c;
	}
}
