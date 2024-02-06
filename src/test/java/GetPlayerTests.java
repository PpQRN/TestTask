import endpoints.Endpoints;
import model.Player;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.FileReader;
import service.PlayerCreator;
import service.PlayerDeleter;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class GetPlayerTests {

    public static final String GET_USER_JSON_PATH = "src/test/resources/playerID.json";

    @Test
    public void getPlayersPositiveTest() throws IOException {
        given()
                .body(String.format(FileReader.readJsonToString(GET_USER_JSON_PATH), 1))
                .contentType("application/json")
                .post(Endpoints.GET_PLAYER_ENDPOINT)
                .then()
                .statusCode(200);
    }

    @Test
    public void getPlayersNegativeTest() throws IOException {
        given()
                .body(String.format(FileReader.readJsonToString(GET_USER_JSON_PATH), "text"))
                .contentType("application/json")
                .post(Endpoints.GET_PLAYER_ENDPOINT)
                .then()
                .statusCode(400);
    }
}
