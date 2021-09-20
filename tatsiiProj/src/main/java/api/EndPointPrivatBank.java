package api;

public interface EndPointPrivatBank {
    String baseUrl = "https://api.privatbank.ua";
    String GET_API_CURRENCY = baseUrl + "/p24api/pubinfo?json&exchange";
}
// protocol://domain/endpoint?get params