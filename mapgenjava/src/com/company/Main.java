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
        maxfields = Integer.parseInt(ask("Number of fields"));
        placed_fields.put("field", 0);
        String sseed = ask("Seed to generate map (leave blank for random)");
        if(sseed.equals(""))
        {
            Random rand = new Random();
            useSeed = rand.nextLong(1000000);
        }
        else
        {
            useSeed = Long.parseLong(sseed);
        }
        place_these.put("lab", Integer.parseInt(ask("Number of labs")));
        placed_fields.put("lab", 0);
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
                    double chance = (double) inspectedFieldCount / remainingSpots;
                    if(chance < rand.nextDouble(1))
                    {
                        placeSomething(x, y, nodemap);
                        if(GetPlaceableLeft() <= 0)
                            return nodemap;
                    }
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
        
        String ret = "";
        
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
        
        return ret;
    }
    
    static String CreateLab()
    {
        placed_fields.put("lab", placed_fields.get("lab") + 1);
        return "create lab" + placed_fields.get("lab");
    }
    
    static String CreateField()
    {
        placed_fields.put("field", placed_fields.get("field") + 1);
        return "create f" + placed_fields.get("field");
    }
    
    static String CreateFields(String[][] nodemap)
    {
        int sd = maxfields;
        String ret = "";
        
        
        for(int x = 0; x < sd; x++)
        {
            for(int y = 0; y < sd; y++)
            {
                if(nodemap[x][y].equals("lab"))
                {
                    ret = ret + CreateLab() + "\n";
                }
                if(nodemap[x][y].equals("field"))
                {
                    ret = ret + CreateField() + "\n";
                }
            }
        }
        
        return ret;
    }
    
    static String GenerateMap()
    {
        String str = CreateFields(NameFields(SelectNodes(NoiseMap())));
        
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
