package com.company;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main
{
    
    static String name = "default";
    static int maxfields = 0;
    static long useSeed;
    
    static HashMap<String, Integer> placed_fields = new HashMap<String, Integer>();
    static HashMap<String, Integer> place_these = new HashMap<String, Integer>();
    
    static void printnn(String msg)
    {
        System.out.print(msg);
    }
    
    static void printas(String msg)
    {
        System.out.println("[MAPGEN] " + msg);
    }
    
    static String ask(String msg)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        printnn(msg + ": ");
        try
        {
            return reader.readLine();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return "";
        }
    }
    
    static void AskForInput()
    {
        name = ask("Name of map");
        place_these.put("field", Integer.parseInt(ask("Number of fields")));
        placed_fields.put("field", -1);
        String sseed = ask("Seed to generate map (leave blank for random)");
        if(sseed.equals(""))
        {
            Random rand = new Random();
            useSeed = rand.nextInt(1000000);
        }
        else
        {
            useSeed = Long.parseLong(sseed);
        }
        place_these.put("lab", Integer.parseInt(ask("Number of labs")));
        placed_fields.put("lab", -1);
        
        place_these.put("bunker", Integer.parseInt(ask("Number of bunkers")));
        placed_fields.put("bunker", -1);
        
        place_these.put("ware", Integer.parseInt(ask("Number of warehouses")));
        placed_fields.put("ware", -1);
        
        place_these.put("bearlab", Integer.parseInt(ask("Number of bearlabs")));
        placed_fields.put("bearlab", -1);
        
        maxfields = place_these.values().stream().reduce(0, Integer::sum);
    }
    
    static int[][] NoiseMap()
    {
        int sd = maxfields;
        int[][] map = new int[sd][sd];
        Random rand = new Random();
        rand.setSeed(useSeed);
        
        for(int x = 0; x < sd; x++)
        {
            for(int y = 0; y < sd; y++)
            {
                map[x][y] = rand.nextInt(10000);
            }
        }
        
        return map;
    }
    
    static int GetPlaceableLeft()
    {
        int ret = 0;
        for(Map.Entry<String, Integer> v : place_these.entrySet())
        {
            ret += v.getValue();
        }
        return ret;
    }
    
    static String GetRandomPlaceable()
    {
        int total = GetPlaceableLeft();
        String[] selector = new String[total];
        int cc = 0;
        for(Map.Entry<String, Integer> v : place_these.entrySet())
        {
            for(int i = 0; i < v.getValue(); i++)
            {
                selector[cc] = v.getKey();
                cc++;
            }
        }
        Random rand = new Random();
        rand.setSeed(useSeed);
        return selector[rand.nextInt(total)];
    }
    
    static void placeSomething(int x, int y, String[][] nodemap)
    {
        String place = GetRandomPlaceable();
        nodemap[x][y] = place;
        place_these.put(place, place_these.get(place) - 1);
    }
    
    static String[][] NameFields(String[][] nodemap)
    {
        Random rand = new Random();
        rand.setSeed(useSeed);
        int sd = maxfields;
        int remainingSpots = maxfields;
        int inspectedFieldCount = 0;
        
        for(int x = 0; x < sd; x++)
        {
            for(int y = 0; y < sd; y++)
            {
                if(nodemap[x][y].equals("field"))
                {
                    inspectedFieldCount++;
                    placeSomething(x, y, nodemap);
                    if(GetPlaceableLeft() <= 0)
                        return nodemap;
                }
            }
        }
        if(GetPlaceableLeft() > 0)
        {
            printas("Couldn't place all special fields :(");
        }
        return nodemap;
    }
    
    static String[][] SelectNodes(int[][] noisemap)
    {
        int sd = maxfields;
        int ceiling = 10000;
        int foundfields = 0;
        String[][] mapnodes = new String[sd][sd];
        while(foundfields < maxfields)
        {
            label:
            for(int x = 0; x < sd; x++)
            {
                for(int y = 0; y < sd; y++)
                {
                    if(noisemap[x][y] > ceiling)
                    {
                        if(!mapnodes[x][y].equals("field"))
                        {
                            mapnodes[x][y] = "field";
                            foundfields++;
                            if(foundfields >= maxfields)
                                break label;
                        }
                    }
                    else
                    {
                        mapnodes[x][y] = "";
                    }
                }
            }
            ceiling--;
        }
        return mapnodes;
    }
    
    static String ConnectFields(String[][] nodemap)
    {
        int sd = maxfields;
        
        StringBuilder ret = new StringBuilder();
        
        //connect each row
        for(int x = 0; x < sd; x++)
        {
            int lastfoundY = -1;
            for(int y = 0; y < sd; y++)
            {
                if(nodemap[x][y].equals("field"))
                {
                    if(lastfoundY == -1)
                    {
                        lastfoundY = y;
                    }
                    else
                    {
                    
                    }
                }
            }
        }
        placed_fields.replaceAll((k, v) -> v = 0);
        
        for(int i = 0; i < maxfields; i++)
        {
            for(int j = 0; j < maxfields; j++)
            {
                if(!nodemap[i][j].equals(""))
                {
                    String name = getFieldName(nodemap, i, j);
                    placed_fields.put(nodemap[i][j], placed_fields.get(nodemap[i][j]) + 1);
                    nodemap[i][j] = name;
                }
            }
        }
        
        for(int i = 0; i < maxfields; i++)
        {
            for(int j = 0; j < maxfields; j++)
            {
                if(!nodemap[i][j].equals(""))
                {
                    String name = nodemap[i][j];
                    
                    for(int x = i + 1; x < maxfields; x++)
                    {
                        if(!nodemap[x][j].equals(""))
                        {
                            ret.append("neighbour ").append(nodemap[x][j]).append(" ").append(name).append("\n");
                        }
                    }
                    for(int y = j + 1; y < maxfields; y++)
                    {
                        if(!nodemap[i][y].equals(""))
                        {
                            ret.append("neighbour ").append(nodemap[i][y]).append(" ").append(name).append("\n");
                        }
                    }
                    
                }
            }
        }
        return ret.toString();
    }
    
    private static String getFieldName(String[][] nodemap, int i, int j)
    {
        String name = "";
        switch(nodemap[i][j])
        {
            case "lab":
                name = "l" + placed_fields.get("lab");
                break;
            case "field":
                name = "f" + placed_fields.get("field");
                break;
            case "bunker":
                name = "b" + placed_fields.get("bunker");
                break;
            case "ware":
                name = "w" + placed_fields.get("ware");
                break;
            case "bear":
                name = "lb" + placed_fields.get("bear");
                break;
        }
        return name;
    }
    
    static Random r = new Random(useSeed);
    static String[] labCodes = "forget,dance,paralyze,protect".split(",");
    static String[] bunkerEq = "gloves,sack,coat,axe".split(",");
    
    static String CreateLab()
    {
        placed_fields.put("lab", placed_fields.get("lab") + 1);
        return "create l" + placed_fields.get("lab") + " lab\nlab l" + placed_fields.get("lab") + " " + labCodes[r.nextInt(labCodes.length)];
    }
    
    static String CreateField()
    {
        placed_fields.put("field", placed_fields.get("field") + 1);
        return "create f" + placed_fields.get("field") + " field";
    }
    
    static String CreateWare()
    {
        placed_fields.put("ware", placed_fields.get("ware") + 1);
        return "create w" + placed_fields.get("ware") + " ware\nware w" + placed_fields.get("ware") + " " + r.nextInt(30) + " " + r.nextInt(30);
    }
    
    static String CreateBear()
    {
        placed_fields.put("bear", placed_fields.get("bear") + 1);
        return "create lb" + placed_fields.get("bear") + " bear";
    }
    
    static String CreateBunker()
    {
        placed_fields.put("bunker", placed_fields.get("bunker") + 1);
        return "create b" + placed_fields.get("bunker") + " bunker\nbunker b" + placed_fields.get("bunker") + " " + bunkerEq[r.nextInt(bunkerEq.length)];
    }
    
    
    static String CreateFields(String[][] nodemap)
    {
        int sd = maxfields;
        StringBuilder ret = new StringBuilder();
        
        for(int x = 0; x < sd; x++)
        {
            for(int y = 0; y < sd; y++)
            {
                switch(nodemap[x][y])
                {
                    case "lab":
                        ret.append(CreateLab()).append("\n");
                        break;
                    case "field":
                        ret.append(CreateField()).append("\n");
                        break;
                    case "bunker":
                        ret.append(CreateBunker()).append("\n");
                        break;
                    case "ware":
                        ret.append(CreateWare()).append("\n");
                        break;
                    case "bear":
                        ret.append(CreateBear()).append("\n");
                        break;
                }
            }
        }
        
        return ret.toString();
    }
    
    static String GenerateMap()
    {
        String[][] arr = NameFields(SelectNodes(NoiseMap()));
        String str = CreateFields(arr);
        str += "\n" + ConnectFields(arr);
        
        Path path = Paths.get(name + ".map");
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path.toString()));
            writer.write(str);
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            printas("Failed to write to file.");
        }
        return path.toAbsolutePath().toString();
    }
    
    public static void main(String[] args)
    {
        AskForInput();
        printas("Generating map...");
        String full = GenerateMap();
        printas("Generation over, full file path:\n  " + full);
    }
}
