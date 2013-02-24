package org.teamrubiconusa.teamrubicon.dao;

import java.util.ArrayList;
import java.util.List;

import org.teamrubiconusa.teamrubicon.model.TeamRubiconDb;

import android.content.Context;
public class TypeDao {

	private static volatile TypeDao instance = null;

	private final List<String> types = new ArrayList<String>();
	private TeamRubiconDb db;

	private TypeDao() {
	}
	
	public static TypeDao getInstance() {
		if (instance == null) {
			synchronized (TypeDao.class) {
				if (instance == null) {
					instance = new TypeDao();
				}
			}
		}
		return instance;
	}
	
	public void setContext(Context context) {
		db = new TeamRubiconDb(context);
		populateList();
	}

	private void populateList() {
		TeamRubiconDb.TypesCursor typesCursor = db.getType();
		for (int rowNum = 0; rowNum < typesCursor.getCount(); rowNum++) {
			typesCursor.moveToPosition(rowNum);
			types.add(typesCursor.getColId());
		}
	}

	public void addType(String type) {
		if (type == null || type.equals("")) {
			return;
		}
		if (!types.contains(type)) {
			types.add(type);
			db.addType(type);
		}
	}

	public void removeType(String type) {
		types.remove(type);
		db.deleteType(type);
	}

	public List<String> getTypes() {
		return types;
	}
}
