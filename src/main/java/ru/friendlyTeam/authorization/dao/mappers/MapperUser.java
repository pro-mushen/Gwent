package ru.friendlyTeam.authorization.dao.mappers;


import org.springframework.jdbc.core.RowMapper;
import ru.friendlyTeam.authorization.dao.pojo.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MapperUser implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setRole(rs.getString("role"));
        user.setFullName(rs.getString("full_name"));
        user.setMail(rs.getString("mail"));
        return user;
    }
}
