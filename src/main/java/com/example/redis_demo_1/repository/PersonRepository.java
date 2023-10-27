package com.example.redis_demo_1.repository;

import com.example.redis_demo_1.domain.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class PersonRepository {
    @Autowired
    private RedisTemplate<String, Person> redisTemplate;
    private static final int PERSON_VALUE_EXPIRY = 1;// person value will be expired after 24 hours
    private static final String PERSON_KEY_PREFIX = "person::";
    public void set(Person person){
        redisTemplate.opsForValue().set(getKey(person.getId()), person, PERSON_VALUE_EXPIRY, TimeUnit.DAYS); // set the key (person::1 ...etc) and set the value as person object and also set the expiry time
    }

    public Person get(String personId) {
        return redisTemplate.opsForValue().get(getKey(personId));
    }

    private String getKey(String personId){
        return PERSON_KEY_PREFIX + personId;
    }

    public Set<String> getAllKeys() {
        return redisTemplate.keys(PERSON_KEY_PREFIX+"*");
    }
    public Person getByKey(String key){
        return redisTemplate.opsForValue().get(key);
    }

    //------------------------- List operations --------------------------------------------------------

    private static final String PERSON_LIST_KEY = "person_list";
    public void lpush(Person person) {
        redisTemplate.opsForList().leftPush(PERSON_LIST_KEY, person);
    }
    public void rpush(Person person) {
        redisTemplate.opsForList().rightPush(PERSON_LIST_KEY, person);
    }

    public List<Person> lpop(int counter){
        return redisTemplate.opsForList().leftPop(PERSON_LIST_KEY, counter);
    }
    public List<Person> rpop(int counter){
        return redisTemplate.opsForList().rightPop(PERSON_LIST_KEY, counter);
    }
    public List<Person> lrange(int start, int end){
        return redisTemplate.opsForList().range(PERSON_LIST_KEY, start, end);
    }

    // ---------------- Hash Operations ------------------------------------------------

    private static final String PERSON_HASH_KEY_PREFIX = "person_hash::";
    public void hmSet(Person person){
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.convertValue(person, Map.class);  // converting java objects into java Map using ObjectMapper
        redisTemplate.opsForHash().putAll(getKeyForHash(person.getId()), map);
    }

    public Person getAll(String personId){
        Map map =  redisTemplate.opsForHash().entries(getKeyForHash(personId));
        // this map need to be converted into Person Object -
        ObjectMapper objectMapper  = new ObjectMapper();
        Person person = objectMapper.convertValue(map, Person.class);
        return person;
    }
    private String getKeyForHash(String personId){
        return PERSON_HASH_KEY_PREFIX + personId;
    }
}
