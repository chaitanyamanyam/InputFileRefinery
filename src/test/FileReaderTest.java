package test;

import com.company.FileReader;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ramakrishnacmanyam on 5/23/17.
 */
public class FileReaderTest
{
    FileReader fileReader;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void setup()
    {
        fileReader = new FileReader();
    }

    @Test
    public void testRead() throws IOException
    {
        String filePath = "/Users/ramakrishnacmanyam/Wistia/resources/ErrorFile.txt";
        final Path path = Paths.get(filePath);
        final long lineCount = Files.lines(path).count();

        List<String> stringList = fileReader.read(filePath);
        assertEquals(lineCount, stringList.size());
    }

    @Test
    public void testReadIOException()
    {
        String filePath = "/Users/ramakrishnacmanyam/Wistia/resources/ErrorFil11.txt";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        assertEquals(null, fileReader.read(filePath));
        assertTrue(outContent.toString().contains("File is closed"));
    }
}
