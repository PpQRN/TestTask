import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreatePlayerTests {
    public static final String GET_USER_JSON_PATH = "src/test/resources/getUser.json";

    @Test
    public void createPlayerWithSupervisorRole() {
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=male&login=testLogin&role=user&screenName=testName")
                .then()
                .statusCode(200);
    }

    @Test
    public void createPlayerWithAdminRole() {
        given()
                .get("http://3.68.165.45/player/create/admin" +
                        "?age=17&gender=male&login=login11111&role=user&screenName=name1")
                .then()
                .statusCode(200);
    }

    @Test
    public void createPlayerWithUserRole() {
        given()
                .get("http://3.68.165.45/player/create/user" +
                        "?age=17&gender=male&login=login11111&role=user&screenName=name1")
                .then()
                .statusCode(403);
    }

    @Test
    public void createPlayerWithWrongGender() {
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=deer&login=login11111&role=user&screenName=name1")
                .then()
                .statusCode(400);
    }

    @Test
    public void createPlayerWithAgeLessThen16() {
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=10&gender=male&login=login11111&role=user&screenName=name1")
                .then()
                .statusCode(400);
    }

    @Test
    public void createPlayerWithAgeMoreThen60() {
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=61&gender=male&login=login11111&role=user&screenName=name1")
                .then()
                .statusCode(400);
    }

    @Test
    public void createPlayerWithAge60() {
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=60&gender=male&login=login11111&role=user&screenName=name1")
                .then()
                .statusCode(200);
    }

    @Test
    public void createPlayerWithAge16() {
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=16&gender=male&login=login11111&role=user&screenName=name1")
                .then()
                .statusCode(200);
    }

    @Test
    public void createPlayerWithValidPass(){
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=male&login=loggg&password=qwera2007&role=user&screenName=Poll")
                .then()
                .statusCode(200);
    }

    @Test
    public void createPlayerWithLessThen7CharPass(){
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=male&login=loggg&password=qwe20&role=user&screenName=Poll")
                .then()
                .statusCode(400);
    }

    @Test
    public void createPlayerWithMoreThen15CharPass(){
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=male&login=loggg&password=qqwweerraa22000077&role=user&screenName=Poll")
                .then()
                .statusCode(400);
    }

    @Test
    public void createPlayerWith7CharPass(){
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=male&login=loggg&password=qwera20&role=user&screenName=Poll")
                .then()
                .statusCode(200);
    }

    @Test
    public void createPlayerWith15CharPass(){
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=male&login=loggg&password=qqwweerra200007&role=user&screenName=Poll")
                .then()
                .statusCode(200);
    }

    @Test
    public void createPlayerWithLetterPass(){
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=male&login=loggg&password=qweraaaa&role=user&screenName=Poll")
                .then()
                .statusCode(400);
    }

    @Test
    public void createPlayerWithNumberPass(){
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=male&login=loggg&password=123452007&role=user&screenName=Poll")
                .then()
                .statusCode(400);
    }

    @Test
    public void createPlayerWithSpecialSymbolsPass(){
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=male&login=loggg&password=qwera2$$$007&role=user&screenName=Poll")
                .then()
                .statusCode(400);
    }

    @Test
    public void createPlayerWithOnlySpecialSymbolsPass(){
        given()
                .get("http://3.68.165.45/player/create/supervisor" +
                        "?age=17&gender=male&login=loggg&password=$$$$$$$$$&role=user&screenName=Poll")
                .then()
                .statusCode(400);
    }
}
