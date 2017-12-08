package ru.bogdanov.puzzlers.day3;

/**
 * As a stress test on the system, the programs here clear the grid and then store the value 1 in square 1.
 * Then, in the same allocation order as shown above, they store the sum of the values in all adjacent squares, including diagonals.
 * <p>
 * So, the first few squares' values are chosen as follows:
 * <p>
 * Square 1 starts with the value 1.
 * <p>
 * Square 2 has only one adjacent filled square (with value 1), so it also stores 1.
 * <p>
 * Square 3 has both of the above squares as neighbors and stores the sum of their values, 2.
 * <p>
 * Square 4 has all three of the aforementioned squares as neighbors and stores the sum of their values, 4.
 * <p>
 * Square 5 only has the first and fourth squares as neighbors, so it gets the value 5.
 * <p>
 * Once a square is written, its value does not change. Therefore, the first few squares would receive the following values:
 * <p>
 * 147  142  133  122   59 <p>
 * 304    5    4    2   57 <p>
 * 330   10    1    1   54 <p>
 * 351   11   23   25   26 <p>
 * 362  747  806--->   ... <p>
 * What is the first value written that is larger than your puzzle input?
 * <p>
 * Your puzzle input is still 312051.
 */


/*
  THIS IS THE UGLIEST CODE, I've ever written, I know it. First of all, it's not a direct solution, if you want to find a solution,
  you should tune length, and just see method getAndCheckValue, which prints a message if we found the solution
  (however it's not so hard to fix, just can make a loop for a variable length).
  Second of all, instead of correct checking all corner cases, I just catch exceptions and do nothing, it works fine,
  but it looks ugly, and the perfomance is bad because of these try-catch blocks, but now I'm too lazy for writing the
  big pile of if-else statements, I think there is a better solution, but now I can't find it:(
  However it works! I solved my problem, when length is 9 we can see the first solution.
  Last remark, length should be an ODD number (because of the problem, think about it, it can't be an even number,
  because on each step, we add two numbers in the end and in the beginning, the initial length is 1, then 3, 5 etc)
*/
public class Puzzler3B {
    public static void main(String[] args) {
        int input = 312051;
        int length = 9;
        int[][] matr = new int[length][length];
        int half = length / 2;
        int increment = 1;
        matr[half][half] = increment++;
        int column = half + 1;
        int str = half;
        int currentHalf = 1;
        int counter = 0;
        while (increment <= length * length) {
            while (counter != 2 * currentHalf - 1) {
                matr[str][column] = getAndCheckValue(matr, str, column, input);
                increment++;
                str--;
                counter++;
            }
            counter = 0;
            while (counter != 2 * currentHalf) {
                matr[str][column] = getAndCheckValue(matr, str, column, input);
                increment++;
                column--;
                counter++;
            }
            counter = 0;
            while (counter != 2 * currentHalf) {
                matr[str][column] = getAndCheckValue(matr, str, column, input);
                increment++;
                str++;
                counter++;
            }
            counter = 0;
            while (counter != 2 * currentHalf + 1) {
                matr[str][column] = getAndCheckValue(matr, str, column, input);
                increment++;
                column++;
                counter++;
            }
            counter = 0;
            currentHalf++;
        }
//        printMatr(matr);
    }

    private static int getAndCheckValue(int[][] matr, int str, int column, int input) {
        int upLeft = 0;
        try {
            upLeft = matr[str - 1][column - 1];
        } catch (Exception ignored) {
        }
        int up = 0;
        try {
            up = matr[str - 1][column];
        } catch (Exception ignored) {
        }
        int upRight = 0;
        try {
            upRight = matr[str - 1][column + 1];
        } catch (Exception ignored) {
        }
        int left = 0;
        try {
            left = matr[str][column - 1];
        } catch (Exception ignored) {
        }
        int right = 0;
        try {
            right = matr[str][column + 1];
        } catch (Exception ignored) {
        }
        int downLeft = 0;
        try {
            downLeft = matr[str + 1][column - 1];
        } catch (Exception ignored) {
        }
        int down = 0;
        try {
            down = matr[str + 1][column];
        } catch (Exception ignored) {
        }
        int downRight = 0;
        try {
            downRight = matr[str + 1][column + 1];
        } catch (Exception ignored) {
        }

        int result = upLeft + up + upRight
                + left + right + downLeft + down + downRight;
        if (result > input) {
            System.out.printf("Number %d is larger then input %d\n", result, input);
        }
        return result;

    }

    private static void printMatr(int[][] matr) {
        for (int[] ints : matr) {
            for (int anInt : ints) {
                System.out.printf("%5d  ", anInt);
            }
            System.out.println();
        }
    }
}
