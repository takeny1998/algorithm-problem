package baekjoon;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class p3425 {



    static String process(ArrayList<String> program, int initValue) {
        Stack<Integer> stack = new Stack<>();
        stack.push(initValue);

        int first, second;
        while (!program.isEmpty()) {
            String[] command = program.remove(0).split(" ");
            switch(command[0]) {
                case "NUM":
                    stack.push(Integer.valueOf(command[1]));
                    break;
                case "POP":
                    stack.pop();
                    break;
                case "INV":
                    stack.push(stack.pop() * -1);
                    break;
                case "DUP":
                    stack.push(stack.peek());
                    break;
                case "SWP":
                    first = stack.pop();
                    second = stack.pop();
                    stack.push(first);
                    stack.push(second);
                    break;
                case "ADD":
                    first = stack.pop();
                    second = stack.pop();
                    stack.push(first + second);
                    break;
                case "SUB":
                    first = stack.pop();
                    second = stack.pop();
                    stack.push(second - first);
                    break;
                case "MUL":
                    first = stack.pop();
                    second = stack.pop();
                    stack.push(second * first);
                    break;
                case "DIV":
                    first = stack.pop();
                    second = stack.pop();
                    int mok = Math.abs(second) / Math.abs(first);
                    if ((first < 0 && second > 0) || (first > 0 && second < 0)) {
                        mok *= -1;
                    }
                    stack.push(mok);
                    break;
                case "MOD":
                    first = stack.pop();
                    second = stack.pop();
                    int floor = Math.abs(second) % Math.abs(first);
                    if (second < 0) {
                        floor *= -1;
                    }
                    stack.push(floor);
                    break;
            }

            for (int i : stack) {
                System.out.println(i);
            }
            System.out.println();
        }
        return "";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

//        ArrayList<String> program = new ArrayList<>();
//        program.add("NUM 3");
//        program.add("DUP");
//        program.add("MUL");
//        program.add("NUM 15");
//        program.add("SWP");
//        program.add("MOD");
//        program.add("NUM -4");
//        program.add("MOD");
//        process(program, 42);
        while (true) {
            ArrayList<String> program = new ArrayList<>();

            // 프로그램 입력하기
            while (true) {
                command = sc.nextLine();
                // EXIT
                if (command.equals("QUIT")) {
                    return;
                }
                if (command.equals("END")) {
                    break;
                }
                program.add(command);
            }

            // 프로그램 실행하기
            int N = sc.nextInt();

            for (int i = 0; i < N; i ++) {
                process(program, sc.nextInt());
            }

            sc.nextLine();
        }
    }
}
