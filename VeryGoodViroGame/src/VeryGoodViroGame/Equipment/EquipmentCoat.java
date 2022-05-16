package VeryGoodViroGame.Equipment;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Equipment.EquipmentCoat.java
//  @ Date : 2022. 03. 27.
//  @ Author :
//
//

import VeryGoodViroGame.Agent.Agent;
import VeryGoodViroGame.Logger;
import VeryGoodViroGame.Virologist;
import VeryGoodViroGame.XRandom;

import java.util.Random;

/**
 * A védőköpeny védőfelszerelést reprezentálja,
 * az ezt viselő virológusra kent ágenseket tartja távol 82,3%-os hatásfokkal.
 */
public class EquipmentCoat extends Equipment
{
    static final float Chance = 0.17f;//Nem emlékszem pontosan mennyi, és hogy így csináljuk e vagy fordítva
    XRandom r = new XRandom();
    
    @Override
    public String GetDrawString()
    {
        return "coat";
    }
    /**
     * (InvItem metódus) véletlenszerűen, előre megadott eséllyel blokkolja
     * (hamis érték visszaadásával) az ágens felkenődését
     *
     * @param agent  az ágens amit fel akar kenni a felkeno virologus
     * @param source az a virológus aki fel akarja kenni az agenst
     */
    public boolean CanAgentBeApplied(Agent agent, Virologist source)
    {
        return r.nextFloat() < Chance;
    }
}
