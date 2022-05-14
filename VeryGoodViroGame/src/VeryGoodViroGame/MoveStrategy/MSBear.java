package VeryGoodViroGame.MoveStrategy;

import VeryGoodViroGame.Agent.Bear;
import VeryGoodViroGame.Field.Field;
import VeryGoodViroGame.Logger;
import VeryGoodViroGame.Virologist;

import java.util.List;

public class MSBear implements iMoveStrategy
{
    @Override
    public void ExecuteMove(Virologist v, Field from, Field to)
    {
        Field random = from.GetRandomNeighbour();
        from.RemoveViro(v);
        
        List<Virologist> virologists = random.GetVirologists();
        for(Virologist viro : virologists)
        {
            viro.ApplyAgent(new Bear(), v);
        }
        
        random.AcceptViro(v);
        
        random.Destroy();
    }
}
