import java.util.Scanner;

public class Second {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = scanner.nextInt();
        }

        for (int i = 1; i < n; ++i) {
            if (a[i - 1] != -1 && a[i] != -1 && a[i] < a[i - 1]) {
                System.out.println("NO");
                return;
            }
        }

        for (int i = 1; i < n; ++i) {
            a[i] = a[i - 1] + 1;
        }

        System.out.println("YES");

        for (int i = 0; i < n; i++)
            System.out.print(a[i] + " ");

    }
}
