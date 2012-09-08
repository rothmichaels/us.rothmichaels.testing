/*
 * FilePathMatcherTests.java
 *
 * Aug 29, 2012 
 */
package us.rothmichaels.testing.matchers;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link AnyObjectMatcherAndSaver} with Strings.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class AnyStringMatcherAndSaverTests {

	AnyObjectMatcherAndSaver<String> testMatcher;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testMatcher = new AnyObjectMatcherAndSaver<String>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Should always match.
	 */
	@Test
	public void matchAnyString() {
		assertTrue(testMatcher.matchesSafely("FOO"));
	}
	
	/**
	 * Check that the right object is returned.
	 */
	@Test
	public void returnCorrectString() {
		testMatcher.matchesSafely("BAR");
		assertEquals("BAR", testMatcher.getMatchedObject());
	}
}
