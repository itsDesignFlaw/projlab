package VeryGoodViroGame.Field;

import VeryGoodViroGame.Agent.Bear;
import VeryGoodViroGame.Logger;
import VeryGoodViroGame.Virologist;

public class FieldLabBear extends FieldLab
{
    @Override
    public void Interact(Virologist v)
    {
        Bear b = new Bear();
        b.Apply(null, v);
    }
}
