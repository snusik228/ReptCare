package com.example.reptcare;

public class Reptile {
    private static String name; //имя
    private static String difficulty; //сложность
    private static int satiety_level = 700; //сытость
    private static int thirst_level = 700; //жажда
    private static int happy_level = 700; //счастье
    private static int temperature_level = 700; //температура
    private static int moisture_level = 700; //влажность
    public static int coins = 0;
    boolean lamp = false;
    public Reptile(){}

    //Геттеры и Сеттеры (ну типа ООП)
    public void setName(String a){
        this.name = a;
    }
    public String getName(){
        return this.name;
    }
    public void setDifficulty(String a){
        this.difficulty = a;
    }
    public String getDifficulty(){
        return this.difficulty;
    }
    public void setSatiety_level(int a){
        if (a >= 0 && a <= 1000) this.satiety_level = a;
    }
    public int getSatiety_level(){
        return this.satiety_level;
    }
    public void setThirst_level(int a){
        if (a >= 0 && a <= 1000) this.thirst_level=a;
    }
    public int getThirst_level(){
        return this.thirst_level;
    }
    public void setHappy_level(int a){
        if (a >= 0 && a <= 1000) this.happy_level=a;
    }
    public int getHappy_level(){
        return this.happy_level;
    }
    public void setTemperature_level(int a){
        if (a >= 0 && a <= 1000) temperature_level = a;
    }
    public int getTemperature_level(){
        return temperature_level;
    }
    public void setMoisture_level(int a){
        if (a >= 0 && a <= 1000) this.moisture_level = a;
    }
    public int getMoisture_level(){
        return this.moisture_level;
    }


    public void play(){
        this.happy_level += 1;
    }
    public void switchLamp(){
        this.lamp = !this.lamp;
    }

}
