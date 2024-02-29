package DesignPatterns.CreationalDesignPatterns.FactoryMethod;

public abstract class Logistics {

    Transport transport;

    public void planDelivery(){
        transport = createTransport();
        transport.deliver();
    }

    public abstract Transport createTransport(); //factory method

}
