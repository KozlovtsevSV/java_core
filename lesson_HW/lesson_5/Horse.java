package ru.geekbrains.lesson_5;

public class Horse extends Animals {


    // не стандартная лошадь
    Horse(float distanceRun, float heightJump, float distanceSwim){
        super("Лошадь", distanceRun, distanceSwim, heightJump);
    }

    // стандартная лошадь
    Horse(){
        super("Лошадь",1500f, 3f, 100f);
    }

}
