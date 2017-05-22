package com.company;

import java.util.Comparator;

/**
 * Created by ramakrishnacmanyam on 5/21/17.
 */
public enum DataSorter implements Comparator<Person>
{
    SortByLastName {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
    },
    SortByDOB {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
        }
    },
    FavColor {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getFavoriteColor().compareTo(o2.getFavoriteColor());
        }
    }
}


