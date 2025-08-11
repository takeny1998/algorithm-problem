package baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p9019 {
    private static int T, A, B;
    private static String result;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static boolean[] visited;


    private static class Pair {
        private int number;
        private String command;

        public Pair(int number, String command) {
            this.number = number;
            this.command = command;
        }
    }

    private static int leftNum(int number) {
        int first = number / 1000;
        int restNumber = number % 1000;
        restNumber *= 10;
        return restNumber + first;
    }

    private static int rightNum(int number) {
        int last = number % 10;
        int restNumber = number / 10;
        return (last * 1000) + restNumber;
    }

    private static int doubleNum(int number) {
        return (number * 2) % 10000;
    }

    private static int reduceNum(int number) {
        return number - 1 < 0 ? 9999: number - 1;
    }


    private static void bfs() {
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(A, ""));
        visited[A] = true;
        int nextNum;

        while (!queue.isEmpty()) {
            Pair curt = queue.poll();

            if (curt.number == B) {
                result = curt.command;
                return;
            }

            // Process "D"
            nextNum = doubleNum(curt.number);
            if (!visited[nextNum]) {
                visited[nextNum] = true;
                queue.add(new Pair(nextNum, curt.command + "D"));
            }

            // Process "S"
            nextNum = reduceNum(curt.number);
            if (!visited[nextNum]) {
                visited[nextNum] = true;
                queue.add(new Pair(nextNum, curt.command + "S"));
            }

            // Process "L"
            nextNum = leftNum(curt.number);
            if (!visited[nextNum]) {
                visited[nextNum] = true;
                queue.add(new Pair(nextNum, curt.command + "L"));
            }

            // Process "R"
            nextNum = rightNum(curt.number);
            if (!visited[nextNum]) {
                visited[nextNum] = true;
                queue.add(new Pair(nextNum, curt.command + "R"));
            }
        }
    }


    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i ++) {
            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            result = "";
            visited = new boolean[10001];
            bfs();

            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }
}
