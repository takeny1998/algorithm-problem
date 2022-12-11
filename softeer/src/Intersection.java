import java.util.*;
import java.io.*;

public class Intersection {
    private final static Map<String, Integer> intersection = new HashMap<>() {
        {
            put("A", 0);
            put("B", 0);
            put("C", 0);
            put("D", 0);
        }
    };

    private final static Map<Integer, List<String>> comingInfo = new HashMap<>();

    final static Map<String, String> rightInfo = new HashMap<String, String>() {
        {
            put("A", "D");
            put("B", "A");
            put("C", "B");
            put("D", "C");
        }
    };
    
    public static void comeCar(List<String> info) {
        for (String position : info) {
            int value = intersection.getOrDefault(position, 0);
            intersection.put(position, value + 1);
        }
    }

    public static boolean isExistCarRight(String position) {
        String rightPosition = rightInfo.get(position);
        int rightCarNum = intersection.getOrDefault(rightPosition, 0);
        if (rightCarNum > 0) {
            return true;
        }
        return false;
    }

    public static void goCar(int sec, String position) {
        int carNum = intersection.get(position);
        for (int i = 0; i < carNum; i ++) {
            System.out.printf("%d\n", sec);
        }
        intersection.put(position, 0);
    }

    public static boolean checkEnd() {
        int totalCarNum = 0;
        int restComeCarNum = 0;
        for (String position : intersection.keySet()) {
            int carNum = intersection.get(position);
            totalCarNum += carNum;
        }

        for (int sec : comingInfo.keySet()) {
            int comeCarNum = comingInfo.getOrDefault(sec, new ArrayList<>()).size();
            restComeCarNum += comeCarNum;
        }
        if (totalCarNum == 0 && restComeCarNum == 0)
            return true;
        return false;
    }

    public static void printRestCar() {
        for (String position : intersection.keySet()) {
            int carNum = intersection.get(position);
            for (int i = 0; i < carNum; i ++) {
                System.out.printf("%d\n", -1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int comeNum = sc.nextInt();

        for(int i = 0; i < comeNum; i ++) {
            int sec = sc.nextInt();
            String position = sc.next();

            List<String> list = comingInfo.getOrDefault(sec, new ArrayList<>());
            list.add(position);
            comingInfo.put(sec, list);
        }


        String[] iter = {"A", "B", "C", "D", "A"};

    }
}