package VeryGoodViroGame.Agent;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Agent.Vaccine.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

import VeryGoodViroGame.Agent.Agent;
import VeryGoodViroGame.EntityManager;
import VeryGoodViroGame.Logger;
import VeryGoodViroGame.Timer;
import VeryGoodViroGame.Virologist;

/**
 * Ez az osztály felelős az azonos genetikai kód alapján készített vírusok semlegesítéséért.
 */
public class Vaccine extends Agent
{
    /**
     * Az az ágens amihez a vakcinát létrehozzuk
     */
    private Agent agentToCure;
    
    /**
     * Vaccine osztály konstruktora, inicializálásért felelős
     *
     * @param agentToCure az inicializálandó attribútum
     */
    public Vaccine(Agent agentToCure)
    {
        this.agentToCure = agentToCure;
    }
    
    /**
     * A forrás virológus beadja a vakcinát a targetnek,
     * és amennyiben a targeton épp hatást gyakorol egy ágens,
     * melyet gyógyít a vakcina, a hatás megszűnik.
     *
     * @param source az ágenst felkenő virológus
     * @param target az a virológus akire felkenik az ágenst
     */
    public void Apply(Virologist source, Virologist target)
    {
        Logger.NewFunctionCall(this, "Apply");
        target.RemoveItem(agentToCure);//TODO: instance of?
        Timer.RemoveSteppable(agentToCure);
        Timer.RemoveSteppable(this);
        Logger.ReturnFunction();
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "\n\tagentToCure: " + agentToCure.getClass().getSimpleName();
    }
}
