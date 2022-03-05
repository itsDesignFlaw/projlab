


namespace CatchThemAll
{
    public class Program
    {
        public static void main()
        {
            Console.WriteLine("Hello, World!");
        }
    }

    interface iInvItem { string Type { get; set; }
        string GetType() { return Type; }
        
    }
    interface iSteppable { 
        void Step(double curTime) { return; } 
    }
    class Virologist : iSteppable {
        int NukiCount; int AmiCount;

        List<iInvItem> items = new List<iInvItem>();
        List<DeployedVaccine> vaccines = new List<DeployedVaccine>();
        List<DeployedVirus> viruses = new List<DeployedVirus>();

        //Amin áll
        Field mezo;
        /*iSteppable iSteppable
        {
            get => default;
            set
            {
            }
        }

        Map Map
        {
            get => default;
            set
            {
            }
        }*/

        bool hasItem(string item) { return false; }
        bool canMoveTo(Field targetField) { return false; }
    }
    class Map { List<Field> fields = new List<Field>(); }
    class Field { }
    class FieldLab : Field { }
    class FieldBunker : Field { int NukiCount; int AmiCount; }
    class FieldWarehouse : Field { }
    abstract class Equipment : iInvItem
    {
        public string Type { get; set; }

        internal iInvItem iInvItem
        {
            get => default;
            set
            {
            }
        }
    }
    class EquipmentSack : Equipment { }
    class EquipmentGloves : Equipment { }
    class EquipmentCoat : Equipment { }
    class GeneticCode : iInvItem
    {
        public string Type { get; set; }

        internal iInvItem iInvItem
        {
            get => default;
            set
            {
            }
        }
    }
    abstract class Agent : iInvItem
    {
        public string Type { get; set; }

        internal iInvItem iInvItem
        {
            get => default;
            set
            {
            }
        }

        internal DeployedAgent DeployedAgent
        {
            get => default;
            set
            {
            }
        }
    }
    class AgentVirus : Agent { }
    class AgentVaccine : Agent { }
    abstract class DeployedAgent { }
    class DeployedVaccine : DeployedAgent { }
    class DeployedVirus : DeployedAgent { }

    //manager classes

    class Timer { List<iSteppable> steppable_reg = new List<iSteppable>(); }
    class GameManager { Map gameMap = new Map();

        internal Map Map
        {
            get => default;
            set
            {
            }
        }

        void startGame() { } 
    }
}