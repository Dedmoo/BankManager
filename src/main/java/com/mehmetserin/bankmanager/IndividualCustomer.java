package com.mehmetserin.bankmanager;

public class IndividualCustomer extends Customer {
    private String gender;
    private String identification;
    private String occupation;

    public IndividualCustomer(
            String name,
            String address,
            String contactDetails,
            String gender,
            String identification,
            String occupation) {
        super(name, address, contactDetails);
        this.gender = gender;
        this.identification = identification;
        this.occupation = occupation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
