package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Protect.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

/**
 *Egy vírust reprezentál a játékban, amíg egy virológuson hatást gyakorol,
 *addig a virológusra nem lehet más ágenst rákenni.
 */
public class Protect extends Agent
{
    /**
     * (InvItem metódus)  Mindig hamissal tér vissza, megakadályozva más ágensek kenését a virológuson.
     *
     * @param agent az ágens amire vizsgáljuk, hogy felkenhető-e
     * @param source a kenést végző virológus
     * @return boolean visszatérési érték, mindig hamis
     */
    public boolean CanAgentBeApplied(Agent agent, Virologist source)
    {
        Logger.NewFunctionCall(this, "CanAgentBeApplied");
        Logger.ReturnFunction();
        return false;
    }
}
