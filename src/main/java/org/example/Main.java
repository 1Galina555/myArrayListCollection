package org.example;

import org.example.MyArrayList.MyArrayList;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> array = new MyArrayList<>();
        array.add(7);
        array.add(7);
        array.add(7);
        array.add(999);
        array.add(81);
        array.add(130);
        array.add(22);
        array.add(4);
        array.add(199);
        array.add(1);
        System.out.println(array);
        array.add(2, 10);
        System.out.println(array);
        array.remove(2);
        System.out.println(array);
        array.quickSort(Comparator.naturalOrder());
        System.out.println(array);
        System.out.println(array.getSize());
    }
}