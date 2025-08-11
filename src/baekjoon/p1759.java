package baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class p1759 {
    static ArrayList<String> alpha = new ArrayList();
    static int L, C;
    static boolean check(String word) {
        int vowels = 0, consonants = 0;

        for (char c : word.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vowels ++;
            } else {
                consonants ++;
            }
        }
        return vowels >= 1 && consonants >= 2;
    }

    static void process(int index, String word) {
        if (word.length() == L) {
            if (check(word)) {
                System.out.println(word);
            }
            return;
        }

        if (index == C) {
            return;
        }

        process(index + 1, word + alpha.get(index));
        process(index + 1, word);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        C = sc.nextInt();

        for (int i = 0; i < C; i ++) {
            alpha.add(sc.next());
        }

        Collections.sort(alpha);

        process(0, "");
    }
}
