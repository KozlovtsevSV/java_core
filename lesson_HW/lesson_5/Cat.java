package ru.geekbrains.lesson_5;

public class Cat extends Animals {

    // не стандартный Кот
    public Cat(float distanceRun, float heightJump){
        super("Кот", distanceRun, heightJump);
    }

    // стандартный Кот
    public Cat(){
        super("Кот",200f, 2f);

    }

    // не будем у кота использовать возможность переопределения в классе Main попробуем обработать с помощью instanceof
//    @Override
//    public String animalsSwim(float distanceSwim){
//        return "Животное " + name + " не умеет плавать";
//    }

}
