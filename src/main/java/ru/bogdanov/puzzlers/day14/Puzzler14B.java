package ru.bogdanov.puzzlers.day14;

import ru.bogdanov.puzzlers.day10.Puzzler10B;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Now, all the defragmenter needs to know is the number of regions. A region is a group of used squares that are all adjacent, not including diagonals. Every used square is in exactly one region: lone used squares form their own isolated regions, while several adjacent squares all count as a single region.
 * <p>
 * In the example above, the following nine regions are visible, each marked with a distinct digit:
 * <p>
 * 11.2.3..-->
 * <p>
 * .1.2.3.4
 * <p>
 * ....5.6.
 * <p>
 * 7.8.55.9
 * <p>
 * .88.5...
 * <p>
 * 88..5..8
 * <p>
 * .8...8..
 * <p>
 * 88.8.88.-->
 * <p>
 * |      |
 * <p>
 * V      V
 * <p>
 * Of particular interest is the region marked 8; while it does not appear contiguous in this small view, all of the squares marked 8 are connected when considering the whole 128x128 grid. In total, in this example, 1242 regions are present.
 * <p>
 * How many regions are present given your key string?
 */
public class Puzzler14B {
    public static void main(String[] args) {
        String input = "hwlqcszp";
        List<String> rows = IntStream.range(0, 128).mapToObj(v -> input + "-" + v).collect(Collectors.toList());
        List<String> hashes = rows.stream().map(Puzzler10B::getKnotHash).collect(Collectors.toList());

        List<String> result = hashes.stream().map(Puzzler14A::hexToBinary).collect(Collectors.toList());
        Square[][] squares = new Square[result.size() + 2][result.size() + 2];
        for (int i = 0; i < result.size(); i++) {
            String current = result.get(i);
            for (int j = 0; j < current.length(); j++) {
                squares[i + 1][j + 1] = new Square(current.charAt(j) == '1');
            }
        }
        for (int i = 0; i < squares.length; i++) {
            squares[0][i] = new Square(false);
            squares[squares.length - 1][i] = new Square(false);
            squares[i][0] = new Square(false);
            squares[i][squares.length - 1] = new Square(false);
        }
        initNeighbours(squares);


        int counter = 1;
        for (int i = 1; i < squares.length - 1; i++) {
            for (int j = 1; j < squares.length - 1; j++) {
                Square square = squares[i][j];
                if (square.isUsed()) {
                    List<Square> neighbours = square.getNeighbours();
                    boolean isFoundNeighbourWithGroup = false;
                    for (Square neighbour : neighbours) {
                        if (neighbour.getNumberOfGroup() != 0 && !isFoundNeighbourWithGroup) {
                            isFoundNeighbourWithGroup = true;
                            square.setNumberOfGroup(neighbour.getNumberOfGroup());
                        } else if (neighbour.getNumberOfGroup() != 0) {
                            setNewNumberOfGroupToNeighboursGroup(squares, square.getNumberOfGroup(), neighbour.getNumberOfGroup());
                        }
                    }
                    if (!isFoundNeighbourWithGroup) {
                        square.setNumberOfGroup(counter++);
                    }
                }
            }
        }
        System.out.println(getCountOfGroups(squares)); //not a good idea to scan it again, but a complexity remains same.

    }

    private static long getCountOfGroups(Square[][] squares) {
        return Arrays.stream(squares).flatMap(Arrays::stream).mapToInt(Square::getNumberOfGroup).distinct().count() - 1;//minus default group
    }

    private static void setNewNumberOfGroupToNeighboursGroup(Square[][] squares, int newNumber, int oldNumber) {
        Arrays.stream(squares)
                .forEach(
                        square -> IntStream.range(0, squares.length)
                                .filter(j -> square[j].getNumberOfGroup() == oldNumber)
                                .forEach(j -> square[j].setNumberOfGroup(newNumber)));
    }

    private static void initNeighbours(Square[][] squares) {
        for (int i = 1; i < squares.length - 1; i++) {
            for (int j = 1; j < squares.length - 1; j++) {
                Square square = squares[i][j];
                List<Square> neighbours = Stream.of(
                        squares[i - 1][j], squares[i][j - 1], squares[i][j + 1], squares[i + 1][j]
                ).collect(Collectors.toList());
                square.setNeighbours(neighbours);
            }
        }
    }
}
