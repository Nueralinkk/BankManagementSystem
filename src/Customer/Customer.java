package Customer;

public class Customer {
    private long customerId;
    private String customerName;
    private String customerAddress;
    private long contactNumber;
    private long accountNumber;
    private String accountType;

    public Customer(long customerId,String customerName,String customerAddress,long contactNumber,long accountNumber,String accountType) {
        this.customerId=customerId;
        this.customerName=customerName;
        this.customerAddress=customerAddress;
        this.contactNumber=contactNumber;
        this.accountNumber=accountNumber;
        this.accountType=accountType;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
