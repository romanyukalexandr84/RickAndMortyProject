package org.example.SpringHomework6.service;

import org.example.SpringHomework6.domain.Characters;
import org.example.SpringHomework6.domain.Result;

public interface ServiceApi {
    public Characters getAllCharacters();
    public Result getSingleCharacter(Integer id);
    public Characters getSortedCharactersByName();
    public Characters getSortedCharactersByGender();
}
