/*
 * FilePathMatcher.java
 *
 * Aug 29, 2012 
 */
package us.rothmichaels.testing.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * <p>A matcher that always returns true and saves
 * the actual input to use in later assertions.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 */
public class AnyObjectMatcherAndSaver<T> extends TypeSafeMatcher<T> {

	/** Path relative to test data directory */
	private T itsObj;

	/**
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo(Description description) {
		description.appendText("< "+itsObj+" >");
	}

	/**
	 * Stores the matched item and return true
	 * 
	 * @param item 	relative file path to store
	 * 
	 * @return 		true / always matches
	 * 
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	public boolean matchesSafely(T item) {
		itsObj = item;
		return true;
	}
	
	/**
	 * Return the object that was matched against.
	 * 
	 * @return		the string
	 */
	public T getMatchedObject() {
		return itsObj;
	}

}
