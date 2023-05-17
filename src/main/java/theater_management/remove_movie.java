package theater_management;

import java.util.Iterator;
import java.util.List;

public class remove_movie {
    public boolean remove_movie(String title, List<Movie> movies) {
        Iterator<Movie> iterator = movies.iterator();
        while (iterator.hasNext()) {
            Movie movie = iterator.next();
            if (movie.getTitle().equals(title)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

}
