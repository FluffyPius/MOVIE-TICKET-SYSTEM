import java.time.LocalDateTime; // allows use of date and time manipulation
import java.util.ArrayList;

public class Screening {

    // Attributes within the screening Class, all private
    private String screeningID; // identifies the a unique screening
    private Movie movie; // connects the screening to a Movie Object as a relationship
    private LocalDateTime screeningDateTime; // stores the screening's date and time without a timezone
    private int totalSeats; // shows the seating capacity
    private ArrayList<String> bookedSeats; // Stores the seat IDs that have been booked

    // Building a parameterised constructor
    public Screening(String screeningID, Movie movie, LocalDateTime screeningDateTime, int totalSeats) {
        this.screeningID = screeningID;
        this.movie = movie;
        this.screeningDateTime = screeningDateTime;
        this.totalSeats = totalSeats;
        this.bookedSeats = new ArrayList<>(); // Starts with an empty list of booked seats
    }

    // introducing the getter methods for the attributes
    public String getScreeningID() {
        return screeningID;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getScreeningDateTime() {
        return screeningDateTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    // this method returns the number of seats that are not yet booked
    public int getAvailableSeats() {
        int availableSeats = totalSeats - bookedSeats.size();
        return availableSeats;
    }

    // this is where the one can book an available and specific seat
    public boolean bookSeat(String seatID) {

        if (seatID != null && !seatID.isBlank() && getAvailableSeats() > 0 && !bookedSeats.contains(seatID)) {
            // seatID != null prevents a null seat ID.
            // !seatID.isBlank() prevents empty or whitespace-only seat IDs.
            bookedSeats.add(seatID); // adds that particular seat in the booked list

            return true;
        }

        return false;
    }

    // this method allows the unbooking of the a seat
    public boolean cancelSeat(String seatID) {

        if (bookedSeats.contains(seatID)) {
            bookedSeats.remove(seatID); // the seat is made available again after cancelling the booking
            return true;
        }

        return false;
    }

    // this methods is going to display the screening details
    public void displayScreeningDetails() {

        System.out.println();
        IO.println("The Screening ID of the movie: " + screeningID);
        IO.println("MOVIE: " + movie.getMovieTitle());
        IO.println("Screening Date and Time: " + screeningDateTime);
        IO.println("Seating Capacity: " + totalSeats);
        IO.println("Available Seats: " + getAvailableSeats());
        System.out.println();
    }
}