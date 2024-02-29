package DesignPatterns.CreationalDesignPatterns.FactoryMethod;

import java.util.Scanner;

public class ClientCode  {

    public static Logistics initialize(){
        Logistics logistics = null;
        System.out.println("How will the delivery be made? Please enter 'ship' or 'truck'.");
        Scanner scanner = new Scanner(System.in);
        String deliveryMethod= scanner.next();

        if(deliveryMethod.equals("ship")){
            logistics=new SeaLogistics();
        }else if(deliveryMethod.equals("truck")){
            logistics= new RoadLogistics();
        }
        return logistics;
    }


    public static void main(String[] args) {

        Logistics l = ClientCode.initialize();
        l.planDelivery();

    }
}
