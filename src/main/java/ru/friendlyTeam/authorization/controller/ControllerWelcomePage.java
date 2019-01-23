package ru.friendlyTeam.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControllerWelcomePage {

    @RequestMapping(value = "")
    public String showRegistrForm(Model model) {
        return "WEB-INF/pages/gwent.html";
    }

}
