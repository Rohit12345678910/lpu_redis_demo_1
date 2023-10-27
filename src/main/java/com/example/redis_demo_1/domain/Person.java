package com.example.redis_demo_1.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable {
    private String id;
    private String name;
    private int age;
    private Address address;
}
