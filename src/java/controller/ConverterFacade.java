package controller;

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
import model.ExchangeRate;
import model.ExchangeRateDTO;

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
        Query findRate = em.createQuery("SELECT e.rate FROM ExchangeRate e WHERE "
                + "e.fromcurrency = :fromCurrency AND "
                + "e.tocurrency = :toCurrency");
        findRate.setParameter("fromCurrency", fromCurrency);
        findRate.setParameter("toCurrency", toCurrency);
        return (double) findRate.getSingleResult();
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
}