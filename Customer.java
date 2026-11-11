// CUSTOMER is our CHILD CLASS
public class Customer extends Person {

    // Customer-specific attribute
    private String customerEmail;

    // Constructor
    public Customer(String personID, String personName, String customerEmail) {

        // Calls the parent class constructor
        super(personID, personName);

        this.customerEmail = customerEmail;
    }

    // Getter
    public String getCustomerEmail() {
        return customerEmail;
    }

    // Setter
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    // Overrides the parent class method
    @Override
    public void displayPersonDetails() {

        super.displayPersonDetails();

        System.out.println("Email: " + customerEmail);
        System.out.println();
    }
}
