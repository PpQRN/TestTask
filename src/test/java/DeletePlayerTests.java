import endpoints.Endpoints;
import model.Player;
import org.testng.annotations.*;
import service.FileReader;
import service.PlayerCreator;
import service.PlayerDeleter;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DeletePlayerTests {

    public static final String DELETE_USER_JSON_PATH = "src/test/resources/playerID.json";
    private Player testPlayer;
    private Player testAdmin;
    private Player testUser;

    @BeforeTest
    private void createEditorsForTests(){
        testAdmin = PlayerCreator.createAdminForTests();
        testUser = PlayerCreator.createUserForTests("TestUser");
    }

    @BeforeMethod
    private void createUserForTests() {
        testPlayer = PlayerCreator.createUserForTests("testPlayer");
    }

    @Test
    public void deletePlayerWithAdminRole() throws IOException {
        given()
                .body(String.format(FileReader.readJsonToString(DELETE_USER_JSON_PATH), testPlayer.getId()))
                .contentType("application/json")
                .delete(String.format(Endpoints.DELETE_PLAYER_ENDPOINT, testAdmin.getLogin()))
                .then()
                .statusCode(204);
        PlayerDeleter.deleteUserAfterTest(testAdmin);
    }

    @Test
    public void deletePlayerWithSupervisorRole() throws IOException {
        given()
                .body(String.format(FileReader.readJsonToString(DELETE_USER_JSON_PATH), testPlayer.getId()))
                .contentType("application/json")
                .delete(String.format(Endpoints.DELETE_PLAYER_ENDPOINT, "supervisor"))
                .then()
                .statusCode(204);
    }

    @Test
    public void deletePlayerWithUserRole() throws IOException {
        Player testUser = PlayerCreator.createUserForTests("TestUser");
        given()
                .body(String.format(FileReader.readJsonToString(DELETE_USER_JSON_PATH), testPlayer.getId()))
                .contentType("application/json")
                .delete(String.format(Endpoints.DELETE_PLAYER_ENDPOINT, testUser.getLogin()))
                .then()
                .statusCode(403);
    }

    @AfterTest
    private void deleteEditorsAfterTest() throws IOException {
        PlayerDeleter.deleteUserAfterTest(testAdmin);
        PlayerDeleter.deleteUserAfterTest(testUser);
    }

}
