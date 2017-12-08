package ru.bogdanov.puzzlers.day2;

import ru.bogdanov.puzzlers.NotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Puzzler2B {
    // O(n^2) - bad bad bad!!
    public static void main(String[] args) throws IOException {
        String input = "C:\\Users\\Python\\IdeaProjects\\puzzlers\\src\\main\\java\\ru\\bogdanov\\puzzlers\\second\\input2.txt";
        Path path = Paths.get(input);
        int result = 0;
        List<String> strings = Files.readAllLines(path);
        for (String string : strings) {
            int[] array = Arrays.stream(string.split("\t")).mapToInt(Integer::parseInt).toArray();
            result += getDivisorsAndResult(array);
        }
        System.out.println(result);

    }

    private static int getDivisorsAndResult(int[] array) {
        for (int x : array) {
            for (int y : array) {
                if (x != y && x % y == 0) {
                    return x / y;
                }
            }
        }
        throw new NotFoundException("There is no two numbers, where one evenly divides to other.");

    }
}
