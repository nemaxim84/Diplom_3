package user;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class User {
    private String name = "Max";
    private String email = "max@ya.ru";
    private String password = "123456";
    public static final String shortPass = "123";

    public String getName() {
        return name = (int) (Math.random() * ((100 - 50) + 1) + 50) + name;
    }

    public String getEmail() {
        return email = (int) (Math.random() * ((100 - 50) + 1) + 50) + email;
    }

    public String getPassword() {
        return password;
    }

    public void createUser(String name, String email, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("password", password);
        body.put("email", email);
        body.put("name", name);
        Response response = given()
                .header("Content-type", "application/json")
                .body(body)
                .when()
                .post(RequestSpecification.baseUrl + RequestSpecification.basePath + RequestSpecification.regUser);
        System.out.println(RequestSpecification.baseUrl + RequestSpecification.basePath + RequestSpecification.regUser);
    }

    public void deleteUser(String email, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("password", password);
        body.put("email", email);

        Response response = given()
                .header("Content-type", "application/json")
                .body(body)
                .when()
                .post(RequestSpecification.baseUrl + RequestSpecification.basePath + RequestSpecification.login);
        String token = response.path("accessToken");

        Response responseNew = given()
                .auth().oauth2(token.substring("Bearer ".length()))
                .delete(RequestSpecification.baseUrl + RequestSpecification.basePath + RequestSpecification.authUser);
    }
}
