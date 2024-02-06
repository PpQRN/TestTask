import endpoints.Endpoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllPlayersTests {

    @Test
    public void getAllPlayersPositiveTest() {
        given()
                .get(Endpoints.GET_ALL_PLAYERS_ENDPOINT)
                .then()
                .statusCode(200);
    }
}
