package uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes;

public class Customers_data {

    //variables
    String Customer_User_Name;
    String Customer_Password;
    String Customer_Email;
    String Customer_Date;
    String Customer_Gender;
    String Customer_PhoneNo;

    public Customers_data() {
    }

    public Customers_data(String customer_User_Name, String customer_Password, String customer_Email, String customer_Date, String customer_Gender, String customer_PhoneNo) {
        this.Customer_User_Name = customer_User_Name;
        this.Customer_Password = customer_Password;
        this.Customer_Email = customer_Email;
        this.Customer_Date = customer_Date;
        this.Customer_Gender = customer_Gender;
        this.Customer_PhoneNo = customer_PhoneNo;
    }

    public String getCustomer_User_Name() {
        return Customer_User_Name;
    }

    public void setCustomer_User_Name(String customer_User_Name) {
        this.Customer_User_Name = customer_User_Name;
    }

    public String getCustomer_Password() {
        return Customer_Password;
    }

    public void setCustomer_Password(String customer_Password) {
        this.Customer_Password = customer_Password;
    }

    public String getCustomer_Email() {
        return Customer_Email;
    }

    public void setCustomer_Email(String customer_Email) {
        this.Customer_Email = customer_Email;
    }

    public String getCustomer_Date() {
        return Customer_Date;
    }

    public void setCustomer_Date(String customer_Date) {
        this.Customer_Date = customer_Date;
    }

    public String getCustomer_Gender() {
        return Customer_Gender;
    }

    public void setCustomer_Gender(String customer_Gender) {
        this.Customer_Gender = customer_Gender;
    }

    public String getCustomer_PhoneNo() {
        return Customer_PhoneNo;
    }

    public void setCustomer_PhoneNo(String customer_PhoneNo) {
        this.Customer_PhoneNo = customer_PhoneNo;
    }
}
