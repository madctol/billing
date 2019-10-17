package es.madc.billing.request.converter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.test.context.junit4.SpringRunner;

import es.madc.billing.bean.BillItem;
import es.madc.billing.request.BillItemRequest;

/**
 * BillItemRequestToBillItemConverterTest
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillItemRequestToBillItemConverter.class)
public class BillItemRequestToBillItemConverterTest {

    private FormattingConversionService conversionService;

    /** Basic initialisation before unit test fires. */
    @Before
    public void setUp() {
        conversionService = new FormattingConversionService();
        conversionService.addConverter(new BillItemRequestToBillItemConverter());
    }

    @Test
    public void convertTest() {

        BillItemRequest b = new BillItemRequest();
        b.setDescription("description");
        b.setUnits(1);
        b.setPrice("0.99");
        BillItem res = conversionService.convert(b, BillItem.class);

        Assert.assertNotNull(res);
        Assert.assertEquals(b.getDescription(), res.getDescription());
        Assert.assertEquals(b.getUnits(), res.getUnits());
        Assert.assertEquals(Double.parseDouble(b.getPrice()), 0.99d, 0.001);

    }

    @Test(expected = ConversionFailedException.class)
    public void invalidConvertTest() {

        BillItemRequest b = new BillItemRequest();
        b.setDescription("description");
        b.setUnits(1);
        b.setPrice("0,99");
        conversionService.convert(b, BillItem.class);

    }

}