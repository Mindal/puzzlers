package ru.bogdanov.puzzlers.day13;

import ru.bogdanov.puzzlers.Utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * You need to cross a vast firewall. The firewall consists of several layers, each with a security scanner that moves back and forth across the layer. To succeed, you must not be detected by a scanner.
 * <p>
 * By studying the firewall briefly, you are able to record (in your puzzle input) the depth of each layer and the range of the scanning area for the scanner within it, written as depth: range. Each layer has a thickness of exactly 1. A layer at depth 0 begins immediately inside the firewall; a layer at depth 1 would start immediately after that.
 * <p>
 * For example, suppose you've recorded the following:
 * <p>
 * 0: 3
 * <p>
 * 1: 2
 * <p>
 * 4: 4
 * <p>
 * 6: 4
 * <p>
 * This means that there is a layer immediately inside the firewall (with range 3), a second layer immediately after that (with range 2), a third layer which begins at depth 4 (with range 4), and a fourth layer which begins at depth 6 (also with range 4). Visually, it might look like this:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [ ] ... ... [ ] ... [ ]
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * Within each layer, a security scanner moves back and forth within its range. Each security scanner starts at the top and moves down until it reaches the bottom, then moves up until it reaches the top, and repeats. A security scanner takes one picosecond to move one step. Drawing scanners as S, the first few picoseconds look like this:
 * <p>
 * <p>
 * Picosecond 0:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [S] [S] ... ... [S] ... [S]
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * <p>
 * Picosecond 1:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [ ] ... ... [ ] ... [ ]
 * <p>
 * [S] [S]         [S]     [S]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * Picosecond 2:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [S] ... ... [ ] ... [ ]
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [S]             [S]     [S]
 * <p>
 * [ ]     [ ]
 * <p>
 * Picosecond 3:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [ ] ... ... [ ] ... [ ]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [S] [S]         [ ]     [ ]
 * <p>
 * [S]     [S]
 * <p>
 * Your plan is to hitch a ride on a packet about to move through the firewall. The packet will travel along the top of each layer, and it moves at one layer per picosecond. Each picosecond, the packet moves one layer forward (its first move takes it into layer 0), and then the scanners move one step. If there is a scanner at the top of the layer as your packet enters it, you are caught. (If a scanner moves into the top of its layer while you are there, you are not caught: it doesn't have time to notice you before you leave.) If you were to do this in the configuration above, marking your current position with parentheses, your passage through the firewall would look like this:
 * <p>
 * Initial state:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [S] [S] ... ... [S] ... [S]
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * Picosecond 0:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * (S) [S] ... ... [S] ... [S]
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * ( ) [ ] ... ... [ ] ... [ ]
 * <p>
 * [S] [S]         [S]     [S]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * Picosecond 1:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] ( ) ... ... [ ] ... [ ]
 * <p>
 * [S] [S]         [S]     [S]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] (S) ... ... [ ] ... [ ]
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [S]             [S]     [S]
 * <p>
 * [ ]     [ ]
 * <p>
 * Picosecond 2:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [S] (.) ... [ ] ... [ ]
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [S]             [S]     [S]
 * <p>
 * [ ]     [ ]
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [ ] (.) ... [ ] ... [ ]
 * <p>
 * [S] [S]         [ ]     [ ]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [S]     [S]
 * <p>
 * Picosecond 3:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [ ] ... (.) [ ] ... [ ]
 * <p>
 * [S] [S]         [ ]     [ ]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [S]     [S]
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [S] [S] ... (.) [ ] ... [ ]
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [ ]             [S]     [S]
 * <p>
 * [ ]     [ ]
 * <p>
 * Picosecond 4:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [S] [S] ... ... ( ) ... [ ]
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [ ]             [S]     [S]
 * <p>
 * [ ]     [ ]
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [ ] ... ... ( ) ... [ ]
 * <p>
 * [S] [S]         [S]     [S]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * Picosecond 5:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [ ] ... ... [ ] (.) [ ]
 * <p>
 * [S] [S]         [S]     [S]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [S] ... ... [S] (.) [S]
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [S]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * Picosecond 6:
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [S] ... ... [S] ... (S)
 * <p>
 * [ ] [ ]         [ ]     [ ]
 * <p>
 * [S]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * 0   1   2   3   4   5   6
 * <p>
 * [ ] [ ] ... ... [ ] ... ( )
 * <p>
 * [S] [S]         [S]     [S]
 * <p>
 * [ ]             [ ]     [ ]
 * <p>
 * [ ]     [ ]
 * <p>
 * In this situation, you are caught in layers 0 and 6, because your packet entered the layer when its scanner was at the top when you entered it. You are not caught in layer 1, since the scanner moved into the top of the layer once you were already there.
 * <p>
 * The severity of getting caught on a layer is equal to its depth multiplied by its range. (Ignore layers in which you do not get caught.) The severity of the whole trip is the sum of these values. In the example above, the trip severity is 0*3 + 6*4 = 24.
 * <p>
 * Given the details of the firewall you've recorded, if you leave immediately, what is the severity of your whole trip?
 */
public class Puzzler13A {
    public static void main(String[] args) {
        List<String> allLines = Utils.getAllLines("day13/input.txt");
        Map<Integer, Layer> layers = allLines.stream()
                .collect(Collectors.toMap
                        (o -> Integer.parseInt(o.split(": ")[0]),
                                o -> new Layer(Integer.parseInt(o.split(": ")[1]))));
        int maxDepth = Collections.max(layers.keySet());
        int output = 0;
        for (int i = 0; i <= maxDepth; i++) {
            if(layers.get(i) != null && layers.get(i).getCounter() == 1){
                output += i * layers.get(i).getRange();
            }
            layers.values().forEach(layer -> {
                if (layer.getCounter() == 1) {
                    layer.setReverse(false);
                } else if (layer.getCounter() == layer.getRange()) {
                    layer.setReverse(true);
                }
                layer.setCounter(layer.isReverse() ? layer.getCounter() - 1 : layer.getCounter() + 1);
            });
        }
        System.out.println(output);
    }
}
