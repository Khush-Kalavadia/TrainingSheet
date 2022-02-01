package com.java.languagebasics;

class Bicycle {

    private int speed = 0;

    private int gear = 1;

    void changeGear(int newValue)
    {
        gear = newValue;
    }

    void speedUp(int increment)
    {
        speed = speed + increment;
    }

    void applyBrakes(int decrement)
    {
        speed = speed - decrement;
    }

    void printStates()
    {
        System.out.println("Speed:" + speed + " gear:" + gear);
    }
}


public class ClassObjectBicycle
{
    public static void main(String[] args)
    {
        try
        {
            // creating objects
            Bicycle bike1 = new Bicycle();

            Bicycle bike2 = new Bicycle();

            // calling methods
            bike1.speedUp(10);

            bike1.changeGear(2);

            bike1.printStates();

            bike2.speedUp(20);

            bike2.changeGear(2);

            bike2.speedUp(10);      //speed increment

            bike2.changeGear(3);

            bike2.printStates();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
