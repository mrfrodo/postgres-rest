package com.frodo.lordoftherings;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class MiddleEarth {

    @Test
    public void frodoShouldBeAResidenctOfMiddleEarth() throws IOException {
        //given
        String jsonString = "{\"name\":\"frodo\", \"address\":\"bagend\"} ";
        ObjectMapper mapper = new ObjectMapper();

        //when
        MiddleEarthResident middleEarthResident = mapper.readValue(jsonString, MiddleEarthResident.class);
        System.out.println(middleEarthResident);

        //then
        String frodo = middleEarthResident.getName();
        String bagend = middleEarthResident.getAddress();
        assertEquals("frodo", frodo);
        assertEquals("bagend", bagend);

    }
}
