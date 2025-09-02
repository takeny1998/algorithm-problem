package study;

import java.util.Scanner;

public class BinarySearch {
    
    private static final int[] nums = { 1, 3, 3, 5, 7 };

    private static int binarySearch(int key) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (nums[mid] == key) return mid;
            
            if (nums[mid] < key) left = mid + 1;
            else right = mid;
        }

        return right;
    }

    private static int lowerBound(int key) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (nums[mid] < key) left = mid + 1;
            else right = mid;
        }

        return right;
    }

    private static int upperBound(int key) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (nums[mid] <= key) left = mid + 1;
            else right = mid;
        }

        return right;
    }

    public static void main(String[] args) throws Exception {

        final Scanner scanner = new Scanner(System.in);

        

        int key = Integer.parseInt(scanner.nextLine());

        System.out.println("binarySearch : " + binarySearch(key));
        System.out.println("lowerBound : " + lowerBound(key));
        System.out.println("upperBound : " + upperBound(key));
        
    }
    
}
