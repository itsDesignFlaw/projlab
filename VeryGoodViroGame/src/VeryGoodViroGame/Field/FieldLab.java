package VeryGoodViroGame.Field;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Field.FieldLab.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

import VeryGoodViroGame.Agent.GeneticCode;
import VeryGoodViroGame.Logger;
import VeryGoodViroGame.Virologist;

/**
 * Egy speciális mező, a labort valósítja meg.
 * Felelőssége a letapogathatóság biztosítása, valamint egy genetikai kód létezésének biztosítása.
 */
public class FieldLab extends Field {
    private GeneticCode code;

      /**
      * Ez a metódus valósítja meg a genetikai kód letapogatást.
      *
      * @param v A virológus aki letapogatja a kódot.
      */
    public void Interact(Virologist v) {
        Logger.NewFunctionCall(this, "Interact");
        v.LearnGeneticCode(code);
        Logger.ReturnFunction();
    }

      /**
      * Ez a metódus valósítja beállítja a laborban fellelhető genetikai kódot.
      *
      * @param geneticCode A kód amit beállítunk, hogy le lehessen tapogatni a laborban.
      */
    public void setCode(GeneticCode geneticCode) {
        Logger.NewFunctionCall(this, "setCode");
        code = geneticCode;
        Logger.ReturnFunction();
    }
}