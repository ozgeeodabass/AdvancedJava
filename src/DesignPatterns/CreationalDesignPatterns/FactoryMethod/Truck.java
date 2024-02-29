package DesignPatterns.CreationalDesignPatterns.FactoryMethod;

import java.sql.SQLOutput;

public class Truck implements Transport{
    @Override
    public void deliver() {
        System.out.println("Delivering with truck..");
    }
}
