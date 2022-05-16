package VeryGoodViroGame;

import VeryGoodViroGame.Agent.Agent;
import VeryGoodViroGame.Agent.GeneticCode;
import VeryGoodViroGame.Equipment.Equipment;
import VeryGoodViroGame.Field.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewController
{
    private boolean useItem = false;
    public Virologist activeViro;
    View view;
    
    public ViewController()
    {
        GameManager.StartGame();
        view = new View(this);
        view.Init();
        Test();
    }
    
    public void Test()
    {
        Virologist v = new Virologist();
        Field f = new Field();
        Field l = new FieldLab();
        Field b = new FieldBunker();
        Field bear = new FieldLabBear();
        Field ware = new FieldWarehouse();
        AddObject(v, "viro");
        AddObject(l, "lab");
        AddObject(f, "field");
        AddObject(b, "bunker");
        AddObject(bear, "bearlab");
        AddObject(ware, "ware");
        
        v.SetField(f);
        f.AddNeighbour(l);
        f.AddNeighbour(b);
        f.AddNeighbour(bear);
        f.AddNeighbour(ware);
        Update(v);
    }
    
    public void Update(Virologist v)
    {
        activeViro = v;
        use = null;
        view.Clear();
        
        Field f = v.GetField();
        List<Field> fields = f.GetNeighbours();
        List<GeneticCode> codes = v.learntCodes;
        List<Virologist> viros = new ArrayList<>(f.GetVirologists());
        List<InvItem> effect = v.items;
        List<Agent> stash = new ArrayList<>(v.stash);
        List<Equipment> eq = v.equipments;
        
        
        view.DrawMap(f, fields);
        view.DrawViros(viros);
        
        ArrayList<InvItem> it = new ArrayList<>(stash);
        it.addAll(stash);
        view.DrawItems(it);
        
        view.DrawGeneticCodes(codes);
        view.DrawEffects(effect.stream().filter(x -> x instanceof Agent).map(x -> (Agent) x).collect(Collectors.toList()));
        
        
        view.Repaint();
    }
    
    public void AddObject(Object o, String s)
    {
        view.AddObject(o, s);
    }
    
    public void RemoveObject(Object o)
    {
        view.RemoveObject(o);
    }
    
    public void MoveViro(Field f)
    {
        activeViro.MoveTo(f);
        Update(activeViro);
    }
    
    public void CraftVirus(GeneticCode gc)
    {
        activeViro.CraftVirus(gc);
    }
    
    public void CraftVaccine(GeneticCode gc)
    {
        activeViro.CraftVaccine(gc);
    }
    
    public void UseAgentOnViro(Agent a, Virologist v)
    {
        activeViro.UseAgent(a, v);
    }
    
    public void Interact()
    {
        activeViro.InteractWithField();
    }
    
    public void StealEquipment(Virologist target, Equipment eq)
    {
        activeViro.StealEquipmentFromViro(target, eq);
    }
    
    public void StealResource(Virologist target)
    {
        activeViro.StealResourceFromViro(target);
    }
    
    public void UseEquipment(Equipment e, Virologist target)
    {
        activeViro.UseEquipment(e, target);
    }
    
    public void DropEquipment(Equipment e)
    {
        activeViro.RemoveEquipment(e);
    }
    
    private Agent use = null;
    
    public void SetAgent(Agent a)
    {
    
    }
}
