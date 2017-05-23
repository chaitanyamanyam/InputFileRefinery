package com.company;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.lang.reflect.Field;
import java.time.LocalDate;

/**
 * Created by ramakrishnacmanyam on 5/21/17.
 */
public class Person
{
    private String lastName, firstName, middleInitial, pet, favoriteColor;
    private LocalDate dateOfBirth;

    public Person(String lastName, String firstName, String middleInitial, String pet, String favoriteColor, LocalDate dateOfBirth) {
        this.lastName = lastName.isEmpty()?"":lastName;
        this.firstName = firstName.isEmpty()?"":firstName;
        this.middleInitial = middleInitial.isEmpty()?"":middleInitial;
        this.pet = pet.isEmpty()?"":pet;
        this.favoriteColor = favoriteColor.isEmpty()?"":favoriteColor;
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getPet() {
        return pet;
    }


    public String getFavoriteColor() {
        return favoriteColor;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : Person.class.getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            stringBuilder.append(String.format("%-15s", value));
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        // field comparison
        return new EqualsBuilder()
                .append(firstName, person.firstName)
                .append(lastName, person.lastName)
                .append(middleInitial, person.middleInitial)
                .append(favoriteColor, person.favoriteColor)
                .append(pet, person.pet)
                .append(dateOfBirth, person.dateOfBirth)
                .isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17,31)
                .append(firstName)
                .append(lastName)
                .append(middleInitial)
                .append(favoriteColor)
                .append(pet)
                .append(dateOfBirth)
                .hashCode();
    }
}
