package ru.bogdanov.puzzlers.day18;

import ru.bogdanov.puzzlers.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzler18A {
    public static void main(String[] args) {
        List<String> input = Utils.getAllLines("day18/input.txt");
        Map<Character, Integer> values = new HashMap<>();
        int numOfInstructions = 0;
        while (true) {
            String string = input.get(numOfInstructions);
            String[] split = string.split(" ");
            String instruction = split[0];
            switch (instruction) {
                case "set": {
                    Character in = split[1].charAt(0);
                    String out = split[2];
                    if (isInteger(out)) {
                        values.merge(in, Integer.parseInt(out), (integer, integer2) -> integer2);
                    } else {
                        values.merge(in, values.getOrDefault(out.charAt(0), 0), (integer, integer2) -> integer2);
                    }

                    break;
                }
                case "add": {
                    Character in = split[1].charAt(0);
                    String out = split[2];
                    if (isInteger(out)) {
                        values.merge(in, Integer.parseInt(out), (integer, integer2) -> integer + integer2);
                    } else {
                        values.merge(in, values.getOrDefault(out.charAt(0), 0), (integer, integer2) -> integer + integer2);
                    }

                    break;

                }
                case "mul": {
                    Character in = split[1].charAt(0);
                    String out = split[2];
                    values.merge(in, Integer.parseInt(out), (integer, integer2) -> integer + integer2);
                    break;
                }
                case "mod": {
                    break;
                }
                case "rcv": {
                    break;
                }
                case "jgz": {
                    break;
                }
                case "snd": {
                    break;
                }
                default:
                    throw new RuntimeException("Incorrect instruction");
            }
            break;

        }
    }

    public static boolean isInteger(String s) {
        return s.matches("^-?\\d+$");
    }
}
