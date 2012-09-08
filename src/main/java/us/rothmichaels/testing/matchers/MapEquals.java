/*
 * MapEquals.java
 *
 * Jul 30, 2012 
 */
package us.rothmichaels.testing.matchers;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsEqual;

import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector.Matcher;

/**
 * <p>A matcher for comparing Maps.
 * 
 * <p>Maps are considered equal if all keys and values are equal
 * and if a value is an array will compare the data not array references.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 * @param <K> Map key type
 * @param <V> Map value type
 */
public class MapEquals<K,V> extends TypeSafeMatcher<Map<K,V>> {
	
	private final Map<K, V> expectedMap;
	

	/**
	 * @param expectedMap
	 */
	MapEquals(Map<K, V> expectedMap) {
		super();
		this.expectedMap = expectedMap;
	}

	/**
	 * Returns true all keys and values in Map are equal.
	 * 
	 * If a value is an array, compares the arrays
	 * for data equality not reference equality.
	 * 
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	public boolean matchesSafely(Map<K, V> actualMap) {
		final Set<K> expectedKeys = expectedMap.keySet();
		final Set<K> actualKeys = actualMap.keySet();
		
		// check key equality
		if (!actualKeys.equals(expectedKeys)) {
			return false;
		}
		
		// check value equality
		for (K key : actualKeys) {
			// use IsEqual<T> matcher to compare values
			IsEqual<V> matcher = new IsEqual<V>(expectedMap.get(key));
			if (!matcher.matches(actualMap.get(key))) {
				return false;
			}
		}
		
		// made it through all tests, so Maps but be equal
		return true;
	}
	
	/**
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo(Description description) {
		description.appendText("<"+this.expectedMap+">");
	}

	@Factory
	public static <K,V> MapEquals<K, V> mapEquals(Map<K,V> expected) {
		return new MapEquals<K, V>(expected);
	}



    
}