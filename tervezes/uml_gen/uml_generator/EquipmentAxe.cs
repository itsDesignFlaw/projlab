using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    public class EquipmentAxe : Equipment
    {
        private bool sharp = true;
        public override void Use(Virologist target)
        {
            Logger.NewFunctionCall(this, "Use");
            if (sharp)
            {
                target.KillVirologist();
                sharp = false;
            }

            Logger.ReturnFunction();
        }
    }
}