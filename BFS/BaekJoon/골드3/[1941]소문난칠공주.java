package BFS;

import java.io.*;
import java.util.*;

public class SevenPrincess {

    static String[][] matrix;

    static int[] selected;

    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        matrix = new String[5][5];
        selected = new int[7];
        result = 0;

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            matrix[i] = line.split("");
        }

        backtrack(0, 0, 0);

        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    static void backtrack(int depth, int index, int yCnt) {


        if (yCnt >= 4) {
            return;
        }

        if (depth == 7) {
            if (bfs()) result++;
            return;
        }

        for (int i = index; i < 25; i++) {
            selected[depth] = i;

            if (matrix[i / 5][i % 5].equals("Y")) backtrack(depth + 1, i + 1, yCnt + 1);
             else backtrack(depth + 1, i + 1, yCnt);



        }
    }


   static boolean bfs() {

       int[] dr = new int[]{0, 0, -1, 1};
       int[] dc = new int[]{-1, 1, 0, 0};

       Queue<ArrayList<Integer>> queue = new LinkedList<>();
       boolean[][] visited = new boolean[5][5];

       int connectedCnt = 1;

       int startNum = selected[0];

       int r = startNum / 5;
       int c = startNum % 5;

       ArrayList<Integer> tmp = new ArrayList<>();
       tmp.add(r);
       tmp.add(c);
       queue.offer(tmp);
       visited[r][c] = true;


       while (!queue.isEmpty()) {

           if (connectedCnt == 7) {
               return true;
           }

           ArrayList<Integer> current = queue.poll();
           int currentRow = current.get(0);
           int currentCol = current.get(1);

           for (int i = 0; i < 4; i++) {
               int nr = currentRow + dr[i];
               int nc = currentCol + dc[i];
               int ni = nr * 5 + nc;

               if (nr >= 0 && nc >= 0 && nr < 5 && nc < 5 && isValid(ni) && !visited[nr][nc]) {
                   connectedCnt++;
                   visited[nr][nc] = true;
                   ArrayList<Integer> temp = new ArrayList<>();
                   temp.add(nr);
                   temp.add(nc);
                   queue.offer(temp);
               }

           }

       }

       return false;
   }

   static boolean isValid(int ni) {
       for (int i = 0; i < 7; i++) {
           if (selected[i] == ni) {
               return true;
           }
       }

       return false;
   }




    }

