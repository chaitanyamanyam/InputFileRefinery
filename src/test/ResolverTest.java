package test;

import com.company.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ResolverTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    Resolver resolver;

    @Before
    public void setup() {
        resolver = new Resolver();
    }

    @Test
    public void testResolve()
    {
        String firstLine = "Runyon Yoshie H Cat 10-15-1979 Red";
        DataParser dataParser = resolver.resolve(firstLine);
        assertEquals(dataParser.getClass().getName(), SpaceFileDataParser.class.getName());

        firstLine = "Runyon|Yoshie|H|Cat|Red|10-15-1979";
        dataParser = resolver.resolve(firstLine);
        assertEquals(dataParser.getClass().getName(), PipeFileDataParser.class.getName());
    }

    @Test
    public void testResolveUnsupportedDelimiter()
    {
        String firstLine = "Runyon.Yoshie.H.Cat.10-15-1979.Red";

        exception.expect(UnsupportedOperationException.class);
        exception.expectMessage("Unsupported Delimiter");
        resolver.resolve(firstLine);
    }
}
