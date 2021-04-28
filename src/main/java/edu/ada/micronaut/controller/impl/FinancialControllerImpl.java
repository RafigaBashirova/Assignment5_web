package edu.ada.micronaut.controller.impl;

import edu.ada.micronaut.controller.FinancialController;
import edu.ada.micronaut.service.FinancialService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/financial")
public class FinancialControllerImpl implements FinancialController {

    protected static final Logger log = LoggerFactory.getLogger(FinancialControllerImpl.class.getName());

    @Inject
    private FinancialService financialService;


    @Override
    @Get
    @Produces(MediaType.APPLICATION_JSON)
    public Object getFinanceData(@QueryValue("provider") String financial_data_provider,
                                 @QueryValue("stock_index") String stock_index) {
        if (financial_data_provider.equals("yahoo")) {
            log.info("*** Financial data provider::" + financial_data_provider + " ***");
            return financialService.getFinancialData(stock_index);
        } else {
            log.info("*** Financial data provider::" + financial_data_provider + " ***");
            return HttpResponse.badRequest("Provider name is invalid");
        }
    }

    @Override
    @Get("/batch")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getBatchFinanceData(@QueryValue("provider") String financial_data_provider) {
        if (financial_data_provider.equals("yahoo")) {
            log.info("*** Financial data provider::" + financial_data_provider + " ***");
            return financialService.getAllFinanceData();
        } else {
            log.info("*** Financial data provider::" + financial_data_provider + " ***");
            return HttpResponse.badRequest("Provider name is invalid");
        }
    }
}
