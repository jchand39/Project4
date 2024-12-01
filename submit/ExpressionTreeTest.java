import org.junit.*;

import static org.junit.Assert.*;

/**
 * Expression tree test class.
 * 
 * @author Jared Chandler
 * @version 17 November 2024
 */
public class ExpressionTreeTest {
    private ExpressionTree tree;

    /**
     * setup() method, runs before each test method below.
     * Reinitialize the ExpressionTree object before each test.
     */
    @Before
    public void setup() {
        // Initialize runner with a valid postfix expression
        tree = new ExpressionTree("3 4 +");
    }

    /**
     * Test for a valid postfix expression with a single operation.
     */
    @Test
    public void testParseAndEvaluateSingleOperation() {
        tree = new ExpressionTree("3 4 +");
        assertTrue("The expression should be parsed successfully.", tree.parse());
        assertEquals("The evaluated result should be 7.", 7, tree.evaluate());
        assertEquals("The infix notation should be (3 + 4).", "(3 + 4)", tree.infixNotation());
        tree = new ExpressionTree("4 3 -");
        assertTrue("The expression should be parsed successfully.", tree.parse());
        assertEquals("The evaluated result should be 1.", 1, tree.evaluate());
        assertEquals("The infix notation should be (3 + 4).", "(4 - 3)", tree.infixNotation());
    }

    /**
     * Test parsing of a more complex valid postfix expression.
     * Verifies that parsing succeeds and root is correctly set.
     */
    @Test
    public void testParseValidComplexExpression() {
        tree = new ExpressionTree("3 4 + 5 *");
        assertTrue("Parse should succeed for '3 4 + 5 *'", tree.parse());
        assertNotNull("Root should not be null after parsing", tree.getRoot());
    }

    /**
     * Test for a postfix expression with multiple operations.
     */
    @Test
    public void testParseAndEvaluateMultipleOperations() {
        tree = new ExpressionTree("3 4 * 5 +");
        assertTrue("The expression should be parsed successfully.", tree.parse());
        assertEquals("The evaluated result should be 17.", 17, tree.evaluate());
        assertEquals("((3 * 4) + 5)", tree.infixNotation());
    }

    /**
     * Test parsing of a valid postfix expression with all operators.
     * Checks parsing and root for expressions with multiple operators.
     */
    @Test
    public void testParseValidExpressionAllOperators() {
        tree = new ExpressionTree("3 4 + 5 6 - *");
        assertTrue("Parse should succeed for '3 4 + 5 6 - *'", tree.parse());
        assertNotNull("Root should not be null after parsing", tree.getRoot());
    }

    /**
     * Test parsing of a valid postfix expression with multi-digit numbers.
     * Ensures parsing handles numbers larger than single digits.
     */
    @Test
    public void testParseValidExpressionWithMultiDigitNumbers() {
        tree = new ExpressionTree("10 20 +");
        assertTrue("Parse should succeed for '10 20 +'", tree.parse());
        assertNotNull("Root should not be null after parsing", tree.getRoot());
    }

    /**
     * Tests the evaluation of an expression with the addition operator.
     * This test case covers line 104 in the evaluate method.
     */
    @Test
    public void testEvaluateAddition() {
        // Set up the expression tree with the postfix expression "3 4 +"
        ExpressionTree tree = new ExpressionTree("3 4 +");
        
        // Parse the expression and evaluate it
        tree.parse();
        
        // Assert that the evaluation result is correct (3 + 4 = 7)
        assertEquals(7, tree.evaluate()); // Should cover the "+" case (line 104)
    }

    /**
     * Tests the evaluation of an expression where division by zero occurs.
     * This test case covers lines 109-110 in the evaluate method to handle the ArithmeticException.
     */
    @Test(expected = ArithmeticException.class)
    public void testEvaluateDivisionByZero() {
        // Set up the expression tree with the postfix expression "3 0 /"
        ExpressionTree tree = new ExpressionTree("3 0 /");
        
        // Parse the expression and evaluate it, which should throw an ArithmeticException
        tree.parse();
        tree.evaluate(); // This should throw ArithmeticException (line 109-110)
    }

    /**
     * Tests the evaluation of an expression with an invalid operator.
     * This test case covers line 113 in the evaluate method to handle IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEvaluateWithInvalidOperator() {
        // Set up the expression tree with an invalid postfix expression "3 4 %"
        ExpressionTree tree = new ExpressionTree("3 4 %");

        // Parse the expression and evaluate it, which should throw an IllegalArgumentException
        tree.parse();
        tree.evaluate(); // This should throw IllegalArgumentException (line 113)
    }
    
    /**
     * Test evaluation of an expression involving division.
     */
    @Test
    public void testEvaluateDivisionOperator() {
        tree = new ExpressionTree("8 2 /");
        assertTrue("Parse should succeed for '8 2 /'", tree.parse());
        int result = tree.evaluate();
        assertEquals("Evaluation of '8 2 /' should be 4", 4, result);
    }

    /**
     * Test for an invalid postfix expression with missing operands.
     */
    @Test
    public void testParseInvalidExpressionMissingOperands() {
        tree = new ExpressionTree("3 +");
        assertFalse("The expression should not be valid.", tree.parse());
    }

    /**
     * Test for an invalid postfix expression with an unknown operator.
     */
    @Test
    public void testParseInvalidExpressionInvalidOperator() {
        tree = new ExpressionTree("3 4 %");
        assertFalse("The expression should not be valid.", tree.parse());
    }

    /**
     * Test for parsing and evaluating an expression with parentheses.
     */
    @Test
    public void testParseAndEvaluateWithParentheses() {
        tree = new ExpressionTree("2 3 + 4 5 * + 5 /");
        assertTrue("The expression should be parsed successfully.", tree.parse());
        assertEquals("The evaluated result should be 5.", 5, tree.evaluate());
        assertEquals("(((2 + 3) + (4 * 5)) / 5)",
                tree.infixNotation());
    }

    /**
     * Test for evaluating a single number expression.
     */
    @Test
    public void testEvaluateSingleNumber() {
        tree = new ExpressionTree("10");
        assertTrue("The expression should be parsed successfully.", tree.parse());
        assertEquals("The evaluated result should be 10.", 10, tree.evaluate());
        assertEquals("The infix notation should be 10.", "10", tree.infixNotation());
    }

    /**
     * Test for parsing and evaluating a complex postfix expression.
     */
    @Test
    public void testParseAndEvaluateComplexExpression() {
        tree = new ExpressionTree("3 4 5 * +");
        assertTrue("The expression should be parsed successfully.", tree.parse());
        assertEquals("The evaluated result should be 23.", 23, tree.evaluate());
        assertEquals("(3 + (4 * 5))", tree.infixNotation());
    }

    /**
     * Test for parsing an empty postfix expression.
     */
    @Test
    public void testParseEmptyExpression() {
        tree = new ExpressionTree("");
        assertFalse("An empty postfix expression should not be valid.", tree.parse());
        assertNull("Root should be null after failed parsing", tree.getRoot());
    }

    /**
     * Tests the evaluate method for valid operators (+, -, *, /) in an expression tree.
     * The method ensures that the correct result is returned for each operation.
     */
    @Test
    public void testEvaluateWithValidToken() {
        // Test for addition
        ExpressionTree tree = new ExpressionTree("3 4 +");
        tree.parse();
        assertEquals(7, tree.evaluate()); // Assuming evaluate() is used correctly here

        // Test for subtraction
        tree = new ExpressionTree("5 2 -");
        tree.parse();
        assertEquals("Subtraction should return 3", 3, tree.evaluate());

        // Test for multiplication
        tree = new ExpressionTree("3 4 *");
        tree.parse();
        assertEquals("Multiplication should return 12", 12, tree.evaluate());

        // Test for division
        tree = new ExpressionTree("10 2 /");
        tree.parse();
        assertEquals("Division should return 5", 5, tree.evaluate());
    }

    /**
     * Test parsing of a postfix expression with an invalid token.
     * Checks that parsing fails and root remains null when invalid tokens are present.
     */
    @Test
    public void testParseInvalidToken() {
        tree = new ExpressionTree("3 4 &");
        assertFalse("Parse should fail for invalid token '&'", tree.parse());
        assertNull("Root should be null after failed parsing", tree.getRoot());
    }

    /**
     * Test parsing of a postfix expression with insufficient operands.
     * Ensures parsing fails when there are not enough operands for an operator.
     */
    @Test
    public void testParseInsufficientOperands() {
        tree = new ExpressionTree("3 +");
        assertFalse("Parse should fail for '3 +'", tree.parse());
        assertNull("Root should be null after failed parsing", tree.getRoot());
    }

    /**
     * Test evaluation after a failed parse.
     */
    @Test
    public void testEvaluateAfterFailedParse() {
        tree = new ExpressionTree("3 4 @"); // '@' is an invalid operator
        assertFalse("Parse should fail for invalid operator '@'", tree.parse());
    }

    /**
     * Test infix notation with null expression.
     * Ensures that infix notation returns an empty string when the tree is empty.
     */
    @Test
    public void testInfixNotationNullExpression() {
        tree = new ExpressionTree("");
        assertFalse("Parse should fail", tree.parse());
        String infix = tree.infixNotation();
        assertEquals("Infix notation should be ''", "", infix);
    }
}
