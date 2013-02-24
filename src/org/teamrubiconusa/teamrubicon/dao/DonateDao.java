package org.teamrubiconusa.teamrubicon.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.teamrubiconusa.teamrubicon.model.Donate;
import org.teamrubiconusa.teamrubicon.model.TeamRubiconDb;

import android.content.Context;

public class DonateDao {

	private static volatile DonateDao instance = null;
	private TeamRubiconDb db;
	private List<Donate> donates = new ArrayList<Donate>();
	private final static String TIME_SEPARATOR = ":";
	
	private DonateDao() {
	}

	public static DonateDao getInstance() {
		if (instance == null) {
			synchronized (DonateDao.class) {
				if (instance == null) {
					instance = new DonateDao();
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
		TeamRubiconDb.DonatesCursor donateCursor = db.getDonates();
		for (int rowNum = 0; rowNum < donateCursor.getCount(); rowNum++) {
			donateCursor.moveToPosition(rowNum);
			
			Donate temp = new Donate(donateCursor.getColId(),
									 TypeDao.getInstance().getTypeByString(donateCursor.getColType()),
									 stringToCalendar(donateCursor.getColTime()));
			donates.add(temp);
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
	
	public List<Donate> getAllDonates() {
		return donates;
	}
}
