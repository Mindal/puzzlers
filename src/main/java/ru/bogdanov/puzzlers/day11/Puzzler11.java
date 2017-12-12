package ru.bogdanov.puzzlers.day11;

import ru.bogdanov.puzzlers.Utils;

import java.io.IOException;

/**
 * Crossing the bridge, you've barely reached the other side of the stream when a program comes up to you, clearly in distress. "It's my child process," she says, "he's gotten lost in an infinite grid!"
 * <p>
 * Fortunately for her, you have plenty of experience with infinite grids.
 * <p>
 * Unfortunately for you, it's a hex grid.
 * <p>
 * The hexagons ("hexes") in this grid are aligned such that adjacent hexes can be found to the north, northeast, southeast, south, southwest, and northwest:
 * <p>
 * \ n  / <p>
 * nw +--+ ne <p>
 * /    \ <p>
 * -+      +- <p>
 * \    / <p>
 * sw +--+ se <p>
 * / s  \ <p>
 * You have the path the child process took. Starting where he started, you need to determine the fewest number of steps required to reach him. (A "step" means to move from the hex you are in to any adjacent hex.)
 * <p>
 * For example:
 * <p>
 * ne,ne,ne is 3 steps away.
 * ne,ne,sw,sw is 0 steps away (back where you started).
 * ne,ne,s,s is 2 steps away (se,se).
 * se,sw,se,sw,sw is 3 steps away (s,s,sw).
 * <p>
 * <p>
 * <p>
 * <p>
 * --- Part Two ---
 * <p>
 * How many steps away is the furthest he ever got from his starting position?
 * <p>
 * <p><p>See cubic coordinates <a href="https://www.redblobgames.com/grids/hexagons/">https://www.redblobgames.com/grids/hexagons/</a>
 * Really GREAT site with beautiful animation
 */
public class Puzzler11 {

    public static void main(String[] args) throws IOException {
        String input = Utils.getLine("day11/input.txt");
        String[] moves = input.split(",");
        int x = 0;
        int y = 0;
        int z = 0;
        int max = 0;
        for (String s : moves) {
            switch (s) {
                case "sw": {
                    x--;
                    z++;
                    break;
                }
                case "s": {
                    y--;
                    z++;
                    break;
                }
                case "se": {
                    y--;
                    x++;
                    break;
                }
                case "ne": {
                    x++;
                    z--;
                    break;
                }
                case "n": {
                    y++;
                    z--;
                    break;
                }
                case "nw": {
                    y++;
                    x--;
                    break;
                }
            }
            if (getMaxFrom3Numbers(x, y, z) > max) {
                max = getMaxFrom3Numbers(x, y, z);
            }
        }
        System.out.println(getMaxFrom3Numbers(x, y, z)); // Puzzler A
        System.out.println(max); // Puzzler B
    }

    private static int getMaxFrom3Numbers(int x, int y, int z) {
        return Math.max(Math.abs(z), Math.max(Math.abs(x), Math.abs(y)));
    }
}
