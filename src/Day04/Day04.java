package Day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {

    public static void main(String[] args) {
        var matrix = readInput();
        var count = 0;
        count += Arrays.stream(matrix).map(r -> String.join("",r)).mapToInt( txt -> countWords(txt)).sum();
        count += Arrays.stream(transpose(matrix)).map(r -> String.join("",r)).mapToInt( txt -> countWords(txt)).sum();
        count += getPrimaryDiagonals(matrix).stream().map(r -> String.join("",r)).mapToInt( txt -> countWords(txt)).sum();
        count += getSecondaryDiagonals(matrix).stream().map(r -> String.join("",r)).mapToInt( txt -> countWords(txt)).sum();
        System.out.println("Day04 - Part1: " + count);
    }

    public static int countWords(String text) {
        int count = 0;
        String word = "XMAS";
        Pattern pattern = Pattern.compile(word);
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            count++;
        }
        pattern = Pattern.compile(new StringBuilder(word).reverse().toString());
        matcher = pattern.matcher(text);
        while(matcher.find()){
            count++;
        }
        return count;

    }

    public static String[][] transpose(String[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Create a new matrix with swapped dimensions
        String[][] transposed = new String[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    public static List<String> getPrimaryDiagonals(String[][] matrix) {
        int n = matrix.length;
        List<String> primaryDiagonals = new ArrayList<>();

        // Diagonals starting from the first row
        for (int startCol = 0; startCol < n; startCol++) {
            StringBuilder diagonal = new StringBuilder();
            int row = 0, col = startCol;
            while (row < n && col < n) {
                diagonal.append(matrix[row][col]);
                row++;
                col++;
            }
            primaryDiagonals.add(diagonal.toString());
        }

        // Diagonals starting from the first column (excluding top-left corner)
        for (int startRow = 1; startRow < n; startRow++) {
            StringBuilder diagonal = new StringBuilder();
            int row = startRow, col = 0;
            while (row < n && col < n) {
                diagonal.append(matrix[row][col]);
                row++;
                col++;
            }
            primaryDiagonals.add(diagonal.toString());
        }

        return primaryDiagonals;
    }

    public static List<String> getSecondaryDiagonals(String[][] matrix) {
        int n = matrix.length;
        List<String> secondaryDiagonals = new ArrayList<>();

        // Diagonals starting from the first row
        for (int startCol = 0; startCol < n; startCol++) {
            StringBuilder diagonal = new StringBuilder();
            int row = 0, col = startCol;
            while (row < n && col >= 0) {
                diagonal.append(matrix[row][col]);
                row++;
                col--;
            }
            secondaryDiagonals.add(diagonal.toString());
        }

        // Diagonals starting from the last column (excluding top-right corner)
        for (int startRow = 1; startRow < n; startRow++) {
            StringBuilder diagonal = new StringBuilder();
            int row = startRow, col = n - 1;
            while (row < n && col >= 0) {
                diagonal.append(matrix[row][col]);
                row++;
                col--;
            }
            secondaryDiagonals.add(diagonal.toString());
        }

        return secondaryDiagonals;
    }

    private static String[][] readInput() {
        String[][] matrix = null;
        try (BufferedReader br = new BufferedReader(new FileReader(Day04.class.getResource("input.txt").getPath()))) {
            String line = br.readLine();
            int i = 0;
            matrix = new String[line.length()][line.length()];
            do {
                matrix[i] = line.split("");
                i++;
            } while ((line = br.readLine()) != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matrix;
    }
}
