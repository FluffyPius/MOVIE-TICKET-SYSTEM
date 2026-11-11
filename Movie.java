public class Movie {

    // Attributes for the Movies
    private String movieID, movieTitle, movieGenre;
    private int durationOfMovie;
    private double baseTicketPrice;

    // creating a constructor with all five attributes as arguments from which the
    // objects will use
    public Movie(String movieID, String movieTitle, String movieGenre, int durationOfMovie,
            double baseTicketPrice) {
        this.movieID = movieID;
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
        this.durationOfMovie = durationOfMovie;
        this.baseTicketPrice = baseTicketPrice;
        // this parameterised constructor is public to allow access from other classes
    }

    // using the getter and setter methods for the attributes to modify them in
    // future use
    public String getMovieID() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public int getDurationOfMovie() {
        return durationOfMovie;
    }

    public void setDurationOfMovie(int durationOfMovie) {
        this.durationOfMovie = durationOfMovie;
    }

    public double getBaseTicketPrice() {
        return baseTicketPrice;
    }

    public void setBaseTicketPrice(double baseTicketPrice) {
        this.baseTicketPrice = baseTicketPrice;
    }

    // this method is to display information about a movie
    public void displayMovieDetails() {
        System.out.println("");
        System.out.println("The movie title is: " + movieTitle);
        System.out.println("Code Number for Movie :" + movieID);
        System.out.println("Genre : " + movieGenre);
        System.out.println("Runtime is :" + durationOfMovie + " minutes");
        System.out.println("Base Ticket Price: UGX " + baseTicketPrice);
        System.out.println("");
    }
}