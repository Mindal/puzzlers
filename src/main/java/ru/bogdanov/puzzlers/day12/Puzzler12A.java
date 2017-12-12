package ru.bogdanov.puzzlers.day12;

import lombok.ToString;
import ru.bogdanov.puzzlers.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Walking along the memory banks of the stream, you find a small village that is experiencing a little confusion: some programs can't communicate with each other.
 * <p>
 * Programs in this village communicate using a fixed system of pipes. Messages are passed between programs using these pipes, but most programs aren't connected to each other directly. Instead, programs pass messages between each other until the message reaches the intended recipient.
 * <p>
 * For some reason, though, some of these messages aren't ever reaching their intended recipient, and the programs suspect that some pipes are missing. They would like you to investigate.
 * <p>
 * You walk through the village and record the ID of each program and the IDs with which it can communicate directly (your puzzle input). Each program has one or more programs with which it can communicate, and these pipes are bidirectional; if 8 says it can communicate with 11, then 11 will say it can communicate with 8.
 * <p>
 * You need to figure out how many programs are in the group that contains program ID 0.
 * <p>
 * For example, suppose you go door-to-door like a travelling salesman and record the following list:
 * <p>
 * 0 <-> 2
 * <p>
 * 1 <-> 1
 * <p>
 * 2 <-> 0, 3, 4
 * <p>
 * 3 <-> 2, 4
 * <p>
 * 4 <-> 2, 3, 6
 * <p>
 * 5 <-> 6
 * <p>
 * 6 <-> 4, 5
 * <p>
 * In this example, the following programs are in the group that contains program ID 0:
 * <p>
 * Program 0 by definition.
 * <p>
 * Program 2, directly connected to program 0.
 * <p>
 * Program 3 via program 2.
 * <p>
 * Program 4 via program 2.
 * <p>
 * Program 5 via programs 6, then 4, then 2.
 * <p>
 * Program 6 via programs 4, then 2.
 * <p>
 * Therefore, a total of 6 programs are in this group; all but program 1, which has a pipe that connects it to itself.
 * <p>
 * How many programs are in the group that contains program ID 0?
 */
public class Puzzler12A {
    public static void main(String[] args) {
        List<String> allLines = Utils.getAllLines("day12/input.txt");
        List<Vertex> vertices = new ArrayList<>();
        allLines.forEach(s -> {

            String[] split = s.split("<->");
            int[] neighbours = Arrays.stream(split[1].trim().split(","))
                    .mapToInt(value -> Integer.parseInt(value.trim()))
                    .toArray();
            vertices.add(new Vertex(neighbours));

        });
        Vertex start = vertices.get(0);
        start.setVisited(true);
        int[] neighbours = start.getNeighbours();
        setVisited(neighbours, vertices);
        System.out.println(vertices.stream().filter(Vertex::isVisited).count());
    }

    private static void setVisited(int[] neighbours, List<Vertex> vertices) {
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
