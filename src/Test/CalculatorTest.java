package Test;
import static org.junit.Assert.*;
import org.junit.Test;

import Model.Calculator;

/**
 * Tests the Calculator class
 * 
 * @author Patrick
 *
 */
public class CalculatorTest {

	private Calculator calc = new Calculator();
	
	/**
	 * TEST 1: One Arithmetic Operations Test
	 */
	@Test
	public void oneOpTest()	{
		calc.userButtons("1");
		calc.userButtons("+");
		calc.userButtons("2");
		calc.userButtons("+");
		calc.userButtons("2");
		calc.userButtons("2");
		calc.userButtons("=");
		assertEquals("25.0", calc.getTotal());
	}

	/**
	 * TEST 2: Multiple Arithmetic Operations Test
	 */
	@Test
	public void multiOpTest()	{
		calc.userButtons("1");
		calc.userButtons("+");
		calc.userButtons("9");
		calc.userButtons("-");
		calc.userButtons("5");
		calc.userButtons("*");
		calc.userButtons("1");
		calc.userButtons("/");
		calc.userButtons("5");
		calc.userButtons("=");
		assertEquals("1.0", calc.getTotal());
	}
	
	/**
	 * TEST 3: Multiple Equals Press Test
	 */
	@Test
	public void multiEqualTest()	{
		calc.userButtons("1");
		calc.userButtons("0");
		calc.userButtons("0");
		calc.userButtons("/");
		calc.userButtons("5");
		calc.userButtons("=");
		calc.userButtons("=");
		calc.userButtons("=");
		assertEquals("0.8", calc.getTotal());
	}
	
	/**
	 * TEST 4: Multiple Arithmetic Press Test
	 */
	@Test
	public void multiArithmPressTest()	{
		calc.userButtons("5");
		calc.userButtons("+");
		calc.userButtons("1");
		calc.userButtons("+");
		calc.userButtons("+");
		calc.userButtons("2");
		calc.userButtons("=");
		assertEquals("8.0", calc.getTotal());
	}
	
	/**
	 * TEST 5: Change value from positive to negative
	 */
	@Test
	public void posToNegTest()	{
		calc.userButtons("1");
		calc.userButtons("2");
		calc.userButtons("-");
		calc.userButtons("2");
		calc.userButtons("+/-");
		calc.userButtons("+");
		calc.userButtons("+/-");
		calc.userButtons("1");
		calc.userButtons("=");
		assertEquals("13.0", calc.getTotal());
	}

	/**
	 * TEST 6: Tests putting negative on 0 in case of ill effect
	 */
	@Test
	public void postToNegOnZeroTest()	{
		calc.userButtons("+/-");
		calc.userButtons("-");
		calc.userButtons("5");
		calc.userButtons("=");
		assertEquals("-5.0", calc.getTotal());
	}
	
	/**
	 * TEST 7: Checks if copying value from field works
	 */
	@Test
	public void copiedFromFieldTest()	{
		calc.userButtons("4");
		calc.userButtons("*");
		calc.copiedValue("25");
		calc.userButtons("=");
		assertEquals("100.0", calc.getTotal());
	}

	/**
	 * TEST 8: Checks if copy value from field works if entered first
	 */
	@Test
	public void copiedFirstFromFieldTest()	{
		calc.copiedValue("250");
		calc.userButtons("*");
		calc.userButtons("4");
		calc.userButtons("=");
		assertEquals("1000.0", calc.getTotal());

	}

	/**
	 * TEST 9: Checks if pressing enter button only still works
	 */
	@Test
	public void onlyNumberAndEqualsTest()	{
		calc.userButtons("1");
		calc.userButtons("2");
		calc.userButtons("=");
		assertEquals("12.0", calc.getTotal());
		
	}

	/**
	 * TEST 10: If arithmetic operation is changed, the calculations should reflect that
	 */
	@Test
	public void changeArithmeticTest()	{
		calc.userButtons("5");
		calc.userButtons("+");
		calc.userButtons("-");
		calc.userButtons("8");
		calc.userButtons("=");
		assertEquals("-3.0", calc.getTotal());
	}
	
	/**
	 * TEST 11: Tests to make sure decimals work
	 */
	@Test
	public void decimalTest()	{
		calc.userButtons("1");
		calc.userButtons(".");
		calc.userButtons("5");
		calc.userButtons("+");
		calc.userButtons(".");
		calc.userButtons("5");
		calc.userButtons("=");
		assertEquals("2.0", calc.getTotal());
	}
	
	/**
	 * TEST 12: Checks to make sure arithmetic continues to 
	 * function after the equal button is pressed
	 */
	@Test
	public void continuousArithmeticTest()	{
		calc.userButtons("1");
		calc.userButtons("+");
		calc.userButtons("2");
		calc.userButtons("=");
		calc.userButtons("/");
		calc.userButtons("3");
		calc.userButtons("=");
		assertEquals("1.0", calc.getTotal());
		
	}
}
