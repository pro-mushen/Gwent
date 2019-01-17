package ru.friendlyTeam.authorization.dao;


import ru.friendlyTeam.authorization.dao.pojo.User;

import java.util.List;

public interface DaoUsers {
    void addUsers(User user);

    int getIdByLogin(String login);

    List<User> getOnlineUsers();
}
