import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class DeletePlayerTests {

    public static final String GET_USER_JSON_PATH = "src/test/resources/getUser.json";

    @Test
    public void deletePlayerWithAdminRole() throws IOException {
        given()
                .body(String.format(readJsonToString(GET_USER_JSON_PATH), 1))
                .contentType("application/json")
                .delete("http://3.68.165.45/player/delete/admin")
                .then()
                .statusCode(204);
    }

    @Test
    public void deletePlayerWithSupervisorRole() throws IOException {
        given()
                .body(String.format(readJsonToString(GET_USER_JSON_PATH), 1))
                .contentType("application/json")
                .delete("http://3.68.165.45/player/delete/supervisor")
                .then()
                .statusCode(204);
    }

    @Test
    public void deletePlayerWithUserRole() throws IOException {
        given()
                .body(String.format(readJsonToString(GET_USER_JSON_PATH), 1))
                .contentType("application/json")
                .delete("http://3.68.165.45/player/delete/user")
                .then()
                .statusCode(403);
    }

    private static String readJsonToString(String path) throws IOException {
        return FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
    }
}
