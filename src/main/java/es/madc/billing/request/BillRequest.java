package es.madc.billing.request;

import java.util.List;

public class BillRequest {
	
	private List<BillItemRequest> items;

	public List<BillItemRequest> getItems() {
		return items;
	}

	public void setItems(List<BillItemRequest> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BillRequest [items=");
		builder.append(items);
		builder.append("]");
		return builder.toString();
	}

}
