/*
 * EqualsDelta.java
 *
 * Copyright (c) 2012 Roth Michaels. All rights reserved.
 *
 * The use and distribution terms for this software are covered by the
 * Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php) 
 * which can be found in the file epl-v10.html at the root of this
 * distribution. By using this software in any fashion, you are agreeing
 * to be bound by the terms of this license.
 *
 * EXCEPT AS EXPRESSLY SET FORTH IN THIS AGREEMENT, THE PROGRAM IS
 * PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, EITHER EXPRESS OR IMPLIED INCLUDING, WITHOUT LIMITATION, ANY
 * WARRANTIES OR CONDITIONS OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY
 * OR FITNESS FOR A PARTICULAR PURPOSE. Each Recipient is solely
 * responsible for determining the appropriateness of using and
 * distributing the Program and assumes all risks associated with its
 * exercise of rights under this Agreement , including but not limited
 * to the risks and costs of program errors, compliance with applicable
 * laws, damage to or loss of data, programs or equipment, and
 * unavailability or interruption of operations.
 *
 * EXCEPT AS EXPRESSLY SET FORTH IN THIS AGREEMENT, NEITHER RECIPIENT
 * NOR ANY CONTRIBUTORS SHALL HAVE ANY LIABILITY FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING WITHOUT LIMITATION LOST PROFITS), HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 * TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
 * THE USE OR DISTRIBUTION OF THE PROGRAM OR THE EXERCISE OF ANY RIGHTS
 * GRANTED HEREUNDER, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGES.
 *
 * You must not remove this notice, or any other, from this software.
 * 
 * Dec 19, 2011 
 */
package us.rothmichaels.testing.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;

/**
 * Matcher for floating point values with delta.
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
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
	 * @param expected
	 *            expected value
	 * @param delta
	 *            allowed delta +/-
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
	 * @param expected
	 *            expected value
	 * @param delta
	 *            allowed delta +/-
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
	 * @param expected
	 *            expected value
	 * @param delta
	 *            allowed delta +/-
	 * 
	 * @return new matcher
	 */
	@Factory
	public static EqualsDelta equalsDelta(Float expected, Float delta) {
		return new EqualsDelta(expected, delta);
	}

	/**
	 * Factory to create a {@code double} delta matcher.
	 * 
	 * @param expected
	 *            expected value
	 * @param delta
	 *            allowed delta +/-
	 * 
	 * @return new matcher
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
