package p1522;

import java.io.*;
import java.util.*;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static char[] array;

    private static int numOfB, left, answer;

    public static void main(String[] args) throws Exception {
        array = br.readLine().toCharArray();
        numOfB = 0;
        answer = 0;

        for (int i = array.length - 1; i >= 0; i --) {
            if (array[i] == 'a') continue;
            numOfB ++;
            left = i;
        }
        
        while (left <= array.length) {
            int countOfB = 0;

            for (int right = left; right < (left + numOfB); right ++) {
                if (array[right % array.length] == 'a') continue;
                countOfB ++;
            }
            answer = Math.max(answer, countOfB);
            left ++;
        }
        bw.write((numOfB - answer) + "\n");
        bw.flush();
    }
}
