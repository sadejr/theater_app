package theater_management;

import java.util.HashMap;

public class GenreMapper {

    // Declare the HashMap
    private HashMap<Integer, String> genresMap;

    public GenreMapper() {
        // Initialize the HashMap
        genresMap = new HashMap<>();

        // Populate the HashMap with genre IDs and names
        genresMap.put(28, "Action");
        genresMap.put(12, "Adventure");
        genresMap.put(16, "Animation");
        genresMap.put(35, "Comedy");
        genresMap.put(80, "Crime");
        genresMap.put(99, "Documentary");
        genresMap.put(18, "Drama");
        genresMap.put(10751, "Family");
        genresMap.put(14, "Fantasy");
        genresMap.put(36, "History");
        genresMap.put(27, "Horror");
        genresMap.put(10402, "Music");
        genresMap.put(9648, "Mystery");
        genresMap.put(10749, "Romance");
        genresMap.put(878, "Science Fiction");
        genresMap.put(10770, "TV Movie");
        genresMap.put(53, "Thriller");
        genresMap.put(10752, "War");
        genresMap.put(37, "Western");
    }

    public String getName(int id) {
        return genresMap.get(id);
    }
}
