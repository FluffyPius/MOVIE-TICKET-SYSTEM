import java.util.List; //   handles ordered sequences of items
import java.util.ArrayList;

//Adding movies to the system.
//Adding screenings.
//Retrieving all available movies.
//Finding a movie using its movieID.
//Finding screenings associated with a particular movie.

public class MovieService {

    private List<Movie> movies; // LISt represents an ordered collection of objects belonging to the class
    private List<Screening> screenings; // having a collection of screening objects
    // lists can change size unlike arrays

    // constructor initializes the empty list
    public MovieService() {
        this.movies = new ArrayList<>();
        this.screenings = new ArrayList<>();
        // we are intializing the collections to empty arraylists
        // when an object is created from this class, new empty movies & screening
        // collections are created
    }

    // this method adds the movie object in the "movies" collection
    public void addMovie(Movie movie) {
        movies.add(movie); // with this, when called, a movie object is added to the list
        // ArrayLists allow duplicates so the same movie can be added twice
    }

    // this method adds the screening object in the "screenings" List
    public void addScreening(Screening screening) {
        screenings.add(screening);
    }

    // we want to return the Movies stored in the list when the method is called
    public List<Movie> getAllMovies() {
        return movies;
    }

    // returning a specific movie from the list using its movie ID
    public Movie findMovieByID(String movieID) {
        // we use an enhanced for loop to loop through the entire list to look for a
        // match

        for (Movie movie : movies) {

            // the condition in the if statement is comparing strings
            if (movie.getMovieID().equals(movieID)) {
                return movie;
            }
        }

        return null;
    }

    // we are now finding the screening associated with the movie
    public List<Screening> getScreeningsForMovie(String movieID) {

        // we are creating a new empty list where to store the screenings associated
        // with the movie
        List<Screening> matchingScreenings = new ArrayList<>();

        for (Screening screening : screenings) {
            if (screening.getMovie().getMovieID().equals(movieID)) {

                matchingScreenings.add(screening);
                // this is adding the matching screening object to the new list created above
            }
        }
        return matchingScreenings;
        // we don't return with in the loop because once a single match is found, the
        // method will end yet there might be multiple screening of the movie which are
        // need to added in the new list
    }

    public Screening findScreeningByID(String screeningID) {

        // Search through all screenings
        for (Screening screening : screenings) {

            // Check whether the Screening ID matches
            if (screening.getScreeningID().equals(screeningID)) {
                return screening;
            }
        }

        // Return null if no screening is found
        return null;
    }
}