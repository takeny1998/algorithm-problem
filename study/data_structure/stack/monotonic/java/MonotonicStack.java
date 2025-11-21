package study.data_structure.binary_search.java;

import java.util.Arrays;
import java.util.Stack;

public class MonotonicStack {

    private static final int[] arr = { 4, 2, 6, 1, 5 }, result = new int[5];

    private static final Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws Exception {

        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.println("num = " + arr[i]);

            System.out.println("stack = " + stack);
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
                System.out.println("stack = " + stack);
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek();
            System.out.println("result = " + Arrays.toString(result));

            stack.push(arr[i]);

            System.out.println("stack = " + stack);
            System.out.println();
        }

    }

}
