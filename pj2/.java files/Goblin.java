package org.example;

public class Goblin extends Enemy{
    public Goblin(int weight, int height) {
        super(weight,height);
    }

    public void attack(){
        System.out.println("Gurgle!");
    }
}
