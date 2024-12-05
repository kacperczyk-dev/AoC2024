package Day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Day01 {

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        readInput(l1, l2);
        l1.sort(Comparator.comparing(Integer::intValue));
        l2.sort(Comparator.comparing(Integer::intValue));
        System.out.println("Day01 - Part1: " + IntStream.range(0, l1.size()).parallel().map(i -> Math.abs(l1.get(i) - l2.get(i))).sum());
        System.out.println("Day01 - Part2: " + IntStream.range(0, l1.size()).parallel().mapToLong(i -> l1.get(i) * l2.stream().filter(n -> Objects.equals(n, l1.get(i))).count()).sum());
    }

    private static void readInput(List<Integer> l1, List<Integer> l2) {
        try (BufferedReader br = new BufferedReader(new FileReader(Day01.class.getResource("input.txt").getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\\s+");
                if (columns.length == 2) {
                    l1.add(Integer.valueOf(columns[0]));
                    l2.add(Integer.valueOf(columns[1]));
                } else {
                    throw new Exception("Invalid format. Expecting two columns for each line.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
