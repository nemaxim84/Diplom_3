package user;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserClient {

    public void createUserRest(String name, String email, String password) {

        Map<String, String> body = new HashMap<>();
        body.put("password", password);
        body.put("email", email);
        body.put("name", name);
        given()
                .header("Content-type", "application/json")
                .body(body)
                .when()
                .post(EndPoint.BASE_URL + EndPoint.BASE_PATH + EndPoint.REG_USER);
    }

    public void deleteUser(String email, String password) {
        Map<String, String> body = new HashMap<>();
        body.put("password", password);
        body.put("email", email);

        Response response = given()
                .header("Content-type", "application/json")
                .body(body)
                .when()
                .post(EndPoint.BASE_URL + EndPoint.BASE_PATH + EndPoint.LOGIN);
        String token = response.path("accessToken");

        given()
                .auth().oauth2(token.substring("Bearer ".length()))
                .delete(EndPoint.BASE_URL + EndPoint.BASE_PATH + EndPoint.AUTH_USER);
    }
}
