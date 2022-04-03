package VeryGoodViroGame;

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
}
