import java.text.MessageFormat;

public class PayPalPayment implements Payment{
    @Override
    public void pay(double amount) {
        System.out.println(MessageFormat.format("Succesfully paid ${0} to merchant using PayPal", amount));
    }
}