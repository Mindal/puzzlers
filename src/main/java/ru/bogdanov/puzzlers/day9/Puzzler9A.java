package ru.bogdanov.puzzlers.day9;

import ru.bogdanov.puzzlers.Utils;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

/**
 * A large stream blocks your path. According to the locals, it's not safe to cross the stream at the moment because it's full of garbage. You look down at the stream; rather than water, you discover that it's a stream of characters.
 * <p>
 * You sit for a while and record part of the stream (your puzzle input). The characters represent groups - sequences that begin with { and end with }. Within a group, there are zero or more other things, separated by commas: either another group or garbage. Since groups can contain other groups, a } only closes the most-recently-opened unclosed group - that is, they are nestable. Your puzzle input represents a single, large group which itself contains many smaller ones.
 * <p>
 * Sometimes, instead of a group, you will find garbage. Garbage begins with < and ends with >. Between those angle brackets, almost any character can appear, including { and }. Within garbage, < has no special meaning.
 * <p>
 * In a futile attempt to clean up the garbage, some program has canceled some of the characters within it using !: inside garbage, any character that comes after ! should be ignored, including <, >, and even another !.
 * <p>
 * You don't see any characters that deviate from these rules. Outside garbage, you only find well-formed groups, and garbage always terminates according to the rules above.
 * <p>
 * Here are some self-contained pieces of garbage:
 * <p>
 * <>, empty garbage.<p>
 * &lt random characters>, garbage containing random characters.<p>
 * <<<<>, because the extra < are ignored.<p>
 * <{!>}>, because the first > is canceled.<p>
 * &lt !!>, because the second ! is canceled, allowing the > to terminate the garbage.<p>
 * &lt !!!>>, because the second ! and the first > are canceled.<p>
 * <{o"i!a,<{i<a>, which ends at the first >.<p>
 * Here are some examples of whole streams and the number of groups they contain:
 * <p>
 * {}, 1 group.<p>
 * {{{}}}, 3 groups.<p>
 * {{},{}}, also 3 groups.<p>
 * {{{},{},{{}}}}, 6 groups.<p>
 * {<{},{},{{}}>}, 1 group (which itself contains garbage).<p>
 * {&lt a>,&lt a>,&lt a>,&lt a>}, 1 group.<p>
 * {{&lt a>},{&lt a>},{&lt a>},{&lt a>}}, 5 groups.<p>
 * {{&lt !>},{&lt !>},{&lt !>},{&lt a>}}, 2 groups (since all but the last > are canceled).<p>
 * Your goal is to find the total score for all groups in your input. Each group is assigned a score which is one more than the score of the group that immediately contains it. (The outermost group gets a score of 1.)
 * <p>
 * {}, score of 1.<p>
 * {{{}}}, score of 1 + 2 + 3 = 6.<p>
 * {{},{}}, score of 1 + 2 + 2 = 5.<p>
 * {{{},{},{{}}}}, score of 1 + 2 + 3 + 3 + 3 + 4 = 16.<p>
 * {&lt a>,&lt a>,&lt a>,&lt a>}, score of 1.<p>
 * {{&lt ab>},{&lt ab>},{&lt ab>},{&lt ab>}}, score of 1 + 2 + 2 + 2 + 2 = 9.<p>
 * {{<!!>},{<!!>},{<!!>},{<!!>}}, score of 1 + 2 + 2 + 2 + 2 = 9.<p>
 * {{&lt a!>},{&lt a!>},{&lt a!>},{&lt ab>}}, score of 1 + 2 = 3.<p>
 * What is the total score for all groups in your input?
 */
public class Puzzler9A {
    public static void main(String[] args) throws IOException {
        String input = Utils.getLine("day9/input.txt");

        while (containsGarbage(input)) {
            input = cleanGarbage(input);
        }
        System.out.println(input);
//        long countOfGroups = getCountOfGroups(input);
//        System.out.println(countOfGroups);
        int countOfScores = getCountOfScores(input);
        System.out.println(countOfScores);
    }

    private static int getCountOfScores(String input) {
        int counter = 0;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '{') {
                stack.push('{');
                counter += stack.size();
            } else if (input.charAt(i) == '}') {
                stack.poll();
            }
        }
        return counter;
    }

    private static long getCountOfGroups(String input) {
        return IntStream.range(0, input.length()).filter(i -> input.charAt(i) == '{').count();

    }

    private static boolean containsGarbage(String input) {
        return input.contains("<");
    }

    private static String cleanGarbage(String input) {
        int beginIndex = input.indexOf('<');
        String substring = input.substring(beginIndex);
        for (int i = 0; i < substring.length(); i++) {
            if (substring.charAt(i) == '>') {
                int countOfExclamationPoint = getCountOfExclamationPoints(substring, i);
                if (countOfExclamationPoint % 2 == 0) {
                    input = input.substring(0, beginIndex);
                    if (i != substring.length() - 1) {
                        input = input + substring.substring(i + 1);
                    }
                    return input;
                }

            }
        }
        return input;
    }

    private static int getCountOfExclamationPoints(String string, int index) {
        if (index == 0) return 0;
        int counter = 0;
        index--;
        while (index != 0 && string.charAt(index) == '!') {
            counter++;
            index--;
        }
        return counter;
    }
}
