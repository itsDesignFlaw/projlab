package VeryGoodViroGame;

import VeryGoodViroGame.Agent.Agent;
import VeryGoodViroGame.Agent.GeneticCode;
import VeryGoodViroGame.Equipment.Equipment;
import VeryGoodViroGame.Field.Field;
import VeryGoodViroGame.Field.FieldBunker;
import VeryGoodViroGame.Field.FieldLab;
import VeryGoodViroGame.Field.FieldWarehouse;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

interface IConsoleInputCommand
{
    public String runcmd(String[] args);
}

public class ConsoleIO
{
    static HashMap<String, IConsoleInputCommand> cmds = new HashMap<String, IConsoleInputCommand>();
    
    static
    {
        cmds.put("load", (args) ->
        {
            //print(false, "dev:" + args[0] + " l:" + args.length + " a: " + args[0].split(".")[0]);
            String[] fileext = args[0].split("\\.");
            if(fileext.length == 0)
                fileext = new String[]{args[0]};
            String flname = fileext[0];
            String flext = "tin";
            if(fileext.length > 1)
            {
                flname = fileext[0];
                flext = fileext[1];
            }
            
            Path filePath = Paths.get(flname + "." + flext);
            //print(false, " [LOADSCRIPT] Trying to load script: " + filePath.toAbsolutePath());
            try
            {
                List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
                for(String line : lines)
                {
                    //System.out.println(" [LOADSCRIPT] running cmd | " + line);
                    String[] linedat = line.split(" ", 2);
                    if(linedat.length == 0 || linedat[0].equals(""))
                        continue;
                    
                    if(linedat.length == 1)
                    {
                        RunCMD(linedat[0], new String[0]);
                    }
                    else
                    {
                        String[] linedat_params = linedat[1].split(" ");
                        RunCMD(linedat[0], linedat_params);
                    }
                    
                }
            }
            catch(IOException ex)
            {
                System.out.format(" [LOADSCRIPT] I/O error: %s%n", ex);
                return "IO Exception, see details above";
            }
            return "";
        });
        
        cmds.put("logents", (args) ->
        {
            if(EntityManager.namedObjects.size() == 0)
                return "No entities found";
            
            for(String name : EntityManager.namedObjects.keySet())
            {
                String value = EntityManager.namedObjects.get(name).toString();
                print(false, "name: " + name + "\n" + value);
            }
            return "";
        });
        
        cmds.put("create", (args) ->
        {
            String name = args[0];
            String classname = args[1];
            
            Object ent = EntityManager.CreateEntity(classname, name);
            if(ent == null)
                return "Couldn't create entity '" + classname + "', no prefab";
            return "";
        });
        cmds.put("neighbour", (args) ->
        {
            Object f1 = EntityManager.GetObjectByName(args[0]);
            Object f2 = EntityManager.GetObjectByName(args[1]);
            if(f1 instanceof Field && f2 instanceof Field)
            {
                ((Field) f1).AddNeighbour((Field) f2);
                ((Field) f2).AddNeighbour((Field) f1);
            }
            return "";
        });
        cmds.put("ware", (args) ->
        {
            Object w = EntityManager.GetObjectByName(args[0]);
            if(w instanceof FieldWarehouse)
            {
                ((FieldWarehouse) w).setResource(new Resource(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
            }
            return "";
        });
        cmds.put("bunker", (args) ->
        {
            Object b = EntityManager.GetObjectByName(args[0]);
            if(b instanceof FieldBunker)
            {
                ((FieldBunker) b).setEquipment((Equipment) EntityManager.CreateEntity(args[1]));
            }
            return "";
        });
        cmds.put("lab", (args) ->
        {
            Object l = EntityManager.GetObjectByName(args[0]);
            if(l instanceof FieldLab)
            {
                ((FieldLab) l).setCode(new GeneticCode((Agent) EntityManager.CreateEntity(args[1])));
            }
            return "";
        });
        cmds.put("list", (args) ->
        {
            System.out.println(EntityManager.ToStringByName(args[0]));
            return "";
        });
        cmds.put("craft", (args) ->
        {
            Object v = EntityManager.GetObjectByName(args[0]);
            if(v instanceof Virologist viro)
            {
                if(args[2].equals("vi"))
                    viro.CraftVirus(viro.learntCodes.get(Integer.parseInt(args[1])));
                else
                    viro.CraftVaccine(viro.learntCodes.get(Integer.parseInt(args[1])));
            }
            return "";
        });
        cmds.put("drop", (args) ->
        {
            Object v = EntityManager.GetObjectByName(args[0]);
            if(v instanceof Virologist viro)
            {
                viro.DestroyEquipment(viro.equipments.get(Integer.parseInt(args[1])));
            }
            return "";
        });
        cmds.put("interact", (args) ->
        {
            Object v = EntityManager.GetObjectByName(args[0]);
            if(v instanceof Virologist viro)
            {
                viro.InteractWithField();
            }
            return "";
        });
        cmds.put("move", (args) ->
        {
            Object v = EntityManager.GetObjectByName(args[0]);
            if(v instanceof Virologist viro)
            {
                viro.MoveTo((Field) EntityManager.GetObjectByName(args[1]));
            }
            return "";
        });
        cmds.put("steal", (args) ->
        {
            Object v = EntityManager.GetObjectByName(args[0]);
            Object v2 = EntityManager.GetObjectByName(args[1]);
            if(v instanceof Virologist viro && v2 instanceof Virologist viro2)
            {
                if(args[2].equals("eq"))
                {
                    viro.StealEquipmentFromViro(viro2, viro2.equipments.get(Integer.parseInt(args[3])));
                }
                else
                {
                    viro.StealResourceFromViro(viro2);
                }
            }
            return "";
        });
        cmds.put("use", (args) ->
        {
            Object v = EntityManager.GetObjectByName(args[0]);
            Object v2 = EntityManager.GetObjectByName(args[1]);
            if(v instanceof Virologist viro && v2 instanceof Virologist viro2)
            {
                if(args[2].equals("ag") || args[2].equals("agent"))
                {
                    viro.UseAgent(viro.stash.get(Integer.parseInt(args[3])), viro2);
                }
                else
                {
                    viro.UseEquipment(viro.equipments.get(Integer.parseInt(args[3])), viro2);
                }
            }
            return "";
        });
        cmds.put("step", (args) ->
        {
            Timer.Step();
            return "";
        });
        cmds.put("random", (args) ->
        {
            XRandom.randoms.add(Float.parseFloat(args[0]) / 100);
            return "";
        });
        
        cmds.put("add", (args) ->
        {
            Object v = EntityManager.GetObjectByName(args[2]);
            if(v instanceof Virologist viro)
            {
                if(args[0].equals("agent") || args[0].equals("ag"))
                {
                    Object agent = EntityManager.GetObjectByName(args[1]);
                    if(agent instanceof Agent a)
                    {
                        if(args.length >= 4 && args[3].equals("1"))
                            viro.stash.add(a);
                        else
                            a.Apply(null, viro);
                    }
                }
                else
                {
                    Object equipment = EntityManager.GetObjectByName(args[1]);
                    if(equipment instanceof Equipment eq)
                    {
                        viro.AddEquipment(eq);
                    }
                }
            }
            return "";
        });
        
        cmds.put("putviro", (args) ->
        {
            Object v = EntityManager.GetObjectByName(args[0]);
            Object f = EntityManager.GetObjectByName(args[1]);
            if(v instanceof Virologist viro && f instanceof Field field)
            {
                field.AcceptViro(viro);
            }
            return "";
        });
        
        cmds.put("clear", (args) ->
        {
            EntityManager.BigRedButton();
            return "";
        });
    }
    
    static void print(boolean alert, String str)
    {
        System.out.println(str);
    }
    
    static void print(String str)
    {
        print(false, str);
    }
    
    static void RunCMD(String cmd, String[] args)
    {
        String ucmd = cmd.toLowerCase();
        String[] uparams = new String[args.length];
        if(args.length > 0)
        {
            for(int i = 0; i < args.length; i++)
                uparams[i] = args[i].toLowerCase();
        }
        
        IConsoleInputCommand runc = cmds.get(ucmd);
        if(runc == null)
        {
            print(true, "Invalid command '" + ucmd + "', no such command!");
            return;
        }
        
        String retStr = "";
        try
        {
            //print("trying to run: "+ucmd + " with parms: " + uparams.length + "::");
            retStr = runc.runcmd(uparams);
        }
        catch(Exception e)
        {
            System.out.format(" [LOADSCRIPT] I/O error: %s%n", e);
            e.printStackTrace();
            retStr = "Invalid parameters: too few probably - otherwise see details above";
        }
        
        if(retStr != "")
            print(true, "Can't run '" + ucmd + "'! " + retStr);
    }
}
