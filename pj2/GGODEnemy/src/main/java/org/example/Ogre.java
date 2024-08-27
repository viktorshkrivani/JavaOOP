package org.example;

public class Ogre extends Enemy{
    public Ogre(int weight, int height){
        super(weight, height);
    }

    public void attack(){
        System.out.println("Ugh!");
    }
}
