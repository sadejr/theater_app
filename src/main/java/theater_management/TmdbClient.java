package theater_management;

import java.io.*;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TmdbClient {

    public static Movie getMovie() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the title of the movie: ");
        String title = input.nextLine();
        title = title.replace(" ", "-");

        System.out.print("Enter the director of the movie: ");
        String director = input.nextLine();

        System.out.print("Enter the duration of the movie (in minutes): ");
        int duration = input.nextInt();

        // Create a Movie object using the details obtained from the API
        return retrieveMovie(title, director, duration);
    }

    // Send a request to the API to get the movie details
    private static Movie retrieveMovie(String title, String director, int duration) {
        int releaseYear = 0;
        String description = "";
        double rating = 0.0;
        int numRatings = 0;

        // Specify the absolute path to the .env file
        String envFilePath = "src/main/java/theater_management/.env";

        // Load the environment variables from the .env file
        String apiKey = null;
        try (BufferedReader br = new BufferedReader(new FileReader(envFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] keyValue = line.split("=");
                if (keyValue.length == 2 && keyValue[0].equals("MOVIE_API_KEY")) {
                    apiKey = keyValue[1];
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to read the .env file");
            e.printStackTrace();
        }

        // Create an instance of the genreHashmap class
        genreHashmap genreMap = new genreHashmap();

        // Send a request to the API to get the movie details
        StringBuilder genres = null;
        try {
            URL url = new URL("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + title);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(content.toString());

            // Extract the movie details from the first result of the search
            JSONArray results = (JSONArray) json.get("results");

            if (results.size() > 0) {
                JSONObject movie = (JSONObject) results.get(0);

                // Extract the release year from the JSON response
                String release_date = (String) movie.get("release_date");
                releaseYear = Integer.parseInt(release_date.substring(0, 4));

                // Extract the movie description from the JSON response
                description = (String) movie.get("overview");

                // Extract the movie genres from the JSON response
                JSONArray genreIds = (JSONArray) movie.get("genre_ids");
                genres = new StringBuilder();
                for (Object id : genreIds) {
                    int genreId = ((Long) id).intValue();
                    String genreName = genreMap.genresMap.get(genreId);
                    genres.append(genreName).append(", ");
                }
                genres = new StringBuilder(genres.substring(0, genres.length() - 2)); // Remove the last comma and space


                // Extract the movie rating from the JSON response
                rating = (Double) movie.get("vote_average");

                // Extract the number of ratings for the movie from the JSON response
                numRatings = ((Long) movie.get("vote_count")).intValue();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Create a Movie object using the details obtained from the API
        if (genres == null) {
            throw new NullPointerException("Variable 'genres' is null.");
        }

        title = title.replace("-", " ");
        return new Movie(title, director, duration, releaseYear, description, genres.toString(), rating, numRatings);
    }
}