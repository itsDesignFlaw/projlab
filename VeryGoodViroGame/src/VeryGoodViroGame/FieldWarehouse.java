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


public class FieldWarehouse extends Field
{
    private Resource resources;
    
    public void Interact(Virologist v)
    {
        v.AddResource(resources);
    }
}

