package com.example.SpringRestApp.controller;

import com.example.SpringRestApp.model.Person;
import com.example.SpringRestApp.model.Response;
import com.example.SpringRestApp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person")

public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService=personService;
    }
    @GetMapping("getAll")
    public List<Person> getAllPersons(){
        return personService.getPersons();
    }
    @GetMapping
    public List<Person> getAllPersons2(){
        return personService.getPersons2();
    }
    @PostMapping("add")
    public Response addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }
    @GetMapping("get/{id}")
    public Person getPerson(@PathVariable ("id") Long id){
        return personService.getPerson(id);
    }

    @GetMapping("get2/{id}")
    public Person getPerson2(@PathVariable ("id") Long id){
        return personService.getPerson2(id);
    }

    @GetMapping("get3/{id}")
    public Person getPerson3(@PathVariable ("id") Long id){
        return personService.getPerson3(id);
    }

    @GetMapping("get4/{id}")
    public Person getPerson4(@PathVariable ("id") Long id){
        return personService.getPerson4(id);
    }

    @PutMapping(path="update/{personId}")
    public Response updatePerson(@PathVariable ("personId") Long personID,@RequestParam(required = false)String name,@RequestParam(required = false)String email){
        return personService.updatePerson(personID,name,email);
    }
    @DeleteMapping(path="delete/{personId}")
    public Response deletePersom(@PathVariable ("personId") Long personID){
         return personService.deletePerson(personID);
    }
}
