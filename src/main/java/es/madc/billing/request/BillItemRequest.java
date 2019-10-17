package es.madc.billing.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about an bill item")
public class BillItemRequest {
	
	@ApiModelProperty(notes = "The item description")
	protected String description;
	
	@ApiModelProperty(notes = "The units of the item")
	protected int units;
	
	@ApiModelProperty(notes = "The item price [XX.XX]")
	protected String price;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BillItemRequest [description=");
		builder.append(description);
		builder.append(", units=");
		builder.append(units);
		builder.append(", price=");
		builder.append(price);
		builder.append("]");
		return builder.toString();
	}

}
