/*
 * FilePathMatcherTests.java
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
 * Aug 29, 2012 
 */
package us.rothmichaels.testing.matchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests {@link AnyObjectMatcherAndSaver} with Strings.
 * 
 * @author Roth Michaels (<i><a
 *         href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
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
