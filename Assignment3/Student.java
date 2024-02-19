package Assignment3;
public class Student extends Person implements Payable{
    private double gpa;

    public double getGpa() {
        return gpa;
    }

    Student(String name, String surname, double gpa){
        super(name, surname);
        setGpa(gpa);
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return getPosition() + " "+ getId() + " " + getName() + " " + getSurname();
    }

    @Override
    public double getPaymentAmount() {
        return (gpa>2.67)?36660.00:0;
    }
}
