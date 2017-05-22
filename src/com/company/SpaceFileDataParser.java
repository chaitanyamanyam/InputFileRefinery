package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by ramakrishnacmanyam on 5/21/17.
 */
public class SpaceFileDataParser implements DataParser {

    private final List<String> pets = new ArrayList<>(Arrays.asList("Dog", "Cat", "None", "Both"));

    @Override
    public List<Person> parse(List<String> plainData) {

        List<Person> listOfPeople = new ArrayList<>();
        String delimiter = Pattern.quote(Delimiter.valueOf(Delimiter.SPACE));

        for (String line: plainData)
        {
            ArrayList<String> stringPieces = new ArrayList<>(Arrays.asList(line.split(delimiter)));

            if (stringPieces.size() != Person.class.getDeclaredFields().length) continue;

            Collections.swap(stringPieces, stringPieces.size() - 2, stringPieces.size() - 1);
            stringPieces.set(3, getPetFromInitial(stringPieces.get(3)));

            DateTimeFormatter format = DateTimeFormatter.ofPattern("M-d-yyyy");

            LocalDate dateOfBirth;
            try {
                dateOfBirth = LocalDate.parse(stringPieces.get(5), format);
            }
            catch (DateTimeParseException e)
            {
                continue;
            }

            Person person = new Person(stringPieces.get(0), stringPieces.get(1), stringPieces.get(2), stringPieces.get(3),
                    stringPieces.get(4), dateOfBirth);
            listOfPeople.add(person);
        }

        return listOfPeople;
    }

    private String getPetFromInitial(String initial) {

        for (String pet : pets)
        {
            if (pet.startsWith(initial)) return pet;
        }

        return "";
    }

}
