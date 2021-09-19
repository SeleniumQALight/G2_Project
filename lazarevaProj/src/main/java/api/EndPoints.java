package api;

public interface EndPoints {

    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{2}";

    String LOGIN = baseUrl + "/api/login";

    String getPB24Url ="https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=";



}
