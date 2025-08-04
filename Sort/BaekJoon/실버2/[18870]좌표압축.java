import java.io.*;
import java.util.*;

public class Main {

    static int[] tmp;

    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] inputNumbers = new int[N];
        int[] copyInput = new int[N];




        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            inputNumbers[i] = Integer.parseInt(st.nextToken());
            copyInput[i] = inputNumbers[i];
        }


        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            map.put(copyInput[i], i);
        }
        mergeSort(inputNumbers, 0, inputNumbers.length - 1);

        int[] result = new int[N];

        result[0] = 0;
        int cur = 1;
        int prev = 0;

        while (prev < N - 1 && cur < N) {
            if (inputNumbers[cur] != inputNumbers[prev]) {
                result[cur] = result[prev] + 1;
            } else {
                result[cur] = result[prev];
            }
            prev++;
            cur++;
        }

        for (int number : copyInput) {
            bw.write(result[map.get(number)] + " ");
        }



        bw.flush();
        bw.close();

    }

    static public void mergeSort(int[] arr, int left, int right) {

        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    static public void merge(int[] arr, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        if (tmp == null || tmp.length != arr.length) {
            tmp = new int[arr.length];
        }

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                tmp[k] = arr[i];
                map.put(arr[i], k);
                k++;
                i++;
            } else {
                tmp[k] = arr[j];
                map.put(arr[j], k);
                k++;
                j++;
            }
        }

        while (i <= mid) {
            tmp[k] = arr[i];
            map.put(arr[i], k);
            k++;
            i++;


        }
        while (j <= right) {
            tmp[k] = arr[j];
            map.put(arr[j], k);
            k++;
            j++;
        }

        for (int index = left; index <= right; index++) {
            arr[index] = tmp[index];
        }

    }
}