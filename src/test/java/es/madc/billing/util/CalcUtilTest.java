package es.madc.billing.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CalcUtil.class)
public class CalcUtilTest {
	
	@Test
	public void roundTest() {
		
		Assert.assertEquals(1.5, CalcUtil.round(1.499), 0.0d);
		Assert.assertEquals(7.15, CalcUtil.round(7.125), 0.0d);
		Assert.assertEquals(4.2, CalcUtil.round(4.1985), 0.0d);
		Assert.assertEquals(1.9, CalcUtil.round(1.899), 0.0d);
		Assert.assertEquals(0.6, CalcUtil.round(0.5625), 0.0d);
		
	}
	
	@Test
	public void calcTaxesTest() {
		
		Assert.assertEquals(1.5, CalcUtil.calcTaxes(14.99,10), 0.0d);
		Assert.assertEquals(7.15, CalcUtil.calcTaxes(47.5,15), 0.0d);
		Assert.assertEquals(4.2, CalcUtil.calcTaxes(27.99,15), 0.0d);
		Assert.assertEquals(1.9, CalcUtil.calcTaxes(18.99,10), 0.0d);
		Assert.assertEquals(0.6, CalcUtil.calcTaxes(11.25,5), 0.0d);
		
	}

}
