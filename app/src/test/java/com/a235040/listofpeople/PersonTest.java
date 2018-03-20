package com.a235040.listofpeople;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void for_correct_age_should_create_person() {
        Person person = Person.createPerson("Jan", "Kowalski", 30);
        System.out.println(person);
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_negative_age_should_throw_exception() {
        Person.createPerson("Jan", "Kowalski", -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void for_age_eq150_or_greater_should_throw_exception() {
        Person.createPerson("Jan", "Kowalski", 150);
    }
}