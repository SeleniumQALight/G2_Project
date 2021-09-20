package api;

import java.net.URI;

public interface Endpoints {
    String baseUrl = "http://qa-complex-app-for-testing.herokuapp.com";
    String POST_BY_USER = baseUrl + "/api/postsByAuthor/{1}";

    String LOGIN = baseUrl + "/api/login";
}
