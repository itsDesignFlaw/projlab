package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.InvItem.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

import VeryGoodViroGame.Agent.Agent;

/**
 * Ez az osztály felel a játékban kifejtett hatások definiálásáért.
 * Minden olyan itemnek (vagy azokhoz köthető osztálynak, pl Ágensek),
 * ami hatást fejt ki egy virológusra, ebből az osztályból kell öröklődnie.
 * Tisztázat működéshez: Az ehhez az osztályhoz tartozó függvények minden,
 * egy virológushoz tartozó InvItem (vagy gyermekosztály) példányokon meghívásra kerülnek,
 * alapértelmezetten ‘true’ boolean értéket adnak ebben az osztályban vissza (IsParalyzed metóduson kívül).
 * Ha a virológus InvItem-jei függvényenként mind végig lesznek iterálva mindaddig,
 * míg egy alapértelmezettel szembemenő (un. ‘blokkoló’) visszatérési értéket kap).
 */
public abstract class InvItem
{
    /**
     *A metódus visszatér egy egész számmal, ami megmutatja,
     * hogy az adott item hatására mennyivel növekszik meg a
     * virológus hordozási kapacitása. Alapértelmezett értéke igaz.
     *
     * @return a fentebb definiált egész szám
     */
    public int GetMaxResource()
    {
        return 0;
    }

    /**
     * A metódus visszatér egy boolean értékkel,
     * az alapján, hogy az adott ágenst fel lehet e kenni a paraméterként kapott virológusra.
     * Alapértelmezett értéke igaz.
     *
     * @param agent az ágens amire vizsgáljuk, hogy felkenhető-e
     * @param source a kenést végző virológus
     * @return a fentebb leírt boolean visszatérési érték
     */
    public boolean CanAgentBeApplied(Agent agent, Virologist source)
    {
        return true;
    }

    /**
     *A metódus visszatért egy boolean értékkel az alapján,
     * hogy az adott ágens engedi e, hogy a virológus raboljon másik virológustól.
     * Alapértelmezett értéke igaz.
     *
     * @return a fentebb leírt boolean visszatérési érték
     */
    public boolean CanSteal()
    {
        return true;
    }

    /**
     *A metódus visszatér egy boolean értékkel az alapján,
     * hogy az adott item hatására megbénul-e a virológus.
     * Alapértelmezett értéke hamis.
     *
     * @return a fentebb leírt boolean visszatérési érték
     */
    public boolean IsParalyzed()
    {
        return false;
    }

    /**
     *A metódus visszatért egy boolean értékkel az alapján,
     * hogy az adott ágens engedi e, hogy a virológus craftolhat e vakcinákat és vírusokat.
     * Alapértelmezett értéke igaz.
     *
     * @return a fentebb leírt boolean visszatérési érték
     */
    public boolean CanCraft()
    {
        return true;
    }

    /**
     *A metódus visszatért egy boolean értékkel az alapján,
     * hogy az adott ágens engedi e, hogy a virológus kapcsolatba léphet a mezővel.
     * Alapértelmezett értéke igaz.
     *
     * @return a fentebb leírt boolean visszatérési érték
     */
    public boolean CanInteract()
    {
        return true;
    }

    /**
     *A metódus visszatért egy boolean értékkel az alapján,
     * hogy az adott ágens engedi e, hogy a virológus kenjen másra ágenst.
     * Alapértelmezett értéke igaz.
     *
     * @return a fentebb leírt boolean visszatérési érték
     */
    public boolean CanApplyAgent()
    {
        return true;
    }
}
