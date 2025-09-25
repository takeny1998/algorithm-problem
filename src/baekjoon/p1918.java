package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class p1918 {
    
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static StringTokenizer st;

    private static char[] chars;

    private static final Stack<Character> stack = new Stack<>();

    private static final Map<Character, Integer> priority = Map.of(
        '(', 3, ')', 3,
        '*', 1, '/', 1,
        '+', 2, '-', 2    
    );

    public static void main(String[] args) throws Exception {
        chars = br.readLine().toCharArray();
        
        for (int i = 0; i < chars.length; i ++) {
            char character = chars[i];

            if (character == '(') {
                stack.add(character);
                continue;
            }
            
            if (character == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    bw.write(stack.pop() + "");
                }
                stack.pop();
                continue;
            }

            if (character == '+' || character == '-' || character == '*' || character == '/') {
                while (!stack.isEmpty() && priority.get(stack.peek()) <= priority.get(character)) {
                    bw.write(stack.pop() + "");
                }

                stack.add(character);

                continue;
            }
            bw.write(character + "");
        }

        while (!stack.isEmpty()) {
            bw.write(stack.pop() + "");
        }

        bw.write("\n");
        bw.flush();
    }

    
}
