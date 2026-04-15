package algospot.java.CLOCKSYNC;

import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static int C, answer;
    private static int[] clocks;

    private static final int[][] BUTTONS = {
        {0, 1, 2},
        {3, 7, 9, 11},
        {4, 10, 14, 15},
        {0, 4, 5, 6, 7},
        {6, 7, 8, 10, 12},
        {0, 2, 14, 15},
        {3, 14, 15},
        {4, 5, 7, 14, 15},
        {1, 2, 3, 4, 5},
        {3, 4, 5, 9, 13}
    };

    private static void pressButton(int i, int iter) {
        for (int r = 0; r < iter; r++) {
            for (int e : BUTTONS[i]) {
                if (clocks[e] == 12) {
                    clocks[e] = 3;
                } else {
                    clocks[e] += 3;
                }
            }
        }
    }

    private static void revertButton(int i, int iter) {
        for (int r = 0; r < iter; r++) {
            for (int e : BUTTONS[i]) {
                if (clocks[e] == 3) {
                    clocks[e] = 12;
                } else {
                    clocks[e] -= 3;
                }
            }
        }
    }

    private static void dfs(int i, int d) {
        if (i == 10) {
            boolean isAnswer = true;
            for (int c = 0; c < 16; c++) {
                if (clocks[c] != 12) {
                    isAnswer = false;
                    break;
                }
            }
            if (isAnswer) {
                answer = Math.min(answer, d);
            }
            return;
        }

        for (int r = 0; r < 4; r++) {
            pressButton(i, r);
            dfs(i + 1, d + r);
            revertButton(i, r);
        }
    }

    public static void main(String[] args) throws Exception {
        C = Integer.parseInt(br.readLine());

        for (int c = 0; c < C; c++) {
            st = new StringTokenizer(br.readLine());
            answer = Integer.MAX_VALUE;

            clocks = new int[16];
            for (int i = 0; i < 16; i++) {
                clocks[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            if (answer == Integer.MAX_VALUE) {
                answer = -1;
            }

            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
