package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by ramakrishnacmanyam on 5/21/17.
 */
public class CommaFileDataParser implements DataParser{

    @Override
    public List<Person> parse(List<String> plainData) {

        List<Person> listOfPeople = new ArrayList<>();
        String delimiter = Pattern.quote(Delimiter.valueOf(Delimiter.COMMA));

        for (String line: plainData)
        {
            ArrayList<String> stringPieces = new ArrayList<>(Arrays.asList(line.split(delimiter)));

            stringPieces.add(2," ");
            if (stringPieces.size() != Person.class.getDeclaredFields().length) continue;

            DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy");

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
}
