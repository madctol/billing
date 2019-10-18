package es.madc.billing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.madc.billing.bean.Bill;
import es.madc.billing.bean.BillItem;
import es.madc.billing.request.BillItemRequest;
import es.madc.billing.response.BillItemResponse;
import es.madc.billing.response.BillResponse;
import es.madc.billing.service.BillingService;
import es.madc.billing.util.CalcUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/billing")
@Api(value = "Billing generation", description = "Operations to generate bills")
public class BillingController {
	
	@Autowired
	private ConversionService conversionService;

	@Autowired
	private BillingService billingSvc;
	
	@ApiOperation(value = "Calculate the taxes of several items", response = BillResponse.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Bill generated"),
		@ApiResponse(code = 400, message = "Invalid param")
	})
	@PostMapping("/")
	public BillResponse generate(@ApiParam(name="bill items") @RequestBody List<BillItemRequest> items) {
		
		// Convertimos los objetos de entrada en objetos de servicio
		List<BillItem> billItems = new ArrayList<>();
		if (items!=null) {
			for (BillItemRequest item: items) {
				billItems.add(conversionService.convert(item, BillItem.class));
			}
		}
		
		// Consultamos si a cada objeto hay que aplicarle impuestos
		processInputData(billItems);
	
		// Calculamos los impuestos
		Bill billResult = billingSvc.calculateTaxes(billItems);
		
		// Convertimos los objetos de servicio a objetos de respuesta
		BillResponse billResponse = new BillResponse();
		if (billResult!=null && billResult.getItems()!=null) {
			billResponse.setItems(new ArrayList<>());
			for (BillItem item: billResult.getItems()) {
				billResponse.getItems().add(conversionService.convert(item, BillItemResponse.class));
			}
			billResponse.setTaxes(CalcUtil.doubleToString(billResult.getTaxes()));
			billResponse.setTotal(CalcUtil.doubleToString(billResult.getTotal()));
		}
		return billResponse;
	}

	private void processInputData(List<BillItem> billItems) {
		for (BillItem item: billItems) {
			item.setImported(billingSvc.isImported(item));
			item.setTaxed(billingSvc.hasTaxes(item));
		}
	}

}
