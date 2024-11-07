package com.labs.java.core;
import java.util.Scanner;

public class CelsiusToFahrenheitConverter {
    public static void main(String[] args){
        double celsius = 100;
        double fahrenheit = (celsius * 9 / 5) + 32;
        System.out.println(celsius + " degree Celsius is equal to "+ fahrenheit + " in Fahrenheit");
        //System is a class part of lang package which is by default imported i.e. java.lang

        Scanner scr = new Scanner(System.in); //getting input from console
        System.out.println("Enter temperature in Celsium : ");
        double cls = scr.nextDouble();
        System.out.println(" cls = " + cls);

        scr.close(); //closing scanner.
    }
}

