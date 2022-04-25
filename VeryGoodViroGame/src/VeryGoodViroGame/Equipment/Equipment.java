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

import VeryGoodViroGame.InvItem;
import VeryGoodViroGame.Logger;
import VeryGoodViroGame.Virologist;

/**
 * A virológusok által felvehető felszerelések/ruhadarabok.
 * Alapvető célja, hogy ezeket az interaktív osztályok közös kezelését elősegítse.
 */
public abstract class Equipment extends InvItem
{
    
    Virologist host;
    String name = "";
    
    public void Use(Virologist target)
    {
        Logger.NewFunctionCall(this, "Use");
        Logger.ReturnFunction();
    }
}