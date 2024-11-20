import itsc2214.BinaryNode;

/**
 * App class
 * 
 * @author Jared Chandler
 * @version July 2024
 */
public class App {
    private static final String RESET = "\u001B[0m", GREEN = "\u001B[32m", RED = "\u001B[31m", CYAN = "\u001B[36m", 
                                YELLOW = "\u001B[33m", BLUE = "\u001B[34m", PURPLE = "\u001B[35m", GRAY = "\u001B[90m";

    public static void main(String[] args) {
        testExpression("3 4 +", "(3 + 4)", 7); testExpression("3 4 * 5 +", "((3 * 4) + 5)", 17);
        testExpression("10", "10", 10); testExpression("2 3 + 4 5 * + 5 /", "(((2 + 3) + (4 * 5)) / 5)", 5);
        testExpression("4 5 + 3 2 * +", "((4 + 5) + (3 * 2))", 15); testExpression("3 0 /", "Invalid (Division by Zero)", null);
        testExpression("5 3 - 2 *", "((5 - 3) * 2)", 4);
    }

    private static void testExpression(String postfix, String expectedInfix, Integer expectedValue) {
        System.out.printf(BLUE + "%-30s" + CYAN + "%s" + RESET + "\n", "Testing Expression:", postfix);
        ExpressionTree tree = new ExpressionTree(postfix);
        if (!tree.parse()) { System.out.println(RED + "Error: Invalid postfix expression.\n" + RESET); return; }

        System.out.printf(GREEN + "%-30s" + RESET + "%s\n", "Expected Infix Notation:", expectedInfix);
        System.out.printf(YELLOW + "%-30s" + RESET + "%s\n", "Actual Infix Notation:", colorInfixNotation(tree.getRoot(), 0));

        try {
            int result = tree.evaluate();
            String checkMark = (expectedValue != null && result == expectedValue) ? GREEN + " (VALID)" + RESET : RED + " (INVALID)" + RESET;
            System.out.printf(GREEN + "%-30s" + RESET + "%s\n", "Expected Evaluation Result:", expectedValue);
            System.out.printf(YELLOW + "%-30s" + RESET + "%s%s\n", "Actual Evaluation Result:", result, checkMark);
        } catch (ArithmeticException e) {
            System.out.printf(RED + "%-30s %s\n" + RESET, "Error:", e.getMessage());
        }
        System.out.println(BLUE + "-------------------------------------------------" + RESET);
    }

    private static String colorInfixNotation(BinaryNode<String> node, int depth) {
        if (node == null) return "";
        if (isNumber(node.getValue())) return GRAY + node.getValue() + RESET;

        String color = getColorForDepth(depth), left = colorInfixNotation(node.getLeft(), depth + 1), right = colorInfixNotation(node.getRight(), depth + 1);
        return color + "(" + GRAY + left + RESET + " " + node.getValue() + " " + GRAY + right + color + ")" + RESET;
    }

    private static String getColorForDepth(int depth) {
        return switch (depth % 5) { case 0 -> RED; case 1 -> GREEN; case 2 -> YELLOW; case 3 -> BLUE; case 4 -> PURPLE; default -> RESET; };
    }

    private static boolean isNumber(String token) {
        try { Integer.parseInt(token); return true; } catch (NumberFormatException e) { return false; }
    }
}