using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    public class Bear : Agent
    {
        public Bear(): base()
        {
            //strategy = new MSBear();
        }

        public override void Apply(Virologist source, Virologist target)
        {
            base.Apply(source, target);
        }

        
        public override void Step()
        {
        }
    }
}