package ru.geekbrains.lesson_5;

public class Bird extends Animals{

    // не стандартная птица
    public Bird(float distanceRun, float heightJump){
        super("Птица", distanceRun, heightJump);
    }

    // не стандартная птица умеет плавать
    public Bird(float distanceRun, float heightJump, float distanceSwim){
        super("Птица", distanceRun, heightJump, distanceSwim);
    }

    // стандартная птица
    public Bird(){
       super("Птица",5f, 0.2f);

    }

    @Override
    public String animalsSwim(float distanceSwim){
        // проверяем что животное совсем не умеет плавать это конечно можно сделать в классе Animals, но для попробовать переопределение попробуем
        if (getDistanceSwim() == 0){
            return "Животное " + getName() + " не умеет плавать";
        }
        else{
            return super.animalsSwim(distanceSwim);
        }
    }

}
