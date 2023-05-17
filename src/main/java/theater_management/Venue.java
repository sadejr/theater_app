package theater_management;

public class Venue {
    private final String name;
    private final String location;

    public Venue(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

}
