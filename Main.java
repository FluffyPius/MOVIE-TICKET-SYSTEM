import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        // we create the Customer Object, MovieService and BookingService Objects
        MovieService movieService = new MovieService();
        BookingService bookingService = new BookingService();

        // we create movie objects with their Attributes
        Movie movie1 = new Movie("M01", "Prestige", "Thriller", 130, 20000);
        Movie movie2 = new Movie("M02", "The Dark Knight", "Action", 134, 15000);
        Movie movie3 = new Movie("M03", "Entergalactic", "Drama", 94, 12000);

        // adding the movie objects in the movieService
        movieService.addMovie(movie1);
        movieService.addMovie(movie2);
        movieService.addMovie(movie3);

        // creating sample screenings
        Screening screening1 = new Screening("S01", movie1, LocalDateTime.of(2026, 11, 30, 14, 30), 40);
        Screening screening2 = new Screening("S02", movie2, LocalDateTime.of(2026, 11, 30, 17, 30), 50);
        Screening screening3 = new Screening("S03", movie3, LocalDateTime.of(2026, 11, 29, 19, 30), 35);

        // adding the to screening
        movieService.addScreening(screening1);
        movieService.addScreening(screening2);
        movieService.addScreening(screening3);

        // instance of the GUI
        GUI gui = new GUI(movieService, bookingService); // it replaces the scanner

    }
}
