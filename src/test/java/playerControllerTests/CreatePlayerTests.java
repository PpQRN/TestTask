package playerControllerTests;

import endpoints.Endpoints;
import io.restassured.response.ValidatableResponse;
import model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import service.PlayerDeleter;
import service.PlayerParser;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CreatePlayerTests {
    private static final Logger logger = LogManager.getLogger(CreatePlayerTests.class);
    Player testPlayer;

    @Test
    public void createPlayerWithSupervisorRole() {
       testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT,
                        "supervisor", 17, "male", "testLogin", "user", "testName"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody().asString());
    }

    //BUG
    @Test
    public void createPlayerWithAdminRole() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT,
                        "admin", 17, "male", "testLogin", "user", "testName"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithUserRole() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT,
                        "user", 17, "male", "testLogin", "user", "testName"))
                .then()
                .statusCode(403)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithWrongGender() {
        ValidatableResponse response = given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT,
                        "supervisor", 17, "deer", "testLogin", "user", "testName"))
                .then();
        testPlayer = PlayerParser.parseResponseBodyToPlayer(response
                .extract()
                .response()
                .getBody().asString());
        response.statusCode(400);
    }

    //BUG
    @Test
    public void createPlayerWithAgeLessThen16() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT,
                        "supervisor", 15, "male", "testLogin", "user", "testName"))
                .then()
                .statusCode(400)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithAgeMoreThen60() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT,
                        "supervisor", 61, "male", "testLogin", "user", "testName"))
                .then()
                .statusCode(400)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithAge60() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT,
                        "supervisor", 60, "male", "testLogin", "user", "testName"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody().asString());
    }

    //BUG
    @Test
    public void createPlayerWithAge16() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT,
                        "supervisor", 16, "male", "testLogin", "user", "testName"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithValidPass() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITH_PASSWORD_ENDPOINT,
                        "supervisor", 20, "male", "testLogin", "qwera2007", "user", "testName"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithLessThen7CharPass() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITH_PASSWORD_ENDPOINT,
                        "supervisor", 15, "male", "testLogin", "qwe20", "user", "testName"))
                .then()
                .statusCode(400)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithMoreThen15CharPass() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITH_PASSWORD_ENDPOINT,
                        "supervisor", 15, "male", "testLogin", "qqwweerraa22000077", "user", "testName"))
                .then()
                .statusCode(400)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWith7CharPass() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITH_PASSWORD_ENDPOINT,
                        "supervisor", 20, "male", "testLogin", "qwera20", "user", "testName"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWith15CharPass() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITH_PASSWORD_ENDPOINT,
                        "supervisor", 20, "male", "testLogin", "qqwweerra200007", "user", "testName"))
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithLetterPass() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITH_PASSWORD_ENDPOINT,
                        "supervisor", 15, "male", "testLogin", "qweraaaa", "user", "testName"))
                .then()
                .statusCode(400)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithNumberPass() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITH_PASSWORD_ENDPOINT,
                        "supervisor", 15, "male", "testLogin", "123452007", "user", "testName"))
                .then()
                .statusCode(400)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithSpecialSymbolsPass() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITH_PASSWORD_ENDPOINT,
                        "supervisor", 15, "male", "testLogin", "qwera2$$$007", "user", "testName"))
                .then()
                .statusCode(400)
                .extract()
                .response()
                .getBody().asString());
    }

    @Test
    public void createPlayerWithOnlySpecialSymbolsPass() {
        testPlayer = PlayerParser.parseResponseBodyToPlayer(given()
                .get(String.format(Endpoints.CREATE_PLAYER_WITH_PASSWORD_ENDPOINT,
                        "supervisor", 15, "male", "testLogin", "$$$$$$$$$", "user", "testName"))
                .then()
                .statusCode(400)
                .extract()
                .response()
                .getBody().asString());
    }

    @AfterMethod
    private void deleteUserAfterTest() throws IOException {
        try {
            PlayerDeleter.deleteUserAfterTest(testPlayer);
            logger.info("Player was successfully deleted after test");
        } catch (NullPointerException exception) {
            logger.warn("Player was not created or deleted");
        }
    }
}
