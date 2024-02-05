package model;

public class Player {
    private int age;

    private String login;
    private String gender;
    private int id;
    private String role;
    private String screenName;
    private String password;

    public Player(int age, String gender, String role, String screenName, String password) {
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.screenName = screenName;
        this.password = password;
    }

    public Player() {
    }

    public Player(int age, String gender, String role, int id, String screenName, String password) {
        this.age = age;
        this.id = id;
        this.gender = gender;
        this.role = role;
        this.screenName = screenName;
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
