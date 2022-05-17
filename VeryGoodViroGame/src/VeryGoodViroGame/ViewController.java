package VeryGoodViroGame;

import VeryGoodViroGame.Agent.Agent;
import VeryGoodViroGame.Agent.Dance;
import VeryGoodViroGame.Agent.GeneticCode;
import VeryGoodViroGame.Agent.Paralyze;
import VeryGoodViroGame.Equipment.Equipment;
import VeryGoodViroGame.Equipment.EquipmentAxe;
import VeryGoodViroGame.Field.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import javax.sound.sampled.*;
import javax.swing.text.html.parser.Entity;


public class ViewController
{
    private boolean useItem = false;
    public Virologist activeViro;
    View view;
    MainMenu mainmenu;
    private boolean moved = false;
    
    public ViewController(int NumberOfViros, MainMenu mainmenu)
    {
        this.mainmenu = mainmenu;
        view = new View(this);
        view.Init();
        //Debug mode,betölti a devmapot
        GameManager.DEBUG_MODE_TEST = true;
        GameManager.controller = this;
        GameManager.StartGame(NumberOfViros, new String[]{"Laci", "Maci"});
        Update(GameManager.GetCurrent());
        //Test();
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
        Agent vitus = new Dance(), para = new Paralyze();
        AddObject(vitus, "dance");
        AddObject(para, "paralyze");
        Equipment e = new EquipmentAxe();
        AddObject(e, "axe");
        
        v.AddEquipment(e);
        v.AddAgentToStash(vitus);
        v.AddAgentToStash(para);
        
        f.AddNeighbour(l);
        f.AddNeighbour(b);
        f.AddNeighbour(bear);
        f.AddNeighbour(ware);
        Update(v);
    }
    
    public void Update()
    {
        if(activeViro != null)
            Update(activeViro);
    }
    
    public void Update(Virologist v)
    {
        if(v != activeViro)
        {
            activeViro = v;
            usea = null;
            usee = null;
        }
        view.Clear();
        
        Field f = v.GetField();
        List<Field> fields = f.GetNeighbours();
        List<GeneticCode> codes = v.learntCodes;
        List<InvItem> effect = v.items;
        List<Agent> stash = new ArrayList<>(v.stash);
        List<Equipment> eq = v.equipments;
        
        view.MarkActiveViro(EntityManager.GetObjectName(v));
        view.DrawMap(f, fields);
        
        ArrayList<InvItem> it = new ArrayList<>(stash);
        it.addAll(eq);
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
    
    void cmd(String CMD, String[] args)
    {
        ConsoleIO.RunCMD(CMD, args);
    }
    
    public void MoveViro(Field f)
    {
        activeViro.MoveTo(f);
        moved = true;
        //Update();
       //mark: nem kell neki, majd ha change turn van, a View addig animal
    }
    
    public void CraftVirus(GeneticCode gc)
    {
        activeViro.CraftVirus(gc);
        Update();
    }
    
    public void CraftVaccine(GeneticCode gc)
    {
        activeViro.CraftVaccine(gc);
        Update();
    }
    
    public void UseAgentOnViro(Virologist v)
    {
        if(usea == null)
            return;
        activeViro.UseAgent(usea, v);
        Update();
    }
    
    public void Interact()
    {
        activeViro.InteractWithField();
        Update();
    }
    
    public void StealEquipment(Virologist target, Equipment eq)
    {
        activeViro.StealEquipmentFromViro(target, eq);
        Update();
    }
    
    public void StealResource(Virologist target)
    {
        activeViro.StealResourceFromViro(target);
        Update();
    }
    
    public void UseEquipment(Virologist target)
    {
        if(usee == null)
            return;
        activeViro.UseEquipment(usee, target);
        Update();
    }
    
    public void DropEquipment(Equipment e)
    {
        activeViro.RemoveEquipment(e);
        Update();
    }
    
    public Agent GetSelectedAgent()
    {
        return usea;
    }
    
    public Equipment GetSelectedEq()
    {
        return usee;
    }
    
    private Agent usea = null;
    private Equipment usee = null;
    
    public void SetAgent(Agent a)
    {
        if(usea != a)
            usea = a;
        else
            usea = null;
        Update();
    }
    
    public void SetEquipment(Equipment e)
    {
        if(usee != e)
            usee = e;
        else
            usee = null;
        Update();
    }
    
    public void EndTurn()
    {
        GameManager.EndTurn();
    }
    
    public Clip PlaySound(String path, boolean loop)
    {
        File audioFile = new File(Main.class.getResource("/resources/" + path).getPath()).getAbsoluteFile();
        AudioInputStream audioInputStream = null;
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        }
        catch(UnsupportedAudioFileException | IOException e)
        {
            e.printStackTrace();
        }
        Clip clip = null;
        try
        {
            clip = AudioSystem.getClip();
        }
        catch(LineUnavailableException e)
        {
            e.printStackTrace();
        }
        try
        {
            clip.open(audioInputStream);
        }
        catch(LineUnavailableException | IOException e)
        {
            e.printStackTrace();
        }
        //Plays audio once
        clip.start();
        
        if(loop)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        return clip;
    }
    
    
    public Clip PlaySound(String path)
    {
        return PlaySound(path, false);
    }
    
    public Clip PlaySound(String path, float volume)
    {
        Clip clip = PlaySound(path);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
        return clip;
    }
    
    public void PlaySound(String path, float volume, boolean isloop)
    {
        Clip c = PlaySound(path, volume);
        c.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
}
