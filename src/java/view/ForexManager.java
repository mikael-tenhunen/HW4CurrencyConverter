package view;

import controller.ConverterFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("forexManager")
@RequestScoped
public class ForexManager implements Serializable {
    @EJB
    private ConverterFacade converterFacade;
    private String fromCurrency;
    private String toCurrency;
    private double rate;
    private List<String> fromCurrencies;
    private List<String> toCurrencies;
    private double amountToConvert;
    private double amountConverted;

    
    @PostConstruct
    public void init() {
        fromCurrencies = converterFacade.getFromCurrencies();
        toCurrencies = converterFacade.getToCurrencies();
        fromCurrency = fromCurrencies.get(0);
        toCurrency = toCurrencies.get(0);
    }
    
    public void convert() {
        rate = converterFacade.getRate(fromCurrency, toCurrency);
        amountConverted = converterFacade.convert(amountToConvert, rate);
    } 

    public List<String> getFromCurrencies() {
        return fromCurrencies;
    }

    public void setFromCurrencies(List<String> fromCurrencies) {
        this.fromCurrencies = fromCurrencies;
    }

    public List<String> getToCurrencies() {
        return toCurrencies;
    }

    public void setToCurrencies(List<String> toCurrencies) {
        this.toCurrencies = toCurrencies;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getAmountToConvert() {
        return amountToConvert;
    }

    public void setAmountToConvert(double amountToConvert) {
        this.amountToConvert = amountToConvert;
    }

    public double getAmountConverted() {
        return amountConverted;
    }

    public void setAmountConverted(double amountConverted) {
        this.amountConverted = amountConverted;
    }
}