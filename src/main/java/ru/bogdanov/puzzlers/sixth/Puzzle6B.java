package ru.bogdanov.puzzlers.sixth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Out of curiosity, the debugger would also like to know the size of the loop: starting from a
 * state that has already been seen, how many block redistribution cycles must be performed before
 * that same state is seen again?
 *
 * In the example above, 2 4 1 2 is seen again after four cycles, and so the answer in that example
 * would be 4.
 *
 * How many cycles are in the infinite loop that arises from the configuration in your puzzle
 * input?
 */
public class Puzzle6B {
  private static List<List<Integer>> allStates = new ArrayList<>();

  public static void main(String[] args) {
    List<Integer> currentList = new ArrayList<>(
        Arrays.asList(2, 8, 8, 5, 4, 2, 3, 1, 5, 5, 1, 2, 15, 13, 5, 14));
    allStates.add(new ArrayList<>(currentList));
    while (true) {
      int max = currentList.get(0);
      int indexOfMax = 0;
      // find max
      for (int i = 0; i < currentList.size(); i++) {
        if (currentList.get(i) > max) {
          max = currentList.get(i);
          indexOfMax = i;
        }
      }
      currentList.set(indexOfMax, 0);
      int currentIndex = indexOfMax == currentList.size() - 1 ? 0 : indexOfMax + 1;
      // give everybody numbers
      while (max != 0) {
        currentList.set(currentIndex, currentList.get(currentIndex) + 1);
        max--;
        currentIndex = currentIndex == currentList.size() - 1 ? 0 : currentIndex + 1;
      }
      if (notSeenBefore(new ArrayList<>(currentList))) {
        allStates.add(new ArrayList<>(currentList));
      }
      else{
        int index = findIndex(new ArrayList<>(currentList));
        System.out.println(allStates.size() - index);
        break;
      }
    }
  }

  private static int findIndex(List<Integer> list) {
    for (int i = 0; i < allStates.size(); i++) {
      List<Integer> allState = allStates.get(i);
      if (allState.equals(list)) {
        return i;
      }
    }
    return -1;
  }

  private static boolean notSeenBefore(List<Integer> currentList) {
    return !allStates.contains(currentList);
  }


}
