// Represents the issued ticket
// Contains ticket identification
// Tracks ticket validity
// Can be marked invalid after cancellation

import java.time.LocalDateTime;

public class Ticket {

    private String ticketID;
    private Booking booking;
    private LocalDateTime issueDateTime;
    private String ticketStatus;

    public Ticket(String ticketID, Booking booking) {
        this.ticketID = ticketID;
        this.booking = booking;
        this.issueDateTime = LocalDateTime.now();
        this.ticketStatus = "VALID";
    }

    public String getTicketID() {
        return ticketID;
    }

    public Booking getBooking() {
        return booking;
    }

    public LocalDateTime getIssueDateTime() {
        return issueDateTime;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void invalidateTicket() {
        // method is meant to check if any bookings were cancelled
        if (booking.getBookingStatus().equals("CANCELLED")) { // comparing two strings

            ticketStatus = "INVALID";
        }

    }

    public void displayTicketDetails() {

        IO.println();
        IO.println("Ticket ID: " + getTicketID());
        IO.println("Customer Name: " + getBooking().getCustomer().getPersonName());
        IO.println("Customer Email: " + getBooking().getCustomer().getCustomerEmail());
        IO.println("Movie Title: " + getBooking().getScreening().getMovie().getMovieTitle());
        IO.println("Seat ID: " + getBooking().getSeatID());
        IO.println("Issue Date & Time: " + getIssueDateTime());
        IO.println("Ticket Status: " + getTicketStatus());
        IO.println();
    }
}