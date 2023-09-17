import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class CheckGroupingSymbols {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java CheckGroupingSymbols <C:\\Users\\mrsma\\OneDrive\\Desktop\\M04>");
            return;
        }

        String CheckGroupingSymbols = args[0];
        if (checkGroupingSymbols(CheckGroupingSymbols)) {
            System.out.println("The file has correct grouping symbols.");
        } else {
            System.out.println("The file has incorrect grouping symbols.");
        }
    }

    public static boolean checkGroupingSymbols(String CheckGroupingSymbols) {
        Stack<Character> stack = new Stack<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CheckGroupingSymbols))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    if (c == '(' || c == '{' || c == '[') {
                        stack.push(c);
                    } else if (c == ')' || c == '}' || c == ']') {
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), c)) {
                            return false;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return false;
        }

        return stack.isEmpty();
    }

    public static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
                (opening == '{' && closing == '}') ||
                (opening == '[' && closing == ']');
    }
}
