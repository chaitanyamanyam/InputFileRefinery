package test;

import com.company.CommaFileDataParser;
import com.company.Person;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommaFileDataParserTest
{
    CommaFileDataParser parser;

    @Before
    public void setup() {
        parser = new CommaFileDataParser();
    }

    @Test
    public void testCommaFileDataParserTest()
    {
        String pipeString = "Runyon,Yoshie,Cat,Red,10/15/1979";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yyyy");

        List<Person> personList = parser.parse(new ArrayList<>(Arrays.asList(pipeString)));

        Person person = personList.get(0);

        assertEquals(personList.size(), 1);
        assertEquals("Runyon", person.getLastName());
        assertEquals("Yoshie", person.getFirstName());
        assertEquals(" ", person.getMiddleInitial());
        assertEquals("Cat", person.getPet());
        assertEquals("Red", person.getFavoriteColor());
        assertEquals(LocalDate.parse("10/15/1979",format), person.getDateOfBirth());
    }

    @Test
    public void testCommaFileDataParserTestForDateTimeParseException()
    {
        String pipeString = "Runyon,Yoshie,Cat,Red,10/15/197";
        List<Person> personList = parser.parse(new ArrayList<>(Arrays.asList(pipeString)));

        assertEquals(personList.size(), 0);
    }
}
