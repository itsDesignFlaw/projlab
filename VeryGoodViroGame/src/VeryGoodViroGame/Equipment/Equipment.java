package VeryGoodViroGame.Equipment;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Equipment.Equipment.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

import VeryGoodViroGame.*;

/**
 * A virológusok által felvehető felszerelések/ruhadarabok.
 * Alapvető célja, hogy ezeket az interaktív osztályok közös kezelését elősegítse.
 */
public abstract class Equipment extends InvItem implements DrawableComponent
{
    
    Virologist host;
    String name = "";
    
    public void Use(Virologist target)
    {
    }
    
    public void SetHost(Virologist host)
    {
        this.host = host;
    }
    
    @Override
    public String toString()
    {
        return "\thost: " + EntityManager.GetObjectName(host);
    }
    
    public String getName()
    {
        return name;
    }
}
