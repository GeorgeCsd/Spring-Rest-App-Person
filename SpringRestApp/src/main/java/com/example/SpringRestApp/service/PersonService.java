package com.example.SpringRestApp.service;
import com.example.SpringRestApp.model.Person;
import com.example.SpringRestApp.model.Response;
import com.example.SpringRestApp.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }
    public List<Person> getPersons(){
        return personRepository.findAll(); //method that already jpa repository has
    }
    public List<Person> getPersons2(){
        return personRepository.findAllPersons(); //method that I had created with a query
    }
    public Response addPerson(Person person){
        Response response=new Response();
        Optional<Person> personOptional=personRepository.findPersonByEmail(person.getEmail());
        if(personOptional.isPresent()){
            throw new IllegalStateException("Person with this id already exists");
        }
        personRepository.save(person);
        response.setMessage("Person created successfully");
        response.setStatus(true);
        return response;
    }
    public Person getPerson(Long id){
        Person person=personRepository.myMethodFindPersonById(id).orElseThrow(()->new IllegalStateException("Person with id"+id+"doesn't exists"));
        return person;
    }
    public Person getPerson2(Long id){
        Person person=personRepository.myMethod2findPersonById(id).orElseThrow(()->new IllegalStateException("Person with id"+id+"doesn't exists"));
        return person;
    }
    public Person getPerson3(Long id){
        Person person=personRepository.myMethod3findPersonById(id).orElseThrow(()->new IllegalStateException("Person with id"+id+"doesn't exists"));
        return person;
    }
    public Person getPerson4(Long id){
        Person person=personRepository.MethodNamefindById(id);
        return person;
    }
    @Transactional
    public Response updatePerson(Long personID, String name, String email) {
        Response responce=new Response();
        Person person=personRepository.findById(personID).orElseThrow(()->new IllegalStateException("Person with id "+ personID + "doesnt' exists"));
        if(name !=null && name.length()>0 && !Objects.equals(person.getName(),name)){
            person.setName(name);
        }
        if(email !=null && email.length()>0 && !Objects.equals(person.getEmail(),email)){
            Optional<Person> studentOptional=personRepository.findPersonByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            person.setEmail(email);
        }
        responce.setStatus(true);
        responce.setMessage("Person was updated successfully with name: "+ name +" and email: "+email);
        return responce;
    }
    public Response deletePerson(Long personID) {
        Response response=new Response();
        boolean exists=personRepository.existsById(personID);
        if(!exists){
            throw new IllegalStateException("Person with id "+personID+"does not exists");
        }
        personRepository.deleteById(personID);
        response.setStatus(true);
        response.setMessage("Person was deleted successfully");
        return response;
    }
}
