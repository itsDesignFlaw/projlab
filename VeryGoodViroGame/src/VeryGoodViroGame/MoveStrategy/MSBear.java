package VeryGoodViroGame.MoveStrategy;

import VeryGoodViroGame.Agent.Bear;
import VeryGoodViroGame.EntityManager;
import VeryGoodViroGame.Field.Field;
import VeryGoodViroGame.Logger;
import VeryGoodViroGame.Virologist;

import java.util.List;
/**
 * Ez az osztály felelős a virológus medve mozgási stratégiájának megvalósításáért.
 */
public class MSBear implements iMoveStrategy
{
    /**
     * Ennek a metódusnak a hatására a virológus egy véletlenszerűen választott mezőre lép, valamint
     * ha egy mezőre lép egy másik viróval, rá is medve vírust ken.
     * @param v A virológus, akin a mozgást végrehajtjuk.
     * @param from a Mező amiről mozgatjuk a virológust
     * @param to a Mező amire mozgatjuk a virológust
     */
    @Override
    public void ExecuteMove(Virologist v, Field from, Field to)
    {
        Field random = from.GetRandomNeighbour();
        from.RemoveViro(v);
        
        List<Virologist> virologists = random.GetVirologists();
        for(Virologist viro : virologists)
        {
            Bear b = new Bear();
            b.Apply(v, viro);
            EntityManager.PutCraftedObject("bear", b, "bear_virus");
        }
        
        random.AcceptViro(v);
        
        random.Destroy();
    }
}
