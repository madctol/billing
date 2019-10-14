package es.madc.billing.response.converter;

import org.springframework.core.convert.converter.Converter;

import es.madc.billing.bean.BillItem;
import es.madc.billing.response.BillItemResponse;
import es.madc.billing.util.CalcUtil;

public class BillItemToBillItemResponseConverter implements Converter<BillItem, BillItemResponse> {

	@Override
	public BillItemResponse convert(BillItem from) {
		BillItemResponse item = new BillItemResponse();
		item.setDescription(from.getDescription());
		item.setUnits(from.getUnits());
		item.setPrice(CalcUtil.doubleToString(from.getPrice()));
		return item;
	}

}
