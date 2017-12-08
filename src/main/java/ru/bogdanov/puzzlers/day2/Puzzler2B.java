package ru.bogdanov.puzzlers.day2;

import ru.bogdanov.puzzlers.NotFoundException;
import ru.bogdanov.puzzlers.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * "Great work; looks like we're on the right track after all. Here's a star for your effort." However, the program seems a little worried. Can programs be worried?
 * <p>
 * "Based on what we're seeing, it looks like all the User wanted is some information about the evenly divisible values in the spreadsheet. Unfortunately, none of us are equipped for that kind of calculation - most of us specialize in bitwise operations."
 * <p>
 * It sounds like the goal is to find the only two numbers in each row where one evenly divides the other - that is, where the result of the division operation is a whole number. They would like you to find those numbers on each line, divide them, and add up each line's result.
 * <p>
 * For example, given the following spreadsheet:
 * <p>
 * 5 9 2 8
 * <p>
 * 9 4 7 3
 * <p>
 * 3 8 6 5
 * <p>
 * In the first row, the only two numbers that evenly divide are 8 and 2; the result of this division is 4.
 * <p>
 * In the second row, the two numbers are 9 and 3; the result is 3.
 * <p>
 * In the third row, the result is 2.
 * <p>
 * In this example, the sum of the results would be 4 + 3 + 2 = 9.
 * <p>
 * What is the sum of each row's result in your puzzle input?
 */
public class Puzzler2B {

  // O(n^2) - bad bad bad!!
  public static void main(String[] args) throws IOException {
    List<String> strings = Utils.getAllLines("day2/input.txt");
    int result = 0;
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
