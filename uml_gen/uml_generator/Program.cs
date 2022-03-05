


namespace CatchThemAll
{

    public abstract class InvItem { 
        



    }
    public interface iSteppable { 
        void Step(double curTime) { return; } 
    }
    public class Virologist : iSteppable {
        List<InvItem> items = new List<InvItem>();
        List<GeneticCode> learntCodes = new List<GeneticCode>(); 

        //Amin áll
        Field mezo;
        private Resource resource;
        private System.Collections.Generic.List<Equipment> equipments;

        void MoveTo(Field targetField) {  }

        public void AddResource(Resource resource)
        {
            throw new System.NotImplementedException();
        }
    }
    public class Map { List<Field> fields = new List<Field>();

        public void GenerateMap()
        {
            throw new System.NotImplementedException();
        }
    }
    public class Field 
    {
        List<Virologist> virologists;
        public virtual bool AcceptViro(Virologist v) { return false; }
        public virtual bool RemoveViro(Virologist v) { return false; }
    }
    public class FieldLab : Field
    {
        private GeneticCode code; public override bool AcceptViro(Virologist v) { return true; }
    }
    public class FieldBunker : Field { private Resource resources; public override bool AcceptViro(Virologist v) { return true; } }
    public class FieldWarehouse : Field
    {
        private Equipment equipment; public override bool AcceptViro(Virologist v) { return true; }
    }
    public abstract class Equipment : InvItem
    {
    }
    public class EquipmentSack : Equipment
    {
        public int GetMaxResource()
        {
            throw new System.NotImplementedException();
        }
    }
    public class EquipmentGloves : Equipment
    {
        public bool CanAgentBeApplied()
        {
            throw new System.NotImplementedException();
        }
    }
    public class EquipmentCoat : Equipment
    {
        public bool CanAgentBeApplied()
        {
            throw new System.NotImplementedException();
        }
    }
    public class GeneticCode
    {
        public string Type { get; set; }

        public Agent agent
        {
            get => default;
            set
            {
            }
        }

        public Agent CreateVirus()
        {
            throw new System.NotImplementedException();
        }

        public Agent CreateVaccine()
        {
            throw new System.NotImplementedException();
        }
    }
    public abstract class Agent : InvItem, iSteppable
    {
        public void Apply(Virologist target)
        {
            throw new System.NotImplementedException();
        }
    }

    //manager classes

    public class Timer { List<iSteppable> steppable_reg = new List<iSteppable>();

        public void Step()
        {
            throw new System.NotImplementedException();
        }
    }
    public class GameManager { Map gameMap = new Map();

        internal Map Map
        {
            get => default;
            set
            {
            }
        }

        void startGame() { } 
    }

    public class Resource
    {
        private int ami;
        private int nuki;

        public void AddAmi(int ami)
        {
            throw new System.NotImplementedException();
        }

        public void RemoveAmi(int ami)
        {
            throw new System.NotImplementedException();
        }
        public void AddNuki(int nuki)
        {
            throw new System.NotImplementedException();
        }

        public void RemoveNuki(int nuki)
        {
            throw new System.NotImplementedException();
        }
    }

    public class Dance : Agent
    {
        public bool Move(Virologist v)
        {
            throw new System.NotImplementedException();
        }
    }

    public class Forget : Agent
    {
        public bool CanRemember()
        {
            throw new System.NotImplementedException();
        }
    }

    public class Paralyze : Agent
    {
        public bool Move(Virologist v)
        {
            throw new System.NotImplementedException();
        }

        public void CanCastSpell()
        {
            throw new System.NotImplementedException();
        }

        public void CanAttack()
        {
            throw new System.NotImplementedException();
        }
    }

    public class Protect : Agent
    {
        public bool CanBeInfected()
        {
            throw new System.NotImplementedException();
        }
    }
}