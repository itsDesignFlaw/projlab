package VeryGoodViroGame;

public class FieldLabBear extends FieldLab
{
    @Override
    public void Interact(Virologist v)
    {
        Logger.NewFunctionCall(this, "Interact");
        Bear b = new Bear();
        b.Apply(null, v);
        Logger.ReturnFunction();
    }
}
