import java.util.Scanner;

class SelfPickupDelivery extends FoodDelivery {
    private String order;
    private String clientPhoneNumber;

    public SelfPickupDelivery(int deliveryTime) {
        super(deliveryTime);
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    class SelfPickupDeliveryFactory implements DeliveryFactory {
        @Override
        public FoodDelivery createDelivery(int deliveryTime) {
            return new SelfPickupDelivery(deliveryTime);
        }
    }

    @Override
    public void deliver() {
        System.out.println("Self Pickup...");
        System.out.println("Order: " + order);
        System.out.println("Client Phone Number: " + clientPhoneNumber);
        setDelivered(true); 
        System.out.println("Thank you for your order! Enjoy your meal!");
    }

    public void requestOrderInfo(Scanner scanner) {
        System.out.print("Enter your order: ");
        this.order = scanner.nextLine();
        System.out.print("Enter client phone number: ");
        this.clientPhoneNumber = scanner.nextLine();
    }
}