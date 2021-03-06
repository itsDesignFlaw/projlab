package VeryGoodViroGame.Agent;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Agent.GeneticCode.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

import VeryGoodViroGame.*;

/**
 * Ez az osztály felelős a hozzájuk tartozó vakcinák és vírusok létrehozásáért,
 * ezek reprezentálják a virológusok craftolási képességét is.
 */
public class GeneticCode implements DrawableComponent
{
    protected Resource cost;
    protected Agent a;
    
    public GeneticCode()
    {
        this(null);
    }
    
    public GeneticCode(Agent a)
    {
        this(a, new Resource(10, 10));
    }
    
    public GeneticCode(Agent a, Resource cost)
    {
        this.cost = cost;
        this.a = a;
    }
    
    /**
     * Beállitja a cost értékét.
     *
     * @param r az átadni kivánt resource érték
     */
    public void setCost(Resource r)
    {
        cost = r;
    }
    
    /**
     * Beállitja az a (ágens amit a genetikai kód tartalmaz) értékét.
     *
     * @param agent az átadni kivánt ágens
     */
    public void setAgent(Agent agent)
    {
        a = agent;
    }
    
    public Agent getAgent()
    {
        return a;
    }
    
    /**
     * létrehozza az adott genetikai kódhoz tartozó vírust.
     *
     * @return a leklónozott ágens
     */
    public Agent CreateVirus()
    {
        Agent clone = a.Clone();
        Timer.AddSteppable(clone);
        
        return clone;
    }
    
    /**
     * létrehozza az adott genetikai kódhoz tartozó vakcinát.
     *
     * @return a leklónozott ágenshez tartozó vakcina
     */
    public Agent CreateVaccine()
    {
        Agent clone = a.Clone();
        Vaccine vaccine = new Vaccine(clone);
        EntityManager.PutCraftedObject("vaccine", vaccine, EntityManager.GetType(clone) + "_vaccine");
        Timer.AddSteppable(vaccine);
        return vaccine;
    }
    
    /**
     * visszatér az adott ágens létrehozásához szükséges erőforrásbeli költséggel.
     *
     * @return a megadott kőltség
     */
    public Resource GetCost()
    {
        return cost;
    }
    
    public boolean CompareCodes(GeneticCode code)
    {
        //TODO:valami ilyesmi kéne
        /*Class c = a.getClass();
        return code.a instanceof c;*/

        if ((code.a == null) || this.a == null)
        {
            return true;
        }
        else
        {
            return code.a.getClass() == a.getClass();
        }
    }
    
    @Override
    public String GetDrawString()
    {
        return a.GetDrawString();
    }
    
    @Override
    public String toString()
    {
        return a == null ? "" : a.getClass().getSimpleName() + " code";
    }
}
