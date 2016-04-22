/**
 * 
 */
package fi.csc.avaa.test.junit;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;

/**
 * @author jmlehtin
 *
 */
public final class Assertions {

	public static void assertTrue(String errMsg, boolean condition) {
		Assert.assertTrue(errMsg, condition);
	}
	
	public static void assertFalse(String errMsg, boolean condition) {
		Assert.assertFalse(errMsg, condition);
	}
	
	public static void assertNull(String errMsg, Object obj) {
		Assert.assertNull(errMsg, obj);
	}
	
	public static void assertNotNull(String errMsg, Object obj) {
		Assert.assertNotNull(errMsg, obj);
	}
	
	// TODO: Create more hamcrest matchers
	public static <T, S extends T> void assertIsNot(String errMsg, T actual, S expected) {
		assertThat(errMsg, actual, not(expected));
	}
	
}
