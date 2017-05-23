package test;


import com.company.Controller;
import com.company.Person;
import com.company.SortingImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ControllerTest
{
    Controller controller;

    @Before
    public void setup() {
        controller = new Controller();
    }

    @Test
    public void testParseEachFile() throws IOException
    {
        String filePath = "/Users/ramakrishnacmanyam/Wistia/resources/pipe.txt";
        final Path path = Paths.get(filePath);
        final long lineCount = Files.lines(path).count();

        List<Person> listOfPerson = Controller.parseEachFile(filePath);

        assertEquals(lineCount, listOfPerson.size());
    }

    @Test
    public void testParseEachFileErrorInput() throws IOException
    {
        String filePath = "/Users/ramakrishnacmanyam/Wistia/resources/ErrorFile.txt";
        final Path path = Paths.get(filePath);
        final long lineCount = Files.lines(path).count();

        List<Person> listOfPerson = Controller.parseEachFile(filePath);

        assertTrue(lineCount > listOfPerson.size());
    }

    @Test
    public void testStart()
    {
        String directoryPath = "/Users/ramakrishnacmanyam/Wistia/resources/";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Controller.start(directoryPath);
        String outContentString = outContent.toString();
        assertTrue(outContentString.contains(SortingImpl.sortByLastName));
        assertTrue(outContentString.contains(SortingImpl.sortByYoungest));
        assertTrue(outContentString.contains(SortingImpl.sortByColorAndLastName));

    }
}
