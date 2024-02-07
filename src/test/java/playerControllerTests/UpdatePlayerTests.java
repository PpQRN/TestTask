package playerControllerTests;

import endpoints.Endpoints;
import model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;
import service.FileReader;
import service.PlayerCreator;
import service.PlayerDeleter;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UpdatePlayerTests {

    private static final Logger logger = LogManager.getLogger(UpdatePlayerTests.class);
    public static final String UPDATE_PLAYER_JSON_PATH = "src/test/resources/patchPlayer.json";
    private Player testPlayer;
    private Player testAdmin;
    private Player testUser;

    @BeforeTest
    private void createEditorsForTests() {
        testAdmin = PlayerCreator.createAdminForTests();
        testUser = PlayerCreator.createUserForTests("TestUser");
        logger.info("Test editors were successfully created");
    }

    @BeforeMethod
    private void createUserForTests() {
        testPlayer = PlayerCreator.createUserForTests("testPlayer");
        logger.info("Test player was successfully created");
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
        logger.info("Test player was successfully deleted");
    }

    @AfterTest
    private void deleteEditorsAfterTest() throws IOException {
        PlayerDeleter.deleteUserAfterTest(testAdmin);
        PlayerDeleter.deleteUserAfterTest(testUser);
        logger.info("Test editors were successfully deleted");
    }
}

