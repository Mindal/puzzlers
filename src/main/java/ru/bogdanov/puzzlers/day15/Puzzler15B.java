package ru.bogdanov.puzzlers.day15;


/**
 * In the interest of trying to align a little better, the generators get more picky about the numbers they actually give to the judge.
 * <p>
 * They still generate values in the same way, but now they only hand a value to the judge when it meets their criteria:
 * <p>
 * Generator A looks for values that are multiples of 4.
 * <p>
 * Generator B looks for values that are multiples of 8.
 * <p>
 * Each generator functions completely independently: they both go through values entirely on their own, only occasionally handing an acceptable value to the judge, and otherwise working through the same sequence of values as before until they find one.
 * <p>
 * The judge still waits for each generator to provide it with a value before comparing them (using the same comparison method as before). It keeps track of the order it receives values; the first values from each generator are compared, then the second values from each generator, then the third values, and so on.
 * <p>
 * Using the example starting values given above, the generators now produce the following first five values each:
 * <p>
 * --Gen. A--  --Gen. B--
 * <p>
 * 1352636452  1233683848
 * <p>
 * 1992081072   862516352
 * <p>
 * 530830436  1159784568
 * <p>
 * 1980017072  1616057672
 * <p>
 * 740335192   412269392
 * <p>
 * These values have the following corresponding binary values:
 * <p>
 * 01010000100111111001100000100100
 * <p>
 * 01001001100010001000010110001000
 * <p>
 * <p>
 * 01110110101111001011111010110000
 * <p>
 * 00110011011010001111010010000000
 * <p>
 * <p>
 * 00011111101000111101010001100100
 * <p>
 * 01000101001000001110100001111000
 * <p>
 * <p>
 * 01110110000001001010100110110000
 * <p>
 * 01100000010100110001010101001000
 * <p>
 * <p>
 * 00101100001000001001111001011000
 * <p>
 * 00011000100100101011101101010000
 * <p>
 * Unfortunately, even though this change makes more bits similar on average, none of these values' lowest 16 bits match. Now, it's not until the 1056th pair that the judge finds the first match:
 * <p>
 * --Gen. A--  --Gen. B--
 * <p>
 * 1023762912   896885216
 * <p>
 * <p>
 * 00111101000001010110000111100000
 * <p>
 * 00110101011101010110000111100000
 * <p>
 * This change makes the generators much slower, and the judge is getting impatient; it is now only willing to consider 5 million pairs. (Using the values from the example above, after five million pairs, the judge would eventually find a total of 309 pairs that match in their lowest 16 bits.)
 * <p>
 * After 5 million pairs, but using this new generator logic, what is the judge's final count?
 */
@SuppressWarnings("All")
public class Puzzler15B {
    private static final long FACTOR_A = 16807L;

    private static final long FACTOR_B = 48271L;

    private static final long MAGIC_NUMBER_FOR_DIVIDING = 2147483647L;

    public static void main(String[] args) {
        int counter = 0;
        long currentA = 591L;
        long currentB = 393L;
        for (int i = 0; i < 5_000_000; i++) {
            System.out.println(i + " / " + 5_000_000);
            currentA = getNextA(currentA);
            currentB = getNextB(currentB);
            if (getLast16Bits(currentA).equals(getLast16Bits(currentB))) {
                counter++;
            }
        }
        System.out.println(counter);

    }

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
        long result = (startB * FACTOR_B) % MAGIC_NUMBER_FOR_DIVIDING;
        while (result % 8 != 0) {
            result = (result * FACTOR_B) % MAGIC_NUMBER_FOR_DIVIDING;
        }
        return result;
    }

    private static long getNextA(long startA) {
        long result = (startA * FACTOR_A) % MAGIC_NUMBER_FOR_DIVIDING;
        while (result % 4 != 0) {
            result = (result * FACTOR_A) % MAGIC_NUMBER_FOR_DIVIDING;
        }
        return result;

    }

}
