import java.util.Scanner;

class Problem {

    public static void main(String[] args) {
        String operator = args[0];
        if (!operator.matches("[+\\-*]")) {
            System.out.println("Unknown operator");
        }
        int firstNum = Integer.parseInt(args[1]);
        int secondNum = Integer.parseInt(args[2]);

        switch (operator) {
            case "+":
                System.out.println(firstNum + secondNum);
                break;

            case "-":
                System.out.println(firstNum - secondNum);
                break;

            case "*":
                System.out.println(firstNum * secondNum);
                break;
        }

    }
}