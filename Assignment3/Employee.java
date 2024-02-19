package Assignment3;
public class Employee extends Person{
    private String position;
    private double salary;

    Employee(String name, String surname, String position, double salary){
        super(name, surname);
        setPosition(position);
        setSalary(salary);
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee:" + getId() + ". " + getName() + " " + getSurname();
    }

    @Override
    public double getPaymentAmount() {
        return salary;
    }
}
