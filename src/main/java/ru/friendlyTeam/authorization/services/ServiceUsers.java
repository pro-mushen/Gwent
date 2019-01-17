package ru.friendlyTeam.authorization.services;


import ru.friendlyTeam.authorization.dao.pojo.User;

import java.util.List;

public interface ServiceUsers {
    void registration(User user);

    int getIdByLogin(String login);

    List<User> getOnlineLogins();
}
