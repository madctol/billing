package es.madc.billing.response;

import java.util.List;

public class BillResponse {
	
	private List<BillItemResponse> items;

	private String taxes;
	
	private String total;

	public List<BillItemResponse> getItems() {
		return items;
	}

	public void setItems(List<BillItemResponse> items) {
		this.items = items;
	}

	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BillResponse [items=");
		builder.append(items);
		builder.append(", taxes=");
		builder.append(taxes);
		builder.append(", total=");
		builder.append(total);
		builder.append("]");
		return builder.toString();
	}
	
}
