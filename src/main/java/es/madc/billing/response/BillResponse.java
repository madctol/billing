package es.madc.billing.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "The bill generated")
public class BillResponse {
	
	@ApiModelProperty(notes = "The items with the prices with taxes included")
	private List<BillItemResponse> items;

	@ApiModelProperty(notes = "The total taxes to pay")
	private String taxes;
	
	@ApiModelProperty(notes = "The total price to pay, taxes included")
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
