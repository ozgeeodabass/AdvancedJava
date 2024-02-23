package java8Features.StreamAPI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Product{
    private int id;
    private String name;
    private double price;

    public Product(int id,String name,double price){
        this.id=id;
        this.name=name;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

class Employee{
    int id;
    String name;
    int salary;

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
}

public class JavaStreamAPI {
    //NOTE: Make sure you know lambda expressions well before working with Stream
    //The Stream API is your best tool to process your in-memory data following a map/filter/reduce approach.
    public static void main(String[] args) {

        //FILTER METHOD
        //A filtering changes the number of objects; it does not change their type.
        //A filtering is modeled by the Predicate functional interface.


        List<Integer> numbers = List.of(10,15,20,25,30);
        //get even numbers with filter method
        List<Integer> evenNumbers = numbers.stream().filter(n -> n%2==0).collect(Collectors.toList());
        System.out.println(evenNumbers);
        //to directly print we can use forEach method after filtering
        numbers.stream().filter(n -> n%2==0).forEach(System.out::println);

        //filtering with multiple conditions
        List<String> names = List.of("Özge","Hasan","Şeyda","Emre","Mahmut","Leyla","Can","Mehmetcan");
        List<String> longNames = names.stream().filter(n -> n.length()>5&&n.length()<8).collect(Collectors.toList());
        System.out.println(longNames);

        //remove the null values
        List<String> words = Arrays.asList("word1","word2",null,"word3",null,"word4","word5",null);
        List<String> result = words.stream().filter(w -> w!=null).collect(Collectors.toList());
        System.out.println(result);

        //filtering with usage of objects in collection
        List<Product> products = Arrays.asList(new Product[]{new Product(1,"Kulaklık",1000), new Product(2,"Televizyon",25000), new Product(3,"Telefon",30000)});
        products.stream()
                .filter(p -> p.getPrice()>15000)
                .forEach(p -> System.out.println(p.getId()));


        //***************************************************************************************************************************************
        //MAP METHOD
        //map is an intermediate operation
        //A mapping consists of the transforming the objects or the values that you are processing
        //A mapping is modeled by the Function functional interface

        List<String> vehicles = Arrays.asList("bus","car","bicycle","train");
        //convert all vehicle names to uppercase
        List<String> vehiclesUpper = vehicles.stream().map(name -> name.toUpperCase()).collect(Collectors.toList());
        System.out.println(vehiclesUpper);


        List<String> vehiclesList = Arrays.asList("bus","car","bicycle","train");
        //print the lengths
        vehiclesList.stream().map(v->v.length()).forEach(System.out::println);

        List<Integer> numbersList = Arrays.asList(2,3,4,5,6);
        //read every value and multiply with 3
        List<Integer> multiplied3 = numbersList.stream().map(n -> n*3).collect(Collectors.toList());
        System.out.println(multiplied3);

        //FILTER AND MAP COMBINATION
        List<Employee> employees = Arrays.asList(new Employee[]{new Employee(1,"Özge",25000), new Employee(2, "Şeyda", 30000), new Employee(2, "Emre",32000), new Employee(3, "Hasan", 35000)});
        //get the list of salaries of employees whose length of the name is greater than 4
        List<Integer> salaries = employees.stream()
                .filter(e->e.name.length()>4)
                .map(e->e.salary)
                .collect(Collectors.toList());
        System.out.println(salaries);



    }
}
