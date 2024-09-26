import java.util.Scanner;

public class Forth {
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int countDivisors(int n) {
        int count = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                count += 2;
                if (i * i == n) {
                    count--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int l = scanner.nextInt();
        int r = scanner.nextInt();

        int result = 0;

        for (int i = l; i <= r; i++) {
            if (!isPrime(i) && i != 1) {
                if (isPrime(countDivisors(i))) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
