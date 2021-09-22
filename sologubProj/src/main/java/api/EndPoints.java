package api;

public interface EndPoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{1}";
    String LOGIN = baseUrl + "/api/login";
    String privatBankBaseUrl = "https://api.privatbank.ua";
    String PRIVATE_BANK_CURRENCY_URL = privatBankBaseUrl + "/p24api/pubinfo";

}
