package apiPrivateBank;

public class CurrencyDTO {
    String ccy;
    String base_ccy;
    Float buy;
    Float sale;

    public CurrencyDTO(String ccy, String base_ccy, Float buy, Float sale) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.buy = buy;
        this.sale = sale;
    }

    public CurrencyDTO(){
    }

    public Float getBuy() {
        return buy;
    }

    public Float getSale() {
        return sale;
    }

    public String getCcy() {
        return ccy;
    }

    public String getBaseCcy() {
        return base_ccy;
    }

    public void setBuy(Float buy) {
        this.buy = buy;
    }

    public void setSale(Float sale){
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

