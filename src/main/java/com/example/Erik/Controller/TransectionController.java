package com.example.Erik.Controller;


import com.example.Erik.model.Transaction;
import com.example.Erik.service.TransectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransectionController {

    @Autowired
    public TransectionService transectionService;



    @PostMapping("/api")
    public Transaction addTransction(@RequestBody Transaction transaction)
    {
        System.out.println(transaction);

        return  transectionService.addTransction(transaction);


    }


}
