package ru.bogdanov.puzzlers.day16;

import ru.bogdanov.puzzlers.Utils;

import java.util.ArrayList;
import java.util.List;

import static ru.bogdanov.puzzlers.day16.Puzzler16A.dance;

/**
 * Now that you're starting to get a feel for the dance moves, you turn your attention to the dance as a whole.
 * <p>
 * Keeping the positions they ended up in from their previous dance, the programs perform it again and again: including the first dance, a total of one billion (1000000000) times.
 * <p>
 * In the example above, their second dance would begin with the order baedc, and use the same dance moves:
 * <p>
 * s1, a spin of size 1: cbaed.
 * <p>
 * x3/4, swapping the last two programs: cbade.
 * <p>
 * pe/b, swapping programs e and b: ceadb.
 * <p>
 * In what order are the programs standing after their billion dances?
 */
public class Puzzler16B {
    public static void main(String[] args) {
        List<List<Character>> list = new ArrayList<>();
        char[] arr = "abcdefghijklmnop".toCharArray();
        String[] input = Utils.getLine("day16/input.txt").split(",");
        while (notContainsInList(list, arr)) {
            dance(arr, input);
        }

        System.out.println(list.size());
        int index = 1_000_000_000 % (list.size());
        System.out.println(index);
        list.get(index).forEach(System.out::print);
    }

    private static boolean notContainsInList(List<List<Character>> list, char[] arr) {
        List<Character> current = new ArrayList<>();
        for (char c : arr) {
            current.add(c);
        }
        return !list.contains(current) && list.add(current);
    }
}
