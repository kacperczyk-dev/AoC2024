package Day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day05 {

    public static void main(String[] args) {
        List<List<Integer>> printOrders = new ArrayList<>();
        Map<Integer, List<Integer>> rules = new HashMap<>();
        readInput(printOrders, rules);
        System.out.println("Day05 - Part1: " + part1(printOrders, rules));
    }

    private static int part1(List<List<Integer>> printOrders, Map<Integer, List<Integer>> rules) {
        return printOrders.parallelStream().mapToInt(po -> {
            boolean bad = false;
            for(int i = 0; i < po.size(); i++) {
                if(!(rules.get(po.get(i)) == null || Collections.disjoint(po.subList(i+1, po.size()), rules.get(po.get(i))))) {
                    bad = true;
                    break;
                }
            }
            if(!bad) {
                return po.get((int) Math.ceil(po.size()/2));
            }
            return 0;
        }).sum();
    }

    private static void readInput(List<List<Integer>> printOrders, Map<Integer, List<Integer>> rules) {
        try (BufferedReader br = new BufferedReader(new FileReader(Day05.class.getResource("input.txt").getPath()))) {
            boolean firstPart = true;
            String line;
            while ((line = br.readLine()) != null) {
                if(line.isEmpty()) {
                    firstPart = false;
                    continue;
                }
                if(firstPart) {
                    String[] pair = line.split("\\|");
                    rules.computeIfAbsent(Integer.parseInt(pair[1].trim()), k -> new ArrayList<>()).add(Integer.parseInt(pair[0].trim()));
                } else {
                    List<Integer> printOrder = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                    printOrders.add(printOrder);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
