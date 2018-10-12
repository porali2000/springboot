package com.porlabs.templates.algo;

import java.util.HashMap;
import java.util.Map;

public class Algo {
    public static void main(String[] args) {
        System.out.println("Start");
        SortingHashMap hash = new SortingHashMap();
        hash.put('F');
        hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A');

        hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A'); hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A'); hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A');
        hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A'); hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A');
        hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A');

        hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A'); hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A'); hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A');
        hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A'); hash.put('a');
        hash.put('A');
        hash.put('D');
        hash.put('A');
        hash.printwithDuplicates();
        hash.printwithoutDuplicates();

    }
}

class SortingHashMap{
    Map<Integer,Character> sorted = new HashMap<>();
    Integer start = 65;
    Map<Character, Integer> internal = new HashMap<>();
    void put(Character c) {
        internal.put(c, (internal.get(c) != null ) ? internal.get(c)+1 : 1);
        sorted.put(c.hashCode(), c);
    }
    void printwithDuplicates() {
        int startAcces = start;
        int startIndex = 0;
        while (startIndex < sorted.size() && startAcces < 165) {
            if(sorted.get(startAcces) != null ) {
                Integer times = internal.get(sorted.get(startAcces));
                while(0 < times) {
                    System.out.print(sorted.get(startAcces));
                    System.out.print(" ");
                    times-- ;
                }
                startIndex++;
            }
            startAcces++;
        }
        System.out.println();
    }

    void printwithoutDuplicates() {
        int startAcces = start;
        int startIndex = 0;
        while (startIndex < sorted.size() && startAcces < 165) {
            if(sorted.get(startAcces) != null ) {
                Integer times = internal.get(sorted.get(startAcces));
                    System.out.print(sorted.get(startAcces));
                    System.out.print(" ");
                startIndex++;
            }
            startAcces++;
        }
        System.out.println();
    }
}
