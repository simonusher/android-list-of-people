package com.a235040.listofpeople;

/**
 * Created by Szymon on 20.03.2018.
 */

public class Person {
    private String name;
    private String surname;
    private int age;

    private Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public static boolean isAgeCorrect(int age){
        return age >= 0 && age < 150;
    }

    public static Person createPerson(String name, String surname, int age){
        if(isAgeCorrect(age)){
            return new Person(name, surname, age);
        } else {
            throw new IllegalArgumentException("Incorrect age!");
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return name + ' ' + surname;
    }
}
