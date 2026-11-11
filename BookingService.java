import java.util.ArrayList;
import java.util.List;

// This class is responsible for:
// Creating customer bookings.
// Checking whether a seat is available.
// Reserving seats.
// Generating tickets.
// Cancelling bookings.
// Invalidating tickets after cancellation.

public class BookingService {

    // the attributes initialized
    private List<Booking> bookings; // meaning the Key is a string and the value is a Booking object
    private List<Ticket> tickets; // Key is a string and the value is a Ticket Object
    private static int bookingCounter = 1;
    private static int ticketCounter = 1;

    public BookingService() {

        this.bookings = new ArrayList<>();
        this.tickets = new ArrayList<>();
        // this.customer = customer;

    }

    private String generateBookingID() {
        return String.format("B%03d", bookingCounter++);
    }

    private String generateTicketID() {
        return String.format("T%03d", ticketCounter++);
    }

    public Ticket createBooking(Screening screening, Customer customer, String seatID) {
        // Generate unique IDs
        String bookingID = generateBookingID();
        String ticketID = generateTicketID();

        boolean seatReserved = screening.bookSeat(seatID);
        // we check seat Availability by using a screening object with a bookSeat Method

        // we check is the seat is actually reserved
        if (!seatReserved) {
            return null;
        }

        // we creat a booking object when the above code returns true
        Booking booking = new Booking(bookingID, screening, customer, seatID);

        // here we store the booking
        bookings.add(booking);

        // we then create a new Ticket object
        Ticket ticket = new Ticket(ticketID, booking);

        // we then store the new ticket
        tickets.add(ticket);

        // this then returns the created ticket
        return ticket;
    }

    // method cancels the booking
    public boolean cancelBooking(String bookingID) {

        // Find the booking using its ID
        Booking booking = getBookingByID(bookingID);

        // Check whether the booking exists
        if (booking == null) {
            return false;
        }

        // Check whether the booking is already cancelled
        if (booking.getBookingStatus().equals("CANCELLED")) {
            return false;
        }

        // Cancel the booking and release its seat
        booking.cancelBooking();

        // Check whether cancellation was successful
        if (!booking.getBookingStatus().equals("CANCELLED")) {
            return false;
        }

        // Find the ticket associated with this booking
        for (Ticket ticket : tickets) {

            if (ticket.getBooking() == booking) {

                // Invalidate the ticket
                ticket.invalidateTicket();

                break;
            }
        }
        return true;
    }

    // we attain a specific booking object using the booking ID
    public Booking getBookingByID(String bookingID) {

        for (Booking booking : bookings) {

            if (booking.getBookingID().equals(bookingID)) {
                return booking;
            }
        }

        return null;
    }

    // we attain the specific ticket object with the ticket ID
    public Ticket getTicketByID(String ticketID) {
        for (Ticket ticket : tickets) {

            if (ticket.getTicketID().equals(ticketID)) {
                return ticket;
            }
        }

        return null;
    }

    // Returns all stored bookings
    public List<Booking> getAllBookings() {
        return bookings;
    }
}