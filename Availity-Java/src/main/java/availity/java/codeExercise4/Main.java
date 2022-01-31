package availity.java.codeExercise4;

import availity.java.codeExercise4.util.ParentheseChecker;

import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a string with parenthese, ( or ). If it is valid ie it has closing parenthese the " +
                "program will let you know.");
        String parenthese = scan.nextLine();
        System.out.println("The string was valid (stack process): " + ParentheseChecker.validParentheseStack(parenthese));
        System.out.println("The string was valid: " + ParentheseChecker.validParenthese(parenthese));
    }
}
