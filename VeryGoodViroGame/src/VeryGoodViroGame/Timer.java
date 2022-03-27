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

public class Timer {
    static List<iSteppable> steppable_reg = new ArrayList<>();

    public static void Step() {
        Logger.NewFunctionCallLegacy("Timer", "Step");
        for (iSteppable s : steppable_reg) {
            s.Step();
        }
        Logger.ReturnFunction();
    }

    public static void AddSteppable(iSteppable item) {
        Logger.NewFunctionCallLegacy("Timer", "AddSteppable");
        steppable_reg.add(item);
        Logger.ReturnFunction();
    }

    public static void RemoveSteppable(iSteppable step) {
        Logger.NewFunctionCallLegacy("Timer", "RemoveSteppable");
        Logger.ReturnFunction();
    }
}
