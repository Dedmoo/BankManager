package com.mehmetserin.bankmanager;

public class InstitutionalCustomer extends Customer {
    private String typeOfInstitution;
    private String industrySector;
    private double annualRevenue;

    public InstitutionalCustomer(
            String name,
            String address,
            String contactDetails,
            String typeOfInstitution,
            String industrySector,
            double annualRevenue) {
        super(name, address, contactDetails);
        this.typeOfInstitution = typeOfInstitution;
        this.industrySector = industrySector;
        this.annualRevenue = annualRevenue;
    }

    public String getTypeOfInstitution() {
        return typeOfInstitution;
    }

    public void setTypeOfInstitution(String typeOfInstitution) {
        this.typeOfInstitution = typeOfInstitution;
    }

    public String getIndustrySector() {
        return industrySector;
    }

    public void setIndustrySector(String industrySector) {
        this.industrySector = industrySector;
    }

    public double getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(double annualRevenue) {
        this.annualRevenue = annualRevenue;
    }
}
