package org.teamrubiconusa.teamrubicon.dao;

import java.util.HashMap;
import java.util.Map;

import org.teamrubiconusa.teamrubicon.model.Item;
import org.teamrubiconusa.teamrubicon.model.Person;
import org.teamrubiconusa.teamrubicon.model.TeamRubiconDb;

import android.content.Context;

public class PersonDao {

	private static volatile PersonDao instance = null;
	private TeamRubiconDb db;
	private Map<Integer, Person> persons = new HashMap<Integer, Person>();
	
	private PersonDao() {
	}

	public static PersonDao getInstance() {
		if (instance == null) {
			synchronized (PersonDao.class) {
				if (instance == null) {
					instance = new PersonDao();
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
		TeamRubiconDb.PersonsCursor PersonCursor = db.getPersons();
		for (int rowNum = 0; rowNum < PersonCursor.getCount(); rowNum++) {
			PersonCursor.moveToPosition(rowNum);
			Person temp = new Person(PersonCursor.getColId(), PersonCursor.getColName(), PersonCursor.getColRole(), PersonCursor.getColPhone());
			persons.put(PersonCursor.getColId(), temp);
		}
	}
	
	public Map<Integer, Person> getPersonMap(){
		return persons;
	}
	
	public Person getPersonById(int id) {
		return persons.get(new Integer(id));
	}
	 
	public boolean addPerson(Person person) {
		if (persons.get(person.getId()) == null) {
			person.getId();
			db.addPerson(person.getId(), person.getName(), person.getRole(), person.getPhone());
			persons.put(person.getId(), person);
			return true;
		}
		return false;
	}
}
