package groupassignment.controller;

import groupassignment.model.Person;
import groupassignment.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello";
    }
    @GetMapping("/all")
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }
//
    @PostMapping("/add")
    public Person addPerson(@RequestBody Person person){
        Person person1 = new Person();
        person1.setName(person.getName());
        personRepository.save(person1);
        return person1;
    }
}
