package org.teamrubiconusa.teamrubicon.model;

import java.util.Calendar;

public class Active {

	Warehouse warehouse;
	Item item;
	Calendar time;
	
	public Active() {
		super();
	}

	public Active(Warehouse warehouse, Item item, Calendar time) {
		super();
		this.warehouse = warehouse;
		this.item = item;
		this.time = time;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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
		result = prime * result + ((item == null) ? 0 : item.hashCode());
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
		Active other = (Active) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Active [warehouse=" + warehouse + ", item=" + item + ", time=" + time.getTime().toString() + "]";
	}
}
