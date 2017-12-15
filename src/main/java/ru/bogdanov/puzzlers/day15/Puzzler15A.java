package ru.bogdanov.puzzlers.day15;

/**
 * Here, you encounter a pair of dueling generators. The generators, called generator A and generator B, are trying to agree on a sequence of numbers. However, one of them is malfunctioning, and so the sequences don't always match.
 * <p>
 * As they do this, a judge waits for each of them to generate its next value, compares the lowest 16 bits of both values, and keeps track of the number of times those parts of the values match.
 * <p>
 * The generators both work on the same principle. To create its next value, a generator will take the previous value it produced, multiply it by a factor (generator A uses 16807; generator B uses 48271), and then keep the remainder of dividing that resulting product by 2147483647. That final remainder is the value it produces next.
 * <p>
 * To calculate each generator's first value, it instead uses a specific starting value as its "previous value" (as listed in your puzzle input).
 * <p>
 * For example, suppose that for starting values, generator A uses 65, while generator B uses 8921. Then, the first five pairs of generated values are:
 * <p>
 * --Gen. A--  --Gen. B--
 * <p>
 * 1092455   430625591
 * <p>
 * 1181022009  1233683848
 * <p>
 * 245556042  1431495498
 * <p>
 * 1744312007   137874439
 * <p>
 * 1352636452   285222916
 * <p>
 * In binary, these pairs are (with generator A's value first in each pair):
 * <p>
 * <p>
 * 00000000000100001010101101100111
 * <p>
 * 00011001101010101101001100110111
 * <p>
 * <p>
 * 01000110011001001111011100111001
 * <p>
 * 01001001100010001000010110001000
 * <p>
 * <p>
 * 00001110101000101110001101001010
 * <p>
 * 01010101010100101110001101001010
 * <p>
 * <p>
 * 01100111111110000001011011000111
 * <p>
 * 00001000001101111100110000000111
 * <p>
 * <p>
 * 01010000100111111001100000100100
 * <p>
 * 00010001000000000010100000000100
 * <p>
 * <p>
 * Here, you can see that the lowest (here, rightmost) 16 bits of the third value match: 1110001101001010. Because of this one match, after processing these five pairs, the judge would have added only 1 to its total.
 * <p>
 * To get a significant sample, the judge would like to consider 40 million pairs. (In the example above, the judge would eventually find a total of 588 pairs that match in their lowest 16 bits.)
 * <p>
 * After 40 million pairs, what is the judge's final count?
 */
public class Puzzler15A {
    private static final long FACTOR_A = 16807L;

    private static final long FACTOR_B = 48271L;

    private static final long MAGIC_NUMBER_FOR_DIVIDING = 2147483647L;

    public static void main(String[] args) {
        int counter = 0;
        long currentA = 591L;
        long currentB = 393L;
        for (int i = 0; i < 40_000_000; i++) {
            System.out.println(i + " / " + 40_000_000);
            currentA = getNextA(currentA);
            currentB = getNextB(currentB);
            if (getLast16Bits(currentA).equals(getLast16Bits(currentB))) {
                counter++;
            }
        }
        System.out.println(counter);

    }

    // TODO: 12/15/2017 bit manipulation will be better
    private static String getLast16Bits(long number) {
        StringBuilder binary = new StringBuilder(Long.toBinaryString(number));
        if (binary.length() < 16) {
            while (binary.length() != 16) {
                binary.insert(0, "0");
            }
            return binary.toString();
        } else {
            return binary.substring(binary.length() - 16, binary.length());
        }


    }

    private static long getNextB(long startB) {
        return (startB * FACTOR_B) % MAGIC_NUMBER_FOR_DIVIDING;
    }

    private static long getNextA(long startA) {
        return (startA * FACTOR_A) % MAGIC_NUMBER_FOR_DIVIDING;

    }
}
