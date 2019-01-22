package ru.friendlyTeam.authorization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Repository;
import ru.friendlyTeam.authorization.dao.mappers.MapperUser;
import ru.friendlyTeam.authorization.dao.pojo.OnlineLogin;
import ru.friendlyTeam.authorization.dao.pojo.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DaoUsersImpl implements DaoUsers {
    JdbcTemplate jdbcTemplate;
    private SessionRegistry sessionRegistry;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate, SessionRegistry sessionRegistry) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public void addUsers(User user) {
        String addUsersQuery = "insert into users VALUES (default, ?, ?, default, ?/*, ?, ?*/)";
        jdbcTemplate.update(addUsersQuery, user.getLogin(), user.getPassword(), user.getRole()/*, user.getFullName(), user.getMail()*/);
    }

    @Override
    public int getIdByLogin(String login) {
        String getUsersQuery = "select * from users where login = ? limit 1";
        User user = jdbcTemplate.query(getUsersQuery, new Object[]{login}, new MapperUser()).get(0);
        return user.getId();
    }

    @Override
    public List<User> getOnlineUsers() {
        List<OnlineLogin> onlineLogins = getOnlineLogins();
        String getUsersQuery = "select * from users where login in (''";
        for (OnlineLogin onlineLogin : onlineLogins) {
            getUsersQuery = getUsersQuery + ", '" + onlineLogin.getLogin() + "'";
        }
        getUsersQuery = getUsersQuery + ")";
        return jdbcTemplate.query(getUsersQuery, new MapperUser());
    }

    private List<OnlineLogin> getOnlineLogins() {
        List<OnlineLogin> list = new ArrayList();
        List<Object> onlineUsers = sessionRegistry.getAllPrincipals();
        for (Object usr : onlineUsers) {
            list.add(new OnlineLogin(((org.springframework.security.core.userdetails.User) usr).getUsername()));
        }
        return list;
    }
}
