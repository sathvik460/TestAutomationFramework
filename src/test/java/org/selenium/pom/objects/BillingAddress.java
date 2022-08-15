package org.selenium.pom.objects;

public class BillingAddress {

    private String firstName;
    private String lastName;
    private String country;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String emailAddress;

    public BillingAddress(){

    }
    public BillingAddress(String firstName, String lastName, String country, String streetAddress, String city,
                          String state, String zipCode, String emailAddress){
        this.firstName=firstName;
        this.lastName=lastName;
        this.country=country;
        this.streetAddress=streetAddress;
        this.city=city;
        this.state=state;
        this.zipCode=zipCode;
        this.emailAddress=emailAddress;


    }

    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public String getCountry(){
        return country;
    }
    public BillingAddress setCountry(String country){
        this.country=country;
        return this;
    }
    public String getState(){
        return state;
    }
    public BillingAddress setState(String state){
        this.state=state;
        return this;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public BillingAddress setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public BillingAddress setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public BillingAddress setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

}
