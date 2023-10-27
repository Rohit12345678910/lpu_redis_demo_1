package com.example.redis_demo_1.dto;

import com.example.redis_demo_1.domain.Address;
import com.example.redis_demo_1.domain.Person;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePersonRequest {
    @NotBlank
    private String name;
    private int age;
    private Address address;

    public Person to(){
        return Person.builder()
                .name(this.name)
                .address(this.address)
                .age(this.age)
                .id(UUID.randomUUID().toString())
                .build();
    }
}
