package es.madc.billing.bean;

public class BillItem {
	
	private String description;
	
	private int units;
	
	private double price;
	
	private boolean taxed;
	
	private boolean imported;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isTaxed() {
		return taxed;
	}

	public void setTaxed(boolean taxed) {
		this.taxed = taxed;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BillItem [description=");
		builder.append(description);
		builder.append(", units=");
		builder.append(units);
		builder.append(", price=");
		builder.append(price);
		builder.append(", taxed=");
		builder.append(taxed);
		builder.append(", imported=");
		builder.append(imported);
		builder.append("]");
		return builder.toString();
	}

}
