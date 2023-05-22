package theater_management;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the theater name: ");
        String theaterName = input.nextLine();

        System.out.print("Enter the theater location: ");
        String theaterLocation = input.nextLine();

        Theater theater = new Theater(theaterName, theaterLocation);

        int choice;
        while(true){
            System.out.println("Welcome to the " + theater.getName() + " at " + theater.getLocation() + "!");
            System.out.println("""
                    1. Add a movie
                    2. Remove a movie
                    3. View a movie
                    4. View all movies
                    5. Add a showtime
                    6. Remove a showtime
                    7. View a showtime
                    8. View all showtimes
                    9. Buy a ticket
                    10. View seating
                    0. Exit""");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            switch(choice) {
                case 0:
                    System.out.println("Goodbye");
                    System.exit(0);

                case 1:
                    TmdbClient tmdbClient = new TmdbClient();
                    Movie movie = tmdbClient.getMovie();
                    if (movie != null) {
                        theater.addMovie(movie);
                        System.out.println("Movie added successfully!\n");
                    }
                    break;

                case 2:
                    input.nextLine(); // consume any leftover input in buffer
                    System.out.print("Enter the title of the movie you want to remove: ");
                    String titleToRemove = input.nextLine();
                    List<Movie> movies = theater.getAllMovies();
                    boolean isRemoved = new MovieService().removeMovie(titleToRemove, movies);
                    if (isRemoved) {
                        System.out.println(titleToRemove + " has been removed from the list.");
                    } else {
                        System.out.println(titleToRemove + " was not found in the list.");
                    }
                    break;

                case 3:
                    input.nextLine(); // consume any leftover input in buffer
                    System.out.print("Enter the title of the movie you want to view: ");
                    String titleToView = input.nextLine();
                    System.out.println();
                    List<Movie> allMovies = theater.getAllMovies();
                    boolean found = false;
                    for (Movie newMovie : allMovies) {
                        if (newMovie.getTitle().equalsIgnoreCase(titleToView)) {
                            System.out.println("Title: " + newMovie.getTitle());
                            System.out.println("Director: " + newMovie.getDirector());
                            System.out.println("Release Year: " + newMovie.getReleaseYear());
                            System.out.println("Review: " + newMovie.getDescription());
                            System.out.println("Genre: " + newMovie.getGenre());
                            System.out.println("Duration: " + newMovie.getDuration());
                            System.out.println("Rating: " + newMovie.getRating());
                            System.out.println("Number of Ratings: " + newMovie.getNumRatings());
                            System.out.println();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println(titleToView + " was not found in the list.");
                    }
                    break;

                case 4:
                    System.out.println();
                    List<Movie> movieList = theater.getAllMovies();
                    for (Movie newMovie : movieList) {
                        System.out.println("Title: " + newMovie.getTitle());
                        System.out.println("Director: " + newMovie.getDirector());
                        System.out.println("Release Year: " + newMovie.getReleaseYear());
                        System.out.println("Review: " + newMovie.getDescription());
                        System.out.println("Genre: " + newMovie.getGenre());
                        System.out.println("Duration: " + newMovie.getDuration());
                        System.out.println("Rating: " + newMovie.getRating());
                        System.out.println("Number of Ratings: " + newMovie.getNumRatings());
                        System.out.println();
                    }
                    break;

                case 5:
                    input.nextLine(); // consume any leftover input in buffer
                    System.out.print("Enter the title of the movie: ");
                    String title = input.nextLine();
                    System.out.print("Enter the time of the showtime (e.g. 7:00 PM): ");
                    String time = input.nextLine();
                    System.out.print("Enter the date of the showtime (e.g. 2022-12-15): ");
                    String date = input.nextLine();
                    System.out.print("Enter the ticket price for the showtime: ");
                    double ticketPrice = input.nextDouble();
                    Showtime showtime = new Showtime(title, time, date, ticketPrice);
                    theater.addShowtime(showtime);
                    break;

                case 6:
                    input.nextLine(); // consume any leftover input in buffer

                    System.out.print("Enter the title of the movie: ");
                    String titleToBeRemoved = input.nextLine();
                    System.out.print("Enter the date of the showtime (e.g. 2022-12-15): ");
                    String dateToBeRemoved = input.nextLine();
                    System.out.print("Enter the time of the showtime (e.g. 7:00 PM): ");
                    String timeToBeRemoved = input.nextLine();

                    Showtime showtimeToRemove = null;
                    List<Showtime> showtimes = theater.getAllShowtimes();
                    for (Showtime showtimeToBeRemoved : showtimes) {
                        if (showtimeToBeRemoved.title().equalsIgnoreCase(titleToBeRemoved) &&
                                showtimeToBeRemoved.date().equals(dateToBeRemoved) &&
                                showtimeToBeRemoved.time().equals(timeToBeRemoved)) {
                            showtimeToRemove = showtimeToBeRemoved;
                            break;
                        }
                    }
                    if (showtimeToRemove != null) {
                        showtimes.remove(showtimeToRemove);
                        System.out.println("Showtime removed successfully.");
                    } else {
                        System.out.println("No showtime found with the specified title, date, and time.");
                    }
                    break;

                case 7:
                    input.nextLine(); // consume any leftover input in buffer
                    System.out.print("Enter the title of the movie to view showtimes: ");
                    String movieTitle = input.nextLine();
                    System.out.println();
                    showtimes = theater.getAllShowtimes();
                    if (showtimes.isEmpty()) {
                        System.out.println("There are no showtimes available for " + movieTitle + ".");
                    } else {
                        System.out.println("Showtimes for " + movieTitle + ":");
                        for (Showtime movie_showtime : showtimes) {
                            System.out.println("Time: " + movie_showtime.time());
                            System.out.println("Date: " + movie_showtime.date());
                            System.out.println("Price: " + String.format("%.1f", movie_showtime.price()));
                            System.out.println();
                        }
                    }
                    break;

                case 8:
                    System.out.println();
                    List<Showtime> allShowtimes = theater.getAllShowtimes();
                    if (allShowtimes.isEmpty()) {
                        System.out.println("There are no showtimes available.");
                    } else {
                        for (Showtime movie_showtimes : allShowtimes) {
                            System.out.println("Movie: " + movie_showtimes.title());
                            System.out.println("Time: " + movie_showtimes.time());
                            System.out.println("Date: " + movie_showtimes.date());
                            System.out.println("Price: " + String.format("%.1f", movie_showtimes.price()));
                            System.out.println();
                        }
                    }
                    break;

                case 9:
                    input.nextLine(); // consume any leftover input in buffer
                    System.out.print("Enter the movie title: ");
                    String title_ticket = input.nextLine();
                    System.out.print("Enter the showtime (e.g. 7:00 PM): ");
                    String time_ticket = input.nextLine();
                    System.out.print("Enter the showtime (e.g. 2022-12-15): ");
                    String date_ticket = input.nextLine();

                    // Find the Showtime object
                    Showtime selectedShowtime = theater.getShowtime(title_ticket, time_ticket, date_ticket);

                    if (selectedShowtime == null) {
                        System.out.println("Showtime not found.");
                    } else {
                        // Ask user to select a seat
                        System.out.print("Enter the seat (row and column): ");
                        int row = input.nextInt();
                        int column = input.nextInt();

                        // Buy the ticket
                        boolean ticketBought = theater.buyTicket(title_ticket, time_ticket, date_ticket, row, column);
                        if (ticketBought) {
                            System.out.println("Ticket bought!");
                        } else {
                            System.out.println("Seat not available.");
                        }
                    }
                    break;

                case 10:
                    input.nextLine(); // consume any leftover input in buffer
                    System.out.print("Enter the movie title: ");
                    String title_seating = input.nextLine();
                    System.out.print("Enter the showtime (e.g. 7:00 PM): ");
                    String time_seating = input.nextLine();
                    System.out.print("Enter the showtime (e.g. 2022-12-15): ");
                    String date_seating = input.nextLine();
                    theater.printSeating(title_seating, time_seating, date_seating);
                    break;

                default:
                    System.out.println("This is not a valid menu choice!");
                    break;
            }
        }
    }
}
