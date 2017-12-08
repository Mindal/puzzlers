package ru.bogdanov.puzzlers.day1;

import ru.bogdanov.puzzlers.Utils;

import java.io.IOException;

/**
 * You notice a progress bar that jumps to 50% completion. Apparently, the door isn't yet satisfied, but it did emit a star as encouragement. The instructions change:
 * <p>
 * Now, instead of considering the next digit, it wants you to consider the digit halfway around the circular list. That is, if your list contains 10 items, only include a digit in your sum if the digit 10/2 = 5 steps forward matches it. Fortunately, your list has an even number of elements.
 * <p>
 * For example:
 * <p>
 * 1212 produces 6: the list contains 4 items, and all four digits match the digit 2 items ahead.
 * <p>
 * 1221 produces 0, because every comparison is between a 1 and a 2.
 * <p>
 * 123425 produces 4, because both 2s match each other, but no other digit has a match.
 * <p>
 * 123123 produces 12.
 * <p>
 * 12131415 produces 4.
 * <p>
 * What is the solution to your new captcha?
 */
public class Puzzler1B {

  public static void main(String[] args) throws IOException {
    String input = Utils.getLine("day1/input.txt");
    int half = input.length() / 2;
    int sum = 0;
    for (int i = 0; i < input.length(); i++) {
      if(input.charAt(i) == input.charAt((i + half)% input.length())){
        sum += Character.getNumericValue(input.charAt(i));
      }
    }
    System.out.println(sum);
  }
}
