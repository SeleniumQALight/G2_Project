package api;

import java.net.URI;

public interface EndPoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{1}";

    String pbbaseUrl = "https://api.privatbank.ua/";
    String CCY_EXCHANGE = pbbaseUrl + "/p24api/pubinfo";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{0}";

    String LOGIN = baseUrl + "/api/login";
}
