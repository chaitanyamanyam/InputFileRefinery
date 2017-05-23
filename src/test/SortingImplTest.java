package test;

import com.company.Person;
import com.company.SortingImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ramakrishnacmanyam on 5/22/17.
 */
public class SortingImplTest {

    SortingImpl sorting;
    Person john = new Person("Walker", "John", "", "Cat", "Red", LocalDate.now().minusYears(10l));
    Person stewart = new Person("Stewart", "Patrick", "L", "Dog", "Red", LocalDate.now().minusYears(15l));
    List<Person> testPersonList = new ArrayList<>(Arrays.asList(john,stewart));

    @Before
    public void setup() {
        sorting = new SortingImpl();
    }

    @Test
    public void testLastNameAsc()
    {
        List<Person> sortedListByLastName = sorting.sortByLastNameAsc(testPersonList);
        assertEquals(sortedListByLastName.get(0), stewart);
        assertEquals(sortedListByLastName.get(1), john);
    }

    @Test
    public void testYoungest()
    {
        List<Person> sortedListByDOB= sorting.sortByYoungest(testPersonList);
        assertEquals(sortedListByDOB.get(0), john);
        assertEquals(sortedListByDOB.get(1), stewart);
    }

    @Test
    public void testColorAndLastName()
    {
        Person paul = new Person("Wood", "Paul", "K", "Spider", "Blue", LocalDate.now());
        testPersonList.add(paul);
        List<Person> sortedListByColorAndLastName = sorting.sortByFavColorAndLastName(testPersonList);

        assertEquals(sortedListByColorAndLastName.get(0), paul);
        assertEquals(sortedListByColorAndLastName.get(1), john);
        assertEquals(sortedListByColorAndLastName.get(2), stewart);
    }

}
