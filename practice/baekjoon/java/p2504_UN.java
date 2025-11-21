package baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class p2504_UN {
    static char[] bracket = new char[31];
    static Stack<Character> stack = new Stack<>();

    static boolean is_same_type(char origin, char target) {
        switch (origin) {
            case '(':
                return target == ')';
            case '[':
                return target == ']';
        }

        return false;
    }

    static int get_score(char target) {
        switch (target) {
            case ')': return 2;
            case ']': return 3;
        }
        return 0;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        bracket = sc.nextLine().toCharArray();
        int ptr = 0, score = 0, answer = 0;

        while (ptr < bracket.length) {
            char curt = bracket[ptr ++];

            for (char c : stack) System.out.print(c + " ");
            System.out.println();

            if (curt == '(' || curt == '[') {
                stack.push(curt);
                continue;
            }

            char top = stack.pop();

            // 쌍이 안맞으면
            if (!is_same_type(top, curt)) {
                System.out.println(-1);
                return;
            }

            get_score(top);


        }

        System.out.println(answer);
    }
}