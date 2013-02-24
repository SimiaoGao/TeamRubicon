package org.teamrubiconusa.teamrubicon.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.teamrubiconusa.teamrubicon.model.Lent;
import org.teamrubiconusa.teamrubicon.model.TeamRubiconDb;

import android.content.Context;

public class LentDao {

	private static volatile LentDao instance = null;
	private TeamRubiconDb db;
	private List<Lent> lents = new ArrayList<Lent>();
	private final static String TIME_SEPARATOR = ":";
	
	private LentDao() {
	}

	public static LentDao getInstance() {
		if (instance == null) {
			synchronized (LentDao.class) {
				if (instance == null) {
					instance = new LentDao();
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
		TeamRubiconDb.LentsCursor lentCursor = db.getLents();
		for (int rowNum = 0; rowNum < lentCursor.getCount(); rowNum++) {
			lentCursor.moveToPosition(rowNum);
			
			Lent temp = new Lent(PersonDao.getInstance().getPersonById(lentCursor.getColPersonId()), 
									 ItemDao.getInstance().getItemById(lentCursor.getColItemId()), 
									 stringToCalendar(lentCursor.getColTime()));
			lents.add(temp);
		}
	}
	
	private static Calendar stringToCalendar(String time) {
		String[] hourAndMinute = time.split(TIME_SEPARATOR);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(0);
		calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourAndMinute[0]));
		calendar.set(Calendar.MINUTE, Integer.parseInt(hourAndMinute[1]));
		return calendar;
	}
	
	public List<Lent> getAllLents() {
		return lents;
	}
}
