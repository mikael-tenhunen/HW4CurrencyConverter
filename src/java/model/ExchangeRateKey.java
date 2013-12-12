package model;

import java.io.Serializable;

public class ExchangeRateKey implements Serializable{
    private String fromcurrency;
    private String tocurrency;
    
    public ExchangeRateKey(String fromcurrency, String tocurrency) {
        this.fromcurrency = fromcurrency;
        this.tocurrency = tocurrency;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fromcurrency != null ? fromcurrency.hashCode() : 0);
        hash += (tocurrency != null ? tocurrency.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExchangeRateKey)) {
            return false;
        }
        ExchangeRateKey other = (ExchangeRateKey) object;
        if (fromcurrency.equals(other.fromcurrency) 
                && tocurrency.equals(other.tocurrency)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "model.ExchangeRate[ fromcurrency=" + fromcurrency 
                + " tocurrency=" + tocurrency + " ]";
    }
}
