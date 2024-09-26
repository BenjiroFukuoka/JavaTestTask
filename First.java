import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class First {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        List<Integer> numbers = new ArrayList<Integer>();

        String[] parts = input.split(",");

        for (String part : parts) {
            if (part.contains("-")) {
                String[] range = part.split("-");
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);

                for (int i = start; i <= end; ++i)
                    numbers.add(i);
            } else {
                numbers.add(Integer.parseInt(part));
            }
        }

        Collections.sort(numbers);

        for (int number : numbers)
            System.out.print(number + " ");
    }
}
