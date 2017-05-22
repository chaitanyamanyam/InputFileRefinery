package com.company;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

public class Controller {

    static final List<String> delimiters = new ArrayList<>(Arrays.asList(",", " ", "|"));
    static final List<String> pets = new ArrayList<>(Arrays.asList("Dog", "Cat", "None", "Both"));

    public static void main(String[] args)
    {

        //1) Date of birth format any specific format
        //2) using reflection preferred.
        //3) how to print values in tabular or just line by line
        //4) Pets are mostly Cat,dog,Both,None is it possible to have B and N in comma separated values.Uniqueness of characters
        //5) Middle Name is empty for now

        start("/Users/ramakrishnacmanyam/Downloads/code-test/input_files/pipe.txt");
    }

    public static void start(String filePath)
    {
        Resolver resolver;
        FileReader fileReader = new FileReader();
        List<String> plainData = fileReader.read(filePath);
        if (!plainData.isEmpty()) {
            String firstLine = plainData.get(0);
            resolver = new Resolver();
            DataParser dataParser = resolver.resolve(firstLine);

            List<Person> listOfPeople = dataParser.parse(plainData);

            if (!listOfPeople.isEmpty())
            {
                printHeader();
                System.out.println("-----------------Sort by Last Name [A-Z]-----------");
                Comparator<Person> lastNameComparator = DataSorter.SortByLastName;
                Collections.sort(listOfPeople, lastNameComparator);
                listOfPeople.forEach(System.out::println);

                System.out.println("-----------------Sort by Youngest------------");
                Comparator<Person> dobComparator = DataSorter.SortByDOB;
                Collections.sort(listOfPeople, dobComparator.reversed());
                listOfPeople.forEach(System.out::println);

                System.out.println("-----------------Sort by Color and LastName------------");
                Comparator<Person> favColorComparator = DataSorter.FavColor;
                Collections.sort(listOfPeople, favColorComparator.thenComparing(lastNameComparator.reversed()));
                listOfPeople.forEach(System.out::println);
            }
        }
    }

    private static void printHeader()
    {
        Field[] allFields = Person.class.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field f : allFields)
            stringBuilder.append(String.format("%-15s", f.getName()));
        System.out.println(stringBuilder.toString());
    }
}
