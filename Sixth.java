import java.util.*;
import java.io.*;

public class Sixth {
    static class Process {
        int id;
        long time;
        List<Integer> dependencies;

        Process(int id, long time) {
            this.id = id;
            this.time = time;
            this.dependencies = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            long time = Long.parseLong(line[0]);
            Process process = new Process(i, time);
            for (int j = 1; j < line.length; j++) {
                process.dependencies.add(Integer.parseInt(line[j]) - 1);
            }
            processes[i] = process;
        }

        long[] dp = new long[n];
        Arrays.fill(dp, -1);

        for (int i = 0; i < n; i++) {
            if (dp[i] == -1) {
                dfs(processes, dp, i);
            }
        }

        long maxTime = 0;
        for (long time : dp) {
            maxTime = Math.max(maxTime, time);
        }

        System.out.println(maxTime);
    }

    static void dfs(Process[] processes, long[] dp, int i) {
        if (dp[i] != -1)
            return;

        long maxDependencyTime = 0;
        for (int dependency : processes[i].dependencies) {
            dfs(processes, dp, dependency);
            maxDependencyTime = Math.max(maxDependencyTime, dp[dependency]);
        }

        dp[i] = maxDependencyTime + processes[i].time;
    }
}
