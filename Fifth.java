import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Fifth {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String startTime = st.nextToken();
        int startHour = Integer.parseInt(startTime.substring(0, 2));
        int startMinute = Integer.parseInt(startTime.substring(3, 5));
        int startSecond = Integer.parseInt(startTime.substring(6, 8));

        int n = Integer.parseInt(br.readLine());
        Map<String, Team> teams = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String teamName = st.nextToken();
            String time = st.nextToken();
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3, 5));
            int second = Integer.parseInt(time.substring(6, 8));
            int elapsedMinutes = getElapsedMinutes(startHour, startMinute, startSecond, hour, minute, second);
            String serverId = st.nextToken();
            String result = st.nextToken();
            teams.computeIfAbsent(teamName, k -> new Team(teamName)).addResult(serverId, result, elapsedMinutes);
        }

        List<Team> teamList = new ArrayList<>(teams.values());
        teamList.sort(Comparator.comparingInt(Team::getScore).reversed().thenComparingInt(Team::getPenaltyTime)
                .thenComparing(Team::getName));

        int rank = 1;
        int prevScore = -1;
        int prevPenaltyTime = -1;
        for (int i = 0; i < teamList.size(); i++) {
            Team team = teamList.get(i);
            if (team.getScore() != prevScore || team.getPenaltyTime() != prevPenaltyTime) {
                rank = i + 1;
            }
            System.out.println(rank + " " + team.getName() + " " + team.getScore() + " " + team.getPenaltyTime());
            prevScore = team.getScore();
            prevPenaltyTime = team.getPenaltyTime();
        }
    }

    private static int getElapsedMinutes(int startHour, int startMinute, int startSecond, int hour, int minute,
            int second) {
        int elapsedSeconds = (hour - startHour) * 3600 + (minute - startMinute) * 60 + (second - startSecond);
        if (elapsedSeconds < 0) {
            elapsedSeconds += 24 * 3600;
        }
        return elapsedSeconds / 60;
    }

    private static class Team {
        private String name;
        private Set<String> accessedServers = new HashSet<>();
        private Map<String, Integer> deniedCount = new HashMap<>();
        private int penaltyTime;

        public Team(String name) {
            this.name = name;
        }

        public void addResult(String serverId, String result, int elapsedMinutes) {
            if (result.equals("ACCESSED")) {
                accessedServers.add(serverId);
                penaltyTime += elapsedMinutes;
                penaltyTime += deniedCount.getOrDefault(serverId, 0) * 20;
                deniedCount.remove(serverId);
            } else if (result.equals("DENIED") || result.equals("FORBIDEN")) {
                deniedCount.put(serverId, deniedCount.getOrDefault(serverId, 0) + 1);
            }
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return accessedServers.size();
        }

        public int getPenaltyTime() {
            return penaltyTime;
        }
    }
}
