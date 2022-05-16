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
        view = new View();
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
        v.SetField(f);
        f.AddNeighbour(l);
        f.AddNeighbour(b);
        f.AddNeighbour(bear);
        f.AddNeighbour(ware);
        f.AddNeighbour(new FieldLabBear());
        f.AddNeighbour(new Field());
        f.AddNeighbour(new FieldBunker());
        Update(v);
    }
    
    public void Update(Virologist v)
    {
        activeViro = v;
        
        Field f = v.GetField();
        List<Field> fields = f.GetNeighbours();
        List<GeneticCode> codes = v.learntCodes;
        List<Virologist> viros = new ArrayList<>();
        viros.add(v);
        viros.addAll(f.GetVirologists());
        List<InvItem> effect = v.items;
        List<Agent> stash = new ArrayList<>(v.stash);
        List<Equipment> eq = v.equipments;
        
        
        view.DrawMap(f.GetDrawString(), fields.stream().map(Field::GetDrawString).collect(Collectors.toList()));
        view.DrawViros(viros.stream().map(Virologist::GetDrawString).collect(Collectors.toList()));
        
        view.DrawItems(stash.stream().map(Agent::GetDrawString).collect(Collectors.toList()));
        view.DrawItems(eq.stream().map(Equipment::GetDrawString).collect(Collectors.toList()));
        
        view.DrawGeneticCodes(codes.stream().map(GeneticCode::GetDrawString).collect(Collectors.toList()));
        view.DrawEffects(effect.stream().map(InvItem::GetDrawString).collect(Collectors.toList()));
        //Hogyan legyenek az effectek?
        //Mármint hol? Virológuson?
        
        view.Repaint();
    }
    
    
    public void MoveViro(Field f)
    {
        activeViro.MoveTo(f);
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
    
}
