package com.java.language.basics;

/**
 * Created by khush on 1/2/22.
 */
public class ClassObjectCar
{

    public static void main(String[] args)
    {
        try
        {
            Car c1 = new Car();

            Car c2 = new Car("Red");

//        c1.colour = "green";  //cant do as it is private

            c1.setColour("green");

            String colour;

            colour = c1.getColour();

            System.out.println(colour);

            colour = c2.getColour();

            System.out.println(colour);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}

class Car
{
    private String colour;

    Car()
    {
        this.colour = "";
    }

    Car(String colour)
    {
        this.colour = colour;
    }

    protected void setColour(String new_colour)    //protected is not an issue
    {
        this.colour = new_colour;
    }

    String getColour()
    {
        return this.colour;
    }
}