import itsc2214.*; // not needed now, but you might in your projects
import java.util.Stack;

/**
 * Project example for testng in ITSC 2214 Course.
 * 
 * @author Jared Chandler
 * @version 17 November 2024
 */
public class ExpressionTree {

    private String postfix;
    private BinaryNode<String> root;

    /**
    * Default constructor for the ExpressionTree class.
    * Initializes the tree and sets up necessary variables.
    *
    * @param postfix A string representing a postfix expression to be parsed.
    */
    public ExpressionTree(String postfix) {
        this.postfix = postfix;
        this.root = null;
    }

    /**
    * Parses the postfix expression to build the expression tree.
    * 
    * @return true if the parsing and tree construction were successful, false otherwise
    */
    public boolean parse() {
        Stack<BinaryNode<String>> stack = new Stack<>();
        
        for (String token : postfix.split(" ")) {
            if (isNumber(token)) {
                stack.push(new BinaryNode<>(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    return false;
                }
                BinaryNode<String> right = stack.pop();
                BinaryNode<String> left = stack.pop();
                stack.push(new BinaryNode<>(token, left, right));
            } else {
                return false;
            }
        }
        
        root = stack.pop();
        return true;
    }
    


    /**
    * Checks if a given token is a numeric value.
    * 
    * @param token The string token to be checked if it is a numeric value
    * @return true if the token is numeric, false otherwise
    */
    public boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
    * Checks if a given token is a valid operator (+, -, *, /, etc.).
    * 
    * @param token The string to be checked as a valid operator
    * @return true if the token is an operator, false otherwise
    */
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    /**
    * Retrieves the root node of the expression tree.
    * 
    * @return the root BinaryNode of the tree
    */
    public BinaryNode<String> getRoot() {
        return root;
    }

    /**
    * Recursively evaluates the expression represented by the subtree rooted at the given node.
    * 
    * @param node the root of the subtree to evaluate
    * @return the result of evaluating the expression
    * @throws IllegalArgumentException if the node is null
    */
    private int evaluate(BinaryNode<String> node) {
        if (root == null) {
            throw new IllegalArgumentException("Expression tree is empty or invalid.");
        }
        
        String value = node.getValue();
        if (isNumber(value))
            return Integer.parseInt(value);

        int leftValue = evaluate(node.getLeft());
        int rightValue = evaluate(node.getRight());

        switch (value) {
            case "+": return leftValue + rightValue;
            case "-": return leftValue - rightValue;
            case "*": return leftValue * rightValue;
            case "/": 
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by 0");
                }
                return leftValue / rightValue;
            default: throw new IllegalArgumentException("Invalid operator: " + value);
        }
    }
    
    /**
    * Evaluates the entire expression tree starting from the root.
    * 
    * @return the result of evaluating the expression
    * @throws ArithmeticException if an invalid operation is encountered
    */
    public int evaluate() throws ArithmeticException {
        return evaluate(root);
    }

    /**
    * Recursively generates the infix notation for the expression tree rooted at the given node.
    * The expression is represented with parentheses around operators and their operands 
    * to maintain the correct order of operations.
    * 
    * @param node the root node of the subtree to convert to infix notation.
    * @return a string representing the subtree in infix notation.
    */
    private String infixNotation(BinaryNode<String> node) {
        if (node == null) {
            return "";
        }

        String value = node.getValue();

        if (isNumber(value)) {
            return value;
        }

        String left = infixNotation(node.getLeft());
        String right = infixNotation(node.getRight());

        return "(" + left + " " + value + " " + right + ")";
    }

    /**
    * Converts the expression tree to an infix notation string.
    * The expression is represented with parentheses around operators 
    * and their operands to maintain the correct order of operations.
    * 
    * @return a string representing the expression in infix notation.
    */
    public String infixNotation() {
        return infixNotation(root);
    }
}
