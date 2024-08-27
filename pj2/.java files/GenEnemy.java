package org.example;

import java.util.ArrayList;
import java.util.Random;

public class GenEnemy {
    public static void main(String[] args) {
        ArrayList<Enemy> enemy = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < 100; i++) {
            int randomNumber = random.nextInt(4) + 1;

            int weight, height;
            if (randomNumber == 1) {
                weight = random.nextInt(6) + 5;
                height = random.nextInt(31) + 70;
                enemy.add(new Goblin(weight, height));
            } else if (randomNumber == 2) {
                weight = 0;
                height = random.nextInt(61) +90;
                enemy.add(new Ghost(weight, height));
            } else if (randomNumber == 3) {
                weight = random.nextInt(81) + 120;
                height = random.nextInt(101) + 200;
                enemy.add(new Ogre(weight, height));
            } else if (randomNumber == 4) {
                weight = random.nextInt(501) + 1000;
                height = random.nextInt(1251) + 750;
                enemy.add(new Dragon(weight,height));
            }
        }

        for (Enemy enemys : enemy) {
            enemys.attack();
        }

    }
}