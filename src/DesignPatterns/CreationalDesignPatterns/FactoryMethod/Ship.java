package DesignPatterns.CreationalDesignPatterns.FactoryMethod;

public class Ship implements Transport{
    @Override
    public void deliver() {
        System.out.println("Delivering with ship..");
    }
}
