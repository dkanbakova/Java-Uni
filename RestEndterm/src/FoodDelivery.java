import java.sql.*;
import java.util.Scanner;

abstract class FoodDelivery {
    protected int deliveryTime;
    private boolean isDelivered;
    interface DeliveryFactory {
        FoodDelivery createDelivery(int deliveryTime);
    }
    public FoodDelivery(int deliveryTime) {
        this.deliveryTime = deliveryTime;
        this.isDelivered = false;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }
    public boolean isDelivered() {
        return isDelivered;
    }
    public void setDelivered(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }
    public abstract void deliver();
    interface FoodDeliveryFactory {
        FoodDelivery createDelivery(int deliveryTime);
    }
    public void saveToDatabase(String orderInfo, String deliveryAddress, String clientPhoneNumber) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "12345");
            String sql = "INSERT INTO deliveries (delivery_time, order_info, delivery_address, client_phone_number) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, deliveryTime);
            statement.setString(2, orderInfo);
            statement.setString(3, deliveryAddress);
            statement.setString(4, clientPhoneNumber);

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}