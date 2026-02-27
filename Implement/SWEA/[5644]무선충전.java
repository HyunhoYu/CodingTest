import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Charger {

    int[] pos;
    int c;
    int p;

    public Charger(int[] pos, int c, int p) {
        this.pos = pos;
        this.c = c;
        this.p = p;
    }
}

class User {
    int[] pos;
    int t;

    public User(int[] pos, int t) {
        this.pos = pos;
        this.t = t;
    }
}


public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= t; i++) {

            st = new StringTokenizer(br.readLine());

            int m = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            int[] userAcmd = new int[m];
            int[] userBcmd = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                userAcmd[j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                userBcmd[j] = Integer.parseInt(st.nextToken());
            }

            Charger[] chargers = new Charger[a];



            for (int j = 0; j < a; j++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                int c = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                Charger charger = new Charger(new int[]{y, x}, c, p);
                chargers[j] = charger;
            }

            User userA = new User(new int[]{1, 1}, 0);
            User userB = new User(new int[]{10, 10}, 0);

            int sum = 0;

            for (int j = 0; j <= m; j++) {
                ArrayList<Charger> availableChargersA = findAvailableChargers(chargers, userA);
                ArrayList<Charger> availableChargersB = findAvailableChargers(chargers, userB);

                int maxSum = 0;


                if (availableChargersA.size() > 0 && availableChargersB.size() > 0) {
                    for (Charger chargerA : availableChargersA) {
                        for (Charger chargerB : availableChargersB) {

                            if (chargerA.pos[0] == chargerB.pos[0] && chargerA.pos[1] == chargerB.pos[1]) {
                                maxSum = Math.max(maxSum, chargerA.p);

                            } else {
                                int ac = chargerA.p;
                                int bc = chargerB.p;

                                maxSum = Math.max(maxSum, ac + bc);
                            }
                        }
                    }
                } else if (availableChargersA.size() > 0) {
                    for (Charger charger : availableChargersA) {
                        maxSum = Math.max(maxSum, charger.p);
                    }
                } else if (availableChargersB.size() > 0) {
                    for (Charger charger : availableChargersB) {
                        maxSum = Math.max(maxSum, charger.p);
                    }
                }

                sum += maxSum;

                if (j == m) {
                    break;
                }

                int[] dr = new int[]{0, -1, 0, 1, 0};
                int[] dc = new int[]{0, 0, 1, 0, -1};

                userA.pos = new int[]{
                        userA.pos[0] + dr[userAcmd[j]],
                        userA.pos[1] + dc[userAcmd[j]]
                };

                userB.pos = new int[]{
                        userB.pos[0] + dr[userBcmd[j]],
                        userB.pos[1] + dc[userBcmd[j]]
                };




            }


            sb.append("#" + i + " " + sum + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static ArrayList<Charger> findAvailableChargers(Charger[] chargers, User user) {

        ArrayList<Charger> availableChargers = new ArrayList<>();

        for (Charger charger : chargers) {
            if (isAvailable(charger, user)) availableChargers.add(charger);
        }

        return availableChargers;


    }

    static boolean isAvailable(Charger charger, User user) {
        return charger.c >= (Math.abs(charger.pos[0] - user.pos[0]) + Math.abs(charger.pos[1] - user.pos[1]));
    }




}