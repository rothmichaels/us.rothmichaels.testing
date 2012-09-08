/*
 * EqualsDelta.java
 *
 * Dec 19, 2011 
 */
package us.rothmichaels.testing.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class EqualsDelta extends TypeSafeMatcher<Float> {

	private float min;
	private float max;
	
	/**
	 * @param expected
	 * @param delta
	 */
	private EqualsDelta(float expected, float delta) {
		this.min = expected - delta;
		this.max = expected + delta;
	}
	
	/**
	 * 
	 */
	@Deprecated
	private EqualsDelta() {
		super();
		throw new AssertionError(this);
	}

	/**
	 * @param expectedType
	 */
	@Deprecated
	private EqualsDelta(Class<Float> expectedType) {
		super(expectedType);
		throw new AssertionError(this);
	}

	

	/**
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo(Description description) {
		description.appendText("asdf");
		
	}

	/**
	 * @see org.hamcrest.TypeSafeMatcher#matchesSafely(java.lang.Object)
	 */
	@Override
	public boolean matchesSafely(Float item) {
		return (item >= min && item <= max);
	}
	
	@Factory
	public static Matcher<Float> equalsDelta(Float expected, Float delta) {
		return new EqualsDelta(expected, delta);
	}

}
