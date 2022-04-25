package VeryGoodViroGame.Agent;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Agent.Agent.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

import VeryGoodViroGame.*;
import VeryGoodViroGame.MoveStrategy.MSSimple;
import VeryGoodViroGame.MoveStrategy.iMoveStrategy;

/**
 * Az összes ágens ősosztálya, az osztály felelőssége, hogy előírja,
 * hogyan lehet felkenni az ágenseket, és felel azért is, hogy csak adott ideig fejtsék ki a hatásukat.
 */
public abstract class Agent extends InvItem implements iSteppable, Cloneable
{
    int activeTimeDefault = 690;//jobb nevet neki
    int activeTime = activeTimeDefault;
    //Akin van
    Virologist host;
    iMoveStrategy strategy = new MSSimple();
    boolean active = false;
    
    /**
     * A source paraméterként kapott virológus megkísérli felkenni az ágenst a targetként megkapott virológusra.
     *
     * @param source az ágenst felkenő virológus
     * @param target az a virológus akire felkenik az ágenst
     */
    public void Apply(Virologist source, Virologist target)
    {
        host = null;
        if(target.ApplyAgent(this, source))
        {
            target.ChangeMoveStrategy(strategy);
            host = target;
            activeTime = activeTimeDefault;
            active = true;
            Timer.AddSteppable(this);
        }
    }
    
    /**
     * Minden időpillanatban meghívódik ez a függvény, és csökkenti az időt ameddig még aktív az ágens.
     */
    public void Step()
    {
        Logger.NewFunctionCall(this, "Step");
        activeTime--;
        if(activeTime <= 0)
        {
            if(active)
            {
                host.RemoveMoveStrategy(strategy);
                host.RemoveItem(this);
            }
            else
            {
                host.RemoveAgentFromStash(this);
            }
            //Ezzel gáz lesz, mert nem szereti a java
            //ha foreach közben kiszedünk a listából
            //Megoldás: klónozás, vagy dead flag
            Timer.RemoveSteppable(this);
        }
        Logger.ReturnFunction();
    }
    
    /**
     * Lemásolja az adott ágenst.
     */
    public Agent Clone()
    {
        try
        {
            return (Agent) super.clone();
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    @Override
    public String toString()
    {
        return "\tactiveTime: " + activeTime + "\n\thost: " + EntityManager.GetObjectName(host) + "\n\tstartegy: " + strategy.getClass().getSimpleName();
    }
}
