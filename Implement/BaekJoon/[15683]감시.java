import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;


class Number {
    ArrayList<ArrayList<Integer>> directionList;
    int directionIndex;
    int row;
    int col;
    int value;
}

class One extends Number{


    public One(int r, int c) {
        this.directionList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            tmp.add(i);
            directionList.add(tmp);
        }

        row = r;
        col = c;
        value = 1;
    }


}

class Two extends Number{



    public Two(int r, int c) {
        this.directionList = new ArrayList<>();

        ArrayList<Integer> tmp = new ArrayList<>();

        tmp.add(0);
        tmp.add(2);

        directionList.add(tmp);

        ArrayList<Integer> tmp2 = new ArrayList<>();

        tmp2.add(1);
        tmp2.add(3);

        directionList.add(tmp2);

        row = r;
        col = c;
        value = 2;

    }
}

class Three extends Number{


    public Three(int r, int c) {
        directionList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int j = 0; j <= 1; j++) {
                tmp.add((i + j) % 4);
            }
            directionList.add(tmp);
        }

        row = r;
        col = c;
        value = 3;
    }
}

class Four extends Number{

    public Four(int r, int c) {
        directionList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                tmp.add((i + j) % 4);
            }
            directionList.add(tmp);
        }

        row = r;
        col = c;
        value = 4;
    }
}


class Five extends Number{

    public Five(int r, int c) {

        directionList = new ArrayList<>();

        ArrayList<Integer> tmp = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            tmp.add(i);
        }

        directionList.add(tmp);

        row = r;
        col = c;
        value = 5;
    }
}



public class Main {

    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static int[][] map;
    static int ans;
    static ArrayList<Number> cctvList;

    static int N;
    static int M;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cctvList = new ArrayList<>();
        map = new int[N][M];
        ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0 && map[i][j] != 6) {

                    switch (map[i][j]) {

                        case 1:
                            cctvList.add(new One(i, j));
                            break;

                        case 2:
                            cctvList.add(new Two(i, j));
                            break;

                        case 3:
                            cctvList.add(new Three(i, j));
                            break;

                        case 4:
                            cctvList.add(new Four(i, j));
                            break;

                        case 5:
                            cctvList.add(new Five(i, j));
                            break;
                    }
                }


            }
        }


        solution(0);

        System.out.println(ans);



    }


    static void solution(int depth) {

        if (depth == cctvList.size()) {


            int[][] copyMap = copyMap();

            int result = simulate(copyMap);

            if (ans > result) ans = result;
        }


        for (int i = depth; i < cctvList.size(); i++) {

            Number current = cctvList.get(i);

            for (int j = 0; j < current.directionList.size(); j++) {
                current.directionIndex = j;
                solution(i + 1);
            }
        }



    }

    static int simulate(int[][] copyMap) {

        int result = 0;

        for (int i = 0; i < cctvList.size(); i++) {

            Number current = cctvList.get(i);



            ArrayList<Integer> directions = current.directionList.get(current.directionIndex);


            for (int j = 0; j < directions.size(); j++) {

                int r = current.row;
                int c = current.col;

                int d = directions.get(j);

                while (true) {


                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    r = nr;
                    c = nc;

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || copyMap[nr][nc] == 6) break;

                    if (copyMap[nr][nc] != 0) continue;

                    copyMap[nr][nc] = -1;




                }



            }

        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (copyMap[i][j] == 0) result++;
            }
        }

        return result;

    }

    static int[][] copyMap() {

        int[][] copyMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        return copyMap;
    }

}