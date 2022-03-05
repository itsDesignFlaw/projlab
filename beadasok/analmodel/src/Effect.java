interface iEquipment
{
    int hi = 0;
    default int getHi() {return hi;}
}

interface EffectHook
{
    boolean canMove(Virologist vir, String dir);
}

public class Effect implements iEquipment {
    EffectHook freezeVirusEffect = (v, d) -> false;
    
    void hi()
    {
        int v = getHi();
    }
}
