package view;

import controller.ConverterFacade;
import model.ExchangeRateDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("forexManager")
@ConversationScoped
public class ForexManager implements Serializable {
    @EJB
    private ConverterFacade converterFacade;
    private ExchangeRateDTO forexDTO;
    private String fromCurrency;
    private String toCurrency;
    private double rate;
    private List<String> fromCurrencies;
    private List<String> toCurrencies;
    
// public ForexManager() {
// forexDTO = converterFacade.persistForex();
// currencies = converterFacade.getCurrencies();
// fromCurrency = currencies.get(0);
// toCurrency = currencies.get(1);
// }
    
    @PostConstruct
    public void init() {
        setDefaultCurrencies();
        fromCurrencies = converterFacade.getFromCurrencies();
        toCurrencies = converterFacade.getToCurrencies();
    }
    
    public void dummy() {
        System.out.println("fromCurrency: " + fromCurrency);
    }
    
    private String jsf22Bugfix() {
        return "";
    }
    
    public void calculateRate() {
        rate = converterFacade.getRate(fromCurrency, toCurrency);
    }
    
    public void setDefaultCurrencies() {
        fromCurrency = converterFacade.getCurrency(1);
        toCurrency = converterFacade.getCurrency(2);
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

    public ExchangeRateDTO getForexDTO() {
        return forexDTO;
    }

    public void setForexDTO(ExchangeRateDTO forexDTO) {
        this.forexDTO = forexDTO;
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
    
    
}