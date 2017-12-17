package ru.bogdanov.puzzlers.day17;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 17: Spinlock ---
 * <p>
 * Suddenly, whirling in the distance, you notice what looks like a massive, pixelated hurricane: a deadly spinlock. This spinlock isn't just consuming computing power, but memory, too; vast, digital mountains are being ripped from the ground and consumed by the vortex.
 * <p>
 * If you don't move quickly, fixing that printer will be the least of your problems.
 * <p>
 * This spinlock's algorithm is simple but efficient, quickly consuming everything in its path. It starts with a circular buffer containing only the value 0, which it marks as the current position. It then steps forward through the circular buffer some number of steps (your puzzle input) before inserting the first new value, 1, after the value it stopped on. The inserted value becomes the current position. Then, it steps forward from there the same number of steps, and wherever it stops, inserts after it the second new value, 2, and uses that as the new current position again.
 * <p>
 * It repeats this process of stepping forward, inserting a new value, and using the location of the inserted value as the new current position a total of 2017 times, inserting 2017 as its final operation, and ending with a total of 2018 values (including 0) in the circular buffer.
 * <p>
 * For example, if the spinlock were to step 3 times per insert, the circular buffer would begin to evolve like this (using parentheses to mark the current position after each iteration of the algorithm):
 * <p>
 * (0), the initial state before any insertions.
 * <p>
 * 0 (1): the spinlock steps forward three times (0, 0, 0), and then inserts the first value, 1, after it. 1 becomes the current position.
 * <p>
 * 0 (2) 1: the spinlock steps forward three times (0, 1, 0), and then inserts the second value, 2, after it. 2 becomes the current position.
 * <p>
 * 0  2 (3) 1: the spinlock steps forward three times (1, 0, 2), and then inserts the third value, 3, after it. 3 becomes the current position.
 * <p>
 * And so on:
 * <p>
 * 0  2 (4) 3  1
 * <p>
 * 0 (5) 2  4  3  1
 * <p>
 * 0  5  2  4  3 (6) 1
 * <p>
 * 0  5 (7) 2  4  3  6  1
 * <p>
 * 0  5  7  2  4  3 (8) 6  1
 * <p>
 * 0 (9) 5  7  2  4  3  8  6  1
 * <p>
 * Eventually, after 2017 insertions, the section of the circular buffer near the last insertion looks like this:
 * <p>
 * 1512  1134  151 (2017) 638  1513  851
 * <p>
 * Perhaps, if you can identify the value that will ultimately be after the last value written (2017), you can short-circuit the spinlock. In this example, that would be 638.
 * <p>
 * What is the value after 2017 in your completed circular buffer?
 */
public class Puzzler17A {
    public static void main(String[] args) {
        int step = 301;
        List<Integer> result = new ArrayList<>();
        result.add(0);
        int currentIndex = 0;
        int currentStep = 0;

        for (int i = 1; i <= 2017; i++) {
            while (currentStep != step) {
                currentIndex++;
                if (currentIndex == result.size()) {
                    currentIndex = 0;
                }
                currentStep++;
            }
            currentStep = 0;
            result.add(++currentIndex, i);
        }
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) == 2017) {
                System.out.println(result.get(i + 1));
            }

        }
    }
}
