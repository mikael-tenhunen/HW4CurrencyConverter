package controller;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
*
* @author miikka
*/
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
            Query findRate = em.createQuery("SELECT e.rate FROM ExchangeRate e WHERE "
                    + "e.fromcurrency = :fromCurrency AND "
                    + "e.tocurrency = :toCurrency");
            findRate.setParameter("fromCurrency", fromCurrency);
            findRate.setParameter("toCurrency", toCurrency);
            return (double) findRate.getSingleResult();
        }
    }
    
    public String getCurrency(int id) {
        Query findCurrency = em.createQuery("SELECT e.fromcurrency FROM ExchangeRate e "
                + "WHERE e.id = :id");
        findCurrency.setParameter("id", id);
        return (String) findCurrency.getSingleResult();
    }
    
    public List<String> getFromCurrencies() {
        Query allCurrencies = em.createQuery("SELECT e.fromcurrency FROM ExchangeRate e");
        List<String> currencies = (List<String>) allCurrencies.getResultList();
        return removeDuplicates(currencies);
    }
    
    public List<String> getToCurrencies() {
        Query allCurrencies = em.createQuery("SELECT e.tocurrency FROM ExchangeRate e");
        List<String> currencies = (List<String>) allCurrencies.getResultList();
        return removeDuplicates(currencies);
    }
    
    private List<String> removeDuplicates(List<String> list) {
         Set<String> set = new HashSet();
         set.addAll(list);
         List<String> noDuplicates = new LinkedList(set);
         return noDuplicates;
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