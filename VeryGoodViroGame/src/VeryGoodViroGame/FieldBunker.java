package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.FieldBunker.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//


public class FieldBunker extends Field
{
    private Equipment equipment;
    private boolean hasEquipment;
    
    public void Interact(Virologist v)
    {
        hasEquipment = Logger.AskQuestion("Does the bunker have equipment in it?");
        if(hasEquipment)
        {
            v.AddEquipment(equipment);
        }
    }
}
