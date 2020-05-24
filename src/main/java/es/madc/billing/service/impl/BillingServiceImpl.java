package es.madc.billing.service.impl;

import java.math.BigDecimal;
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
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal taxes = BigDecimal.ZERO;
		if (items != null) {
			BigDecimal itemTaxes;
			BigDecimal itemTotalPrice;
			for (BillItem item : items) {
				itemTaxes = CalcUtil.calcTaxes(item.getPrice(), getItemTaxes(item));
				itemTotalPrice = item.getPrice().add(itemTaxes);
				taxes = taxes.add(itemTaxes);
				total = total.add(itemTotalPrice);
				item.setPrice(itemTotalPrice);
			}
		}
		Bill bill = new Bill();
		bill.setTaxes(taxes);
		bill.setTotal(total);
		return bill;
	}

	private BigDecimal getItemTaxes(BillItem item) {
		BigDecimal tax = BigDecimal.ZERO;
		if (item.isImported()) {
			tax = tax.add(new BigDecimal(5));
		}
		if (item.isTaxed()) {
			tax = tax.add(new BigDecimal(10));
		}
		return tax;
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
