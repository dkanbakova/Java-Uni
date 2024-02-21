public class Main {
    public static void main(String[] args) {
        try {
            Payment payment = PaymentFactory.create(PaymentMethod.APPLE_PAY);
            payment.pay(1000.0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}