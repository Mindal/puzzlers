package ru.bogdanov.puzzlers.second;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Puzzler2A {
    public static void main(String[] args) throws IOException {
        String input = "C:\\Users\\Python\\IdeaProjects\\puzzlers\\src\\main\\java\\ru\\bogdanov\\puzzlers\\second\\input1.txt";
        Path path = Paths.get(input);
        int result = 0;
        List<String> strings = Files.readAllLines(path);
        for (String s : strings) {
            String[] split = s.split("\t");
            int min = getMin(split);
            int max = getMax(split);
            result += max - min;
        }
        System.out.println(result);
    }

    private static int getMax(String[] split) {
        //noinspection ConstantConditions
        return Arrays.stream(split).map(Integer::parseInt).max(Comparator.naturalOrder()).orElse(null);
    }

    private static int getMin(String[] split) {
        //noinspection ConstantConditions
        return Arrays.stream(split).map(Integer::parseInt).min(Comparator.naturalOrder()).orElse(null);
    }
}
