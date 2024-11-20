import itsc2214.*; // not needed now, but you might in your projects

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
    * @return a string representation of the initialized ExpressionTree
    */
    public ExpressionTree(String postfix) {
        if (postfix == null || postfix.isEmpty()) {
            throw new IllegalArgumentException("Postfix expression cannot be null or empty.");
        }
        this.postfix = postfix;
        this.root = null;
    }

    /**
    * Parses the postfix expression to build the expression tree.
    * 
    * @return true if the parsing and tree construction were successful, false otherwise
    */
    public boolean parse() {
        StackADT<String> 
    }

    /**
    * Checks if a given token is a numeric value.
    * 
    * @return true if the token is numeric, false otherwise
    */
    public boolean isNumbering() {
        return;
    }

    /**
    * Checks if a given token is a valid operator (+, -, *, /, etc.).
    * 
    * @return true if the token is an operator, false otherwise
    */
    public boolean isOperator() {
        return;
    }

    /**
    * Retrieves the root node of the expression tree.
    * 
    * @return the root BinaryNode of the tree
    */
    public BinaryNode<String> getRoot() {
        return;
    }

    /**
    * Recursively evaluates the expression represented by the subtree rooted at the given node.
    * 
    * @param node the root of the subtree to evaluate
    * @return the result of evaluating the expression
    * @throws IllegalArgumentException if the node is null
    */
    private int evaluate(BinaryNode<String> node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }
        String value = node.getValue();
        if (isNumber(value))
            return Integer.parseInt(value);
            
        int leftValue = evaluate(node.getLeft());
        int rightValue = evaluate(node.getRight());

        return switch (value) {
            case "+" -> leftValue + rightValue;
            case "-" -> leftValue + rightValue;
            case "*" -> leftValue + rightValue;
            case "/" -> {
                if(rightValue == 0) throw new ArithmeticException("Division by 0");
                yield leftValue / rightValue;
            }
            default -> throw new IllegalArgumentException("Invalid operator: " + value);
        };
    }
    
    
    /**
    * Evaluates the entire expression tree starting from the root.
    * 
    * @return the result of evaluating the expression
    * @throws ArithmeticException if an invalid operation is encountered
    */
    private int evaluate() throws ArithmeticException {
        return evaluate(root);
    }

    /**
     * Does something simple
     * 
     * @param
     * @return
     */
    private String infixNotation() {
        return;
    }

    /**
     * Does something simple
     * 
     * @param
     * @return
     */
    private String infixNotation() {
        return;
    }
}
