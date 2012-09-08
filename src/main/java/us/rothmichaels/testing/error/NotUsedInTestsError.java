/*
 * NotUsedInTests.java
 *
 * Aug 14, 2012
 */
package us.rothmichaels.testing.error;

/**
 * Throw instead of an {@link AssertionError} in methods you wish to
 * throw an exception from when used unexpectedly in a test.
 *
 * @author Roth Michaels (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 */
public class NotUsedInTestsError extends AssertionError {
	private static final long serialVersionUID = 5477715812750379221L;

    public NotUsedInTestsError(Object arg0) {
        super(arg0);
    }

    public NotUsedInTestsError(boolean arg0) {
        super(arg0);
    }

    public NotUsedInTestsError(char arg0) {
        super(arg0);
    }

    public NotUsedInTestsError(int arg0) {
        super(arg0);
    }

    public NotUsedInTestsError(long arg0) {
        super(arg0);
    }

    public NotUsedInTestsError(float arg0) {
        super(arg0);
    }

    public NotUsedInTestsError(double arg0) {
        super(arg0);
    }
}
