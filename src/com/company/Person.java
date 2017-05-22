package com.company;

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
}
