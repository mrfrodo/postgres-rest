package com.frodo.postgresrest.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Address {
    private String streetName;
    private String city;
    private String country;
}
