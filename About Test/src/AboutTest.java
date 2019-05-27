import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Abraham, Joel, Jonathan, Patrick
 * 
 * Tests the About GUI
 *
 */
public class AboutTest {
	
	About about;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		about = new About();
	}

	@Test
	public void testGetVersion() throws FileNotFoundException {
		assertTrue(1.00 == about.getVersion());
	}

}
