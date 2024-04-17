package org.example.SpringHomework6.controller;

import org.example.SpringHomework6.service.ServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ControllerAPI {

    @Autowired
    private ServiceApi serviceApi;

    @GetMapping("/characters")
    public String getCharacters(Model model) {
        model.addAttribute("characters", serviceApi.getAllCharacters().getResults());
        return "characters";
    }

    @GetMapping("/character/{id}")
    public String getSingleCharacter(Model model, @PathVariable Integer id) {
        model.addAttribute("character", serviceApi.getSingleCharacter(id));
        return "character";
    }

    @GetMapping("/characters/sortedbyname")
    public String getSortedCharactersByName(Model model) {
        model.addAttribute("characters", serviceApi.getSortedCharactersByName().getResults());
        return "characters";
    }

    @GetMapping("/characters/sortedbygender")
    public String getSortedCharactersByGender(Model model) {
        model.addAttribute("characters", serviceApi.getSortedCharactersByGender().getResults());
        return "characters";
    }
}