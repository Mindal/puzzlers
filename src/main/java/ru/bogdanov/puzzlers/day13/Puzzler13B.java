package ru.bogdanov.puzzlers.day13;

import ru.bogdanov.puzzlers.Utils;

import java.util.List;
public class Puzzler13B {
    public static void main(String[] args) throws Exception {
        List<String> allLines = Utils.getAllLines("day13/input.txt");
        Puzzler13B puzzler = new Puzzler13B();
        puzzler.build(allLines);
        puzzler.runB();
    }

    private Wall[] firewall;

    public void build(List<String> input) {
        firewall = new Wall[100];
        for (String s : input) {
            String[] args  = s.split(": ");
            firewall[Integer.parseInt(args[0])] = new Wall(Integer.parseInt(args[1]));

        }
    }




    public void runB() {
        int start = 0;
        while(!safe(start)) start++;
        System.out.println(start);
    }

    public boolean safe(int time) {
        for(int i = 0; i < 100; i++) {
            int ind = i + time;
            if(firewall[i] != null && !firewall[i].open(ind)) {
                return false;
            }
        }
        return true;
    }

    public class Wall {
        public int size;

        public Wall(int size) {
            this.size = size;
        }

        public boolean open(int time) {
            return (time % ((size-1) * 2) != 0);
        }
    }
}