import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double radius = scanner.nextDouble();

        System.out.println(radius * radius * Math.PI);
    }
}