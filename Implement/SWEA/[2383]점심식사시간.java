import java.io.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

class Point {
    int row;
    int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Stairs {
    Point point;
    Queue<Person> waittingQueue;
    Person[] progressQueue;
    int length;

    public Stairs(Point point, int length) {
        this.point = point;
        this.length = length;
        waittingQueue = new ArrayDeque<>();
        progressQueue = new Person[3];
    }
}

class Person implements Comparable<Person>{
    Point initPoint;
    int goDownInitTime;
    Stairs targetStairs;
    int dist;

    //테스트용
    int num;

    public Person(Point initPoint, int num) {
        this.initPoint = initPoint;
        this.num = num;
    }

    @Override
    public int compareTo(Person o) {
        return o.dist - this.dist;
    }
}

public class Main {

    static ArrayList<Person> people;
    static Stairs[] stairsArr;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            people = new ArrayList<>();
            stairsArr = new Stairs[2];
            min = Integer.MAX_VALUE;

            int n = Integer.parseInt(br.readLine());

            int personNum = 1;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    int num = Integer.parseInt(st.nextToken());

                    if (num == 1) {
                        people.add(new Person(new Point(i, j), personNum));
                        personNum++;
                    } else if (num > 1) {

                        if (stairsArr[0] == null) {
                            stairsArr[0] = new Stairs(new Point(i, j), num);
                        } else {
                            stairsArr[1] = new Stairs(new Point(i, j), num);
                        }
                    }
                }
            }

            //테스트용
//            forTest();
//            System.out.println(simulate());
            solution(0);

            sb.append("#" + tc + " " + min + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }

    static void solution(int index) {
        matchPersonAndStairs(index);
    }


    static void matchPersonAndStairs(int index) {

        if (index == people.size()) {

            for (Person person : people) {
                Stairs targetStairs = person.targetStairs;

                int targetStairsRow = targetStairs.point.row;
                int targetStairsCol = targetStairs.point.col;

                int personRow = person.initPoint.row;
                int personCol = person.initPoint.col;

                person.dist = Math.abs(personRow - targetStairsRow) + Math.abs(personCol - targetStairsCol);
            }

            int result = simulate();

            if (result < min) min = result;
            return;
        }

        Person person = people.get(index);

        person.targetStairs = stairsArr[0];
        matchPersonAndStairs(index + 1);

        person.targetStairs = stairsArr[1];
        matchPersonAndStairs(index + 1);


    }

    static int simulate() {

        int time = 1;

        ArrayList<Person> tmpPeople = new ArrayList<>();

        for (int i = 0; i < people.size(); i++) {
            tmpPeople.add(people.get(i));
        }

        while (true) {

            for (Stairs stairs : stairsArr) {

                Person[] progressQueue = stairs.progressQueue;

                for (int i = 0; i < progressQueue.length; i++) {

                    Person person = progressQueue[i];

                    if (person == null) continue;

                    if (time == person.goDownInitTime + stairs.length) {
                        progressQueue[i] = null;

                        //테스트용

//                        System.out.println(time + "분 후" + person.num + "번 사람 이동 완료");
                    }
                }
            }

            for (Stairs stairs : stairsArr) {
                Person[] progressQueue = stairs.progressQueue;

                for (int i = 0; i < progressQueue.length; i++) {

                    if (progressQueue[i] == null && !stairs.waittingQueue.isEmpty()) {
                        Person person = stairs.waittingQueue.poll();
                        person.goDownInitTime = time;
                        progressQueue[i] = person;

                        //테스트용
//                        System.out.println(time + "분 후" + person.num + "번 사람이 계단을 내려가기 시작");
                    }
                }
            }

            Collections.sort(tmpPeople);

            int count = 0;

            for (Person person : tmpPeople) {

                if (person.dist == time) count++;
            }

            for (int i = 0; i < count; i++) {
                Person person = tmpPeople.remove(tmpPeople.size() - 1);
                person.targetStairs.waittingQueue.offer(person);

                //테스트용
//                System.out.println(time + "분 후" + person.num + "번 사람이 계단 입구 도착");
            }

            if (tmpPeople.size() == 0) {

                boolean endFlag = true;
                for (Stairs stairs : stairsArr) {

                    if (!stairs.waittingQueue.isEmpty()) endFlag = false;

                    for (Person person : stairs.progressQueue) {
                        if (person != null) endFlag = false;
                    }
                }

                if (endFlag) break;

            }


            time++;


        }

        return time;
    }

    static void forTest() {



        for (Person person : people) {

            if (person.num < 5) {
                person.targetStairs = stairsArr[0];
                int stairsRow = stairsArr[0].point.row;
                int stairsCol = stairsArr[0].point.col;

                int personRow = person.initPoint.row;
                int personCol = person.initPoint.col;

                person.dist = Math.abs(personRow - stairsRow) + Math.abs(personCol - stairsCol);
            } else {
                person.targetStairs = stairsArr[1];

                int stairsRow = stairsArr[1].point.row;
                int stairsCol = stairsArr[1].point.col;

                int personRow = person.initPoint.row;
                int personCol = person.initPoint.col;

                person.dist = Math.abs(personRow - stairsRow) + Math.abs(personCol - stairsCol);
            }
        }
    }


}