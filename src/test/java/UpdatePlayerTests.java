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

public class UpdatePlayerTests {

    public static final String UPDATE_PLAYER_JSON_PATH = "src/test/resources/patchPlayer.json";
    private Player testPlayer;
    private Player testAdmin;
    private Player testUser;

    @BeforeMethod
    private void createUserForTests() {
        testPlayer = PlayerCreator.createUserForTests("testPlayer");
        testAdmin = PlayerCreator.createAdminForTests();
        testUser = PlayerCreator.createUserForTests("testUser");
    }

    @Test
    public void updatePlayerWithSupervisorRole() throws IOException {
        given()
                .body(String.format(FileReader.readJsonToString(UPDATE_PLAYER_JSON_PATH), 30))
                .contentType("application/json")
                .patch(String.format(Endpoints.UPDATE_PLAYER_ENDPOINT, "supervisor", testPlayer.getId()))
                .then()
                .statusCode(200);
    }

    @Test
    public void updatePlayerWithAdminRole() throws IOException {
        given()
                .body(String.format(FileReader.readJsonToString(UPDATE_PLAYER_JSON_PATH), 30))
                .contentType("application/json")
                .patch(String.format(Endpoints.UPDATE_PLAYER_ENDPOINT, testAdmin.getLogin(), testPlayer.getId()))
                .then()
                .statusCode(200);
    }

    @Test
    public void updatePlayerWithUserRole() throws IOException {
        given()
                .body(String.format(FileReader.readJsonToString(UPDATE_PLAYER_JSON_PATH), 30))
                .contentType("application/json")
                .patch(String.format(Endpoints.UPDATE_PLAYER_ENDPOINT, testUser.getLogin(), testPlayer.getId()))
                .then()
                .statusCode(403);
    }

    @AfterMethod
    private void deleteUserAfterTest() throws IOException {
        PlayerDeleter.deleteUserAfterTest(testPlayer);
        PlayerDeleter.deleteUserAfterTest(testAdmin);
        PlayerDeleter.deleteUserAfterTest(testUser);
    }
}

