using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    public class FieldLabBear : FieldLab
    {
        public override void Interact(Virologist v)
        {
            Logger.NewFunctionCall(this, "Interact");
            Bear b = new Bear();
            b.Apply(null, v);
            Logger.ReturnFunction();
        }
    }
}