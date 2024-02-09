package java8Features;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

//for using in examples
class Product{
    int id;
    String name;
    int price;

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //other methods
    public int getPrice(){
        return this.price;
    }
}

public class LambdaExpressions {

        //lambda expressions comes with java8
        //three steps of define a lambda expr:
        //1-identify the type
        //2-find the right method
        //implement it

        //restrictions: it has to be a functional interface,
        // so an anonymous class that does not implement a functional interfac cannot be written as lambda expr.

        //functional interface -> is an interface that has only one abstract method. (it can be 1 or more other concrete methods)

        //lambda syntax has 3 parts:
        //1-block or parameters
        //2-an arrow (->)
        //3- block of code which is the body of the method

        Predicate<String> predicate = (String s) ->{ return s.length()==5;};
        //simplify the syntax
        Predicate<String> predicateSimplified =  s ->  s.length()==5;
        //because; compiler knows that you are implementing an abstract method of the Predicate interface.

        //calling the lambda expr;
        //example
         List<String> retainOnlyLength5 (List<String> list){
            Predicate<String> pred = s -> s.length()==5;
            List<String> retainedList = new ArrayList<>();
            for(String s: list){
                if (pred.test(s))      //<-- calling the abstr method will call the lambda
                    retainedList.add(s);
            }
            return  retainedList;
        }

        //* NOTE: Variables used in lambda should be final
        //Reason: lambdas cannot modify variables defined outside their body.
        //this is called capturing. Lambdas cannot capture variables, they can capture values.
        //Wrong Code:
        //int cartAmount=0;   //it can be final
         //Consumer<Product> consumer = product -> cartAmount+=product.getPrice();



    //There are 4 category of functional interfaces:
    //Suppliers
    //Comsumers
    //Predicates
    //Functions

    //********************Creating or Prodiving objects with Supplier<T> Interface***********************
    //a supplier does not take any arguments and returns an object.
    // it has no default or static method, just a plain get() method;
    //@FunctionalInterface
    //public interface Supplier<T> {
    //
    //    T get();
    //}

    //Implementation of this interface as lambda expression;
    Supplier<String> supplier = () -> "Hi there!";
    //suplier.get() will invoke the lambda

    //for ex: specialized form of supplier interface: IntSupplier
    //it is useful in boxing/unboxing operation between int and Integer
    Random random = new Random(314L);
    IntSupplier intSupplier = () -> random.nextInt();
    int integer = intSupplier.getAsInt();
    //The JDK gives you four of these specialized suppliers, to avoid unnecessary boxing / unboxing in your application;
    // IntSupplier, BooleanSupplier, LongSupplier and DoubleSupplier.

    //********************Consuming objects with Consumer<T> Interface***********************




    }

