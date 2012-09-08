/*
 * EqualsDelta.java
 *
 * Dec 19, 2011 
 */
package us.rothmichaels.testing.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matcher for floating point values with delta.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class EqualsDelta extends BaseMatcher<Number> {

	private final Float minf;
	private final Float maxf;
	
	private final Double min;
	private final Double max;
	
	/**
	 * Create a {@code float} delta matcher.
	 * 
	 * @param expected		expected value
	 * @param delta			allowed delta +/-
	 */
	private EqualsDelta(Float expected, Float delta) {
		this.minf = expected - delta;
		this.maxf = expected + delta;
		this.min = null;
		this.max = null;
	}
	
	/**
	 * Create a {@code double} delta matcher
	 * 
	 * @param expected		expected value
	 * @param delta			allowed delta +/-
	 */
	private EqualsDelta(Double expected, Double delta) {
		this.min = expected - delta;
		this.max = expected + delta;
		this.minf = null;
		this.maxf = null;
	}
	
	/**
	 * Factory to create a {@code float} delta matcher.
	 * 
	 * @param expected		expected value
	 * @param delta			allowed delta +/-
	 * 
	 * @return				new matcher
	 */
	@Factory
	public static EqualsDelta equalsDelta(Float expected, Float delta) {
		return new EqualsDelta(expected, delta);
	}
	
	/**
	 * Factory to create a {@code double} delta matcher.
	 * 
	 * @param expected		expected value
	 * @param delta			allowed delta +/-
	 * 
	 * @return				new matcher
	 */
	@Factory
	public static EqualsDelta equalsDelta(Double expected, Double delta) {
		return new EqualsDelta(expected, delta);
	}
	
	/**
	 * @see org.hamcrest.SelfDescribing#describeTo(org.hamcrest.Description)
	 */
	@Override
	public void describeTo(Description description) {
		description.appendText("asdf");
		
	}

	/**
	 * @see org.hamcrest.Matcher#matches(java.lang.Object)
	 */
	@Override
	public boolean matches(Object arg0) {
		if (arg0 instanceof Float) {
			final Float f = ((Float) arg0).floatValue();
			return (minf != null) && (f >= minf && f <= maxf);
		} else if (arg0 instanceof Double) {
			final Double d = ((Double) arg0).doubleValue();
			return (min != null) && (d >= min && d <= max);
		}
		
		return false;
	}

}
