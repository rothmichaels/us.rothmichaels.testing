/*
 * EqualsDeltaTests.java
 *
 * Sep 17, 2012 
 */
package us.rothmichaels.testing.matchers;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link EqualsDelta}.
 *
 * @author Roth Michaels 
 * (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class EqualsDeltaTests {
	
	static final double DELTA = 0.001;
	
	static final double WITHIN_DELTA = DELTA / 2.0;
	
	static final double OUTSIDE_DELTA = DELTA * 2.0;
	
	EqualsDelta floatMatcher;
	float expectedFloat;
	float goodF;
	float badF;
	EqualsDelta doubleMatcher;
	double expectedDouble;
	double goodD;
	double badD;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		expectedFloat = (float) Math.random();
		goodF = expectedFloat + (float) WITHIN_DELTA;
		badF = expectedFloat + (float) OUTSIDE_DELTA;
		expectedDouble = Math.random();
		goodD = expectedDouble + WITHIN_DELTA;
		badD = expectedDouble + OUTSIDE_DELTA;
		
		
		floatMatcher = EqualsDelta.equalsDelta(expectedFloat, (float) DELTA);
		doubleMatcher = EqualsDelta.equalsDelta(expectedDouble, DELTA);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void mathFloatAgainstFloat() {
		assertTrue("with delta", floatMatcher.matches(goodF));
		assertFalse("outside delta", floatMatcher.matches(badF));
	}
	
	@Test
	public void matchFloatAgainstDouble() {
		assertFalse("with delta",doubleMatcher.matches(goodF));
		assertFalse("outside delta", doubleMatcher.matches(badF));
	}
	
	@Test
	public void mathDoubleAgainstDouble() {
		assertTrue("with delta",doubleMatcher.matches(goodD));
		assertFalse("outside delta", doubleMatcher.matches(badD));
	}
	
	@Test
	public void matchDoubleAgainstFloat() {
		assertFalse("with delta",floatMatcher.matches(goodD));
		assertFalse("outside delta", floatMatcher.matches(badD));
	}

}
