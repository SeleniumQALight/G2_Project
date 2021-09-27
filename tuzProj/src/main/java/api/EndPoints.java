package api;


public interface EndPoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";

    String LOGIN = baseUrl + "/api/login";

    String privatBaseUrl = "https://api.privatbank.ua";
    String CURRENCY_EXCHANGE = privatBaseUrl + "/p24api/pubinfo?json&exchange";


    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{1}";
}