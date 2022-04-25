package VeryGoodViroGame.Equipment;

import VeryGoodViroGame.EntityManager;
import VeryGoodViroGame.Logger;
import VeryGoodViroGame.Virologist;

public class EquipmentAxe extends Equipment
{
    private boolean sharp = true;
    
    public EquipmentAxe()
    {
        name = "Axe";
    }
    
    @Override
    public void Use(Virologist target)
    {
        Logger.NewFunctionCall(this, "Use");
        if(sharp)
        {
            target.KillVirologist();
            sharp = false;
        }
        Logger.ReturnFunction();
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "\n\tsharp: " + sharp;
    }
}
