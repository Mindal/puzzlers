package ru.bogdanov.puzzlers.seventh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzler7B {
    public static void main(String[] args) throws IOException {
        Map<String, Node> nodes = new HashMap<>();
        String path = "C:\\Users\\Python\\IdeaProjects\\puzzlers\\src\\main\\java\\ru\\bogdanov\\puzzlers\\seventh\\input7.txt";
        List<String> input = Files.readAllLines(Paths.get(path));
        input.forEach(s -> {
            String[] split = s.split("->");

            String nodeName = split[0].substring(0, split[0].indexOf('(') - 1);
            nodes.put(nodeName, new Node(getWeight(split[0])));
        });

        input.forEach(s -> {
            String[] split = s.split("->");

            String nodeName = split[0].substring(0, split[0].indexOf('(') - 1);
            if (split.length > 1) {
                for (String s1 : split[1].split(",")) {
                    Node node;
                    node = nodes.get(s1.trim());
                    node.setParent(nodes.get(nodeName));
                    nodes.get(nodeName).getChildren().add(node);
                }
            }
        });
        Node current = nodes.get("dgoocsw"); //root, hack from previous task(7A), but can be easily calculated here
        Node previous = current;
        while (current != null) {

            List<Node> children = current.getChildren();
            int sum1 = getTotalWeight(children.get(0));
            int sum2 = getTotalWeight(children.get(1));
            int sum3 = getTotalWeight(children.get(2));
            int rightSum = (sum1 == sum2) ? sum1 : sum1 == sum3 ? sum1 : sum2; // what will we do if we have only 2 children?

            Node wrongNode = getWrongNode(rightSum, children);
            if(wrongNode == null){
                System.out.println("Answer is " + (current.getWeight() - getDifference(previous)));
                return;
            }
            previous = current;
            current = wrongNode;
        }
    }

    private static Node getWrongNode(Integer rightSum, List<Node> nodes) {
        return nodes.stream().filter(node -> getTotalWeight(node) != rightSum).findFirst().orElse(null);
    }

    private static int getDifference(Node node) {
        for (Node child : node.getParent().getChildren()) {
            int childTotalWeight = getTotalWeight(child);
            int nodeTotalWeight = getTotalWeight(node);
            if(childTotalWeight != nodeTotalWeight){
                return nodeTotalWeight - childTotalWeight;
            }
        }
        return -1; //shouldn't be returned if the input is correct
    }


    private static int getTotalWeight(Node node) {
        int result = node.getWeight();
        List<Node> children = node.getChildren();
        for (Node child : children) {
            result += getTotalWeight(child);
        }
        return result;
    }


    private static int getWeight(String s) {
        return Integer.parseInt(s.substring(s.indexOf("(") + 1, s.indexOf(")")));
    }
}
