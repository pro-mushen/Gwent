package ru.friendlyTeam.authorization.dao.pojo;

public class OnlineLogin {
    private String login;

    public OnlineLogin() {
    }

    public OnlineLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
