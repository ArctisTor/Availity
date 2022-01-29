package availity.java.codeExercise4;


import availity.java.codeExercise4.util.ParentheseChecker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

@SpringBootTest
public class codeExercise4Test {

    @Test
    public void testValildParentheseChecker() {
        //happy paths
        assertTrue(ParentheseChecker.validParenthese("()"));
        assertTrue(ParentheseChecker.validParenthese("()()"));
        assertTrue(ParentheseChecker.validParenthese("(())"));
        assertTrue(ParentheseChecker.validParenthese("(())(())"));
        assertTrue(ParentheseChecker.validParenthese("(()) (())"));
        assertTrue(ParentheseChecker.validParenthese("((()))((()))"));
        assertTrue(ParentheseChecker.validParenthese("( ( ( ) ) ) ( ( ( ) ) )"));

        //sad paths
        assertFalse(ParentheseChecker.validParenthese(""));
        assertFalse(ParentheseChecker.validParenthese("("));
        assertFalse(ParentheseChecker.validParenthese(")"));
        assertFalse(ParentheseChecker.validParenthese("(()"));
        assertFalse(ParentheseChecker.validParenthese("(())(()"));
        assertFalse(ParentheseChecker.validParenthese("((())(())"));
    }

    @Test
    public void testValidParentheseCheckerStack(){
        //happy paths
        assertTrue(ParentheseChecker.validParentheseStack("()"));
        assertTrue(ParentheseChecker.validParentheseStack("()()"));
        assertTrue(ParentheseChecker.validParentheseStack("(())"));
        assertTrue(ParentheseChecker.validParentheseStack("(())(())"));
        assertTrue(ParentheseChecker.validParentheseStack("(()) (())"));
        assertTrue(ParentheseChecker.validParentheseStack("((()))((()))"));
        assertTrue(ParentheseChecker.validParentheseStack("( ( ( ) ) ) ( ( ( ) ) )"));

        //sad paths
        assertFalse(ParentheseChecker.validParenthese(""));
        assertFalse(ParentheseChecker.validParentheseStack("("));
        assertFalse(ParentheseChecker.validParentheseStack(")"));
        assertFalse(ParentheseChecker.validParentheseStack("(()"));
        assertFalse(ParentheseChecker.validParentheseStack("(())(()"));
        assertFalse(ParentheseChecker.validParentheseStack("((())(())"));
    }

}
