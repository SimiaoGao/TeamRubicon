package org.teamrubiconusa.teamrubicon.dao;

import java.util.ArrayList;
import java.util.List;

import org.teamrubiconusa.teamrubicon.model.Inactive;
import org.teamrubiconusa.teamrubicon.model.TeamRubiconDb;

import android.content.Context;

public class InactiveDao {
	
	private static volatile InactiveDao instance = null;
	private TeamRubiconDb db;
	private List<Inactive> inactives = new ArrayList<Inactive>();
	
	private InactiveDao() {
	}

	public static InactiveDao getInstance() {
		if (instance == null) {
			synchronized (InactiveDao.class) {
				if (instance == null) {
					instance = new InactiveDao();
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
//		TeamRubiconDb.InactivesCursor inactiveCursor = db.getInactives();
//		for (int rowNum = 0; rowNum < inactiveCursor.getCount(); rowNum++) {
//			inactiveCursor.moveToPosition(rowNum);
//			
//			Inactive temp = new Inactive(WarehouseDao.getInstance().getWarehouseById(inactiveCursor.getColWarehouseId()), 
//									 ItemDao.getInstance().getItemById(inactiveCursor.getColItemId()));
//			inactives.add(temp);
//		}
	}
	
	public List<Inactive> getAllInactives() {
		return inactives;
	}
}
