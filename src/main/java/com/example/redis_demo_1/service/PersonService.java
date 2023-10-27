package com.example.redis_demo_1.service;

import com.example.redis_demo_1.domain.Person;
import com.example.redis_demo_1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public void create(Person person){
        personRepository.set(person);
    }

    public Person get(String personId) {
        return personRepository.get(personId);
    }

    public List<Person> getAll() {
        Set<String> keys = personRepository.getAllKeys();
        return keys.stream()
                .map(k -> personRepository.getByKey(k))
                .collect(Collectors.toList());    // iterate over the list of keys and collect the values of them in list and return it

    }

// ----------------------------- List Operations -----------------------------------------------------------
    public void lpush(Person person) {
        personRepository.lpush(person);
    }
    public void rpush(Person person) {
        personRepository.rpush(person);
    }
    public List<Person> lpop(int counter) {
       return personRepository.lpop(counter);
    }
    public List<Person> rpop(int counter) {
        return personRepository.rpop(counter);
    }
    public List<Person> lrange(int start, int end) {
        return personRepository.lrange(start, end);
    }

// ----------------------- Hash operations -------------------------------------------------

    public void hmSet(Person person){
        personRepository.hmSet(person);
    }

    public Person getAll(String personId){
        return personRepository.getAll(personId);
    }
}
