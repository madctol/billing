package es.madc.billing.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import es.madc.billing.bean.Bill;
import es.madc.billing.bean.BillItem;
import es.madc.billing.service.BillingService;
import es.madc.billing.util.CalcUtil;

@Service
public class BillingServiceImpl implements BillingService {
	
	private static final List<String> NO_TAXES = Arrays.asList(
		"book", "food", "medical", 
		"chocolate", "pills"); 

	@Override
	public Bill calculateTaxes(List<BillItem> items) {
		double total = 0d;
		double taxes = 0d;
		if (items != null) {
			double itemTaxes;
			double itemTotalPrice;
			for (BillItem item : items) {
				itemTotalPrice = item.getPrice();
				itemTaxes = CalcUtil.calcTaxes(itemTotalPrice, getItemTax(item));
				itemTotalPrice += itemTaxes;
				taxes += itemTaxes;
				total += itemTotalPrice;
				item.setPrice(itemTotalPrice);
			}
		}
		Bill bill = new Bill();
		bill.setTaxes(taxes);
		bill.setTotal(total);
		return bill;
	}

	private int getItemTax(BillItem item) {
		return (item.isImported() ? 5 : 0) + (item.isTaxed() ? 10 : 0);
	}

	@Override
	public boolean hasTaxes(BillItem item) {
		return NO_TAXES.stream()
					.filter(elem -> item.getDescription().contains(elem))
					.findAny().orElse(null) == null;
	}
	
	@Override
	public boolean isImported(BillItem item) {
		return item != null && item.getDescription() != null && item.getDescription().contains("imported");
	}

}
