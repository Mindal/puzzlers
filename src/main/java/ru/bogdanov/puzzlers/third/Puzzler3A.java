package ru.bogdanov.puzzlers.third;

/**
 * Each square on the grid is allocated in a spiral pattern starting at a location marked 1 and then counting up while spiraling outward. For example, the first few squares are allocated like this:
 * <p>
 * 17  16  15  14  13 <p>
 * 18   5   4   3  12 <p>
 * 19   6   1   2  11 <p>
 * 20   7   8   9  10 <p>
 * 21  22  23---> ... <p>
 * While this is very space-efficient (no squares are skipped), requested data must be carried back to square 1 (the location of the only access port for this memory system) by programs that can only move up, down, left, or right. They always take the shortest path: the Manhattan Distance between the location of the data and square 1.
 * <p>
 * For example:
 * <p>
 * Data from square 1 is carried 0 steps, since it's at the access port.
 * Data from square 12 is carried 3 steps, such as: down, left, left.
 * Data from square 23 is carried only 2 steps: up twice.
 * Data from square 1024 must be carried 31 steps.
 * How many steps are required to carry the data from the square identified in your puzzle input all the way to the access port?
 */
public class Puzzler3A {
    public static void main(String[] args) {
        int input = 312051;
        int i = 2;
        int num = 1;
        while (num < input) {
            num = num + i * 4;
            i += 2;
        }
        int minCount = i / 2 - 1; // count of layers, easy to check
        int current = 2 * minCount; // max count
        boolean reversed = false;
        while (num != input) {
            num--;
            if (!reversed) {
                current--;
            } else {
                current++;
            }
            if (current == minCount) {
                reversed = true;
            } else if (current == 2 * minCount) {
                reversed = false;
            }
        }
        System.out.println(current);
    }
}
