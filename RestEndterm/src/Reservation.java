import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Reservation {
    private static final String DB_URL = "jdbc:postgresql://localhost:5433/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "12345";

    public ArrayList<ArrayList<String>> reservations;
    public Reservation() {
        this.reservations = new ArrayList<>();
    }
    public void createReservation(String firstName, String lastName, Date dateTime, int partySize, int contact) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "INSERT INTO Reservation (first_name, last_name, date_time, party_size, contact, status) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setTimestamp(3, new Timestamp(dateTime.getTime()));
            statement.setInt(4, partySize);
            statement.setInt(5, contact);
            statement.setBoolean(6, true);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reservation created successfully!");
            } else {
                System.out.println("Error creating reservation. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database or executing query: " + e.getMessage());
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ArrayList<ArrayList<String>> getReservations() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "SELECT * FROM Reservation";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            reservations = new ArrayList<>();
            while (resultSet.next()) {
                ArrayList<String> reservationDetails = new ArrayList<>();
                reservationDetails.add(resultSet.getString("first_name"));
                reservationDetails.add(resultSet.getString("last_name"));
                reservationDetails.add(String.valueOf(resultSet.getTimestamp("date_time")));
                reservationDetails.add(String.valueOf(resultSet.getInt("party_size")));
                reservationDetails.add(String.valueOf(resultSet.getInt("contact")));
                reservationDetails.add(String.valueOf(resultSet.getBoolean("status")));
                reservations.add(reservationDetails);
            }

            return reservations;
        } catch (SQLException e) {
            System.out.println("Error connecting to database or executing query: " + e.getMessage());
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    public void cancelReservation(int contact) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            String sql = "DELETE FROM Reservation WHERE contact = ?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, contact);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reservation canceled successfully!");
            } else {
                System.out.println("No reservation found with that contact number.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database or executing query: " + e.getMessage());
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
