package theater_management;

import java.util.ArrayList;
import java.util.List;

public class Theater extends Venue {

    private final List<Movie> movies;
    private final List<Showtime> showtimes;
    private final boolean[][] seating;

    public Theater(String name, String location) {
        super(name, location);
        movies = new ArrayList<>();
        showtimes = new ArrayList<>();
        seating = new boolean[10][10];
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public void addShowtime(Showtime showtime) {
        showtimes.add(showtime);
    }

    public Showtime getShowtime(String title, String time, String date) {
        for (Showtime showtime : showtimes) {
            if (showtime.title().equalsIgnoreCase(title)
                    && showtime.time().equalsIgnoreCase(time)
                    && showtime.date().equalsIgnoreCase(date)) {
                return showtime;
            }
        }
        return null;
    }

    public Showtime getShowtimeByTitle(String title) {
        for (Showtime showtime : showtimes) {
            if (showtime.title().equalsIgnoreCase(title)) {
                return showtime;
            }
        }
        return null;
    }

    public List<Showtime> getAllShowtimes() {
        return showtimes;
    }

    public boolean buyTicket(String title, String time, String date, int row, int col) {
        Showtime showtime = getShowtime(title, time, date);
        if (showtime != null) {
            if (!seating[row - 1][col - 1]) {
                seating[row - 1][col - 1] = true;
                return true;
            }
        }
        return false;
    }

    public void printSeating(String title, String time, String date) {
        Showtime showtime = getShowtime(title, time, date);
        if (showtime != null) {
            boolean[][] seatStatus = new boolean[10][10];
            for (int i = 0; i < seatStatus.length; i++) {
                System.arraycopy(seating[i], 0, seatStatus[i], 0, seatStatus[i].length);
            }
            System.out.println("Seating for " + title + " at " + time + " on " + date + ":");
            for (boolean[] status : seatStatus) {
                for (boolean b : status) {
                    if (b) {
                        System.out.print("[X] ");
                    } else {
                        System.out.print("[O] ");
                    }
                }
                System.out.println();
            }
        }
    }
}
