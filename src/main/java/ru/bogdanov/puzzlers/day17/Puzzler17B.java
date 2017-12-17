package ru.bogdanov.puzzlers.day17;

/**
 * The spinlock does not short-circuit. Instead, it gets more angry. At least, you assume that's what happened; it's spinning significantly faster than it was a moment ago.
 * <p>
 * You have good news and bad news.
 * <p>
 * The good news is that you have improved calculations for how to stop the spinlock. They indicate that you actually need to identify the value after 0 in the current state of the circular buffer.
 * <p>
 * The bad news is that while you were determining this, the spinlock has just finished inserting its fifty millionth value (50000000).
 */
public class Puzzler17B {
    public static void main(String[] args) {
        int step = 301;
        int j = 0;
        int result = -1;
        for (int i = 1; i <= 50_000_000; i++) {
            j = (j + step) % i + 1;
            if (j == 1) {
                result = i;
            }
        }
        System.out.println(result);
    }
}
