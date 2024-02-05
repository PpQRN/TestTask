import io.restassured.response.ResponseBody;
import model.Player;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.PlayerService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class UpdatePlayerTests {

    public static final String GET_USER_JSON_PATH = "src/test/resources/getUser.json";
    public static final String  UPDATE_PLAYER_JSON_PATH = "src/test/resources/playerPatchParam.json";
    private Player testPlayer;

    @BeforeMethod
    private void createUserForTests() {
        ResponseBody body =
                given()
                        .get("http://3.68.165.45/player/create/supervisor" +
                                "?age=20&gender=male&login=TestLogin&role=user&screenName=TestScrName")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response()
                        .getBody();
        testPlayer = PlayerService.parseResponseBodyToPlayer(body.asString());
    }

    @Test
    public void updatePlayerWithSupervisorRole() throws IOException {
        given()
                .body(String.format(readJsonToString(UPDATE_PLAYER_JSON_PATH), 18))
                .contentType("application/json")
                .patch(String.format("http://3.68.165.45/player/update/supervisor/%s", testPlayer.getId()))
                .then()
                .statusCode(200);
    }

    @Test
    public void updatePlayerWithAdminRole() throws IOException {
        given()
                .body(String.format(readJsonToString(UPDATE_PLAYER_JSON_PATH), 18))
                .contentType("application/json")
                .patch(String.format("http://3.68.165.45/player/update/admin/%s", testPlayer.getId()))
                .then()
                .statusCode(200);
    }

    @Test
    public void updatePlayerWithUserRole() throws IOException {
        given()
                .body(String.format(readJsonToString(UPDATE_PLAYER_JSON_PATH), 18))
                .contentType("application/json")
                .patch(String.format("http://3.68.165.45/player/update/user/%s", testPlayer.getId()))
                .then()
                .statusCode(200);
    }

    private static String readJsonToString(String path) throws IOException {
        return FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
    }

    @AfterMethod
    private void deleteUserAfterTest() throws IOException {
        given()
                .body(String.format(readJsonToString(GET_USER_JSON_PATH), testPlayer.getId()))
                .contentType("application/json")
                .delete("http://3.68.165.45/player/delete/supervisor")
                .then()
                .statusCode(204);
    }
}

