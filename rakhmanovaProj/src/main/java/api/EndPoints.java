package api;


public interface EndPoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{1}";


    String LOGIN = baseUrl + "/api/login";

    String privatBankUrl = "https://api.privatbank.ua";
    String CURRENCY_LIST = privatBankUrl + "/p24api/pubinfo"; //?json&exchange&coursid=5

}
