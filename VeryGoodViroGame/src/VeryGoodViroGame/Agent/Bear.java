package VeryGoodViroGame.Agent;

import VeryGoodViroGame.DrawableComponent;
import VeryGoodViroGame.MoveStrategy.MSBear;

public class Bear extends Agent
{
   
    public Bear()
    {
        super();
        strategy = new MSBear();
    }
    
    @Override
    public void Step()
    {
        //Do not lower counter
    }
    @Override
    public String GetDrawString()
    {
        return "bear";
    }
}
