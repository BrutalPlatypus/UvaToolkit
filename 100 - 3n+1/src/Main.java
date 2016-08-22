import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String in = scanner.nextLine();
            int[] parts = Arrays.stream(in.split(" ")).mapToInt(Integer::parseInt).toArray();
            int maxCycles = 0;
            for (int i = parts[0]; i <= parts[1]; i++) {
                int counter = 1;
                int num = i;
                while (num != 1) {
                    counter++;
                    if (num % 2 == 1) {
                        num = 3 * num + 1;
                    } else {
                        num = num / 2;
                    }
                }
                if (maxCycles < counter) {
                    maxCycles = counter;
                }
            }
            System.out.println(String.format("%s %s %s", parts[0], parts[1], maxCycles));
        }
    }
}
