import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here

        String input = reader.readLine().trim();


        reader.close();
        String[] count = input.split("\\s+");
        System.out.println(count[0].equals("") ? 0 : count.length);

    }
}