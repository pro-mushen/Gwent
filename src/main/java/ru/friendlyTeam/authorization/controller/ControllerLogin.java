package ru.friendlyTeam.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerLogin {

    @RequestMapping(value = "login")
    String showLogin(
            @RequestParam(value = "error", required = false) String error,
            Model model) {
        model.addAttribute("loginError", error);
        return "login";
    }
}
