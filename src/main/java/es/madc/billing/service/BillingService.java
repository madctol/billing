package es.madc.billing.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.madc.billing.bean.Bill;
import es.madc.billing.bean.BillItem;

@Service
public interface BillingService {
	
	public Bill calculateTaxes(List<BillItem> items);
	
	public boolean hasTaxes(BillItem item);
	
	public boolean isImported(BillItem item);

}
