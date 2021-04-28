package edu.ada.micronaut.service.impl;

import edu.ada.micronaut.service.FinancialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.inject.Singleton;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@Singleton
public class YahooFinanceServiceImpl implements FinancialService {

    protected static final Logger log = LoggerFactory.getLogger(YahooFinanceServiceImpl.class.getName());


    @Override
    public Object getFinancialData(String stock_index) {
        Stock stock = null;
        BigDecimal price = BigDecimal.ONE;
        try {
            stock = YahooFinance.get(stock_index);
            price = stock.getQuote(true).getPrice();
            log.info("*** stock price ::" + price + " ***");
            return price;

        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return price;

    }

    @Override
    public Object getAllFinanceData() {

        Map<String, Stock> stocks = null;
        try {
            String[] symbols = new String[] {"INTC", "BABA", "TSLA", "AIR.PA", "GOOGL", "YHOO"};
            log.info("*** STOCK INDEXES:: \"INTC\", \"BABA\", \"TSLA\", \"AIR.PA\", \"GOOGL\", \"YHOO\"  ***");
            stocks = YahooFinance.get(symbols);
            log.info("*** stock price ::" + stocks.toString() + " ***");

        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return stocks.toString();
    }

}
