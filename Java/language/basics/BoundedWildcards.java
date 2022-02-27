package com.java.language.basics;

//  Glass
//  Liquid <- Juice <- OrangeJuice <- Mirinda

class Glass<T>{}

interface Liquid{}

class Juice implements Liquid{}

class OrangeJuice extends Juice{}

class Mirinda extends OrangeJuice{}

class Tray
{
    void add(Glass<? extends Juice> g1){}
    //this is upper bounded. Provides bound at upper end. Liquid <- JUICE <- ORANGEJUICE <- MIRINDA

    void remove(Glass<? super Juice> g2){}
    //this is lower bounded. Provides bound at lower end. LIQUID <- JUICE <- OrangeJuice <- Mirinda
}

public class BoundedWildcards
{
    public static void main(String[] args)
    {
        try
        {
            Tray t2 = new Tray();

            t2.add(new Glass<>());              //type inference would be acting over here

            t2.add(new Glass<Juice>());

            t2.add(new Glass<OrangeJuice>());

//            t2.putValue(new Glass<Liquid>());      //Liquid will give error. As liquid does not extend juice

            t2.remove(new Glass<Juice>());

            t2.remove(new Glass<Liquid>());

//            t2.remove(new Glass<Mirinda>());  //error as it crosses the lower bound

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

}
