package Day02;

import Day01.Day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day02 {

    public static void main(String[] args) {
        List<List<Integer>> reports = new ArrayList<>();
        readInputInto(reports);
        System.out.println("Day02 - Part1: " + part1(reports));
    }

    private static int part1(List<List<Integer>> reports) {
        return reports.parallelStream().mapToInt(report -> {
            int prev = report.get(0);
            boolean increasing = report.get(1) - prev > 0;
            boolean safe = true;
            for (int i = 1; i < report.size(); i++) {
                if ((increasing && !(report.get(i) - prev > 0 && report.get(i) - prev <= 3)) || !increasing && !(report.get(i) - prev < 0 && report.get(i) - prev >= -3)) {
                    safe = false;
                    break;
                }
                prev = report.get(i);
            }
            return safe ? 1 : 0;
        }).sum();
    }

    private static void readInputInto(List<List<Integer>> l1) {
        try (BufferedReader br = new BufferedReader(new FileReader(Day02.class.getResource("input.txt").getPath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split("\\s+");
                l1.add(Arrays.stream(columns).map(Integer::valueOf).collect(Collectors.toList()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
