import java.io.*;
import java.util.*;



public class Main {

    static boolean[] visited;
    static String[] command;
    static Queue<Integer> queue;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            command = new String[10000];
            Arrays.fill(command, "");
            visited = new boolean[10000];
            queue = new ArrayDeque<>();
            StringTokenizer st = new StringTokenizer(br.readLine());

            int origin = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            bfs(origin, target);
            bw.write(command[target] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void bfs(int origin, int target) {



        queue.offer(origin);
        visited[origin] = true;

        while (!queue.isEmpty() && !visited[target]) {
            Integer current = queue.poll();

            int d = (current * 2) % 10000;
            int s = current == 0 ? 9999 : current - 1;
            int l = (current % 1000) * 10 + (current / 1000);
            int r = (current % 10) * 1000 + (current / 10);

            if (!visited[d]) {
                visited[d] = true;
                queue.offer(d);
                command[d] = command[current] + "D";
            }

            if (!visited[s]) {
                visited[s] = true;
                queue.offer(s);
                command[s] = command[current] + "S";
            }

            if (!visited[l]) {
                visited[l] = true;
                queue.offer(l);
                command[l] = command[current] + "L";
            }

            if (!visited[r]) {
                visited[r] = true;
                queue.offer(r);
                command[r] = command[current] + "R";
            }


        }
    }

}

