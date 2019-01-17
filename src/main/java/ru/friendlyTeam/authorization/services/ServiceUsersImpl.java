package ru.friendlyTeam.authorization.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.friendlyTeam.authorization.dao.DaoUsers;
import ru.friendlyTeam.authorization.dao.pojo.User;

import java.util.List;


@Service
public class ServiceUsersImpl implements ServiceUsers {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private DaoUsers daoUsers;

    @Autowired
    public void setBCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setDaoUsers(DaoUsers daoUsers) {
        this.daoUsers = daoUsers;
    }

    @Override
    public void registration(User user) {
        String passwordHash = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordHash);
        daoUsers.addUsers(user);
    }

    @Override
    public int getIdByLogin(String login) {
        return daoUsers.getIdByLogin(login);
    }

    @Override
    public List<User> getOnlineLogins() {
        return daoUsers.getOnlineUsers();
    }
}
