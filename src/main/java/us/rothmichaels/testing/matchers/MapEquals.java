/*
 * MapEquals.java
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
 * Jul 30, 2012 
 */
package us.rothmichaels.testing.matchers;

import java.util.Map;
import java.util.Set;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsEqual;

/**
 * <p>
 * A matcher for comparing Maps.
 * 
 * <p>
 * Maps are considered equal if all keys and values are equal and if a value is
 * an array will compare the data not array references.
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 * 
 * @param <K>
 *            Map key type
 * @param <V>
 *            Map value type
 */
public class MapEquals<K, V> extends TypeSafeMatcher<Map<K, V>> {

	private final Map<K, V> expectedMap;

	/**
	 * Create a map matcher.
	 * 
	 * @param expectedMap
	 *            map to match against
	 */
	private MapEquals(Map<K, V> expectedMap) {
		super();
		this.expectedMap = expectedMap;
	}

	/**
	 * Returns true all keys and values in Map are equal.
	 * 
	 * If a value is an array, compares the arrays for data equality not
	 * reference equality.
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
		for (final K key : actualKeys) {
			// use IsEqual<T> matcher to compare values
			final IsEqual<V> matcher = new IsEqual<V>(expectedMap.get(key));
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
		description.appendText("<" + this.expectedMap + ">");
	}

	/**
	 * Factory to create a map matcher.
	 * 
	 * @param expected
	 *            expected map
	 * 
	 * @return the matcher
	 */
	@Factory
	public static <K, V> MapEquals<K, V> mapEquals(Map<K, V> expected) {
		return new MapEquals<K, V>(expected);
	}

}