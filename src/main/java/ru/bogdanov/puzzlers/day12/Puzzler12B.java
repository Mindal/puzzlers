package ru.bogdanov.puzzlers.day12;

import lombok.ToString;
import ru.bogdanov.puzzlers.Utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * There are more programs than just the ones in the group containing program ID 0. The rest of them have no way of reaching that group, and still might have no way of reaching each other.
 * <p>
 * A group is a collection of programs that can all communicate via pipes either directly or indirectly. The programs you identified just a moment ago are all part of the same group. Now, they would like you to determine the total number of groups.
 * <p>
 * In the example above, there were 2 groups: one consisting of programs 0,2,3,4,5,6, and the other consisting solely of program 1.
 * <p>
 * How many groups are there in total?
 */

public class Puzzler12B {
    public static void main(String[] args) {
        List<String> allLines = Utils.getAllLines("day12/input.txt");
        Map<Integer, Vertex> vertices = new HashMap<>();
        allLines.forEach(s -> {

            String[] split = s.split("<->");
            int[] neighbours = Arrays.stream(split[1].trim().split(","))
                    .mapToInt(value -> Integer.parseInt(value.trim()))
                    .toArray();
            vertices.put(Integer.valueOf(split[0].trim()), new Vertex(neighbours));

        });
        int count = 0;
        while (!vertices.isEmpty()) {
           Vertex start = vertices.entrySet().iterator().next().getValue();
            start.setVisited(true);
            int[] neighbours = start.getNeighbours();
            setVisited(neighbours, vertices);
            int[] ints = vertices.entrySet().stream().filter(e -> e.getValue().isVisited).mapToInt(Map.Entry::getKey).toArray();
            Arrays.stream(ints).forEach(vertices::remove);
            count++;
        }
        System.out.println(count);

    }

    private static void setVisited(int[] neighbours, Map<Integer, Vertex> vertices) {
        int length = neighbours.length;
        long count = Arrays.stream(neighbours).filter(value -> vertices.get(value).isVisited()).count();
        if (count != length) {
            Arrays.stream(neighbours)
                    .mapToObj(vertices::get)
                    .filter(vertex -> !vertex.isVisited())
                    .forEach(vertex -> {
                        vertex.setVisited(true);
                        setVisited(vertex.getNeighbours(), vertices);
                    });
        }
    }


    @ToString
    public static class Vertex {
        private int[] neighbours;
        private boolean isVisited;


        boolean isVisited() {
            return isVisited;
        }

        void setVisited(boolean visited) {
            isVisited = visited;
        }

        int[] getNeighbours() {
            return neighbours;
        }

        Vertex(int[] neighbours) {
            this.neighbours = neighbours;
            isVisited = false;
        }

    }

}
