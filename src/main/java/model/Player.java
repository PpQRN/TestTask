package model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private int age;
    private String login;
    private String gender;
    private int id;
    private String role;
    private String screenName;
    private String password;
}
