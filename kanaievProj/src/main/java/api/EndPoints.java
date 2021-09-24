package api;

public interface EndPoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{1}";
    String LOGIN = baseUrl + "/api/login";
    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POST = baseUrl + "/api/post/{0}";

    String PRIVAT_BASE_URL = "https://api.privatbank.ua";
    String GET_CURRENCY_COURSE_PRIVAT = PRIVAT_BASE_URL + "/p24api/pubinfo";
}
