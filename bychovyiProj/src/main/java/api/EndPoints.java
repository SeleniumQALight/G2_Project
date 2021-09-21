package api;

public interface EndPoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{1}";
    String LOGIN = baseUrl + "/api/login";

    String basuUrlBrivatBank = "https://api.privatbank.ua";
    String CASH_RATE = basuUrlBrivatBank + "/p24api/pubinfo";
    String CREATE_POST = baseUrl + "/api/create-post";
    String DELETE_POSTS = baseUrl + "/api/post/{0}";
}
