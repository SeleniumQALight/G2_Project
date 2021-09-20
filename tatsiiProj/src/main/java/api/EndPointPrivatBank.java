package api;

public interface EndPointPrivatBank {
    String baseUrl = "https://api.privatbank.ua";
    String POST_BY_USER = baseUrl + "/p24api/pubinfo?json&exchange&coursid=5/{0}";

    String LOGIN = baseUrl + "/api/login";
}
