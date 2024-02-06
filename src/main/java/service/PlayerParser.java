package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Player;

public class PlayerParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Player parseResponseBodyToPlayer(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, Player.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
