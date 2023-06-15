package com.example.Erik.service;

import com.example.Erik.model.person;
import com.example.Erik.repo.personrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class personservice {

    @Autowired
    public personrepo personrepo1;

    public person saveperson(person person)
    {
        return personrepo1.save(person);
    }
}
