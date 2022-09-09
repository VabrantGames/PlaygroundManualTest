package com.javaproject;

public class JavaProject {

    public static void main(String[] args) {
        JavaProject p = new JavaProject();
        float product = p.multiply(10, 10);
        System.out.println("The product is " + product);
    }

    public float multiply(float n1, float n2) {
        return n1 * n2;
    }
}