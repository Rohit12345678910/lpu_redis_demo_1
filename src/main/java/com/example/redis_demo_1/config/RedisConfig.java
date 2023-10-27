package com.example.redis_demo_1.config;

import com.example.redis_demo_1.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Persistable;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public LettuceConnectionFactory getConnection(){
        //RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(); // to connect to local server we can use defaut constructor
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                "redis-14168.c16.us-east-1-3.ec2.cloud.redislabs.com",
                14168
        );
        redisStandaloneConfiguration.setPassword("uFrExbrrAndwD8NdNBNw5ZbGqksKVCCQ");

        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);  // here actually it will connect with the server, above we have just defined the configurations
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Person> getTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate(); // using redis template we can execute the queries
        redisTemplate.setConnectionFactory(getConnection());

        // Now We need to convert Java String and Person into Redis Strings, If we want to store them in redis and vvice-versa. We can use serealization and deseralization for this.

        redisTemplate.setKeySerializer(new StringRedisSerializer()); // in seralization this will convert string to bytes and those bytes will be stored as string in redis, and deseralization vice-versa
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer()); // in seralization this will convert java object into bytes and vice-versa

        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

        return redisTemplate;
    }
}
