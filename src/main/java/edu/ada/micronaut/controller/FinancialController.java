package edu.ada.micronaut.controller;

public interface FinancialController {


    public Object getFinanceData(String financial_data_provider, String stock_index);

    public Object getBatchFinanceData(String financial_data_provider);
}
