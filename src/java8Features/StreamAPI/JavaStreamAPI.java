package java8Features.StreamAPI;
import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.*;
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

        //***************************************************************************************************************************************
        //FLATMAP METHOD
        //Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream produced by applying the provided mapping function to each element.
        //NOTE: map works in one-to-one manner, but flatmap works in one-to-many manner.

        List<Integer> list1= Arrays.asList(1,2);
        List<Integer> list2= Arrays.asList(3,4);
        List<Integer> list3= Arrays.asList(5,6);
        List<List<Integer>> finalList = Arrays.asList(list1,list2,list3);
        System.out.println(finalList);
        List<Integer> finalResult= finalList.stream()
                .flatMap(x -> x.stream().map(n->n+10))
                .collect(Collectors.toList());
        System.out.println(finalResult);


        List<String> teamA = Arrays.asList("Scott","John","April");
        List<String> teamB = Arrays.asList("Jony","Kitty","Ken");
        List<String> teamC = Arrays.asList("Chen","Jary","Lina");
        List<List<String>> listOfAllTeams = Arrays.asList(teamA,teamB,teamC);
        //get the people whoes names starts with 'J' in each team
        List<String> filteredResult = listOfAllTeams.stream()
                .flatMap(l->l.stream().filter(n->n.startsWith("J")))
                .collect(Collectors.toList());
        System.out.println(filteredResult);

        //***************************************************************************************************************************************
        //OTHER METHODS

        //distinct method -> non-terminal method
        List<Integer> ids = Arrays.asList(1,2,3,1,3,4,4,5,7);
        //find distinct ids
        List<Integer> distinctIds= ids.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctIds);

        //count -> terminal method
        //find the num of elements of distinct ids
        long count = distinctIds.stream().count();
        System.out.println("Count of distinct ids: " + count);

        //limit -> non-terminal method
        //i want to collect some limited objects from ids list
        List<Integer> limited4 = ids.stream().limit(4).collect(Collectors.toList());
        System.out.println("Limited 4 objects: "+limited4);

        //min and max methods -> terminal methods
        //get max id
        Optional<Integer> maxId = ids.stream().max((id1, id2)->{return id1.compareTo(id2);});
        System.out.println(maxId.get());

        //reduce method -> terminal method
        //reduce -> Performs a reduction on the elements of this stream, using an associative accumulation function, and returns an Optional describing the reduced value, if any.
        List<String> stringList = Arrays.asList("A","B","C","1","2","3");
        Optional<String> reduced= stringList.stream().reduce((value,combinedvalue)->{
            return combinedvalue+value;
        });
        System.out.println("Reduced value: " + reduced.get());

        //Note: non-terminal operations returns stream

        //sorted method -> non-terminal method, sorts the objects in the stream
        List<Integer> listNotSorted = Arrays.asList(2,4,1,3,7,5,9,1);
        List<Integer> listSorted = listNotSorted.stream().sorted((i1,i2)->{return i1.compareTo(i2);}).collect(Collectors.toList());
        System.out.println("Sorted list: "+listSorted);
        //how to sort in reversed order?
        List<Integer> listSortedReverse = listNotSorted.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("Reversed Sorted List: " + listSortedReverse);

        //anyMatch -> Returns whether any elements of this stream match the provided predicate.
        //allMatch -> Returns whether all elements of this stream match the provided predicate.
        //noneMatch -> Returns whether no elements of this stream match the provided predicate.
        Set<String> fruites = new HashSet<String>();
        fruites.addAll(List.of("one apple","one mango","two grapes","two apples","two orange"));
        boolean anyMatchResult = fruites.stream().anyMatch(f->f.startsWith("one"));
        System.out.println("Is there any fruit that we have only one of it: " + anyMatchResult);
        boolean allMatchResult = fruites.stream().allMatch(f->f.startsWith("two"));
        System.out.println("Are there two of each of our fruits: " + allMatchResult);
        boolean noneMatchResult = fruites.stream().noneMatch(f->f.startsWith("three"));
        System.out.println("Isn't there any fruit that we has 3 of it: " + noneMatchResult);

        //findFirst -> terminal method, returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty.
        Optional<String> first = fruites.stream().findFirst();
        System.out.println("First fruit: " + first.get());
        //findAny -> terminal method, returns an Optional describing some element of the stream, or an empty Optional if the stream is empty.
        Optional<String> any = fruites.stream().findAny();
        System.out.println("Any fruit: " + any.get());




    }
}
