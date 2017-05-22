package com.company;

import java.lang.reflect.Field;
import java.util.*;

public class Controller {

    public static void main(String[] args)
    {
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
