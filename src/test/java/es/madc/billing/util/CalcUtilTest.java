package es.madc.billing.util;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CalcUtil.class)
public class CalcUtilTest {
	
	@Test
	public void formatPriceTest() {
		
		Assert.assertEquals("1.50", CalcUtil.formatPrice(new BigDecimal("1.499")));
		Assert.assertEquals("7.15", CalcUtil.formatPrice(new BigDecimal("7.125")));
		Assert.assertEquals("4.20", CalcUtil.formatPrice(new BigDecimal("4.1985")));
		Assert.assertEquals("1.90", CalcUtil.formatPrice(new BigDecimal("1.899")));
		Assert.assertEquals("0.60", CalcUtil.formatPrice(new BigDecimal("0.5625")));
		
	}
	
	@Test
	public void calcTaxesTest() {
		
		Assert.assertEquals(new BigDecimal("0.00"), CalcUtil.calcTaxes(new BigDecimal("14.99"), new BigDecimal("0")));
		Assert.assertEquals(new BigDecimal("1.50"), CalcUtil.calcTaxes(new BigDecimal("14.99"), new BigDecimal("10")));
		Assert.assertEquals(new BigDecimal("7.15"), CalcUtil.calcTaxes(new BigDecimal("47.50"), new BigDecimal("15")));
		Assert.assertEquals(new BigDecimal("4.20"), CalcUtil.calcTaxes(new BigDecimal("27.99"), new BigDecimal("15")));
		Assert.assertEquals(new BigDecimal("1.90"), CalcUtil.calcTaxes(new BigDecimal("18.99"), new BigDecimal("10")));
		Assert.assertEquals(new BigDecimal("0.60"), CalcUtil.calcTaxes(new BigDecimal("11.25"), new BigDecimal("5")));
		
	}

}
