package org.example;

public class Ghost extends Enemy{
    public Ghost(int weight, int height) {
        super(weight,height);
    }

    public void attack() {
        System.out.println("Boo!");
    }
}
