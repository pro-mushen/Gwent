package ru.friendlyTeam.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerWelcomePage {

    @RequestMapping(value = "/gwent1")
    public ModelAndView showRegistrForm1(ModelMap model) {
        model.addAttribute("attribute", "forwardWithForwardPrefix");
        return new ModelAndView("redirect:gwent", model);
    }

    @RequestMapping(value = "/gwent")
    public String showRegistrForm(Model model) {
        return "/html-view/pages/gwent.html";
    }

}
