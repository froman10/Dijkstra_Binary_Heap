/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryheap;

import java.util.Scanner;

/**
 *
 * @author Alejandro
 */
public class BinaryHeap {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Heap<Integer> hp = new Heap<Integer>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter next int, 'done' to stop: ");
        String line = sc.next();
        while (!line.equals("done")) {
            hp.insert(Integer.parseInt(line));
            System.out.println(hp);
            System.out.print("Enter next int, 'done' to stop: ");
            line = sc.next();
        }
         
        while (!hp.isEmpty()) {
            int max = hp.delete();
            System.out.println(max + " " + hp);
        }
    }
}
