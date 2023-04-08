package com.example.SpringRestApp;

import com.example.SpringRestApp.model.Person;
import com.example.SpringRestApp.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class SpringRestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(PersonRepository personRepository){
		return args -> {
			personRepository.save(new Person("Mariam","mariam.jamal@gmail.com", LocalDate.of(2000, Month.JANUARY,5)));
			personRepository.save(new Person("Alex","alex.@gmail.com", LocalDate.of(2004,Month.JANUARY,5)));
		};
	}
}
