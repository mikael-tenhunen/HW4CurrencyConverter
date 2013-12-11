package model;

public interface ExchangeRateDTO {
    public Integer getId();
    public String getFromCurrency();
    public String getToCurrency();
    public double getRate();
// public List<String> getCurrencyList();
}