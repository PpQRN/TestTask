import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllPlayersTests {

    @Test
    public void getAllPlayersPositiveTest() {
        given()
                .get("http://3.68.165.45/player/get/all")
                .then()
                .statusCode(200);
    }
}
