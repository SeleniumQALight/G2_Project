package apiPrivateBank;

public class CurrencyDTO {
    String ccy;
    String base_ccy;
    String buy;
    String sale;

    public CurrencyDTO(String ccy, String base_ccy, String buy, String sale) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.buy = buy;
        this.sale = sale;
    }

    public CurrencyDTO(){
    }

    public String getBuy() {
        return buy;
    }

    public String getSale() {
        return sale;
    }

    public String getCcy() {
        return ccy;
    }

    public String getBaseCcy() {
        return base_ccy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public void setSale(String sale){
        this.sale = sale;
    }

    public void setCcy(String ccy){
        this.ccy = ccy;
    }

    public void setBase_ccy(String base_ccy){
        this.base_ccy = base_ccy;
    }


    @Override
    public String toString() {
        return "CurrencyDTO{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                '}';
    }
}

