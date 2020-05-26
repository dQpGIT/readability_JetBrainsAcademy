import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // start coding here
        ArrayList<Character> input = new ArrayList<>();

        int ch = reader.read();
        while (ch != -1) {
            char character = (char) ch;
            input.add(character);
            ch = reader.read();
        }
        reader.close();

        for (int i = input.size() - 1; i >= 0; i--) {
            System.out.print(input.get(i));
        }
    }
}