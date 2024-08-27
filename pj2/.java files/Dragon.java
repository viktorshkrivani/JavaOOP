package org.example;

public class Dragon extends Enemy{
    public Dragon (int weight, int height){
        super(weight, height);
    }

    public void attack(){
        System.out.println("Rawr!");
    }
}


