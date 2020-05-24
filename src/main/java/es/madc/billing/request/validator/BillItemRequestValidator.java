package es.madc.billing.request.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.madc.billing.request.BillItemRequest;

/**
 * BillItemRequestValidator
 */
public class BillItemRequestValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BillItemRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        BillItemRequest b = (BillItemRequest) obj;
        if (b.getUnits() < 0) {
            errors.rejectValue("units", "negativevalue");
        }
        if (b.getPrice() == null || "".equals(b.getPrice())) {
            errors.rejectValue("price", "empty");
        } else if (!isNumber(b.getPrice())) {
            errors.rejectValue("price", "notanumber");
        }
    }

    private boolean isNumber(String number) {
        boolean res = false;
        try {
            Double.parseDouble(number);
            res = true;
        } catch (NumberFormatException e) {
            // No es un número
        }
        return res;
    }
}