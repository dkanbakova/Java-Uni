package Assignment3;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        Employee employee1 = new Employee("John", "Lennon", "Manager", 27045.78);
        Employee employee2 = new Employee("George", "Harriston", "Developer", 50000.00);
        Student student1 = new Student("Ringo", "Starr", 2.8);
        Student student2 = new Student("Paul", "McCartney", 3.6);

        ArrayList<Person> listOfPeople = new ArrayList<Person>();
        listOfPeople.add(employee1);
        listOfPeople.add(employee2);
        listOfPeople.add(student1);
        listOfPeople.add(student2);

        Collections.sort(listOfPeople);
        printData(listOfPeople);
    }
    public static void printData(Iterable<Person> people){
        for(Person person: people){
            if(person instanceof Employee){
                System.out.println(person.toString() + " earns " + ((Employee) person).getPaymentAmount() + "tenge");
            } else if(person instanceof Student){
                System.out.println(person.toString() + " earns " + ((Student) person).getPaymentAmount() + "tenge");
            }
        }
    }
}