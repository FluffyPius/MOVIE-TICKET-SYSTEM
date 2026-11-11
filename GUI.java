import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.awt.Font;
import java.awt.Color;

public class GUI {

    private MovieService movieService;
    private BookingService bookingService;
    private static int customerCounter = 1; // its a counter

    // to generate customer IDs
    private String generateCustomerID() {
        return String.format("C%03d", customerCounter++);
    }

    public GUI(MovieService movieService, BookingService bookingService) {
        this.movieService = movieService;
        this.bookingService = bookingService;

        // Create the main window
        JFrame frame = new JFrame("Movie Ticketing System");

        // Set window dimensions
        frame.setSize(800, 500);

        // Centre the window on the screen
        frame.setLocationRelativeTo(null);

        // Close the application when the window closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel using BorderLayout
        JPanel panel = new JPanel(new BorderLayout(10, 20));

        // Set the main background colour
        panel.setBackground(new Color(18, 18, 28));

        // Create the heading panel
        JPanel headingPanel = new JPanel();
        headingPanel.setBackground(new Color(18, 18, 28));

        // Create the heading
        JLabel heading = new JLabel("MOVIE TICKETING SYSTEM");

        // Align the heading to the center
        heading.setHorizontalAlignment(SwingConstants.CENTER);

        // Style the heading
        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setForeground(new Color(212, 175, 55));

        headingPanel.setLayout(new GridLayout(2, 1, 5, 5));

        headingPanel.add(heading);

        // Create a subtitle
        JLabel subtitle = new JLabel("YOUR MOVIE EXPERIENCE STARTS HERE");

        // Style the subtitle
        subtitle.setFont(new Font("Arial", Font.PLAIN, 12));
        subtitle.setForeground(Color.LIGHT_GRAY);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        headingPanel.add(subtitle);

        // Create a panel for the navigation buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 15, 15));

        // Add spacing around the button section
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));
        buttonPanel.setBackground(new Color(18, 18, 28));

        // Create buttons
        JButton viewMoviesButton = new JButton("View Movies");
        JButton bookTicketButton = new JButton("Book a Ticket");
        JButton viewBookingButton = new JButton("View Booking");
        JButton cancelBookingButton = new JButton("Cancel Booking");
        JButton viewScreeningsButton = new JButton("View Screenings");
        // add buttons
        buttonPanel.add(viewMoviesButton);
        buttonPanel.add(bookTicketButton);
        buttonPanel.add(viewBookingButton);
        buttonPanel.add(cancelBookingButton);
        buttonPanel.add(viewScreeningsButton);

        // Style the navigation buttons
        JButton[] buttons = {
                viewMoviesButton,
                bookTicketButton,
                viewBookingButton,
                cancelBookingButton,
                viewScreeningsButton
        };

        for (JButton button : buttons) {
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(new Color(35, 35, 45));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
            button.setOpaque(true);
            button.addMouseListener(new java.awt.event.MouseAdapter() {

                @Override
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    button.setBackground(new Color(212, 175, 55));
                    button.setForeground(Color.BLACK);
                }

                @Override
                public void mouseExited(java.awt.event.MouseEvent e) {
                    button.setBackground(new Color(35, 35, 45));
                    button.setForeground(Color.WHITE);
                }
            });
        }

        // View Movies button
        viewMoviesButton.addActionListener(e -> {

            // Retrieve all movies
            List<Movie> movies = movieService.getAllMovies();

            // Check whether any movies exist
            if (movies.isEmpty()) {
                JOptionPane.showMessageDialog(
                        frame,
                        "No movies are currently available.",
                        "NO MOVIES FOUND",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Create an empty String to store movie details
            String movieDetails = "";

            // Loop through all movies
            for (Movie movie : movies) {

                movieDetails += "Movie ID: " + movie.getMovieID() + "\n" +
                        "Title: " + movie.getMovieTitle() + "\n" +
                        "Genre: " + movie.getMovieGenre() + "\n" +
                        "Duration: " + movie.getDurationOfMovie() + " minutes\n" +
                        "Price: " + movie.getBaseTicketPrice() + "\n\n";
            }

            // Display the movie details
            // Create an area for displaying movie details
            JTextArea textArea = new JTextArea(movieDetails, 12, 35);

            // Prevent users from editing movie information
            textArea.setEditable(false);

            // Enable automatic text wrapping
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            // Style the text
            textArea.setFont(new Font("Arial", Font.PLAIN, 14));

            // Add scrolling functionality
            JScrollPane scrollPane = new JScrollPane(textArea);

            // Display the movie information
            JOptionPane.showMessageDialog(
                    frame,
                    scrollPane,
                    "AVAILABLE MOVIES",
                    JOptionPane.INFORMATION_MESSAGE);

        });

        viewScreeningsButton.addActionListener(e -> {

            // Ask the user to enter a Movie ID
            String movieID = JOptionPane.showInputDialog(
                    frame, "Enter Movie ID:");

            // Check whether the user cancelled the input
            if (movieID == null) {
                return;
            }

            // Retrieve screenings for the selected movie
            java.util.List<Screening> screenings = movieService.getScreeningsForMovie(movieID);

            // Check whether screenings exist
            if (screenings.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No screenings found!");
                return;
            }

            // Store screening information
            String screeningDetails = "";

            for (Screening screening : screenings) {

                screeningDetails += "Screening ID: " + screening.getScreeningID() + "\n" +
                        "Movie: " + screening.getMovie().getMovieTitle() + "\n" +
                        "Date: " + screening.getScreeningDateTime() + "\n" +
                        "Total Seats: " + screening.getTotalSeats() + "\n" +
                        "Available Seats: " + screening.getAvailableSeats() + "\n\n";
            }

            // Display screening information
            // Create an area for displaying screening details
            JTextArea textArea = new JTextArea(screeningDetails, 12, 35);

            // Prevent editing
            textArea.setEditable(false);

            // Enable text wrapping
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);

            // Style the text
            textArea.setFont(new Font("Arial", Font.PLAIN, 14));

            // Add scrolling functionality
            JScrollPane scrollPane = new JScrollPane(textArea);

            // Display screening information
            JOptionPane.showMessageDialog(
                    frame,
                    scrollPane,
                    "AVAILABLE SCREENINGS",
                    JOptionPane.INFORMATION_MESSAGE);

        });

        // Book Ticket button
        bookTicketButton.addActionListener(e -> {
            // Ask for the customer's name
            String customerName = JOptionPane.showInputDialog(
                    frame, "Enter your name:");

            // Stop if the user cancels
            if (customerName == null) {
                return;
            }

            // Ask for the customer's email
            String customerEmail = JOptionPane.showInputDialog(
                    frame, "Enter your email:");

            if (customerEmail == null) {
                return;
            }

            // creating a customer object
            Customer customer = new Customer(generateCustomerID(), customerName, customerEmail);

            // Display all available movies
            String movieDetails = "";

            for (Movie movie : movieService.getAllMovies()) {
                movieDetails += "Movie ID: " + movie.getMovieID() + "\n" + "Title: " + movie.getMovieTitle() + "\n\n";
            }

            // Show available movies
            JOptionPane.showMessageDialog(frame, movieDetails);

            // Ask the customer to select a movie
            String movieID = JOptionPane.showInputDialog(
                    frame, "Enter the Movie ID:");

            // Stop if the user cancels
            if (movieID == null) {
                return;
            }

            // Find the selected movie
            Movie selectedMovie = movieService.findMovieByID(movieID);

            // Check whether the movie exists
            if (selectedMovie == null) {
                JOptionPane.showMessageDialog(frame, "Movie not found!");
                return;
            }

            // Retrieve screenings for the selected movie
            java.util.List<Screening> screenings = movieService.getScreeningsForMovie(movieID);

            // Display available screenings
            String screeningDetails = "";

            for (Screening screening : screenings) {

                screeningDetails += "Screening ID: " + screening.getScreeningID() + "\n" +
                        "Date: " + screening.getScreeningDateTime() + "\n" +
                        "Available Seats: " + screening.getAvailableSeats() + "\n\n";
            }

            // Show the screenings
            JOptionPane.showMessageDialog(frame, screeningDetails);

            // Ask the customer to select a screening
            String screeningID = JOptionPane.showInputDialog(
                    frame, "Enter the Screening ID:");

            // Stop if the user cancels
            if (screeningID == null) {
                return;
            }

            // Find the selected screening
            Screening selectedScreening = movieService.findScreeningByID(screeningID);

            // Check whether the screening exists
            if (selectedScreening == null ||
                    !selectedScreening.getMovie().getMovieID().equals(movieID)) {

                JOptionPane.showMessageDialog(frame, "Invalid screening selection!");
                return;
            }

            // Display the number of available seats
            JOptionPane.showMessageDialog(
                    frame,
                    "Available Seats: " + selectedScreening.getAvailableSeats());

            // Ask the customer to enter a seat ID
            String seatID = JOptionPane.showInputDialog(
                    frame, "Enter your preferred seat (e.g. A1):");

            // Stop if the user cancels
            if (seatID == null) {
                return;
            }

            // Create the booking and generate a ticket
            Ticket ticket = bookingService.createBooking(
                    selectedScreening,
                    customer,
                    seatID);

            // Check whether booking was successful
            if (ticket == null) {
                JOptionPane.showMessageDialog(frame,
                        "Booking failed! The seat may already be occupied or no seats are available.");
                return;
            }

            // Retrieve the Booking object from the Ticket
            Booking booking = ticket.getBooking();

            // Display booking confirmation
            // Create booking confirmation details
            String confirmationDetails = "BOOKING SUCCESSFUL!\n\n" +
                    "Booking ID: " + booking.getBookingID() + "\n" +
                    "Ticket ID: " + ticket.getTicketID() + "\n" +
                    "Customer: " + customer.getPersonName() + "\n" +
                    "Movie: " + selectedMovie.getMovieTitle() + "\n" +
                    "Screening: " + selectedScreening.getScreeningID() + "\n" +
                    "Seat: " + seatID + "\n" +
                    "Status: " + ticket.getTicketStatus();

            // Create a text area
            JTextArea textArea = new JTextArea(confirmationDetails, 12, 35);
            textArea.setEditable(false);
            textArea.setFont(new Font("Arial", Font.PLAIN, 14));

            // Display the confirmation
            JOptionPane.showMessageDialog(
                    frame,
                    textArea,
                    "BOOKING CONFIRMATION",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // View Booking button
        viewBookingButton.addActionListener(e -> {

            // Ask for the Booking ID
            String bookingID = JOptionPane.showInputDialog(
                    frame, "Enter your Booking ID:");

            // Stop if the user cancels
            if (bookingID == null) {
                return;
            }

            // Find the booking
            Booking booking = bookingService.getBookingByID(bookingID);

            // Check whether the booking exists
            if (booking == null) {
                JOptionPane.showMessageDialog(frame, "Booking not found!");
                return;
            }

            // Retrieve and display booking details
            String bookingDetails = "Booking ID: " + booking.getBookingID() + "\n" +
                    "Customer: " + booking.getCustomer().getPersonName() + "\n" +
                    "Movie: " + booking.getScreening().getMovie().getMovieTitle() + "\n" +
                    "Screening: " + booking.getScreening().getScreeningID() + "\n" +
                    "Seat: " + booking.getSeatID() + "\n" +
                    "Booking Date: " + booking.getBookingDateTime() + "\n" +
                    "Booking Status: " + booking.getBookingStatus();

            // Create a text area for booking details
            JTextArea textArea = new JTextArea(bookingDetails, 12, 35);

            // Prevent editing
            textArea.setEditable(false);

            // Style the text
            textArea.setFont(new Font("Arial", Font.PLAIN, 14));

            // Add scrolling
            JScrollPane scrollPane = new JScrollPane(textArea);

            // Display booking information
            JOptionPane.showMessageDialog(
                    frame,
                    scrollPane,
                    "BOOKING DETAILS",
                    JOptionPane.INFORMATION_MESSAGE);

        });

        // Cancel Booking button
        cancelBookingButton.addActionListener(e -> {

            // Ask the user for the Booking ID
            String bookingID = JOptionPane.showInputDialog(
                    frame, "Enter your Booking ID:");

            // Stop if the user cancels
            if (bookingID == null) {
                return;
            }

            // Find the booking
            Booking booking = bookingService.getBookingByID(bookingID);

            // Check whether the booking exists
            if (booking == null) {
                JOptionPane.showMessageDialog(frame, "Booking not found!");
                return;
            }

            // Check whether the booking is already cancelled
            if (booking.getBookingStatus().equals("CANCELLED")) {
                JOptionPane.showMessageDialog(frame, "This booking is already cancelled!");
                return;
            }

            // Ask the user to confirm cancellation
            int confirmation = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to cancel this booking?",
                    "Confirm Cancellation",
                    JOptionPane.YES_NO_OPTION);

            // Cancel only if the user selects YES
            if (confirmation == JOptionPane.YES_OPTION) {

                boolean cancelled = bookingService.cancelBooking(bookingID);

                if (cancelled) {
                    JOptionPane.showMessageDialog(
                            frame, "Booking cancelled successfully!", "BOOKING CANCELLED",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(
                            frame, "Unable to cancel booking. Please check the booking ID.",
                            "CANCELLATION FAILED", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        /// Position the heading panel at the top
        panel.add(headingPanel, BorderLayout.NORTH);

        // Position the navigation buttons in the centre
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Add the panel to the window
        frame.add(panel);

        // Display the window
        frame.setVisible(true);
    }
}