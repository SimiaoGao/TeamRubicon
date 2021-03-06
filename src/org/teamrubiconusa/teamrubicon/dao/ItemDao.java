package org.teamrubiconusa.teamrubicon.dao;

import java.util.HashMap;
import java.util.Map;

import org.teamrubiconusa.teamrubicon.model.Item;
import org.teamrubiconusa.teamrubicon.model.TeamRubiconDb;

import android.content.Context;

public class ItemDao {

	private static volatile ItemDao instance = null;
	private TeamRubiconDb db;
	private Map<Integer, Item> items = new HashMap<Integer, Item>();
	
	private ItemDao() {
	}

	public static ItemDao getInstance() {
		if (instance == null) {
			synchronized (ItemDao.class) {
				if (instance == null) {
					instance = new ItemDao();
				}
			}
		}
		return instance;
	}

	public void setContext(Context context) {
		db = new TeamRubiconDb(context);
		populateMap();
	}
	
	private void populateMap() {
		TeamRubiconDb.ItemsCursor itemCursor = db.getItems();
		for (int rowNum = 0; rowNum < itemCursor.getCount(); rowNum++) {
			itemCursor.moveToPosition(rowNum);
			Item temp = new Item(itemCursor.getColId(), itemCursor.getColType(), itemCursor.getColCondition(), itemCursor.getColWarehouse());
			items.put(itemCursor.getColId(), temp);
		}
	}
	 
	public boolean addItem(Item item) {
		if (items.get(item.getId()) == null) {
			db.addItem(item.getId(), item.getType(), item.getCondition(), item.getWarehouse());
			items.put(item.getId(), item);
			return true;
		}
		return false;
	}
	
	public Item getItemById(int id) {
		return items.get(new Integer(id));
	}
}
