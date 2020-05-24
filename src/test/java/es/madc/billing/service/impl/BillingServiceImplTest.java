package es.madc.billing.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.madc.billing.bean.Bill;
import es.madc.billing.bean.BillItem;
import es.madc.billing.service.BillingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BillingServiceImpl.class)
public class BillingServiceImplTest {
	
	@Autowired
	private BillingService billingSvc;
	
	private BillItem getItem(boolean taxed, boolean imported) {
		BillItem item = new BillItem();
		item.setDescription("cd");
		item.setPrice(new BigDecimal("14.99"));
		item.setUnits(1);
		item.setTaxed(taxed);
		item.setImported(imported);
		return item;
	}
	
	@Test
	public void testBasicWithoutTaxes() {
		List<BillItem> items = new ArrayList<>(1);
		items.add(getItem(false, false));
		Bill res = billingSvc.calculateTaxes(items);
		
		Assert.assertNotNull(res);
		Assert.assertEquals(new BigDecimal("0.00"), res.getTaxes());
		Assert.assertEquals(new BigDecimal("14.99"), res.getTotal());
	}
	
	@Test
	public void testBasicWithTaxes() {
		BillItem item = getItem(true, false);
		List<BillItem> items = new ArrayList<>(1);
		items.add(item);
		Bill res = billingSvc.calculateTaxes(items);
		
		Assert.assertNotNull(res);
		Assert.assertEquals(new BigDecimal("1.50"), res.getTaxes());
		Assert.assertEquals(new BigDecimal("16.49"), res.getTotal());
	}
	
	@Test
	public void testImportedWithTaxes() {
		BillItem item = getItem(true, true);
		List<BillItem> items = new ArrayList<>(1);
		items.add(item);
		Bill res = billingSvc.calculateTaxes(items);
		
		Assert.assertNotNull(res);
		Assert.assertEquals(new BigDecimal("2.25"), res.getTaxes());
		Assert.assertEquals(new BigDecimal("17.24"), res.getTotal());
	}
	
	@Test
	public void testMixedItems() {
		List<BillItem> items = new ArrayList<>(4);
		items.add(getItem(false, false));
		items.add(getItem(true, false));
		items.add(getItem(false, true));
		items.add(getItem(true, true));
		Bill res = billingSvc.calculateTaxes(items);
		
		Assert.assertNotNull(res);
		Assert.assertEquals(new BigDecimal("4.50"), res.getTaxes());
		Assert.assertEquals(new BigDecimal("64.46"), res.getTotal());
	}

}
