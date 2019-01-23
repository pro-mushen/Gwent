package ru.friendlyTeam.authorization.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ControllerAuthorization {

    @RequestMapping(value = "/authorization")
    String showLogin(
            Principal principal,
            Model model) {
        String name = "";
        if (principal != null) {
            name = principal.getName();
            System.out.println(name);
        }
        return name;
    }

}
