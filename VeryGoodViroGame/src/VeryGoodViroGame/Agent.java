package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Agent.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//


public abstract class Agent extends InvItem implements iSteppable
{
    int activeTime;
    //Akin van
    Virologist host;
    iMoveStrategy strategy;
    
    public void Apply(Virologist source, Virologist target)
    {
        Logger.NewFunctionCall("Agent.Apply");
        if(target.ApplyAgent(this, source))
        {
            target.ChangeMoveStrategy(strategy);
        }
        Logger.ReturnFunction();
    }
    
    public void Step()
    {
        Logger.NewFunctionCall("Agent.Step");
        activeTime--;
        if(activeTime <= 0)
        {
            host.RemoveMoveStrategy(strategy);
            host.RemoveItem(this);
            //Ezzel gáz lesz, mert nem szereti a java
            //ha foreach közben kiszedünk a listából
            //Megoldás: klónozás, vagy dead flag
            Timer.RemoveSteppable(this);
        }
        Logger.ReturnFunction();
    }
    
    public Agent Clone()
    {
        Logger.NewFunctionCall("Agent.Clone");
        Logger.ReturnFunction();
        return null;
    }
    
}
