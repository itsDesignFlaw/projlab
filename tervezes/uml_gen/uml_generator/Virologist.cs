using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace VeryGoodViroGame
{
    /// <summary>
    /// Egy játékos által irányított virológust reprezentál a játékban.
    /// Ez az osztály felelős a virológus által véghezvihető tevékenységek megvalósításáért,
    /// ilyenek például a mozgás, az erőforrások gyűjtése, és az ágensek létrehozása.
    /// </summary>
    public class Virologist
    {
        iMoveStrategy moveStrategy = new MSSimple();
        List<InvItem> items = new List<InvItem>();
        List<GeneticCode> learntCodes = new List<GeneticCode>();
        List<Agent> stash = new List<Agent>();
        //Amin áll
        Field mezo = new Field();
        private Resource resource = new Resource();
        private List<Equipment> equipments = new List<Equipment>();
        /// <summary>
        /// Beállítja a virológus erőforrás tagváltozóját.
        /// </summary>
        /// <param name="r">erőforrás</param>
        public virtual void SetResource(Resource r)
        {
            resource = r;
        }

        /// <summary>
        /// Beállítja a mezőt amin a virológus áll.
        /// </summary>
        /// <param name="f">A mező ahol a virológus állni fog.</param>
        public virtual void SetField(Field f)
        {
            mezo = f;
        }

        /// <summary>
        /// A metódus visszatér egy igaz vagy hamis értékkel,
        /// annak megfeleleőn, hogy az adott időpillanatban meg van e bénítva a virológus.
        /// </summary>
        /// <returns>Igaz, ha a virológus le van bénulva, másképp hamis.</returns>
        public virtual bool IsParalyzed()
        {
            Logger.NewFunctionCall(this, "IsParalyzed");
            foreach (InvItem item in items)
            {
                if (item.IsParalyzed())
                {
                    return true;
                }
            }

            Logger.ReturnFunction();
            return false;
        }

        /// <summary>
        /// A virológus tartalékát növeli a kapott anyaggal.
        /// </summary>
        /// <param name="amount">Az anyagmennyiség amit a virológusnak adunk.</param>
        public virtual void AddResource(Resource amount)
        {
            Logger.NewFunctionCall(this, "AddResource");

            //Összeadja az items listán a GetMaxResource értékét, majd hozzáaadja az alap értéket
            int maxResource = 20;
            foreach (InvItem item in items)
            {
                maxResource += item.GetMaxResource();
            }

            resource.Add(amount);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Elvesz a virológustól anyagot.
        /// </summary>
        /// <param name="amount">Az elvett anyag mennyisége.</param>
        /// <returns>resource</returns>
        public virtual Resource RemoveResource(Resource amount)
        {
            Logger.NewFunctionCall(this, "RemoveResource");
            if (IsParalyzed())
                resource.Remove(amount);
            Logger.ReturnFunction();
            return new Resource();
        }

        /// <summary>
        /// Craftol egy vírust
        /// </summary>
        /// <param name="code">a genetikai kód amivel a vírus előáll</param>
        public virtual void CraftVirus(GeneticCode code)
        {
            Logger.NewFunctionCall(this, "CraftVirus");
            foreach (InvItem item in items)
            {
                if (!item.CanCraft())
                {
                    Logger.ReturnFunction();
                    return;
                }
            }

            Resource cost = code.GetCost();

            //has enough resource:
            if (Logger.AskQuestion("Sufficient resources available to thy liking, sire"))
            {
                resource.Remove(cost);
                Agent created = code.CreateVirus();
                AddAgentToStash(created);
            }

            Logger.ReturnFunction();
        }

        /// <summary>
        /// Egy ágenst hozzáad a virológus tárolójához.
        /// </summary>
        /// <param name="agent">Az ágens amit hozzáadunk a virológus tárolójához.</param>
        public virtual void AddAgentToStash(Agent agent)
        {
            Logger.NewFunctionCall(this, "AddAgentToStash");
            stash.Add(agent);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Ezzel a függvénnyel  a virológus lecraftolja az adott genetikai kódhoz tartozó vakcinát.
        /// </summary>
        /// <param name="code">Az előállítandó vakcinához tartozó genetikai kód.</param>
        public virtual void CraftVaccine(GeneticCode code)
        {
            Logger.NewFunctionCall(this, "CraftVaccine");
            foreach (InvItem item in items)
            {
                if (!item.CanCraft())
                {
                    Logger.ReturnFunction();
                    return;
                }
            }

            Resource cost = code.GetCost();

            //has enough resource:
            if (Logger.AskQuestion("Sufficient resources available to thy liking, sire"))
            {
                resource.Remove(cost);
                Agent created = code.CreateVaccine();
                AddAgentToStash(created);
            }

            Logger.ReturnFunction();
        }

        //Mindegyiknél feltételezzük, hogy meg tudja érinteni, előtte ellenőrizzük
        //When somebody uses an agent on you
        /// <summary>
        /// Az ágenst virológushoz adó metódus.
        /// </summary>
        /// <param name="agent">a felkenődő ágens</param>
        /// <param name="source">a virológus aki az ágenst felkente</param>
        /// <returns>boolean visszatérési érték, sikeres volt-e a kenés</returns>
        public virtual bool ApplyAgent(Agent agent, Virologist source)
        {
            Logger.NewFunctionCall(this, "ApplyAgent");
            foreach (InvItem item in items)
            {
                if (!item.CanAgentBeApplied(agent, source))
                {
                    Logger.ReturnFunction();
                    return false;
                }
            }

            AddItem(agent);
            Logger.ReturnFunction();
            return true;
        }

        //When you want to apply an agent on somebody
        /// <summary>
        /// Ágens másik virológusra való felkenését megvalósító metódus.
        /// </summary>
        /// <param name="agent">a felkenni kívánt metódus</param>
        /// <param name="target">virológus akire az ágens felkenjük</param>
        public virtual void UseAgent(Agent agent, Virologist target)
        {
            Logger.NewFunctionCall(this, "UseAgent");
            foreach (InvItem item in items)
            {
                if (!item.CanApplyAgent())
                {
                    Logger.ReturnFunction();
                    return;
                }
            }

            agent.Apply(this, target);
            Logger.ReturnFunction();
        }

        //Felszedi a mezőn lévő cuccokat, azaz meghívja az Interact fv-ét
        /// <summary>
        /// A virológus kapcsolatba lép az adott mezővel, amin tartózkodik.
        /// </summary>
        public virtual void InteractWithField()
        {
            Logger.NewFunctionCall(this, "InteractWithField");
            foreach (InvItem item in items)
            {
                if (!item.CanInteract())
                {
                    Logger.ReturnFunction();
                    return;
                }
            }

            mezo.Interact(this);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// A paraméterként kapott célponttól ellopja a szintén paraméterben jelölt felszerelést,
        /// amennyiben a célpont lebénult állapotban van.
        /// </summary>
        /// <param name="target">A célpont akitől el akarja lopni.</param>
        /// <param name="equipment">A felszerelés amit el akar lopni.</param>
        public virtual void StealEquipmentFromViro(Virologist target, Equipment equipment)
        {
            Logger.NewFunctionCall(this, "StealEquipmentFromViro");
            foreach (InvItem item in items)
            {
                if (!item.CanSteal())
                {
                    Logger.ReturnFunction();
                    return;
                }
            }

            if (target.RemoveEquipment(equipment))
            {
                AddEquipment(equipment);
            }

            Logger.ReturnFunction();
        }

        /// <summary>
        /// A paraméterként kapott célponttól ellopja
        /// a szintén paraméterben jelölt mennyiségű anyagot, amennyiben a célpont lebénult állapotban van.
        /// </summary>
        /// <param name="target">a virolügus akitől lop</param>
        /// <param name="amount">a mennyiség amit ellop</param>
        public virtual void StealResourceFromViro(Virologist target, Resource amount)
        {
            Logger.NewFunctionCall(this, "StealResourceFromViro");
            foreach (InvItem item in items)
            {
                if (!item.CanSteal())
                {
                    Logger.ReturnFunction();
                    return;
                }
            }

            Resource removed = target.RemoveResource(amount);
            AddResource(removed);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Eltávolítja a virológustól az equipment-et
        /// </summary>
        /// <param name="equipment">amit eltávolit</param>
        /// <returns>visszaadja, hogy el tudta-e távolitani</returns>
        public virtual bool RemoveEquipment(Equipment equipment)
        {
            Logger.NewFunctionCall(this, "RemoveEquipment");
            IsParalyzed();
            DestroyEquipment(equipment);
            Logger.ReturnFunction();
            return true;
        }

        public virtual void DestroyEquipment(Equipment equipment)
        {
            Logger.NewFunctionCall(this, "DestroyEquipment");
            equipments.Remove(equipment);
            items.Remove(equipment);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Hozzáadjuk a virológushoz az equipmentet.
        /// </summary>
        /// <param name="equipment">a felszerelés amit a virológushoz adunk</param>
        public virtual void AddEquipment(Equipment equipment)
        {
            Logger.NewFunctionCall(this, "AddEquipment");
            this.equipments.Add(equipment);
            AddItem(equipment);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// A metódus eltávolítja a paraméterül kapott hatást a virológusról.
        /// </summary>
        /// <param name="item">A hatás amit eltávolít.</param>
        public virtual void RemoveItem(InvItem item)
        {
            Logger.NewFunctionCall(this, "RemoveItem");
            Logger.ReturnFunction();
        }

        /// <summary>
        /// A metódus kifejti a paraméterül kapott hatást a virológusra, és hozzéadja az itemekhez
        /// </summary>
        /// <param name="item">-ezt adja hozzá</param>
        public virtual void AddItem(InvItem item)
        {
            Logger.NewFunctionCall(this, "AddItem");
            items.Add(item);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// A virológus megtanulja az adott genetikai kódot.
        /// </summary>
        /// <param name="code">ezt tanulja meg éppen</param>
        public virtual void LearnGeneticCode(GeneticCode code)
        {
            Logger.NewFunctionCall(this, "LearnGeneticCode");
            learntCodes.Add(code);

            //if(learnt all codes)
            if (Logger.AskQuestion("Did she/he learned all the genetikus codes?"))
                GameManager.EndGame(this);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Visszaadja a mezőt amin a virológus tartózkodik.
        /// </summary>
        /// <returns>A mező amin a virológus tartózkodik.</returns>
        public virtual Field GetField()
        {
            Logger.NewFunctionCall(this, "GetField");
            Logger.ReturnFunction();
            return mezo;
        }

        /// <summary>
        /// Megváltoztatja a virológus aktuális mozgási stratégiáját.
        /// </summary>
        /// <param name="strategy">erre változtatja meg</param>
        public virtual void ChangeMoveStrategy(iMoveStrategy strategy)
        {
            Logger.NewFunctionCall(this, "ChangeMoveStrategy");
            moveStrategy = strategy;
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Eltávolítja az átadott mozgási stratégiát, és visszarakja az éppen érvényeset.
        /// </summary>
        /// <param name="strategy">ezt távolitja el</param>
        //Hiper szuper magic függvény, mindent is tud
        public virtual void RemoveMoveStrategy(iMoveStrategy strategy)
        {
            Logger.NewFunctionCall(this, "RemoveMoveStrategy");

            //42
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Másik mezőre lépést megvalósító metódus
        /// </summary>
        /// <param name="to">a mező amire lépni szeretnénk</param>
        public virtual void MoveTo(Field to)
        {
            Logger.NewFunctionCall(this, "MoveTo");
            moveStrategy.ExecuteMove(this, mezo, to);
            Logger.ReturnFunction();
        }

        /// <summary>
        /// Nem írja a szöveg, hogy csak a medvével fertőzött Virológust lehet megölni,
        /// szóval mindenkit meg lehet
        /// </summary>
        public virtual void KillVirologist()
        {
            Logger.NewFunctionCall(this, "KillVirologist");

            //dead
            Logger.ReturnFunction();
        }

        public virtual void UseEquipment(Equipment e, Virologist target)
        {
            e.Use(target);
        }
    }
} //
