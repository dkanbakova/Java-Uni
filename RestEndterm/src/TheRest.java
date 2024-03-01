import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class TheRest {

    private String name;
    private String location;
    private String cuisine;
    private int capacity;
    private String openingHours;
    private String closingHours;
    private Map<String, String> menu;

    public TheRest(String name, String location, String cuisine, int capacity, String openingHours, String closingHours, MenuDataSource menuDataSource) {
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        this.capacity = capacity;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.menu = menuDataSource.getMenuData();
    }

    public TheRest(String name, MenuDataSource databaseMenuDataSource) {
        this.name = name;
        this.menu = databaseMenuDataSource.getMenuData();
    }

    public TheRest(String theRest, String s, String italian, int i, String time, String time1) {
    }

    public boolean checkAvailability(String date, String time, int partySize) {
        return true;
    }

    public void displayMenu() {
        System.out.println("Menu for " + name + ":");
        for (Map.Entry<String, String> entry : menu.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public String getOpeningHours() {
        return "Opening hours: " + openingHours + " - " + closingHours;
    }

    public void getMenuData(String dishName, String description) {
        menu.put(dishName, description);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Map<String, String> getMenu() {
        return menu;
    }

    public void setMenu(Map<String, String> menu) {
        this.menu = menu;
    }

}
