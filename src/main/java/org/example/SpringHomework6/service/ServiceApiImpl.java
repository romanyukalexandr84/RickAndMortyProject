package org.example.SpringHomework6.service;

import org.example.SpringHomework6.domain.Characters;
import org.example.SpringHomework6.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Comparator;
import java.util.List;

@Service
public class ServiceApiImpl implements ServiceApi{

    @Autowired
    private RestTemplate template;

    @Autowired
    private HttpHeaders headers;

    @Autowired
    private Environment environment;

    @Override
    public Characters getAllCharacters() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> responce = template.exchange(environment.getProperty("CHARACTER_API"), HttpMethod.GET, entity, Characters.class);
        return responce.getBody();
    }

    @Override
    public Result getSingleCharacter(Integer id) {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String path = environment.getProperty("CHARACTER_API").concat("/").concat(String.valueOf(id));
        ResponseEntity<Result> responce = template.exchange(path, HttpMethod.GET, entity, Result.class);
        return responce.getBody();
    }

    @Override
    public Characters getSortedCharactersByName() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> responce = template.exchange(environment.getProperty("CHARACTER_API"), HttpMethod.GET, entity, Characters.class);
        responce.getBody().setResults(responce.getBody().getResults().stream().sorted(myComparatorByName).toList());
        return responce.getBody();
    }

    @Override
    public Characters getSortedCharactersByGender() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> responce = template.exchange(environment.getProperty("CHARACTER_API"), HttpMethod.GET, entity, Characters.class);
        responce.getBody().setResults(responce.getBody().getResults().stream().sorted(myComparatorByGender).toList());
        return responce.getBody();
    }

    public static Comparator<Result> myComparatorByName = new Comparator<Result>() {
        @Override
        public int compare(Result o1, Result o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static Comparator<Result> myComparatorByGender = new Comparator<Result>() {
        @Override
        public int compare(Result o1, Result o2) {
            return o1.getGender().compareTo(o2.getGender());
        }
    };
}
