package ru.bogdanov.puzzlers.day1;

import java.io.IOException;
import ru.bogdanov.puzzlers.Utils;

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
