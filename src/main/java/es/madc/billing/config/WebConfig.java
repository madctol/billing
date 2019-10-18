package es.madc.billing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import es.madc.billing.request.converter.BillItemRequestToBillItemConverter;
import es.madc.billing.response.converter.BillItemToBillItemResponseConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
 
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // Damos de alta los converters
    	registry.addConverter(new BillItemRequestToBillItemConverter());
        registry.addConverter(new BillItemToBillItemResponseConverter());
    }
}