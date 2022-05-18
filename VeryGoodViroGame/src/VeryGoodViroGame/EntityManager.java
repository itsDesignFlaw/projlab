package VeryGoodViroGame;

import VeryGoodViroGame.Agent.*;
import VeryGoodViroGame.Equipment.EquipmentAxe;
import VeryGoodViroGame.Equipment.EquipmentCoat;
import VeryGoodViroGame.Equipment.EquipmentGloves;
import VeryGoodViroGame.Equipment.EquipmentSack;
import VeryGoodViroGame.Field.*;

import java.util.HashMap;

interface IEntityGenerator
{
    Object generate();
}

public class EntityManager
{
    public static HashMap<String, Object> namedObjects = new HashMap<String, Object>();
    static HashMap<Object, String> classObject = new HashMap<Object, String>();
    static HashMap<String, IEntityGenerator> EntityGen = new HashMap<String, IEntityGenerator>();
    
    static
    {
        EntityGen.put("viro", Virologist::new);
        EntityGen.put("field", Field::new);
        EntityGen.put("ware", FieldWarehouse::new);
        EntityGen.put("bunker", FieldBunker::new);
        EntityGen.put("lab", FieldLab::new);
        EntityGen.put("bear", FieldLabBear::new);
        EntityGen.put("coat", EquipmentCoat::new);
        EntityGen.put("sack", EquipmentSack::new);
        EntityGen.put("axe", EquipmentAxe::new);
        EntityGen.put("gloves", EquipmentGloves::new);
        EntityGen.put("forget", Forget::new);
        EntityGen.put("paralyze", Paralyze::new);
        EntityGen.put("dance", Dance::new);
        EntityGen.put("protect", Protect::new);
        EntityGen.put("forget_vaccine", () -> new Vaccine(new Forget()));
        EntityGen.put("paralyze_vaccine", () -> new Vaccine(new Paralyze()));
        EntityGen.put("dance_vaccine", () -> new Vaccine(new Dance()));
        EntityGen.put("protect_vaccine", () -> new Vaccine(new Protect()));
        EntityGen.put("bear_virus", Bear::new);
    }
    
    public static Object GetObjectByName(String name)
    {
        return namedObjects.get(name);
    }
    
    public static String GetObjectName(Object object)
    {
        for(String objname : namedObjects.keySet())
        {
            if(object == namedObjects.get(objname))
                return objname;
        }
        ;
        return "DHaN, maybe bear?"; //todo ez mia  fasz?
    }
    
    public static void PutNamedObject(String name, Object object, String c)
    {
        namedObjects.put(name, object);
        classObject.put(object, c);
    }
    
    public static void PutCraftedObject(String name, Object object, String c)
    {
        int db = 0;
        while(namedObjects.containsKey(name + db))
            db++;
        PutNamedObject(name + db, object, c);
        GameManager.controller.AddObject(object, c);
    }
    
    public static String GetType(Object o)
    {
        return classObject.get(o);
    }
    
    public static Object CreateEntity(String classname)
    {
        int db = 0;
        while(namedObjects.containsKey(classname + db))
            db++;
        
        Object o = CreateEntity(classname, classname + db);
        return o;
    }
    
    public static Object CreateEntity(String classname, String name)
    {
        if(!EntityGen.containsKey(classname))
            return null;
        
        Object ent = EntityGen.get(classname).generate();
        PutNamedObject(name, ent, classname);
        GameManager.controller.AddObject(ent, classname);
        return ent;
    }
    
    public static void AdminClone(Cloneable e, Cloneable c)
    {
        PutCraftedObject(GetObjectName(e), c, classObject.get(e));
    }
    
    public static String ToStringByName(String name)
    {
        Object o = GetObjectByName(name);
        if(o == null)
            return "Object not found";
        return "name: \"" + name + "\"\n" + o.toString();
    }
    
    /**
     * Don't touch this
     */
    public static void BigRedButton()
    {
        namedObjects.clear();
    }
}
