package VeryGoodViroGame;

import java.util.List;

public class MSBear implements iMoveStrategy
{
    @Override
    public void ExecuteMove(Virologist v, Field from, Field to)
    {
        Logger.NewFunctionCall(this, "ExecuteMove");
        Field random = from.GetRandomNeighbour();
        from.RemoveViro(v);
        
        List<Virologist> virologists = random.GetVirologists();
        for(Virologist viro : virologists)
        {
            viro.ApplyAgent(new Bear(), v);
        }
        
        random.AcceptViro(v);
        
        random.Destroy();
        Logger.ReturnFunction();
    }
}
