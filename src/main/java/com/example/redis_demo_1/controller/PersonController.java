package com.example.redis_demo_1.controller;

import com.example.redis_demo_1.domain.Person;
import com.example.redis_demo_1.dto.CreatePersonRequest;
import com.example.redis_demo_1.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;
    // String operations -
    @PostMapping("/")
    public void createPerson(@RequestBody @Valid CreatePersonRequest request){
        personService.create(request.to());
    }
    @GetMapping("/")
    public Person getPerson(@RequestParam("id") String personId){
        return personService.get(personId);
    }
    @GetMapping("/all")
    public List<Person> getAll(){
        return personService.getAll();
    }


    //--------------------------------------- List operations --------------------------------------------------
    @PostMapping("/lpush")
    public void lpush(@RequestBody @Valid CreatePersonRequest request){
        personService.lpush(request.to());
    }
    @PostMapping("/rpush")
    public void rpush(@RequestBody @Valid CreatePersonRequest request){
        personService.rpush(request.to());
    }

    @DeleteMapping("/lpop")
    public List<Person> lpop(@RequestParam(value = "count", required = false, defaultValue = "1") int count){
        return personService.lpop(count);
    }
    @DeleteMapping("/rpop")
    public List<Person> rpop(@RequestParam(value = "count", required = false, defaultValue = "1") int count){
        return personService.lpop(count);
    }
    @GetMapping("/lrange")
    public List<Person> lrange(@RequestParam(value = "start", required = false, defaultValue = "0") int start, @RequestParam(value = "end", required = false, defaultValue = "-1") int end){
        return personService.lrange(start, end);
    }

    // ------------------ Hash Operations -----------------------------------------------------------

    @PostMapping("/hmset")
    public void hmSet(@RequestBody @Valid CreatePersonRequest request){
        personService.hmSet(request.to());
    }

    @GetMapping("/hmget")
    public Person getAll(@RequestParam(value = "id") String id){
        return personService.getAll(id);
    }

}
