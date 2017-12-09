package ru.bogdanov.puzzlers.day9;

import java.io.IOException;

/**
 * Now, you're ready to remove the garbage.
 * <p>
 * To prove you've removed it, you need to count all of the characters within the garbage. The leading and trailing < and > don't count, nor do any canceled characters or the ! doing the canceling.
 * <p>
 * <>, 0 characters.<p>
 * &lt random characters>, 18 characters.<p>
 * <<<<>, 3 characters.<p>
 * <{!>}>, 2 characters.<p>
 * &lt !!>, 1 character(first space).<p>
 * &lt !!!>>, 1 character(first space).<p>
 * <{o"i!a,<{i<a>, 10 characters.<p>
 * How many non-canceled characters are within the garbage in your puzzle input?
 */
public class Puzzler9B {

    public static void main(String[] args) throws IOException {
        String input = "<!!!>>";
        int garbageScore = 0;
        boolean insideGarbage = false;
        boolean skipChar = false;
        for (char c : input.toCharArray()) {
            if (insideGarbage) {
                if (skipChar) {
                    skipChar = false;
                } else if (c == '!') {
                    skipChar = true;
                } else if (c == '>') {
                    insideGarbage = false;
                } else garbageScore++;
            } else {
                if (c == '<') {
                    insideGarbage = true;
                }
            }
        }
        System.out.println(garbageScore);

    }
}
