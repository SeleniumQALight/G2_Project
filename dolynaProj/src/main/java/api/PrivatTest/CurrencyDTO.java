package api.PrivatTest;

import com.google.gson.annotations.SerializedName;

public class CurrencyDTO {
    @SerializedName("ccy")
    String ccy;
    @SerializedName("base_ccy")
    String base_ccy;
    @SerializedName("buy")
    String buy;
    @SerializedName("sale")
    String sale;

    public CurrencyDTO(String ccy, String base_ccy, String buy, String sale){
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.buy = buy;
        this.sale = sale;
    }
    public CurrencyDTO(String usd, String uah) {

    }
    public String getCcy(){
        return ccy;
    }
    public String getBase_ccy(){
        return base_ccy;
    }
    public String getBuy(){
        return buy;
    }
    public String getSale(){
        return sale;
    }
    @Override
    public String toString(){
        return "CurrencyDTO{" +
                "ccy='" + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", sale='" + sale + '\'' +
                '}';


    }


    }

