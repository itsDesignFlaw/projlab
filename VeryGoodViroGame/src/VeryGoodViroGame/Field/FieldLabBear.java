package VeryGoodViroGame.Field;

import VeryGoodViroGame.Agent.Bear;
import VeryGoodViroGame.GameManager;
import VeryGoodViroGame.Logger;
import VeryGoodViroGame.Virologist;

public class FieldLabBear extends FieldLab
{
    public FieldLabBear()
    {
        Map curmap = GameManager.GetMap();
        if(curmap != null)
        {
            curmap.Swallow(this, false);
        }
    }
    @Override
    public void Interact(Virologist v)
    {
        Bear b = new Bear();
        b.Apply(null, v);
    }
    @Override
    public String GetDrawString()
    {
        return "bearlab";
    }
}
