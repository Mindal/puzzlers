package ru.bogdanov.puzzlers.day14;

import ru.bogdanov.puzzlers.day10.Puzzler10B;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Suddenly, a scheduled job activates the system's disk defragmenter. Were the situation different, you might sit and watch it for a while, but today, you just don't have that kind of time. It's soaking up valuable system resources that are needed elsewhere, and so the only option is to help it finish its task as soon as possible.
 * <p>
 * The disk in question consists of a 128x128 grid; each square of the grid is either free or used. On this disk, the state of the grid is tracked by the bits in a sequence of knot hashes.
 * <p>
 * A total of 128 knot hashes are calculated, each corresponding to a single row in the grid; each hash contains 128 bits which correspond to individual grid squares. Each bit of a hash indicates whether that square is free (0) or used (1).
 * <p>
 * The hash inputs are a key string (your puzzle input), a dash, and a number from 0 to 127 corresponding to the row. For example, if your key string were flqrgnkx, then the first row would be given by the bits of the knot hash of flqrgnkx-0, the second row from the bits of the knot hash of flqrgnkx-1, and so on until the last row, flqrgnkx-127.
 * <p>
 * The output of a knot hash is traditionally represented by 32 hexadecimal digits; each of these digits correspond to 4 bits, for a total of 4 * 32 = 128 bits. To convert to bits, turn each hexadecimal digit to its equivalent binary value, high-bit first: 0 becomes 0000, 1 becomes 0001, e becomes 1110, f becomes 1111, and so on; a hash that begins with a0c2017... in hexadecimal would begin with 10100000110000100000000101110000... in binary.
 * <p>
 * Continuing this process, the first 8 rows and columns for key flqrgnkx appear as follows, using # to denote used squares, and . to denote free ones:
 * <p>
 * ##.#.#..-->
 * <p>
 * .#.#.#.#
 * <p>
 * ....#.#.
 * <p>
 * #.#.##.#
 * <p>
 * .##.#...
 * <p>
 * ##..#..#
 * <p>
 * .#...#..
 * <p>
 * ##.#.##.-->
 * <p>
 * |      |
 * <p>
 * V      V
 * <p>
 * In this example, 8108 squares are used across the entire 128x128 grid.
 * <p>
 * Given your actual key string, how many squares are used?
 */
public class Puzzler14A {
    public static void main(String[] args) {
        String input = "hwlqcszp";
        List<String> rows = IntStream.range(0, 128).mapToObj(v -> input + "-" + v).collect(Collectors.toList());
        List<String> hashes = rows.stream().map(Puzzler10B::getKnotHash).collect(Collectors.toList());

        List<String> result = hashes.stream().map(Puzzler14A::hexToBinary).collect(Collectors.toList());
        int countOfUsedPoints = result.stream()
                .map(s -> s.codePoints().mapToObj(c -> (char) c) //map to Character stream
                        .filter(ch -> ch == '1')
                        .count())
                .mapToInt(Long::intValue)
                .sum();
        System.out.println(countOfUsedPoints);
    }

    public static String hexToBinary(String s) {
        StringBuilder preBin = new StringBuilder(new BigInteger(s, 16).toString(2));
        while (preBin.length() < 128) {
            preBin.insert(0, "0");
        }

        return preBin.toString();
    }
}
