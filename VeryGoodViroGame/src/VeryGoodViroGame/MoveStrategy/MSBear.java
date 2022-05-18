package VeryGoodViroGame.MoveStrategy;

import VeryGoodViroGame.Agent.Bear;
import VeryGoodViroGame.EntityManager;
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
            Bear b = new Bear();
            viro.ApplyAgent(b, v);
            EntityManager.PutCraftedObject("bear", b, "bear_virus");
        }
        
        random.AcceptViro(v);
        
        random.Destroy();
    }
}
