package com.streamingserviceserver.model.person;

import java.util.Objects;

public class Person {

    public String personId;
    public String firstName;
    public String middleName;
    public String lastName;

    public Person(String personId, String firstName, String middleName, String lastName) {
        this.personId = personId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public String getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String fullName() {
        return String.format("%s %s %s", getFirstName(), getMiddleName(), getLastName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId.equals(person.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }
}
