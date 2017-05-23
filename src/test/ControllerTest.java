package test;


import com.company.Controller;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ControllerTest
{
    Controller controller;

    @Before
    public void setup() {
        controller = new Controller();
    }

    @Test
    public void testStart()
    {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Controller.start("/Users/ramakrishnacmanyam/Downloads/code-test/input_files/pipe.txt");

    }
}
