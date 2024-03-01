import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewSystem {

    private static final String DB_URL = "jdbc:postgresql://localhost:5433/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "12345";

    public void leaveReview(Review review) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Review (Text_review, Score) VALUES (?, ?)")) {

            statement.setString(1, review.getText());
            statement.setInt(2, review.getRating());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Review submitted successfully!");
            } else {
                System.out.println("Error submitting review. Please try again.");
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database or executing query: " + e.getMessage());
            throw new RuntimeException("Failed to leave review", e);
        }
    }

    public List<Review> viewReviews() {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Review")) {

            while (resultSet.next()) {
                String text = resultSet.getString("Text_review");
                int rating = resultSet.getInt("Score");
                reviews.add(new Review(text, rating));
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database or executing query: " + e.getMessage());
        }
        return reviews;
    }
}