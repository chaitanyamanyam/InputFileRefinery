package test;

import com.company.Person;
import com.company.PipeFileDataParser;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PipeFileDataParseTest
{
    PipeFileDataParser parser;

    @Before
    public void setup() {
        parser = new PipeFileDataParser();
    }

    @Test
    public void testPipeFileDataParserTest()
    {
        String pipeString = "Runyon|Yoshie|H|Cat|Red|10-15-1979";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("M-d-yyyy");

        List<Person> personList = parser.parse(new ArrayList<>(Arrays.asList(pipeString)));

        Person person = personList.get(0);

        assertEquals(personList.size(), 1);
        assertEquals("Runyon", person.getLastName());
        assertEquals("Yoshie", person.getFirstName());
        assertEquals("H", person.getMiddleInitial());
        assertEquals("Cat", person.getPet());
        assertEquals("Red", person.getFavoriteColor());
        assertEquals(LocalDate.parse("10-15-1979", format), person.getDateOfBirth());
    }

    @Test
    public void testPipeFileDataParserTestForDateTimeParseException()
    {
        String pipeString = "Runyon|Yoshie|H|Cat|Red|10-15-197";
        List<Person> personList = parser.parse(new ArrayList<>(Arrays.asList(pipeString)));
        assertEquals(personList.size(), 0);

        pipeString = "Runyon|Yoshie|H|Cat|Red";
        personList = parser.parse(new ArrayList<>(Arrays.asList(pipeString)));
        assertEquals(personList.size(), 0);
    }

}
