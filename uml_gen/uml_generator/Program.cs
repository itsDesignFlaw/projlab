


namespace CatchThemAll
{

    public interface iSteppable
    {
        void Step(double curTime);
    }
    public class Virologist : iSteppable
    {
        List<InvItem> items = new List<InvItem>();
        List<GeneticCode> learntCodes = new List<GeneticCode>();

        //Amin áll
        Field mezo;
        private Resource resource;
        private System.Collections.Generic.List<Equipment> equipments;

        public void MoveTo(Field targetField)
        { }

        public bool IsParalyzed()
        { return false; }

        //Összeadja az items listán a GetMaxResource értékét, majd hozzáaadja az alap értéket
        public void AddResource(Resource ammount)
        {
            throw new System.NotImplementedException();
        }
        public void RemoveResource(Resource ammount)
        {
            throw new System.NotImplementedException();
        }

        public void Step(double curTime)
        {
            throw new NotImplementedException();
        }

        //Craftol egy vírust
        public void CraftVirus(GeneticCode code)
        {
            throw new System.NotImplementedException();
        }
        public void CraftVaccine(GeneticCode code)
        {
            throw new System.NotImplementedException();
        }
        //Mindegyiknél feltételezzük, hogy meg tudja érinteni, előtte ellenőrizzük
        //When somebody uses an agent on you
        public void ApplyAgent(Agent agent, Virologist source)
        {
            throw new System.NotImplementedException();
        }
        //When you want to apply an agent on somebody
        public void UseAgent(Agent agent, Virologist target)
        {
            throw new System.NotImplementedException();
        }

        //Felszedi a mezőn lévő cuccokat, azaz meghívja az Interact fv-ét
        public void InteractWithMap()
        {
            throw new System.NotImplementedException();
        }

        //A target-tól ellopja az equipment-et
        public void StealEquipmentFromViro(Virologist target, Equipment equipment)
        {
            throw new System.NotImplementedException();
        }
        public void StealResourceFromViro(Virologist target, Resource ammount)
        {
            throw new System.NotImplementedException();
        }

        //Eltávolítja a virológustól az equipment-et
        public void RemoveEquipment(Equipment equipment)
        {
            throw new System.NotImplementedException();
        }

        public void AddEquipment(Equipment equipment)
        {
            throw new System.NotImplementedException();
        }

        //Elveszi a item-et
        public void RemoveItem(InvItem item)
        {
            throw new System.NotImplementedException();
        }
        //Hozzáadja a item-et
        public void AddItem(InvItem item)
        {
            throw new System.NotImplementedException();
        }
    }
    public class Map
    {
        List<Field> fields = new List<Field>();

        public void GenerateMap()
        {
            throw new System.NotImplementedException();
        }
    }
    public class Field
    {
        List<Virologist> virologists;
        public void AcceptViro(Virologist v) { }
        public virtual bool Interact(Virologist v) { return false; }
        public virtual bool RemoveViro(Virologist v) { return false; }
    }
    public class FieldLab : Field
    {
        private GeneticCode code; public override bool Interact(Virologist v) { return true; }
    }
    public class FieldBunker : Field { private Resource resources; public override bool Interact(Virologist v) { return true; } }
    public class FieldWarehouse : Field
    {
        private Equipment equipment; public override bool Interact(Virologist v) { return true; }
    }
    public abstract class InvItem
    {
        public virtual int GetMaxResource()
        {
            throw new System.NotImplementedException();
        }
        public virtual bool CanAgentBeApplied(Agent agent, Virologist source)
        {
            throw new System.NotImplementedException();
        }
        public virtual bool CanRemember()
        {
            throw new System.NotImplementedException();
        }
        public virtual bool CanMove(Virologist v)
        {
            throw new System.NotImplementedException();
        }

        public virtual bool CanApplyAgent()
        {
            throw new System.NotImplementedException();
        }

        public virtual void CanAttack()
        {
            throw new System.NotImplementedException();
        }
        public virtual bool CanBeInfected()
        {
            throw new System.NotImplementedException();
        }
        public virtual bool IsParalyzed()
        {
            return false;
        }

    }
    public abstract class Equipment : InvItem
    {
    }
    public class EquipmentSack : Equipment
    {
        public override int GetMaxResource()
        {
            throw new System.NotImplementedException();
        }
    }
    public class EquipmentGloves : Equipment
    {
        public override bool CanAgentBeApplied(Agent agent, Virologist source)
        {
            throw new System.NotImplementedException();
        }
    }
    public class EquipmentCoat : Equipment
    {
        public override bool CanAgentBeApplied(Agent agent, Virologist source)
        {
            throw new System.NotImplementedException();
        }
    }
    public class GeneticCode
    {
        private Resource cost;

        private Agent agent;

        public Agent CreateVirus()
        {
            throw new System.NotImplementedException();
        }

        public Agent CreateVaccine()
        {
            throw new System.NotImplementedException();
        }

        public Resource GetCost()
        {
            throw new System.NotImplementedException();
        }
    }
    public abstract class Agent : InvItem, iSteppable
    {
        int activeTime;
        public virtual void Apply(Virologist source, Virologist target)
        {
            throw new System.NotImplementedException();
        }

        public virtual void Step(double curTime)
        {
            throw new NotImplementedException();
        }
    }

    //manager classes

    public class Timer
    {
        List<iSteppable> steppable_reg = new List<iSteppable>();

        public void Step()
        {
            throw new System.NotImplementedException();
        }
    }
    public class GameManager
    {
        Map gameMap = new Map();

        Map Map;

        public void StartGame() { }
        public void EndGame() { }
    }

    public class Resource
    {
        private int ami;
        private int nuki;
        public void Add(Resource resource)
        {
            throw new System.NotImplementedException();
        }

        public bool Remove(Resource resource)
        {
            throw new System.NotImplementedException();
        }
        /*public void AddAmi(int ami)
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
        }*/
    }

    public class Dance : Agent
    {
        //Vagy tudja kire van rákenve, akkor nem kell átadni Virologist-ot
        public override bool CanMove(Virologist v)
        {
            throw new System.NotImplementedException();
        }
    }

    public class Forget : Agent
    {
        public override bool CanRemember()
        {
            throw new System.NotImplementedException();
        }
    }

    public class Paralyze : Agent
    {
        public override bool CanMove(Virologist v)
        {
            throw new System.NotImplementedException();
        }

        public override bool CanApplyAgent()
        {
            throw new System.NotImplementedException();
        }

        public override void CanAttack()
        {
            throw new System.NotImplementedException();
        }
        public override bool IsParalyzed()
        {
            return true;
        }
    }

    public class Protect : Agent
    {
        public override bool CanMove(Virologist v)
        {
            throw new System.NotImplementedException();
        }

        public override bool CanApplyAgent()
        {
            throw new System.NotImplementedException();
        }

        public override void CanAttack()
        {
            throw new System.NotImplementedException();
        }
    }

    public class Vaccine : Agent
    {
        private Agent agentToCure;

        public override void Apply(Virologist target)
        {
            throw new System.NotImplementedException();
        }
    }
}