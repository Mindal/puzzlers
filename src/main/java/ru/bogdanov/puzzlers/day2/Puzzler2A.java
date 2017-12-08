package ru.bogdanov.puzzlers.day2;

import ru.bogdanov.puzzlers.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * As you walk through the door, a glowing humanoid shape yells in your direction. "You there! Your state appears to be idle. Come help us repair the corruption in this spreadsheet - if we take another millisecond, we'll have to display an hourglass cursor!"
 * <p>
 * The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process is on the right track, they need you to calculate the spreadsheet's checksum. For each row, determine the difference between the largest value and the smallest value; the checksum is the sum of all of these differences.
 * <p>
 * For example, given the following spreadsheet:
 * <p>
 * 5 1 9 5
 * <p>
 * 7 5 3
 * <p>
 * 2 4 6 8
 * <p>
 * The first row's largest and smallest values are 9 and 1, and their difference is 8.
 * <p>
 * The second row's largest and smallest values are 7 and 3, and their difference is 4.
 * <p>
 * The third row's difference is 6.
 * <p>
 * In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.
 */
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
