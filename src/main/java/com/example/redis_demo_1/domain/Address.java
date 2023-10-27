package com.example.redis_demo_1.domain;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address implements Serializable {

    private String street;
    private String city;
    private String state;
    private String country;

}
