package service;

import endpoints.Endpoints;
import model.Player;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PlayerDeleter {

    public static final String DELETE_USER_JSON_PATH = "src/test/resources/playerID.json";

    public static void deleteUserAfterTest(Player testPlayer) throws IOException {
        given()
                .body(String.format(FileReader.readJsonToString(DELETE_USER_JSON_PATH), testPlayer.getId()))
                .contentType("application/json")
                .delete(String.format(Endpoints.DELETE_PLAYER_ENDPOINT, "supervisor"));
    }
}
