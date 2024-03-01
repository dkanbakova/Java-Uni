import java.util.Scanner;

class RestaurantDelivery extends FoodDelivery {
    private String order;
    private String deliveryAddress;
    private String clientPhoneNumber;

    public RestaurantDelivery(int deliveryTime) {
        super(deliveryTime);
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    class RestaurantDeliveryFactory implements DeliveryFactory {
        @Override
        public FoodDelivery createDelivery(int deliveryTime) {
            return new RestaurantDelivery(deliveryTime);
        }
    }

    @Override
    public void deliver() {
        System.out.println("Delivery by restaurant...");
        System.out.println("Order: " + order);
        System.out.println("Delivery Address: " + deliveryAddress);
        System.out.println("Client Phone Number: " + clientPhoneNumber);
        setDelivered(true);
        System.out.println("Thanks for the order! Your order will be delivered as soon as it is ready, wait and enjoy the meal!");
    }
    public void requestOrderInfo(Scanner scanner) {
        System.out.print("Enter your order: ");
        this.order = scanner.nextLine();
        System.out.print("Enter delivery address: ");
        this.deliveryAddress = scanner.nextLine();
        System.out.print("Enter client phone number: ");
        this.clientPhoneNumber = scanner.nextLine();
    }
}