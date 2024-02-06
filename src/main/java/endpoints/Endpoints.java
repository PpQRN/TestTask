package endpoints;

import service.PropertiesReader;

public class Endpoints {
    private static final String APP_URL = PropertiesReader.getEndPointsProperty("app.url");

    public static final String CREATE_PLAYER_WITHOUT_PASSWORD_ENDPOINT =  APP_URL + "player/create/" +
            "%s?age=%s&gender=%s&login=%s&role=%s&screenName=%s";
    public static final String CREATE_PLAYER_WITH_PASSWORD_ENDPOINT = APP_URL + "player/create/" +
            "%s?age=%s&gender=%s&login=%s&password=%s&role=%s&screenName=%s";
    public static final String DELETE_PLAYER_ENDPOINT = APP_URL + "player/delete/%s";
    public static final String GET_ALL_PLAYERS_ENDPOINT = APP_URL + "player/get/all";
    public static final String GET_PLAYER_ENDPOINT = APP_URL + "player/get";
    public static final String UPDATE_PLAYER_ENDPOINT = APP_URL + "player/update/%s/%s";
}
