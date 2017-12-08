package ru.bogdanov.puzzlers.day1;

import ru.bogdanov.puzzlers.Utils;

import java.io.IOException;

/**
 * The night before Christmas, one of Santa's Elves calls you in a panic. "The printer's broken! We can't print the Naughty or Nice List!" By the time you make it to sub-basement 17, there are only a few minutes until midnight. "We have a big problem," she says; "there must be almost fifty bugs in this system, but nothing else can print The List. Stand in this square, quick! There's no time to explain; if you can convince them to pay you in stars, you'll be able to--" She pulls a lever and the world goes blurry.
 * <p>
 * When your eyes can focus again, everything seems a lot more pixelated than before. She must have sent you inside the computer! You check the system clock: 25 milliseconds until midnight. With that much time, you should be able to collect all fifty stars by December 25th.
 * <p>
 * Collect stars by solving puzzles. Two puzzles will be made available on each day millisecond in the advent calendar; the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 * <p>
 * You're standing in a room with "digitization quarantine" written in LEDs along one wall. The only door is locked, but it includes a small interface. "Restricted Area - Strictly No Digitized Users Allowed."
 * <p>
 * It goes on to explain that you may only leave by solving a captcha to prove you're not a human. Apparently, you only get one millisecond to solve the captcha: too fast for a normal human, but it feels like hours to you.
 * <p>
 * The captcha requires you to review a sequence of digits (your puzzle input) and find the sum of all digits that match the next digit in the list. The list is circular, so the digit after the last digit is the first digit in the list.
 * <p>
 * For example:
 * <p>
 * 1122 produces a sum of 3 (1 + 2) because the first digit (1) matches the second digit and the third digit (2) matches the fourth digit.
 * <p>
 * 1111 produces 4 because each digit (all 1) matches the next.
 * <p>
 * 1234 produces 0 because no digit matches the next.
 * <p>
 * 91212129 produces 9 because the only digit that matches the next one is the last digit, 9.
 * <p>
 * What is the solution to your captcha?
 */
public class Puzzler1A {

    public static void main(String[] args) throws IOException {
        String input = Utils.getLine("day1/input.txt");
        char[] chars = input.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                sum += Character.getNumericValue(chars[i]);
            }
        }
        if (chars[chars.length - 1] == chars[0]) {
            sum += Character.getNumericValue(chars[0]);
        }
        System.out.println(sum);
    }

}
