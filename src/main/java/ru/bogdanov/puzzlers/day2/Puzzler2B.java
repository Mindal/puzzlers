package ru.bogdanov.puzzlers.day2;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import ru.bogdanov.puzzlers.NotFoundException;
import ru.bogdanov.puzzlers.Utils;

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
