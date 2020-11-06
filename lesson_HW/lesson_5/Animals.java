package ru.geekbrains.lesson_5;

public class Animals {

    // Сделаем все private что бы не возможно было поменять значения, для улучшения животных можно написать get метобы тренировки :)
    private String name;
    private float distanceRun;
    private float heightJump;
    private float distanceSwim;

    public Animals(String name, float distanceRun, float heightJump, float distanceSwim){
        this.name = name;
        this.distanceRun = distanceRun;
        this.heightJump = heightJump;
        this.distanceSwim = distanceSwim;
    }

    public Animals(String name, float distanceRun, float heightJump){
        this.name = name;
        this.distanceRun = distanceRun;
        this.heightJump = heightJump;
    }

    public String animalsRun(float distanceRun){
        if(distanceRun <= this.distanceRun){
            return "Животное " + name + " пробежало: " + distanceRun + " метров";
        }
        else {
            return "Животное " + name + " не пробежало: " + distanceRun + " метров, максимальная дистанция: "+ this.distanceRun;
        }
    }

    public String animalsJump(float heightJump){
        if(heightJump <= this.heightJump){
            return "Животное " + name + " подпрыгнуло на : " + heightJump + " метров";
        }
        else {
            return "Животное " + name + " не подпрыгнуло на : " + heightJump + " метров, максимальная высота прыжка : "+ this.heightJump;
        }

    }

    public String animalsSwim(float distanceSwim){
        if(distanceSwim <= this.distanceSwim){
            return "Животное " + name + " проплыло: " + distanceSwim + " метров";
        }
        else {
            return "Животное " + name + " не проплыло: " + distanceSwim + " метров, максимальная дистанция: "+ this.distanceSwim;
        }
    }

    public String getName() {
        return name;
    }

    public float getDistanceRun() {
        return distanceRun;
    }

    public float getHeightJump() {
        return heightJump;
    }

    public float getDistanceSwim() {
        return distanceSwim;
    }
}
