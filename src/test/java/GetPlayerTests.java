import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;

public class GetPlayerTests {

    public static final String GET_USER_JSON_PATH = "src/test/resources/getUser.json";

    @Test
    public void getPlayersPositiveTest() throws IOException {
        given()
                .body(String.format(readJsonToString(GET_USER_JSON_PATH), 1))
                .contentType("application/json")
                .post("http://3.68.165.45/player/get")
                .then()
                .statusCode(200);
    }

    @Test
    public void getPlayersNegativeTest() throws IOException {
        given()
                .body(String.format(readJsonToString(GET_USER_JSON_PATH), "text"))
                .contentType("application/json")
                .post("http://3.68.165.45/player/get")
                .then()
                .statusCode(400);
    }

    private static String readJsonToString(String path) throws IOException {
        return FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
    }

}
