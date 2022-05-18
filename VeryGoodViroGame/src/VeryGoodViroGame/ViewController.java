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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


import javax.sound.sampled.*;

/**
 * Osztály ami kezeli a megjelentítést, vagyis a View osztályt
 */
public class ViewController
{
    private boolean useItem = false;
    public Virologist activeViro;
    IView view;
    MainMenu mainmenu;
    private Field currentfield;
    private boolean moved = false;
    private HashMap<Virologist, Field> prevfield = new HashMap<>();
    
    /**
     * @return mainmenu visszaadása
     */
    public MainMenu getMainmenu()
    {
        return mainmenu;
    }
    
    public void setMainmenu(MainMenu mainmenu)
    {
        this.mainmenu = mainmenu;
    }
    
    /**
     * Konstruktor, létrehoz egy új viewt, elindítja a játékot
     *
     * @param NumberOfViros virológusok száma a játékban
     * @param mainmenu      a Main Menu
     */
    public ViewController(int NumberOfViros, MainMenu mainmenu)
    {
        this.mainmenu = mainmenu;
        view = new View(this);
        view.Init();
        //Debug mode,betölti a devmapot
        GameManager.controller = this;
        GameManager.StartGame(NumberOfViros, new String[]{"Laci", "Maci"});
        Update(GameManager.GetCurrent());
        //Test();
    }
    
    public IView getView()
    {
        return view;
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
    
    /**
     * Update-eli az aktuális virológust
     */
    public void Update()
    {
        if(activeViro != null)
            Update(activeViro);
    }
    
    /**
     * @return Az előző mező ahol az activeViro tartózkodott
     */
    public Field GetPrevField()
    {
        return prevfield.get(activeViro);
    }
    
    /**
     * Átlép a következő játékosra (vagyis virológusra), újra kirajzol, az új viró szemszögéből
     *
     * @param v a sorban követkeő virológus
     */
    public void Update(Virologist v)
    {
        if(v != activeViro)
        {
            activeViro = v;
            usea = null;
            usee = null;
        }
        moved = false;
        view.Clear();
        
        Field f = v.GetField();
        currentfield = f;
        
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
        view.DrawResource(v.getResource());
        
        view.Repaint();
    }
    
    /**
     * Uj object hozzáadása a kirajzolandó objektumokhoz
     */
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
    
    /**
     * Átlépteti grafikusan a virót a kiválasztott mezőre
     *
     * @param f az új mező ahova lépünk
     * @return visszaadja a current mezőt
     */
    public boolean MoveViro(Field f)
    {
        Field cur = activeViro.GetField();
        activeViro.MoveTo(f);
        moved = activeViro.GetField() != currentfield;
        return activeViro.GetField() == cur;
        //Update();
        //mark: nem kell neki, majd ha change turn van, a View addig animal
    }
    
    /**
     * Legyárt egy vírust az activeVirónak
     *
     * @param gc a genetikai kód ami alapján craftolni akar
     */
    public void CraftVirus(GeneticCode gc)
    {
        if(moved)
        {
            //PopUp mozogtal
            return;
        }
        activeViro.CraftVirus(gc);
        Update();
    }
    
    /**
     * Legyárt egy vakcinát az activeVirónak
     *
     * @param gc a genetikai kód ami alapján craftolni akar
     */
    public void CraftVaccine(GeneticCode gc)
    {
        if(moved)
        {
            //PopUp mozogtal
            return;
        }
        activeViro.CraftVaccine(gc);
        Update();
    }
    
    /**
     * Ráteszi a kiválasztott ágenst a kiválasztott viróra
     *
     * @param v a kiválasztott viró
     */
    public void UseAgentOnViro(Virologist v)
    {
        if(moved)
        {
            //PopUp mozogtal
            return;
        }
        if(usea == null)
            return;
        activeViro.UseAgent(usea, v);
        Update();
    }
    
    /**
     * Meghívja a viró interactWithField fv-ét, ha már az új mezőn van
     */
    public void Interact()
    {
        if(moved)
        {
            //PopUp mozogtal
            return;
        }
        activeViro.InteractWithField();
        Update();
    }
    
    /**
     * Egyik viró a másiktól equipmentet lop
     *
     * @param target akitől lopni fog
     * @param eq     amit lopni akar
     */
    public void StealEquipment(Virologist target, Equipment eq)
    {
        if(moved)
        {
            //PopUp mozogtal
            return;
        }
        activeViro.StealEquipmentFromViro(target, eq);
        Update();
    }
    
    /**
     * Egyik viró a másiktól resource-t lop
     *
     * @param target akitől lopni fog
     */
    public void StealResource(Virologist target)
    {
        if(moved)
        {
            //PopUp mozogtal
            return;
        }
        activeViro.StealResourceFromViro(target);
        Update();
    }
    
    /**
     * Használ egy eszközt egy másik virón
     *
     * @param target a mit sem sejtő célpont
     */
    public void UseEquipment(Virologist target)
    {
        if(moved)
        {
            //PopUp mozogtal
            return;
        }
        if(usee == null)
            return;
        activeViro.UseEquipment(usee, target);
        Update();
    }
    
    /**
     * Kidobja a kiválasztott eszközt
     *
     * @param e a kiválasztott eszköz
     */
    public void DropEquipment(Equipment e)
    {
        if(moved)
        {
            //PopUp mozogtal
            return;
        }
        activeViro.DestroyEquipment(e);
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
        if(moved)
        {
            //PopUp mozogtal
            return;
        }
        if(usea != a)
            usea = a;
        else
            usea = null;
        Update();
    }
    
    public void SetEquipment(Equipment e)
    {
        if(moved)
        {
            //PopUp mozogtal
            return;
        }
        if(usee != e)
            usee = e;
        else
            usee = null;
        Update();
    }
    
    /**
     * Ezzel fejezi be az activeViro a körét
     */
    public void EndTurn()
    {
        if(currentfield != activeViro.GetField())
            prevfield.put(activeViro, currentfield);
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
