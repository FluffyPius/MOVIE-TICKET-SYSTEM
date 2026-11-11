import java.time.LocalDateTime;
// here we have records a reservation
// contaiins customer details
// tracks booking status
// booking can be cancelled  

public class Booking {

    // the local Attributes
    private String bookingID;
    private Screening screening;
    private Customer customer;
    private String seatID;
    private LocalDateTime bookingDateTime;
    private String bookingStatus;

    // Creating a Parameterized constructor
    public Booking(String bookingID, Screening screening, Customer customer, String seatID) {

        this.bookingID = bookingID;
        this.screening = screening;
        this.customer = customer;
        this.seatID = seatID;
        this.bookingDateTime = LocalDateTime.now(); // capture the current date and time automatically.
        this.bookingStatus = "CONFIRMED"; // // new bookings should start with a confirmation status

    }

    // here we are implementing the getter methods to access booking Info
    public String getBookingID() {
        return bookingID;
    }

    public Screening getScreening() {
        return screening;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getSeatID() {
        return seatID;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    // method should cancel booked seats from associated Screening & change booking
    // status to Cancelled
    public void cancelBooking() {

        if (screening.cancelSeat(seatID)) {
            bookingStatus = "CANCELLED";
            // screening object cancels the cooking status changes to cancelled
        }
        // method has no return type
    }

    // Am method to display the information
    public void displayBookingDetails() {

        IO.println();
        IO.println("Booking ID: " + bookingID);
        IO.println("Customer Name: " + customer.getPersonName());
        IO.println("Movie Title: " + screening.getMovie().getMovieTitle());
        IO.println("Screening Date & Time: " + screening.getScreeningDateTime());
        IO.println("Seat ID: " + seatID);
        IO.println("Booking Date & Time: " + bookingDateTime);
        IO.println("Booking Status: " + bookingStatus);
        IO.println();
    }
}