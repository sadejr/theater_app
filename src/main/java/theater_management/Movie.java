package theater_management;

import java.util.ArrayList;
import java.util.List;

public class Movie {
        private final String title;
        private final int releaseYear;
        private final String description;
        private final String genre;
        private final double rating;
        private final int numRatings;

        public Movie(String title, int releaseYear, String description, String genre,
                     double rating, int numRatings) {
            this.title = title;
            this.releaseYear = releaseYear;
            this.description = description;
            this.genre = genre;
            this.rating = rating;
            this.numRatings = numRatings;
        }


        public String getTitle() {
            return title;
        }

        public int getReleaseYear() {
            return releaseYear;
        }

        public String getDescription() {
            return description;
        }

        public String getGenre() {
            return genre;
        }

        public double getRating() {
            return rating;
        }

        public int getNumRatings() {
            return numRatings;
        }

}
