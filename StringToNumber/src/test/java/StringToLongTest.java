package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import main.java.StringToLong;

import org.junit.Test;

/**
 * @author PPUNJAK(PIYUSH) On 05/08/2016
 * 
 * this class is used to validate the StringToLong.class 
 * using JUNIT 
 */
public class StringToLongTest {
	
	/*public StringToLongTest(final String methodName) {
		super(methodName);
	}*/

	/*@Before	
	public void setUp() {}
	
	@After
	public void tearDown() {}*/
	
	// ======================= negative test case
	@Test
	public void testStirngForNull() {
		try {
			StringToLong.convertStringToLong(null);
			fail("null not supported as a value for conversion");
		} catch (final NumberFormatException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testStirngStartingWithPlus() {
		try {
			StringToLong.convertStringToLong("+12345");
			fail("String can not start with + sign");
		} catch (final NumberFormatException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testStirngForDecimal() {
		try {
			StringToLong.convertStringToLong("12.345");
			fail("Decimal not supported here");
		} catch (final NumberFormatException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testStirngForAlpha() {
		try {
			StringToLong.convertStringToLong("12345a");
			fail("Alphabet not supported here");
		} catch (final NumberFormatException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testStirngForNegtiveBetween() {
		try {
			StringToLong.convertStringToLong("123-45");
			fail("- has to be at the start");
		} catch (final NumberFormatException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testStirngForBoundryMin() {
		try {
			// MIN_VAL : -9223372036854775808L
			StringToLong.convertStringToLong("-9223372036854775809"); 
			fail("- has to be at the start");
		} catch (final NumberFormatException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testStirngForBoundryMax() {
		try {
			// MAX_VAL : 9223372036854775807L
			StringToLong.convertStringToLong("9223372036854775808");
			fail("- has to be at the start");
		} catch (final NumberFormatException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testStirngForSentence() {
		try {
			// MAX_VAL : 9223372036854775807L
			StringToLong.convertStringToLong("hdkjhs 1244 dfjhdk");
			fail("Sentence can not be converted");
		} catch (final NumberFormatException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testStirngForSpecialChar() {
		try {
			// MAX_VAL : 9223372036854775807L
			StringToLong.convertStringToLong("##92233");
			fail("Special character can not be converted");
		} catch (final NumberFormatException e) {
			assertNotNull(e);
		}
	}
	
	// ======================= positive test case
	@Test
	public void testStirngForNegative() {
		final Long val = StringToLong.convertStringToLong("-2345");
		assertEquals(new Long("-2345"), val);
	}
	
	@Test
	public void testStirng() {
		final Long val = StringToLong.convertStringToLong("2345");
		assertEquals(new Long("2345"), val);
	}
}
