import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int h1 = scanner.nextInt();
        int h2 = scanner.nextInt();
        int h3 = scanner.nextInt();


        boolean ascending = false;
        boolean descending = false;


        if (h1 <= h2) {
            if (h2 <= h3) {
                ascending = true;

            }
        }

        if (h1 >= h2) {
            if (h2 >= h3) {
                descending = true;
            }
        }

        System.out.println( ascending || descending );


        long lo = Integer.MAX_VALUE;
        int i = (int) lo;
        char ch = (char) i;
        double d = ch;
    }
}