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

    @Test
    public void shouldMapToGenericObject () throws IOException {

        //given
        String jsonString = "{\"name\":\"frodo\", \"address\":\"bagend\"} ";

        ObjectMapper mapper = new ObjectMapper();

        Object obj = mapper.readValue(jsonString, Object.class);
        System.out.println("obj type: " + obj.getClass().toString()); // java.util.LinkedHashMap
        System.out.println("obj: " + obj);
    }

    @Test
    public void shouldMapToSpecificObject () throws IOException {

        //given
        String jsonString = "{\"name\":\"frodo\", \"address\":\"bagend\"} ";
        ObjectMapper mapper = new ObjectMapper();

        // parsing JSON to concrete Object
        MiddleEarthResident middleEarthResident = mapper.readValue(jsonString, MiddleEarthResident.class);
        System.out.println("middleEarthResident: " + middleEarthResident);
        System.out.println("middleEarthResident.author: " + middleEarthResident.getName());
        System.out.println("middleEarthResident.address: " + middleEarthResident.getAddress());
        System.out.println("middleEarthResident.obj class: " + middleEarthResident.getClass()); //com.frodo.lordoftherings.MiddleEarthResidentp
    }
}
