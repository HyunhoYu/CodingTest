import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;

class Num {
    int value;
    boolean fusionFlag;

    public Num(int value) {
        this.value = value;
        this.fusionFlag = false;
    }
}


public class Main {

    static Num[][] map;
    static int ans;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        map = new Num[N][N];
        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = new Num(Integer.parseInt(st.nextToken()));
            }
        }

        simulate(N, 0);



        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();


    }


    static void simulate(int N, int depth) {

        if (depth == 5) {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    if (map[i][j].value > ans) {
                        ans = map[i][j].value;

                    }
                }
            }

            return;
        }

        Num[][] copy = copyArr(map);
        moveRight(N);
        simulate(N, depth + 1);
        map = copyArr(copy);


        copy = copyArr(map);
        moveLeft(N);

        simulate(N, depth + 1);
        map = copyArr(copy);


        copy = copyArr(map);
        moveUp(N);

        simulate(N, depth + 1);
        map = copyArr(copy);


        copy = copyArr(map);
        moveDown(N);

        simulate(N, depth + 1);
        map = copyArr(copy);



    }

    static void makeAllFusionFlagFalse(Num[][] map) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j].fusionFlag = false;
            }
        }
    }

    static Num[][] copyArr(Num[][] map) {

        Num[][] tmpArr = new Num[map.length][map.length];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                tmpArr[i][j] = new Num(map[i][j].value);
                tmpArr[i][j].fusionFlag = map[i][j].fusionFlag;
            }
        }

        return tmpArr;
    }

    static void moveRight(int N) {

        for (int i = 0; i < N; i++) {

            for (int j = N - 1; j >= 0; j--) {

                int currentIndex = j;
                while (true) {

                    int nextIndex = currentIndex + 1;

                    if (nextIndex >= N) break;

                    Num currentNum = map[i][currentIndex];
                    Num nextNum = map[i][nextIndex];

                    if (currentNum.value == 0) break;

                    if (nextNum.value == 0) {

                        map[i][nextIndex] = currentNum;
                        map[i][currentIndex] = nextNum;
                        currentIndex++;

                    } else if (nextNum.value != 0) {

                        if (!nextNum.fusionFlag && !currentNum.fusionFlag && nextNum.value == currentNum.value) {
                            nextNum.value += currentNum.value;
                            nextNum.fusionFlag = true;
                            map[i][currentIndex] = new Num(0);
                        }

                        break;
                    }


                }

            }

        }



        makeAllFusionFlagFalse(map);

    }


    static void moveLeft(int N) {

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                int currentIndex = j;

                while (true) {

                    int nextIndex = currentIndex - 1;

                    if (nextIndex < 0) break;

                    Num currentNum = map[i][currentIndex];
                    Num nextNum = map[i][nextIndex];

                    if (currentNum.value == 0) break;

                    if (nextNum.value == 0) {

                        map[i][nextIndex] = currentNum;
                        map[i][currentIndex] = nextNum;
                        currentIndex--;
                    } else if (nextNum.value != 0) {

                        if (!nextNum.fusionFlag && !currentNum.fusionFlag && nextNum.value == currentNum.value) {
                            nextNum.value += currentNum.value;
                            nextNum.fusionFlag = true;
                            map[i][currentIndex] = new Num(0);
                        }

                        break;
                    }

                }

            }
        }


        makeAllFusionFlagFalse(map);

    }

    static void moveUp(int N) {

        for (int i = 0; i < N; i++) {

            for (int j = 0; j < N; j++) {

                int currentIndex = j;

                while (true) {

                    int nextIndex = currentIndex - 1;

                    if (nextIndex < 0 ) break;

                    Num currentNum = map[currentIndex][i];
                    Num nextNum = map[nextIndex][i];

                    if (currentNum.value == 0) break;

                    if (nextNum.value == 0) {

                        map[nextIndex][i] = currentNum;
                        map[currentIndex][i] = nextNum;
                        currentIndex--;

                    } else if (nextNum.value != 0) {

                        if (!nextNum.fusionFlag && !currentNum.fusionFlag && nextNum.value == currentNum.value) {
                            nextNum.value += currentNum.value;
                            nextNum.fusionFlag = true;
                            map[currentIndex][i] = new Num(0);
                        }

                        break;
                    }
                }

            }

        }


        makeAllFusionFlagFalse(map);

    }

    static void moveDown(int N) {

        for (int i = 0; i < N; i++) {

            for (int j = N - 1; j >= 0; j--) {

                int currentIndex = j;

                while (true) {

                    int nextIndex = currentIndex + 1;

                    if (nextIndex >= N) break;

                    Num currentNum = map[currentIndex][i];
                    Num nextNum = map[nextIndex][i];

                    if (currentNum.value == 0) break;

                    if (nextNum.value == 0) {

                        map[nextIndex][i] = currentNum;
                        map[currentIndex][i] = nextNum;
                        currentIndex++;
                    } else if (nextNum.value != 0) {

                        if (!nextNum.fusionFlag && !currentNum.fusionFlag && nextNum.value == currentNum.value) {
                            nextNum.value += currentNum.value;
                            nextNum.fusionFlag = true;
                            map[currentIndex][i] = new Num(0);
                        }

                        break;
                    }

                }
            }
        }



        makeAllFusionFlagFalse(map);
    }

}