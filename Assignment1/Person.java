public class Person{
    private String name;
    private int age;

    public Person(String _name, int _age){
        setName(_name);
        setAge(_age);
    }

    public void setName(String _name){
        name = _name;
    }
    public void setAge(int _age){
        age = _age;
    }

    public void printDetails(){
        System.out.println(getName() + ". " + getAge());
    }

    public String getName(){
        String infoName = "Name: " + name;
        return infoName;
    }
    public String getAge(){
        String infoAge = "Age: " + age;
        return infoAge;
    }


    public static void main(String[] args){
        Person person1 = new Person("Danel", 17);
        person1.printDetails();
    }
}
