package org.teamrubiconusa.teamrubicon.model;

public class Inactive {

	Warehouse warehouse;
	Item item;
	int amount;
	
	public Inactive() {
		super();
	}

	public Inactive(Warehouse warehouse, Item item, int amount) {
		super();
		this.warehouse = warehouse;
		this.item = item;
		this.amount = amount;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
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
		Inactive other = (Inactive) obj;
		if (amount != other.amount)
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (warehouse == null) {
			if (other.warehouse != null)
				return false;
		} else if (!warehouse.equals(other.warehouse))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Inactive [warehouse=" + warehouse + ", item=" + item + ", amount=" + amount + "]";
	}
}
