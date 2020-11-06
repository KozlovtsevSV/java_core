package ru.geekbrains.lesson_5;

public class Dog extends Animals {

    // не стандартный собакен
    public Dog(float distanceRun, float distanceSwim, float heightJump){
        super("Собака", distanceRun, heightJump, distanceSwim);
    }

    // стандартный собакен
    public Dog(){
        super("Собака",500f, 0.5f, 10f);
    }

}
