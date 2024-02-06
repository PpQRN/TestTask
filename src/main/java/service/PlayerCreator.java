package service;

import endpoints.Endpoints;
import model.Player;

import static io.restassured.RestAssured.given;

public class PlayerCreator {

    public static Player createUserForTests(String login) {
        return PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT,
                        "supervisor", 20, "male", login, "user", "TestScrNameUser"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody().asString());
    }

    public static Player createAdminForTests() {
        return PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT,
                        "supervisor", 20, "male", "loginForTestsAdmin", "admin", "TestScrNameAdmin"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody().asString());
    }
}
