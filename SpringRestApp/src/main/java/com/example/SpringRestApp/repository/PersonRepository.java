package com.example.SpringRestApp.repository;

import com.example.SpringRestApp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{
    @Query("SELECT p FROM Person p WHERE p.email=?1")
    Optional<Person> findPersonByEmail(String email);

    @Query("SELECT p FROM Person p WHERE p.id=?1") //do the method getName with positional parameters
    Optional<Person> myMethodFindPersonById(Long id);

    @Query("SELECT p FROM Person p WHERE p.id=:id") //do the method getName with named bind parameters
    Optional<Person> myMethod2findPersonById(/*@Param("name")*/ Long id);

    @Query(value = "SELECT * from pErSon  WHERE iD = :id ",nativeQuery = true) //do the method getName with nativeQuery
    Optional<Person> myMethod3findPersonById(Long id);

    public Person MethodNamefindById(Long id); //do the method getName with NamedQuery

    @Query(value="SELECT * FROM pErSoN",nativeQuery = true) //do the method getAll with native Query
    List<Person> findAllPersons();


}
