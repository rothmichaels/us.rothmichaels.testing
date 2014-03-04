/*
 * AsyncTester.java
 *
 * Copyright (c) 2014 Roth Michaels. All rights reserved.
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
 * Mar 4, 2014 
 */
package us.rothmichaels.testing.async;

/**
 * Class for throwing JUnit assertions from another thread.
 *
 * @author Roth Michaels 
 * (<i><a href="mailto:roth@rothmichaels.us">roth@rothmichaels.us</a></i>)
 *
 */
public class AsyncTester {

	/** Thread used to run tests */ 
	private final Thread thread;
	/** Possible error returned from tests */
	private volatile AssertionError error;
	
	/**
	 * Create an object for running tests in
	 * another thread and returning errors thrown
	 * back to the main thread.
	 * 
	 * @param runner Will be run by internal thread 
	 * to perform tests when calling runTest()
	 */
	public AsyncTester(final Runnable runner) {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					runner.run();
				} catch (AssertionError e) {
					error = e;
				}
			}
		});
	}
	
	/**
	 * Run the tests via the Runner provided during construction.
	 */
	public void runTest() {
		thread.start();
	}
	
	/**
	 * Will throw an AssertionError if an error was thrown
	 * by the test running thread.
	 */
	public void verify() {
		try {
			thread.join();
			if (error != null) {
				throw error;
			}
		} catch (InterruptedException e) {
			throw new AssertionError(e);
		}
	}
}
