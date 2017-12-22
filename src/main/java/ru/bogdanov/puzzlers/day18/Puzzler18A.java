package ru.bogdanov.puzzlers.day18;

import ru.bogdanov.puzzlers.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzler18A {
    public static void main(String[] args) {
        List<String> input = Utils.getAllLines("day18/input.txt");
        Map<Character, Long> values = new HashMap<>();
        initMap(values);
        int numOfInstructions = 0;
        Long result = 0L;
        label:
        while (true) {
            String string = input.get(numOfInstructions);
            String[] split = string.split(" ");
            Character in = split[1].charAt(0);
            String out = "";
            if (split.length == 3) {
                out = split[2];
            }
            String instruction = split[0];
            switch (instruction) {
                case "set":
                    if (isInteger(out)) {
                        values.put(in, Long.valueOf(out));
                    } else {
                        values.put(in, values.get(out.charAt(0)));
                    }
                    numOfInstructions++;
                    break;
                case "add":
                    if (isInteger(out)) {
                        values.put(in, values.get(in) + Long.valueOf(out));
                    } else {
                        values.put(in, values.get(in) + values.get(out.charAt(0)));
                    }
                    numOfInstructions++;
                    break;
                case "mul":
                    if (isInteger(out)) {
                        values.put(in, values.get(in) * Long.valueOf(out));
                    } else {
                        values.put(in, values.get(in) * values.get(out.charAt(0)));
                    }
                    numOfInstructions++;
                    break;
                case "mod":
                    if (isInteger(out)) {
                        values.put(in, values.get(in) % Long.valueOf(out));
                    } else {
                        values.put(in, values.get(in) % values.get(out.charAt(0)));
                    }
                    numOfInstructions++;
                    break;
                case "rcv":
                    if (values.get(in) != 0) break label;
                    else numOfInstructions++;
                    break;
                case "jgz":
                    if (values.get(in) > 0) {
                        if (isInteger(out)) {
                            numOfInstructions += Integer.parseInt(out);
                        } else {
                            numOfInstructions += values.get(out.charAt(0));
                        }
                    } else numOfInstructions++;
                    break;
                case "snd":
                    result = values.get(in);
                    numOfInstructions++;
                    break;
                default:
                    throw new RuntimeException("Incorrect instruction");
            }
        }
        System.out.println(result);
    }

    private static void initMap(Map<Character, Long> values) {
        String characters = "iapbf";
        for (char c : characters.toCharArray()) {
            values.put(c, 0L);
        }
    }

    public static boolean isInteger(String s) {
        return s.matches("^-?\\d+$");
    }
}
