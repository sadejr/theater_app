package theater_management;

public class Showtime {
    private String title;
    private String time;
    private String date;
    private double price;
    private boolean[][] seating;
    private boolean initialized; // Flag to track initialization status

    public Showtime(String title, String time, String date, double price) {
        this.title = title;
        this.time = time;
        this.date = date;
        this.price = price;
        this.initialized = false; // Initially not initialized
    }

    public void initializeSeating() {
        seating = new boolean[10][10];
        initialized = true; // Set the flag to true after initialization
    }

    public boolean isInitialized() {
        return initialized;
    }

    public boolean[][] getSeating() {
        return seating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
