package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


@Entity
@IdClass(ExchangeRateKey.class)
public class ExchangeRate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String fromcurrency;
    @Id
    private String tocurrency;
    private double rate;

    public String getFromcurrency() {
        return fromcurrency;
    }

    public void setFromcurrency(String fromcurrency) {
        this.fromcurrency = fromcurrency;
    }

    public String getTocurrency() {
        return tocurrency;
    }

    public void setTocurrency(String tocurrency) {
        this.tocurrency = tocurrency;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }    
}
