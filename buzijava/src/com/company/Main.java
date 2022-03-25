package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("megbassza, odanezz megbassza");

        System.out.println("irjatok ide a neveteket: Dani");
        System.out.println("irjatok ide a neveteket: Zoli");
        System.out.println("irjatok ide a neveteket: Mark");
        System.out.println("Mukodik");


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
        String name = reader.readLine();

        // Printing the read line
        System.out.println(name);
    }
}
