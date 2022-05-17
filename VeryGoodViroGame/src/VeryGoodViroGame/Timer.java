package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Timer.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//


import java.util.ArrayList;
import java.util.List;

public class Timer
{
    static List<iSteppable> steppable_reg = new ArrayList<>();
    
    public static void Step()
    {
        List<iSteppable> temp = new ArrayList<>(steppable_reg);
        temp.forEach(iSteppable::Step);
    }
    
    public static void AddSteppable(iSteppable item)
    {
        if(!steppable_reg.contains(item))
            steppable_reg.add(item);
    }
    
    public static void RemoveSteppable(iSteppable step)
    {
        steppable_reg.remove(step);
    }
}
