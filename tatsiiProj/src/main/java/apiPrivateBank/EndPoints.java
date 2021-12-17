package apiPrivateBank;

public interface EndPoints {
    String apiBaseUrl = "https://api.privatbank.ua/p24api";
    String uiBaseUrl = "https://next.privat24.ua/exchange-rates";

    String API_GET_EXCHANGE_COURSE = apiBaseUrl + "/pubinfo?json&exchange&coursid={0}";
    String UI_GET_EXCHANGE_COURSE = uiBaseUrl;
}