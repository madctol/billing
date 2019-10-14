package es.madc.billing.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.madc.billing.bean.BillItem;
import es.madc.billing.bean.Bill;
import es.madc.billing.service.BillingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=BillingServiceImpl.class)
public class BillingServiceImplTest {
	
	@Autowired
	private BillingService billingSvc;
	
	private BillItem getBasicItem() {
		BillItem item = new BillItem();
		item.setDescription("cd");
		item.setPrice(14.99);
		item.setUnits(1);
		return item;
	}
	
	@Test
	public void testBasicWithoutTaxes() {
		BillItem item = getBasicItem();
		List<BillItem> items = new ArrayList<>(1);
		items.add(item);
		Bill res = billingSvc.calculateTaxes(items);
		
		Assert.assertNotNull(res);
		Assert.assertEquals(0, res.getTaxes(), 0.001d);
		Assert.assertEquals(14.99, res.getTotal(), 0.001d);
	}
	
	@Test
	public void testBasicWithTaxes() {
		BillItem item = getBasicItem();
		item.setTaxed(true);
		List<BillItem> items = new ArrayList<>(1);
		items.add(item);
		Bill res = billingSvc.calculateTaxes(items);
		
		Assert.assertNotNull(res);
		Assert.assertEquals(1.5, res.getTaxes(), 0.001d);
		Assert.assertEquals(16.49, res.getTotal(), 0.001d);
	}
	
	@Test
	public void testImportedWithTaxes() {
		BillItem item = getBasicItem();
		item.setTaxed(true);
		item.setImported(true);
		List<BillItem> items = new ArrayList<>(1);
		items.add(item);
		Bill res = billingSvc.calculateTaxes(items);
		
		Assert.assertNotNull(res);
		Assert.assertEquals(2.25, res.getTaxes(), 0.001d);
		Assert.assertEquals(17.24, res.getTotal(), 0.001d);
	}

}
