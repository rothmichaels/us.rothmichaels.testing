/*
 * MapEqualsTests.java
 *
 * Sep 17, 2012 
 */
package us.rothmichaels.testing.matchers;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link MapEquals}
 *
 * @author Roth Michaels 
 * (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class MapEqualsTests {

	static final Map<String,Object> EXPECTED_MAP;

	static final Object EXPECTED_OBJECT = new Object();

	static {
		EXPECTED_MAP = new HashMap<String, Object>();
		EXPECTED_MAP.put("FOO", new int[] { 1, 2, 3 });
		EXPECTED_MAP.put("BAR", EXPECTED_OBJECT);
		EXPECTED_MAP.put("BAZ", "hello, world");
	}

	MapEquals<String, Object> testMatcher;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testMatcher = MapEquals.mapEquals(EXPECTED_MAP);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void matchEqual() {
		Map<String,Object> testMap = new HashMap<String, Object>();
		testMap.put("FOO", new int[] { 1, 2, 3 });
		testMap.put("BAR", EXPECTED_OBJECT);
		testMap.put("BAZ", "hello, world");

		assertTrue(testMatcher.matchesSafely(testMap));
	}

	@Test
	public void matchSame() {
		assertTrue(testMatcher.matchesSafely(EXPECTED_MAP));
	}

	@Test
	public void matchClone() {
		Map<String,Object> testMap = new HashMap<String, Object>(EXPECTED_MAP);
		
		assertTrue(testMatcher.matchesSafely(testMap));
	}
	
	@Test
	public void notEqual() {
		Map<String,Object> testMap = new HashMap<String, Object>();
		testMap.put("FOO", new int[] { 1, 0, 3 });
		testMap.put("BAR", EXPECTED_OBJECT);
		testMap.put("BAZ", "hello world");
		
		assertFalse(testMatcher.matchesSafely(testMap));
	}

}
