package com.frodo.postgresrest.domain;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO {
    private String firstName;
    private String lastName;
    private Object info;
}
