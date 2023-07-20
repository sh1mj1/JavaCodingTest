package org.example.priorityqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class PriorityQueueTest {

    public static void main(String[] args) {

        // Integer 타입으로 우선순위 큐 선언(낮은 숫자 순으로 우선순위 결정)
        PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<>();

        // Integer 타입으로 우선순위 큐 선언(높은 숫자 순으로 우선순위 결정)
        PriorityQueue<Integer> priorityQueue2 = new PriorityQueue<>(Collections.reverseOrder());

        // String 타입으로 우선순위 큐 선언(낮은 숫자 순으로 우선순위 결정)
        PriorityQueue<String> priorityQueue3 = new PriorityQueue<>();

        // String 타입으로 우선순위 큐 선언(높은 숫자 순으로 우선순위 결정)
        PriorityQueue<String> priorityQueue4 = new PriorityQueue<>(Comparator.reverseOrder());

        priorityQueue1.add(1);
        priorityQueue1.add(9);
        priorityQueue1.add(10);
        priorityQueue1.add(2);
        priorityQueue1.add(12);
        priorityQueue1.add(5);
        priorityQueue1.add(4);


        System.out.println(priorityQueue1.peek());

        Iterator iterator = priorityQueue1.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }

    }
}
