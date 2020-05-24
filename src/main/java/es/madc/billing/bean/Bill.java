package es.madc.billing.bean;

import java.math.BigDecimal;
import java.util.List;

public class Bill {
	
	private List<BillItem> items;
	
	private BigDecimal taxes;
	
	private BigDecimal total;

	public List<BillItem> getItems() {
		return items;
	}

	public void setItems(List<BillItem> items) {
		this.items = items;
	}

	public BigDecimal getTaxes() {
		return taxes;
	}

	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bill [items=");
		builder.append(items);
		builder.append(", taxes=");
		builder.append(taxes);
		builder.append(", total=");
		builder.append(total);
		builder.append("]");
		return builder.toString();
	}

}
