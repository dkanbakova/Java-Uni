import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseMenuDataSource implements MenuDataSource {
    @Override
    public Map<String, String> getMenuData() {
        Map<String, String> menuData = new HashMap<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "12345");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM menu");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String dishName = resultSet.getString("dish_name");
                String description = resultSet.getString("dish_description");
                menuData.put(dishName, description);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuData;
    }
}
