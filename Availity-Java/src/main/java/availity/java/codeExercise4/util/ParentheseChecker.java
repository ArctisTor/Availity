package availity.java.codeExercise4.util;

import java.util.Stack;

public class ParentheseChecker {

    public static boolean validParenthese(String potential) {

        int leftParathese = -1, rightParathese = -1;
        int indexLeft = -1;
        int indexRight = -1;

        do {
            leftParathese++;
            indexLeft = potential.indexOf("(", indexLeft+1);
        } while (indexLeft >= 0);

        do {
            rightParathese++;
            indexRight =  potential.indexOf(")", (indexRight+1));
        } while (indexRight >= 0);

        if (rightParathese <= 0 || leftParathese <= 0) {
            return false;
        } else if (rightParathese != leftParathese) {
            return false;
        } else {
            return true;
        }


        //either parentheses were not found.
//        if (indexLeft  == -1 || indexRight == -1) {
//            return false;
//        }


//        while(indexLeft >= 0) {
//            leftParathese++;
//            indexLeft = potential.indexOf(")", (indexLeft+1));
//        }
//
//        while (indexRight >= 0) {
//            rightParathese++;
//            indexRight = potential.indexOf("(", (indexRight+1));
//        }

        //uneven parentheses
//        if(leftParathese != rightParathese) {
//            return false;
//        }
//
//        return true;
    }

    public static boolean validParentheseStack(String potential) {
        char[] potentialArray = potential.toCharArray();
        Stack<Character> parentheseStack = new Stack<>();
        for (int i = 0; i < potentialArray.length; i++) {
            char character = potentialArray[i];

            if (character == '('){
                parentheseStack.push(character);
            } else if (character == ')') {
                try {
                    parentheseStack.pop();
                //Not valid, ) came before a (
                } catch (Exception e) {
                 return false;
                }
            }
        }
        return (parentheseStack.size() == 0);
    }
}
