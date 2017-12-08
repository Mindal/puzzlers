package ru.bogdanov.puzzlers.day2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import ru.bogdanov.puzzlers.Utils;

public class Puzzler2A {
    public static void main(String[] args) throws IOException {
        List<String> strings = Utils.getAllLines("day2/input.txt");
        int result = 0;
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
