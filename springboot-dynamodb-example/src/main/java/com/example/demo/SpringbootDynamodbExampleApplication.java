package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;

@SpringBootApplication
@RestController
public class SpringbootDynamodbExampleApplication {

	@Autowired
	private PersonRepository repository;
	
	@PostMapping("/savePerson")
	public Person savePerson(@RequestBody Person person)
	{
		return repository.addPerson(person);
	}
	
	@GetMapping("/getPerson/{personID}")
	public Person getPerson(@PathVariable String personId)
	{
		return repository.findPersonByPersonId(personId);
	}
	@DeleteMapping("/deletePerson")
	public String deletePerson(@RequestBody Person person)
	{
		return repository.deletePerson(person);
	}
	
	@PutMapping("/editPerson")
	public String updatePerson(@RequestBody Person person)
	{
		return repository.editPerson(person);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootDynamodbExampleApplication.class, args);
	}

}
