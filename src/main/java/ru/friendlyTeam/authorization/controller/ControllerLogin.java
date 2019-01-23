package ru.friendlyTeam.authorization.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.friendlyTeam.authorization.dao.pojo.User;
import ru.friendlyTeam.authorization.services.ServiceUsers;

@Controller
public class ControllerLogin {

    ServiceUsers serviceUsers;

    @Autowired
    public void setServiceUsers(ServiceUsers serviceUsers) {
        this.serviceUsers = serviceUsers;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showloginForm(Model model) {
        return "WEB-INF/pages/login.jsp";
    }

    @RequestMapping(value = "/registr", method = RequestMethod.GET)
    public String showRegistrForm(Model model) {
        return "registr";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(
            @RequestParam(value = "userLogin") String login,
            @RequestParam(value = "userPassword") String password,
            Model model) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setRole("ROLE_USER");
        System.out.println(login);
        System.out.println(password);
        serviceUsers.registration(user);
        return "registr";
    }
}
