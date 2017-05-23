package com.company;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ramakrishnacmanyam on 5/22/17.
 */
public class SortingImpl
{
    public void sortPersonList(List<Person> personList)
    {
        printHeader();
        sortByLastNameAsc(personList);
        sortByYoungest(personList);
        sortByFavColorAndLastName(personList);
    }

    private void printHeader()
    {
        Field[] allFields = Person.class.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field f : allFields)
            stringBuilder.append(String.format("%-15s", f.getName()));
        System.out.println(stringBuilder.toString());
    }

    public List<Person> sortByLastNameAsc(List<Person> listOfPeople)
    {
        System.out.println("-----------------Sort by Last Name [A-Z]-----------");
        Comparator<Person> lastNameComparator = DataSorter.SortByLastName;
        Collections.sort(listOfPeople, lastNameComparator);
        listOfPeople.forEach(System.out::println);
        return listOfPeople;
    }

    public List<Person> sortByYoungest(List<Person> listOfPeople)
    {
        System.out.println("-----------------Sort by Youngest------------");
        Comparator<Person> dobComparator = DataSorter.SortByDOB;
        Collections.sort(listOfPeople, dobComparator.reversed());
        listOfPeople.forEach(System.out::println);
        return listOfPeople;
    }

    public List<Person> sortByFavColorAndLastName(List<Person> listOfPeople)
    {
        System.out.println("-----------------Sort by Color and LastName------------");
        Comparator<Person> favColorComparator = DataSorter.FavColor;
        Comparator<Person> lastNameComparator = DataSorter.SortByLastName;
        Collections.sort(listOfPeople, favColorComparator.thenComparing(lastNameComparator.reversed()));
        listOfPeople.forEach(System.out::println);
        return listOfPeople;
    }
}
