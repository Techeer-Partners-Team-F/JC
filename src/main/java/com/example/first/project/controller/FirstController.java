package com.example.first.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname","F");
        return "goodbye";
    }

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        model.addAttribute("username","F");
        return "greetings";
    }


}
