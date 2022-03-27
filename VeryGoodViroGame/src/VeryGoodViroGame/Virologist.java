package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Virologist.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//


import java.util.ArrayList;
import java.util.List;

public class Virologist
{
    iMoveStrategy moveStrategy = new MSSimple();
    List<InvItem> items = new ArrayList<>();
    List<GeneticCode> learntCodes = new ArrayList<>();
    List<Agent> stash = new ArrayList<>();
    
    //Amin áll
    Field mezo = new Field();
    private Resource resource = new Resource();
    private List<Equipment> equipments;
    
    public void SetResource(Resource r)
    {
        resource = r;
    }
    
    public void SetField(Field f)
    {
        mezo = f;
    }
    
    public boolean IsParalyzed()
    {
        Logger.NewFunctionCall("Virologist.IsParalyzed");
        
        for(InvItem item : items)
        {
            if(item.IsParalyzed())
            {
                return true;
            }
        }
        Logger.ReturnFunction();
        return false;
    }
    
    //Összeadja az items listán a GetMaxResource értékét, majd hozzáaadja az alap értéket
    public void AddResource(Resource ammount)
    {
        Logger.NewFunctionCall("Virologist.AddResource");
        int maxResource = 20;
        for(InvItem item : items)
        {
            maxResource += item.GetMaxResource();
        }
        resource.Add(ammount);
        Logger.ReturnFunction();
    }
    
    public Resource RemoveResource(Resource ammount)
    {
        Logger.NewFunctionCall("Virologist.RemoveResource");
        IsParalyzed();
        Logger.ReturnFunction();
        return new Resource();
    }
    
    //Craftol egy vírust
    public void CraftVirus(GeneticCode code)
    {
        Logger.NewFunctionCall("Virologist.CraftVirus");
        for(InvItem item : items)
        {
            if(!item.CanCraft())
            {
                Logger.ReturnFunction();
                return;
            }
        }
        Resource cost = code.GetCost();
        //has enough resource:
        resource.Remove(cost);
        Agent created = code.CreateVirus();
        AddAgentToStash(created);
        Logger.ReturnFunction();
    }
    
    public void AddAgentToStash(Agent agent)
    {
        Logger.NewFunctionCall("Virologist.AddAgentToStash");
        Logger.ReturnFunction();
        
    }
    
    public void CraftVaccine(GeneticCode code)
    {
        Logger.NewFunctionCall("Virologist.CraftVaccine");
        for(InvItem item : items)
        {
            if(!item.CanCraft())
            {
                Logger.ReturnFunction();
                return;
            }
        }
        Resource cost = code.GetCost();
        //has enough resource:
        if(Logger.AskQuestion("Sufficient resources available to thy liking, sire"))
        {
            resource.Remove(cost);
            Agent created = code.CreateVaccine();
            AddAgentToStash(created);
        }
        Logger.ReturnFunction();
    }
    
    //Mindegyiknél feltételezzük, hogy meg tudja érinteni, előtte ellenőrizzük
    //When somebody uses an agent on you
    public boolean ApplyAgent(Agent agent, Virologist source)
    {
        Logger.NewFunctionCall("Virologist.ApplyAgent");
        for(InvItem item : items)
        {
            if(!item.CanAgentBeApplied(agent, source))
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
    public void UseAgent(Agent agent, Virologist target)
    {
        Logger.NewFunctionCall("Virologist.UseAgent");
        for(InvItem item : items)
        {
            if(!item.CanApplyAgent())
            {
                Logger.ReturnFunction();
                return;
            }
        }
        agent.Apply(this, target);
        Logger.ReturnFunction();
    }
    
    //Felszedi a mezőn lévő cuccokat, azaz meghívja az Interact fv-ét
    public void InteractWithField()
    {
        Logger.NewFunctionCall("Virologist.InteractWithField");
        for(InvItem item : items)
        {
            if(!item.CanInteract())
            {
                Logger.ReturnFunction();
                return;
            }
        }
        mezo.Interact(this);
        Logger.ReturnFunction();
    }
    
    //A target-tól ellopja az equipment-et
    public void StealEquipmentFromViro(Virologist target, Equipment equipment)
    {
        Logger.NewFunctionCall("Virologist.StealEquipmentFromViro");
        for(InvItem item : items)
        {
            if(!item.CanSteal())
            {
                Logger.ReturnFunction();
                return;
            }
        }
        if(target.RemoveEquipment(equipment))
        {
            AddEquipment(equipment);
        }
        Logger.ReturnFunction();
    }
    
    public void StealResourceFromViro(Virologist target, Resource amount)
    {
        Logger.NewFunctionCall("Virologist.StealResourceFromViro");
        for(InvItem item : items)
        {
            if(!item.CanSteal())
            {
                Logger.ReturnFunction();
                return;
            }
        }
        Resource removed = target.RemoveResource(amount);
        AddResource(removed);
        Logger.ReturnFunction();
    }
    
    //Eltávolítja a virológustól az equipment-et
    public boolean RemoveEquipment(Equipment equipment)
    {
        Logger.NewFunctionCall("Virologist.RemoveEquipment");
        IsParalyzed();
        Logger.ReturnFunction();
        return true;
    }
    
    public void AddEquipment(Equipment equipment)
    {
        Logger.NewFunctionCall("Virologist.AddEquipment");
        AddItem(equipment);
        Logger.ReturnFunction();
    }
    
    //Elveszi a item-et
    public void RemoveItem(InvItem item)
    {
        Logger.NewFunctionCall("Virologist.RemoveItem");
        Logger.ReturnFunction();
    }
    
    //Hozzáadja a item-et
    public void AddItem(InvItem item)
    {
        Logger.NewFunctionCall("Virologist.AddItem");
        Logger.ReturnFunction();
    }
    
    public void LearnGeneticCode(GeneticCode code)
    {
        Logger.NewFunctionCall("Virologist.LearnGeneticCode");
        //if(learnt all codes)
        if(Logger.AskQuestion("Did she/he learned all the genetikus codes?"))
            GameManager.EndGame(this);
        
        Logger.ReturnFunction();
    }
    
    public Field GetField()
    {
        Logger.NewFunctionCall("Virologist.GetField");
        Logger.ReturnFunction();
        return mezo;
    }
    
    public void ChangeMoveStrategy(iMoveStrategy strategy)
    {
        Logger.NewFunctionCall("Virologist.ChangeMoveStrategy");
        moveStrategy = strategy;
        Logger.ReturnFunction();
    }
    
    //Hiper szuper magic függvény, mindent is tud
    public void RemoveMoveStrategy(iMoveStrategy strategy)
    {
        Logger.NewFunctionCall("Virologist.RemoveMoveStrategy");
        //42
        Logger.ReturnFunction();
    }
    
    public void MoveTo(Field to)
    {
        Logger.NewFunctionCall("Virologist.MoveTo");
        moveStrategy.ExecuteMove(this, mezo, to);
        Logger.ReturnFunction();
    }
}

