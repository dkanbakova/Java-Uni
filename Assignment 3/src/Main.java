import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;



public class Main {

    public static void main(String[] args) {
        try {
            // Адрес нашей базы данных "tsn_pg_demo" на локальном компьютере (localhost)
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5433/postgres";

            // Создание свойств соединения с базой данных
            Properties authorization = new Properties();
            authorization.put("user", "postgres"); // Зададим имя пользователя БД
            authorization.put("password", "12345"); // Зададим пароль доступа в БД

            // Создание соединения с базой данных
            Connection connection = DriverManager.getConnection(url, authorization);

            // Создание оператора доступа к базе данных
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            // Создание таблицы, если ее пока нет
            //statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (id SERIAL PRIMARY KEY, name VARCHAR(30) NOT NULL, surname VARCHAR(30) NOT NULL, age INTEGER default 0, email VARCHAR(30) NOT NULL)");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Choose some operation:");
                System.out.println("1 - Create");
                System.out.println("2 -Read");
                System.out.println("3 - Update");
                System.out.println("4 - Delete");
                System.out.println("5 - Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        createData(statement);
                        break;
                    case 2:
                        readData(statement);
                        break;
                    case 3:
                        updateData(statement);
                        break;
                    case 4:
                        deleteData(statement);
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (Exception e) {
            System.err.println("Error while accessing DataBase!");
            e.printStackTrace();
        }
    }

    private static void createData(Statement statement) throws SQLException {
        // Создание нового пользователя
        statement.executeUpdate("INSERT INTO users (id, name, surname, age, email) VALUES (4, 'Gulmira', 'Slay', 25, 'gulslay@gmail.com')");
        System.out.println("User created successfully!");
    }

    private static void readData(Statement statement) throws SQLException, ClassNotFoundException {
        // Чтение данных из таблицы и вывод в консоль
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        displayResultSet(resultSet);
    }

    private static void updateData(Statement statement) throws SQLException {
        // Обновление данных пользователя
        statement.executeUpdate("UPDATE users SET age = 20 WHERE name = 'Gulmira'");
        System.out.println("User was updated successfully!");
    }

    private static void deleteData(Statement statement) throws SQLException {
        // Удаление пользователя
        statement.executeUpdate("DELETE FROM users WHERE name = 'Gulmira'");
        System.out.println("User was deleted successfully!");
    }

    private static void displayResultSet(ResultSet resultSet) throws SQLException {
        resultSet.first();
        for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
            System.out.print(resultSet.getMetaData().getColumnName(j) + "\t\t");
        }
        System.out.println();

        resultSet.beforeFirst();
        while (resultSet.next()) {
            for (int j = 1; j <= resultSet.getMetaData().getColumnCount(); j++) {
                System.out.print(resultSet.getString(j) + "\t\t");
            }
            System.out.println();
        }
    }
}
