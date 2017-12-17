package ru.bogdanov.puzzlers.day16;

import ru.bogdanov.puzzlers.Utils;

/**
 * You come upon a very unusual sight; a group of programs here appear to be dancing.
 * <p>
 * There are sixteen programs in total, named a through p. They start by standing in a line: a stands in position 0, b stands in position 1, and so on until p, which stands in position 15.
 * <p>
 * The programs' dance consists of a sequence of dance moves:
 * <p>
 * Spin, written sX, makes X programs move from the end to the front, but maintain their order otherwise. (For example, s3 on abcde produces cdeab).
 * <p>
 * Exchange, written xA/B, makes the programs at positions A and B swap places.
 * <p>
 * Partner, written pA/B, makes the programs named A and B swap places.
 * <p>
 * For example, with only five programs standing in a line (abcde), they could do the following dance:
 * <p>
 * s1, a spin of size 1: eabcd.
 * <p>
 * x3/4, swapping the last two programs: eabdc.
 * <p>
 * pe/b, swapping programs e and b: baedc.
 * <p>
 * After finishing their dance, the programs end up in order baedc.
 * <p>
 * You watch the dance for a while and record their dance moves (your puzzle input). In what order are the programs standing after their dance?
 */
public class Puzzler16A {
    private static void spin(char arr[], int d) {
        int length = arr.length;
        int j, k;
        char temp;
        for (int i = 0; i < gcd(d, length); i++) {
            /* move i-th values of blocks */
            temp = arr[i];
            j = i;
            k = j - d;
            while (k != i) {

                if (k < 0)
                    k = k + length;
                arr[j] = arr[k];
                j = k;
                k = j - d;
            }
            arr[j] = temp;
        }
    }

    private static void swapChar(char[] arr, char ch1, char ch2) {
        int index1 = findIndexOfChar(arr, ch1);
        int index2 = findIndexOfChar(arr, ch2);
        swapInt(arr, index1, index2);
    }

    private static void swapInt(char[] arr, int index1, int index2) {
        char temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    private static int findIndexOfChar(char[] arr, char ch1) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ch1) {
                return i;
            }
        }
        return -1; //not found
    }

    public static void main(String[] args) {
        char[] arr = "abcdefghijklmnop".toCharArray();
        String[] input = Utils.getLine("day16/input.txt").split(",");
        dance(arr, input);
        printChars(arr);


    }

    static void dance(char[] arr, String[] input) {
        for (String s : input) {
            if (s.startsWith("s")) {
                spin(arr, Integer.parseInt(s.substring(1)));
            } else if (s.startsWith("p")) {
                swapChar(arr, s.charAt(1), s.charAt(3));

            } else if (s.startsWith("x")) {
                String[] split = s.substring(1).split("/");
                swapInt(arr, Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }
        }
    }

    private static void printChars(char[] arr) {
        for (char c : arr) {
            System.out.print(c);
        }
        System.out.println();
    }


    /*Fuction to get gcd of a and b*/
    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }


}
