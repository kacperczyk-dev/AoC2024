package Day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

    public static void main(String[] args) {
        String memory = readInput();
        System.out.println("Day03 - Part1: " + part1(memory));
        //System.out.println("Day03 - Part2: " + part2(memory));
    }

    // Does not work
//    private static int part2(String memory) {
//        int result = 0;
//        String regex = "(?<!don't\\(\\))(?<=do\\(\\)|^|\\W)mul\\((\\d{1,3}),(\\d{1,3})\\)";
//        Matcher matcher = Pattern.compile(regex).matcher(memory);
//        while (matcher.find()) {
//            String fullMatch = matcher.group(0);
//            String x = matcher.group(1);
//            String y = matcher.group(2);
//            result += Integer.parseInt(x) * Integer.parseInt(y);
//        }
//        return result;
//    }

    private static int part1(String memory) {
        int result = 0;
        String regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        Matcher matcher = Pattern.compile(regex).matcher(memory);
        while (matcher.find()) {
            String fullMatch = matcher.group(0);
            String x = matcher.group(1);
            String y = matcher.group(2);
            result += Integer.parseInt(x) * Integer.parseInt(y);
        }
        return result;
    }

    private static String readInput() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(Day03.class.getResource("input.txt").getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
