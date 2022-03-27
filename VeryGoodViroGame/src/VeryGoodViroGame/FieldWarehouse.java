package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.FieldWarehouse.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

/**
 *Egy speciális mező, a raktárat valósítja meg.
 * Felelőssége, adott raktárban anyagok létezésének
 * valamint felvételének biztosítása.
 */
public class FieldWarehouse extends Field {
    private Resource resources;

    /**
     * Ez a metódus valósítja meg a feladat szövege szerinti felszerelés felvételét.
     *
     * @param v A virológus aki a mezőn van.
     */
    public void Interact(Virologist v) {
        Logger.NewFunctionCall("FieldWarehouse.Interact");
        v.AddResource(resources);
        Logger.ReturnFunction();
    }

    /**
     * Ez a metódus valósítja meg, hogy az adott mezőn milyen felszerelés jelenjen meg.
     *
     * @param r felszerelés
     */
    public void setResource(Resource r) {
        resources = r;
    }
}

