package ru.bogdanov.puzzlers.day5;

import java.io.IOException;
import java.util.List;
import ru.bogdanov.puzzlers.Utils;

/**
 * Now, the jumps are even stranger: after each jump, if the offset was three or more, instead
 * decrease it by 1. Otherwise, increase it by 1 as before.
 *
 * Using this rule with the above example, the process now takes 10 steps, and the offset values
 * after finding the exit are left as 2 3 2 3 -1.
 *
 * How many steps does it now take to reach the exit?
 */

public class Puzzler5B {

  public static void main(String[] args) throws IOException {
    List<Integer> game = Utils.getAllIntegerLines("day5/input.txt");
    int i = 0;
    int counter = 0;
    while (i < game.size()) {
      int current = game.get(i);
      if (current >= 3){
        game.set(i, current - 1);
      }
      else{
        game.set(i, current + 1);
      }
      i += current;
      counter++;
    }
    System.out.println(counter);
  }

}
