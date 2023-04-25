package com.example.SpringRestApp.service;

import com.example.SpringRestApp.exception.ApiRequestException;
import com.example.SpringRestApp.model.Person;
import com.example.SpringRestApp.model.Response;
import com.example.SpringRestApp.repository.PersonRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getPersons() {
        return this.personRepository.findAll();
    }

    public List<Person> getPersons2() {
        return this.personRepository.findAllPersons();
    }

    public Response addPerson(Person person) {
        Response response = new Response();
        Optional<Person> personOptional = this.personRepository.findPersonByEmail(person.getEmail());
        if (personOptional.isPresent()) {
            throw new IllegalStateException("Person with this id already exists");
        } else {
            this.personRepository.save(person);
            response.setMessage("Person created successfully");
            response.setStatus(true);
            return response;
        }
    }

    public Person getPerson(Long id) {
        Person person = (Person)this.personRepository.myMethodFindPersonById(id).orElseThrow(() -> {
            return new ApiRequestException("Person with id " + id + " doesn't exists");
        });
        return person;
    }

    public Person getPerson2(Long id) {
        Person person = (Person)this.personRepository.myMethod2findPersonById(id).orElseThrow(() -> {
            return new ApiRequestException("Person with id " + id + " doesn't exists");
        });
        return person;
    }

    public Person getPerson3(Long id) {
        Person person = (Person)this.personRepository.myMethod3findPersonById(id).orElseThrow(() -> {
            return new ApiRequestException("Person with id " + id + " doesn't exists");
        });
        return person;
    }

    public Person getPerson4(Long id) {
        Person person = this.personRepository.MethodNamefindById(id);
        return person;
    }

    @Transactional
    public Response updatePerson(Long personID, String name, String email) {
        Response responce = new Response();
        Person person = (Person)this.personRepository.findById(personID).orElseThrow(() -> {
            return new IllegalStateException("Person with id " + personID + "doesnt' exists");
        });
        if (name != null && name.length() > 0 && !Objects.equals(person.getName(), name)) {
            person.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(person.getEmail(), email)) {
            Optional<Person> studentOptional = this.personRepository.findPersonByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }

            person.setEmail(email);
        }

        responce.setStatus(true);
        responce.setMessage("Person was updated successfully with name: " + name + " and email: " + email);
        return responce;
    }

    public Response deletePerson(Long personID) {
        Response response = new Response();
        boolean exists = this.personRepository.existsById(personID);
        if (!exists) {
            throw new IllegalStateException("Person with id " + personID + "does not exists");
        } else {
            this.personRepository.deleteById(personID);
            response.setStatus(true);
            response.setMessage("Person was deleted successfully");
            return response;
        }
    }
}
