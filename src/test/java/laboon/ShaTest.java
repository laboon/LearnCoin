package laboon;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShaTest {


    // Test if we get the correct SHA-256 string for a small
    // string.
    @Test
    public void testShaAsStringSmall() {
	String result = Sha.sha256AsString("meow");
	assertEquals("404cdd7bc109c432f8cc2443b45bcfe95980f5107215c645236e577929ac3e52", result);
    }

    // Test if we get the correct SHA-256 string for a large
    // string.
    @Test
    public void testShaAsStringLarge() {
	String result = Sha.sha256AsString(
					"Now is the winter of our discontent " 
					+ "Made glorious summer by this sun of York; "
					+ "And all the clouds that lour'd upon our house "
					+"In the deep bosom of the ocean buried.");
	assertEquals("8b2ba8b45f60e4211c12a1f2fd682fc6a4d31e919c980d8bc07a9240b96d638d", result);

    }

    
}
