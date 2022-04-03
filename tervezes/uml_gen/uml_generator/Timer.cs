using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    public class Timer
    {
        static List<iSteppable> steppable_reg = new List<iSteppable>();
        public static void Step()
        {
            Logger.NewFunctionCallLegacy("Timer", "Step");
            foreach (iSteppable s in steppable_reg)
            {
                s.Step();
            }

            Logger.ReturnFunction();
        }

        public static void AddSteppable(iSteppable item)
        {
            Logger.NewFunctionCallLegacy("Timer", "AddSteppable");
            steppable_reg.Add(item);
            Logger.ReturnFunction();
        }

        public static void RemoveSteppable(iSteppable step)
        {
            Logger.NewFunctionCallLegacy("Timer", "RemoveSteppable");
            Logger.ReturnFunction();
        }
    }
} //
