package org.teamrubiconusa.teamrubicon.model;

import java.util.Calendar;

public class Donate {

	int id;
	Type type;
	Calendar time;
	
	public Donate() {
		super();
	}

	public Donate(Type type, Calendar time) {
		super();
		this.type = type;
		this.time = time;
	}

	public Donate(int id, Type type, Calendar time) {
		super();
		this.id = id;
		this.type = type;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Calendar getTime() {
		return time;
	}

	public void setTime(Calendar time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donate other = (Donate) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Donate [id=" + id + ", type=" + type + ", time=" + time + "]";
	}
}
