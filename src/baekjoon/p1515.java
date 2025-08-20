package baekjoon;

import java.io.*;

public class p1515 {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int[] arr;

    private static int answer = 0, ptr = 0;

    public static void main(String[] args) throws Exception {

        String phrase = br.readLine();
        arr = new int[phrase.length()];

        for (int i = 0; i < phrase.length(); i++) {
            arr[i] = Integer.parseInt(phrase.charAt(i) + "");
        }

        while (ptr < arr.length) {
            answer++;
            phrase = String.valueOf(answer);
            for (int i = 0; i < phrase.length() && ptr < arr.length; i++) {

                int parseInt = Integer.parseInt(phrase.charAt(i) + "");

                if (arr[ptr] != parseInt) continue;
                ptr ++;
            }

        }

        bw.write(answer + "\n");
        bw.flush();
    }


}
