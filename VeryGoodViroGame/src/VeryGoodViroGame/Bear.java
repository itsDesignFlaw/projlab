package VeryGoodViroGame;

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
}
