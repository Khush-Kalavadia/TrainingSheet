package com.java.languagebasics;

class Vehicle
{
    String licensePlate = null;

    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }
}

class car extends Vehicle
{

    protected String licensePlate = null;     //need to be public or protected and not private

    @Override
    public void setLicensePlate(String license)
    {
        super.setLicensePlate(license);
    }

    @Override
    public String getLicensePlate()
    {
        return super.getLicensePlate();
    }

    String getLicensePlateCar()
    {
        return this.licensePlate;
    }

    void updateLicensePlate(String license)
    {
        this.licensePlate = license;
    }
}

public class FieldInheritanceVehicle
{
    public static void main(String[] str)
    {
        car c1 = new car();

        c1.setLicensePlate("123"); //setting super variable

        c1.updateLicensePlate("abc");  //updating variable using this i.e. for car object

        System.out.println("license plate: " + c1.getLicensePlate());

        System.out.println("license plate of car: " + c1.getLicensePlateCar());
        //conclusion both super and child variable behave differently
    }
}



