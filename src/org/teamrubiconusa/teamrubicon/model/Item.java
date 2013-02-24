package org.teamrubiconusa.teamrubicon.model;

public class Item {

	int id;
	String type;
	String condition;
	
	public Item() {
		super();
	}
	
	public Item(int id, String type, String condition) {
		super();
		this.id = id;
		this.type = type;
		this.condition = condition;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
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
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", type=" + type + ", condition=" + condition + "]";
	}
}
