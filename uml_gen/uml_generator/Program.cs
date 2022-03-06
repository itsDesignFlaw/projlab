


namespace CatchThemAll
{

    public interface iSteppable
    {
        void Step();
    }
    public class Virologist
    {
        List<InvItem> items = new List<InvItem>();
        List<GeneticCode> learntCodes = new List<GeneticCode>();
        List<Agent> stash = new List<Agent>();

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
        public void InteractWithField()
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

        public void LearnGeneticCode(GeneticCode code)
        {
            throw new System.NotImplementedException();
        }

        public void AddIAgentToStash(Agent agent)
        {
            throw new System.NotImplementedException();
        }

        public Field GetField()
        {
            throw new System.NotImplementedException();
        }

        public void MoveDirectly(Field mezo)
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

        public Field GetRandomNeighbour(Field mezo)
        {
            throw new System.NotImplementedException();
        }
    }
    public class Field
    {
        List<Virologist> virologists;
        public void AcceptViro(Virologist v) { }
        public virtual void Interact(Virologist v) {  }
        public virtual void RemoveViro(Virologist v) {  }
    }
    public class FieldLab : Field
    {
        private GeneticCode code; public override void Interact(Virologist v) {  }
    }
    public class FieldWarehouse : Field { private Resource resources; public override void Interact(Virologist v) {  } }
    public class FieldBunker : Field
    {
        private Equipment equipment;
        private bool hasEquipment;

        public override void Interact(Virologist v) {  }
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
        public virtual bool CanSteal()
        {
            throw new System.NotImplementedException();
        }
        public virtual bool CanMove(Virologist v)
        {
            throw new System.NotImplementedException();
        }
        public virtual bool IsParalyzed()
        {
            return false;
        }
        public virtual bool CanCraft()
        {
            return false;
        }

        public virtual bool CanApplyAgent()
        {
            throw new System.NotImplementedException();
        }
    }
    public abstract class Equipment : InvItem
    {
    }
    public class EquipmentSack : Equipment
    {
        private Resource amount;

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
        //Akin van
        Virologist host;
        public virtual void Apply(Virologist source, Virologist target)
        {
            throw new System.NotImplementedException();
        }

        public virtual void Step()
        {
            throw new NotImplementedException();
        }

        public Agent Clone()
        {
            throw new System.NotImplementedException();
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

        public void AddSteppable(iSteppable item)
        {
            throw new System.NotImplementedException();
        }

        public void RemoveSteppable(iSteppable step)
        {
            throw new System.NotImplementedException();
        }
    }
    public class GameManager
    {

        Map Map;

        public void StartGame() { }
        public void EndGame(Virologist winner) { }
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
        public override bool IsParalyzed()
        {
            return true;
        }
        public override bool CanMove(Virologist v)
        {
            throw new System.NotImplementedException();
        }
        public override bool CanApplyAgent()
        {
            throw new System.NotImplementedException();
        }
        public override bool CanSteal()
        {
            throw new System.NotImplementedException();
        }
        public override bool CanCraft()
        {
            throw new System.NotImplementedException();
        }

    }

    public class Protect : Agent
    {

        public override bool CanAgentBeApplied(Agent agent, Virologist source)
        {
            throw new System.NotImplementedException();
        }
    }

    public class Vaccine : Agent
    {
        private Agent agentToCure;

        public Vaccine(Agent agentToCure)
        {

        }

        public override void Apply(Virologist source, Virologist target)
        {
            throw new System.NotImplementedException();
        }
    }
}