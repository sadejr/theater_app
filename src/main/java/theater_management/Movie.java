package theater_management;

import java.util.ArrayList;
import java.util.List;

public class Movie {
        private final String title;
        private final String director;
        private final int duration;
        private final int releaseYear;
        private final String description;
        private final String genre;
        private final double rating;
        private final int numRatings;

        public Movie(String title, String director, int duration, int releaseYear, String description, String genre,
                     double rating, int numRatings) {
            this.title = title;
            this.director = director;
            this.duration = duration;
            this.releaseYear = releaseYear;
            this.description = description;
            this.genre = genre;
            this.rating = rating;
            this.numRatings = numRatings;
        }


        public String getTitle() {
            return title;
        }

        public String getDirector() {
            return director;
        }

        public int getDuration() {
            return duration;
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
