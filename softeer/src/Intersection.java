import java.util.*;
import java.io.*;

public class Intersection {
    private final static Map<String, Integer> intersection = new HashMap<>();

    private final static Map<Integer, List<String>> comingInfo = new HashMap<>();

    final static Map<String, String> rightInfo = new HashMap<String, String>() {
        {
            put("A", "D");
            put("B", "A");
            put("C", "B");
            put("D", "c");
        }
    };
    
    public static void comeCar(List<String> info) {
        for (String position : info) {
            int value = intersection.getOrDefault(position, 0);
            intersection.put(position, value + 1);
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

        int sec = 0;

        comeCar(comingInfo.get(10));
    }
}