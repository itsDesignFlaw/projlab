package VeryGoodViroGame.Agent;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Agent.Paralyze.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

import VeryGoodViroGame.Logger;
import VeryGoodViroGame.MoveStrategy.MSParalyzed;

/**
 * Egy vírust reprezentál a játékban, amely mindaddig,
 * míg egy virológusra hatást gyakorol, addig az lebénul,
 * tehát nem tud mozogni, vagy interaktálni más entitásokkal.
 * Felelőssége, az InvItem osztály bizonyos metódusainak felülírása.
 */
public class Paralyze extends Agent
{
    public Paralyze()
    {
        super();
        strategy = new MSParalyzed();
        //activeTimeDefault = 1;
    }
    
    /**
     * (InvItem metódus) Mindig igazat ad vissza, tehát ilyenkor kifosztható.
     *
     * @return boolean visszatérési érték, mindig igaz
     */
    public boolean IsParalyzed()
    {
        return true;
    }
    
    
    /**
     * (InvItem metódus) Hamisat ad vissza, a virológus nem tud ágenst felkenni.
     *
     * @return boolean visszatérési érték, mindig hamis
     */
    public boolean CanApplyAgent()
    {
        return false;
    }
    
    /**
     * (InvItem metódus) Hamisat ad vissza, a virológus nem tud lopni.
     *
     * @return boolean visszatérési érték, mindig hamis
     */
    public boolean CanSteal()
    {
        return false;
    }
    
    /**
     * (InvItem metódus) Hamisat ad vissza, a virológus nem tud ágenst létrehozni.
     *
     * @return boolean visszatérési érték, mindig hamis
     */
    public boolean CanCraft()
    {
        return false;
    }
    
    /**
     * (InvItem metódus) Hamisat ad vissza, a virológus nem tud adott mezőkkel a megfelelő módon interaktálni.
     *
     * @return boolean visszatérési érték, mindig hamis
     */
    public boolean CanInteract()
    {
        return false;
    }
    @Override
    public String GetDrawString()
    {
        return "paralyze";
    }
}
