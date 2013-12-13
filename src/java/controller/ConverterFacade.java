package controller;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.ExchangeRate;
import model.ExchangeRateKey;


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ConverterFacade {
    @PersistenceContext(unitName = "HW4CurrencyConverterPU")
    private EntityManager em;
    
    public double getRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return 1.0;
        }
        else {
            return em.find(ExchangeRate.class, new ExchangeRateKey(fromCurrency, toCurrency)).getRate();
        }
    }
    
    public List<String> getFromCurrencies() {
        Query allCurrencies = em.createQuery("SELECT DISTINCT e.fromcurrency FROM ExchangeRate e");
        List<String> currencies = (List<String>) allCurrencies.getResultList();
        return currencies;
    }
    
    public List<String> getToCurrencies() {
        Query allCurrencies = em.createQuery("SELECT DISTINCT e.tocurrency FROM ExchangeRate e");
        List<String> currencies = (List<String>) allCurrencies.getResultList();
        return currencies;
    }
   
    public double convert(double amount, double rate) {
        //convert to BigDecimal to avoid floating point rounding errors
        //this eats up server resources but gives us more precise results.
        BigDecimal amountBD = new BigDecimal(amount);
        BigDecimal rateBD = new BigDecimal(rate);
        return amountBD.multiply(rateBD).setScale(2, BigDecimal.ROUND_HALF_UP).
                doubleValue();
    }
}