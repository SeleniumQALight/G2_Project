package api;

public interface EndPoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";
    String LOGIN = baseUrl + "/api/login";

    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{0}";

    String baseUrlPrivat = "https://api.privatbank.ua/";
    String EXCHANGE_RATES = baseUrlPrivat + "/p24api/pubinfo";
}
