import edu.princeton.cs.algs4.In;
import java.util.Arrays;

public class BaseballElimination {
    private int teamNum, gameNum, vertexNum;
    private String[] team;
    private int[] w, l, r;
    private int[][] g;

    private int[][] gameAddr;
    private int[]   teamAddr;

    private void readFile(String filename) {
        In in = new In(filename);
        teamNum = in.readInt();
        gameNum = (teamNum-1)*(teamNum-1)/2;

        team = new String[teamNum];
        w = new int[teamNum];
        l = new int[teamNum];
        r = new int[teamNum];
        g = new int[teamNum][teamNum];

        in.readLine(); // to remove spaces (no meaning)

        for (int i = 0; i < teamNum; i++)
            readLine(in, i);

        // System.out.println(Arrays.toString(w));
        // System.out.println(wins("Houston"));
        // System.out.println(losses("Houston"));

    }

    private void readLine(In in, int i) {
        team[i] = in.readString();
        w[i] = in.readInt();
        l[i] = in.readInt();
        r[i] = in.readInt();
        for (int j = 0; j < teamNum; j++)
            g[i][j] = in.readInt();
    }

    public BaseballElimination(String filename) {       // create a baseball division from given filename in format specified below
        readFile(filename);

        updateAddr(2);

    }

    private void print2dArray(int[][] array) {
        for (int i = 0; i < array.length; i++)
            print1dArray(array[i]);
    }

    private void print1dArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    private void updateAddr(int team) {
        int V = teamNum;

        gameAddr = new int[V][V];
        int count = 2;          // offset: s, t
        for (int i = 0; i < V; i++) {
            if (i == team) continue;
            for (int j = i+1; j < V; j++) {
                if (j == team) continue;
                gameAddr[i][j] = count;
                count++;
            }
        }
        print2dArray(gameAddr);

        teamAddr = new int[V];
        for (int i = 0; i < V; i++) {
            if (i == team) continue;
            teamAddr[i] = count; // offset: 2, gameNum
            count++;
        }
        print1dArray(teamAddr);

        vertexNum = count;
    }

    public int numberOfTeams() {                        // number of teams
        return teamNum;
    }

    public Iterable<String> teams() {                   // all teams
        return Arrays.asList(team);
    }

    private int teamIdx(String team) {
        int i = 0;
        for (i = 0; i < teamNum; i++) {
            if (team.equals(this.team[i]))
                break;
        }
        return i;
    }

    public int wins(String team) {                      // number of wins for given team
        int idx = teamIdx(team);
        if (idx == teamNum) throw new IllegalArgumentException();
        return w[idx];

    } 

    public int losses(String team) {                    // number of losses for given team
        int idx = teamIdx(team);
        if (idx == teamNum) throw new IllegalArgumentException();
        return l[idx];
    }

    /*
    public int remaining(String team) {                 // number of remaining games for given team 
    }
    public int against(String team1, String team2) {    // number of remaining games between team1 and team2
    }
    public boolean isEliminated(String team) {          // is given team eliminated?
    }
    public Iterable<String> certificateOfElimination(String team) {  
        // subset R of teams that eliminates given team; null if not eliminated
    }
    */

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        /*
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
        */
    }
}
