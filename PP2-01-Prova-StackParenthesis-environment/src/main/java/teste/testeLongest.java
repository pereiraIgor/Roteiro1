package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.stack.parenthesis.LongestValidParenthesisSubstringImpl;

public class testeLongest {
	private LongestValidParenthesisSubstringImpl longs;

	@Before
	public void iniciaLongest() {
		longs = new LongestValidParenthesisSubstringImpl();
	}

	@Test
	public void testFindLongest() {
		assertEquals("((()))()", longs.findLongest("((()))()((()()"));
	}
}
