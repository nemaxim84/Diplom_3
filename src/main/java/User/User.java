package User;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class User {
    public void deleteUser(String email, String password) {
        String body = "{\"email\":\"" + email + "\","
                + "\"password\":\"" + password + "\"}";
        Response response = given()
                .header("Content-type", "application/json")
                .body(body)
                .when()
                .post("https://stellarburgers.nomoreparties.site/api/auth/login");
        String token = response.path("accessToken");

        Response response1 = given()
                .auth().oauth2(token.substring("Bearer ".length()))
                .delete("https://stellarburgers.nomoreparties.site/api/auth/user");
    }
}
