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
        return age > 0 && age < 150;
    }

    public static Person createPerson(String name, String surname, int age){
        if(isAgeCorrect(age)){
            return new Person(name, surname, age);
        } else {
            throw new IllegalArgumentException("Incorrect age!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (age != person.age) return false;
        if (!name.equals(person.name)) return false;
        return surname.equals(person.surname);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + age;
        return result;
    }
}
