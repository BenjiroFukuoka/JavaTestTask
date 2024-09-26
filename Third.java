import java.util.Scanner;

public class Third {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String sequence = scanner.nextLine();
        String requiredChars = scanner.nextLine();
        int maxLen = scanner.nextInt();

        String password = "-1";
        int latestStart = -1;

        for (int i = 0; i < sequence.length(); i++) {
            for (int j = i + requiredChars.length(); j <= sequence.length() && j <= i + maxLen; j++) {
                String candidate = sequence.substring(i, j);
                if (isValidPassword(candidate, requiredChars) && i >= latestStart) {
                    latestStart = i;
                    password = candidate;
                }
            }
        }

        System.out.println(password);

    }

    private static boolean isValidPassword(String candidate, String requiredChars) {
        if (candidate.length() < requiredChars.length()) {
            return false;
        }

        for (char c : requiredChars.toCharArray()) {
            if (!candidate.contains(String.valueOf(c))) {
                return false;
            }
        }

        return true;
    }
}
