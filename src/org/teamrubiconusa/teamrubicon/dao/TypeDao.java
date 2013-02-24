package org.teamrubiconusa.teamrubicon.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.teamrubiconusa.teamrubicon.model.TeamRubiconDb;
import org.teamrubiconusa.teamrubicon.model.Type;

import android.content.Context;
public class TypeDao {

	private static volatile TypeDao instance = null;

	private final Map<String, Type> types = new HashMap<String, Type>();
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
			types.put(typesCursor.getColId(), new Type(typesCursor.getColId()));
		}
	}

	public void addType(String type) {
		if (type == null || type.equals("")) {
			return;
		}
		if (types.get(type) != null) {
			types.put(type, new Type(type));
			db.addType(type);
		}
	}

	public void removeType(String type) {
		types.remove(type);
		db.deleteType(type);
	}

	public List<String> getStringTypes() {
		List<String> keyList = new ArrayList<String>();
		for (String s : types.keySet()) {
			keyList.add(s);
		}
		return keyList;
	}
	
	public Type getTypeByString(String type) {
		return types.get(type);
	}
}
