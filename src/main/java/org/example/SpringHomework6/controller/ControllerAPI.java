package org.example.SpringHomework6.controller;

import org.example.SpringHomework6.service.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerAPI {
    @Autowired
    private ServiceApi serviceApi;

    @GetMapping("/characters")
    public String getCharacters(Model model) {
        model.addAttribute("characters", serviceApi.getAllCharacters().getResults());
        return "characters";
    }

}