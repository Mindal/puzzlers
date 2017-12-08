package ru.bogdanov.puzzlers.day1;

import java.io.IOException;
import ru.bogdanov.puzzlers.Utils;

public class Puzzler1A {

  public static void main(String[] args) throws IOException {
    String input = Utils.getLine("day1/input.txt");
    char[] chars = input.toCharArray();
    int sum = 0;
    for (int i = 0; i < chars.length - 1; i++) {
      if(chars[i] == chars[i + 1]){
        sum += Character.getNumericValue(chars[i]);
      }
    }
    if(chars[chars.length - 1] == chars[0]){
      sum += Character.getNumericValue(chars[0]);
    }
    System.out.println(sum);
  }

}
