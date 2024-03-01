import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reservation reservation = new Reservation();
        ReviewSystem reviewSystem = new ReviewSystem();
        Tables squareTable1 = new SquareTable(4);
        Tables roundTable1 = new RoundTable(6);
        Tables squareTable2 = new SquareTable(8);
        squareTable1.assigningTables();
        roundTable1.assigningTables();
        squareTable2.assigningTables();
        squareTable1.assigningTables();



        MenuDataSource menuDataSource = new DatabaseMenuDataSource();
        TheRest theRest = new TheRest("The Rest", "Location", "Cuisine", 100, "9:00 AM", "10:00 PM", menuDataSource);

        while (true) {
            System.out.println("\nWelcome to the Reservation System!");
            System.out.println("1. Make a reservation");
            System.out.println("2. View all reservations");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Leave a review");
            System.out.println("5. View reviews");
            System.out.println("6. Menu");
            System.out.println("7. View table types");
            System.out.println("8. Delivery food");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter your first name: ");
                    String firstName = scanner.next();
                    System.out.print("Enter your last name: ");
                    String lastName = scanner.next();
                    System.out.print("Enter your contact number: ");
                    int contact = scanner.nextInt();
                    System.out.print("Enter party size: ");
                    int partySize = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter date and time (YYYY-MM-DD HH:MM): ");
                    String dateTimeString = scanner.nextLine();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date dateTime = null;
                    boolean validDateTime = false;

                    while (!validDateTime) {
                        try {
                            dateTime = dateFormat.parse(dateTimeString);
                            validDateTime = true;
                        } catch (ParseException e) {
                            System.out.println("Invalid date and time format. Please use the format YYYY-MM-DD HH:MM");
                            System.out.print("Enter date and time again: ");
                            dateTimeString = scanner.nextLine();
                        }
                    }
                    try {
                        reservation.createReservation(firstName, lastName, dateTime, partySize, contact);
                    } catch (SQLException e) {
                        System.out.println("Error creating reservation: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("\nReservation details:");
                    try {
                        ArrayList<ArrayList<String>> reservations = reservation.getReservations();
                        for (ArrayList<String> reservationDetails : reservations) {
                            System.out.println("Reservation ID: " + reservationDetails.get(4));
                            System.out.println("Date and Time: " + reservationDetails.get(2));
                            System.out.println("Party Size: " + reservationDetails.get(3));
                            System.out.println("Client: " + reservationDetails.get(0) +" " + reservationDetails.get(1) );
                            System.out.println();
                        }
                    } catch (SQLException e) {
                        System.out.println("Error retrieving reservations: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter contact number to cancel: ");
                    try {
                        contact = scanner.nextInt();
                        reservation.cancelReservation(contact);
                    } catch (SQLException e) {
                        System.out.println("Error canceling reservation: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter review text: ");
                    String reviewText = scanner.nextLine();
                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    scanner.nextLine();

                    ReviewBuilder builder = new ReviewBuilder();
                    builder.setText(reviewText).setRating(rating);
                    Review review = builder.build();

                    reviewSystem.leaveReview(review);
                    break;

                case 5:
                    List<Review> reviews = reviewSystem.viewReviews();
                    if (!reviews.isEmpty()) {
                        for (Review review1 : reviews) {
                            System.out.println(review1);
                        }
                    } else {
                        System.out.println("No reviews found.");
                    }
                    break;

                case 6:
                    theRest.displayMenu();
                    break;

                case 7:
                    System.out.print("Enter table type (round or square): ");
                    String tableType = scanner.nextLine();

                    System.out.print("Enter table capacity: ");
                    int capacity = scanner.nextInt();

                    Tables table = null;

                    switch (tableType.toLowerCase()) {
                        case "round":
                            table = new RoundTable(capacity);
                            break;
                        case "square":
                            table = new SquareTable(capacity);
                            break;
                        default:
                            System.out.println("Invalid table type.");
                            return;
                    }

                    table.assigningTables();
                    break;
                case 8:
                    System.out.println("Choose delivery method:");
                    System.out.println("1. Delivery");
                    System.out.println("2. Self Pick Up");
                    System.out.print("Enter your choice: ");
                    int deliveryChoice = scanner.nextInt();
                    scanner.nextLine();
                    switch (deliveryChoice) {
                        case 1:
                            RestaurantDelivery restaurantDelivery = new RestaurantDelivery(30);
                            restaurantDelivery.requestOrderInfo(scanner);
                            restaurantDelivery.deliver();
                            break;
                        case 2:
                            SelfPickupDelivery selfPickup = new SelfPickupDelivery(0);
                            selfPickup.requestOrderInfo(scanner);
                            selfPickup.deliver();
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

                case 9:
                    System.out.println("Exiting");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}