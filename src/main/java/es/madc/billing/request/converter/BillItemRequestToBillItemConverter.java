package es.madc.billing.request.converter;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;

import es.madc.billing.bean.BillItem;
import es.madc.billing.request.BillItemRequest;

public class BillItemRequestToBillItemConverter implements Converter<BillItemRequest, BillItem> {

	@Override
	public BillItem convert(BillItemRequest from) {
		BillItem item = new BillItem();
		item.setDescription(from.getDescription());
		item.setPrice(new BigDecimal(from.getPrice()));
		item.setUnits(from.getUnits());
		return item;
	}

}
