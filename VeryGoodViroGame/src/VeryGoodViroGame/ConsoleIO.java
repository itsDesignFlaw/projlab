package VeryGoodViroGame;

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

public class ConsoleIO{
    static HashMap<String, IConsoleInputCommand> cmds = new HashMap<String, IConsoleInputCommand>();

    static {
        cmds.put("load", (args)->
        {
            //print(false, "dev:" + args[0] + " l:" + args.length + " a: " + args[0].split(".")[0]);
            String[] fileext = args[0].split(".");
            if (fileext.length == 0)
                fileext = new String[]{args[0]};
            String flname = fileext[0];
            String flext = "tin";
            if (fileext.length > 1)
            {
                flname = fileext[0];
                flext = fileext[1];
            }

            Path filePath = Paths.get(flname + "." + flext);
            //print(false, " [LOADSCRIPT] Trying to load script: " + filePath.toAbsolutePath());
            try {
                List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
                for(String line: lines) {
                    //System.out.println(" [LOADSCRIPT] running cmd | " + line);
                    String[] linedat = line.split(" ", 2);

                    if (linedat.length == 1)
                    {
                        RunCMD(linedat[0], new String[0]);
                    }
                    else
                    {
                        String[] linedat_params = linedat[1].split(" ");
                        RunCMD(linedat[0], linedat_params);
                    }

                }
            } catch (IOException ex) {
                System.out.format(" [LOADSCRIPT] I/O error: %s%n", ex);
                return "IO Exception, see details above";
            }
           return "";
        });

        cmds.put("logents", (args)->
        {
            if (EntityManager.namedObjects.size() == 0)
                return "No entities found";

            for (String name: EntityManager.namedObjects.keySet()) {
                String value = EntityManager.namedObjects.get(name).toString();
                print(false, "name: " + name + "\n" + value.toString());
            }
            return "";
        });

        cmds.put("create", (args)->
        {
            String name = args[0];
            String classname = args[1];

            Object ent = EntityManager.CreateEntity(classname, name);
            if (ent==null)
                return "Couldn't create entity '"+classname+"', no prefab";
            return "";
        });

        cmds.put("logents2", (args)->
        {
            return "";
        });
    }

    static void print(boolean alert, String str)
    {
        System.out.println(str);
    }
    static void print(String str) {print(false, str);}

    static void RunCMD(String cmd, String[] args)
    {
        String ucmd = cmd.toLowerCase();
        String[] uparams = new String[args.length];
        if (args.length > 0)
        {
            for (int i = 0; i< args.length; i++)
                uparams[i] = args[i].toLowerCase();
        }

        IConsoleInputCommand runc = cmds.get(ucmd);
        if (runc==null){
            print(true, "Invalid command '"+ucmd+"', no such command!");
            return;
        }

        String retStr = "";
        try {
            //print("trying to run: "+ucmd + " with parms: " + uparams.length + "::");
            retStr = runc.runcmd(uparams);
        }
        catch(Exception e) {
            System.out.format(" [LOADSCRIPT] I/O error: %s%n", e);
            e.printStackTrace();
            retStr = "Invalid parameters: too few probably - otherwise see details above";
        }

        if (retStr!="")
            print(true, "Can't run '"+ucmd+"'! "+retStr);
    }
}
