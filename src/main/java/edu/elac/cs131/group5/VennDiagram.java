package edu.elac.cs131.group5;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

public class VennDiagram {
    // Union combines set a and b
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    // Intersection finds common elements between set a and b
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    /* Test union and intersection functions
    public static void main(String[] args) {
        Set<String> setA = new HashSet<>(Arrays.asList("1", "2", "3", "cat", "horse"));
        Set<String> setB = new HashSet<>(Arrays.asList("3","cat", "dog","4", "5")); 
        System.out.println("Union: " + union(setA, setB));
        System.out.println("Intersection: " + intersection(setA, setB));
    } 
    */
}