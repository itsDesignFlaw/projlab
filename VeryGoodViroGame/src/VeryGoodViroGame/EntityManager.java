package VeryGoodViroGame;

import java.util.HashMap;

interface IEntityGenerator
{
    Object generate();
}

public class EntityManager {
    public static HashMap<String, Object> namedObjects = new HashMap<String, Object>();
    static HashMap<String, IEntityGenerator> EntityGen = new HashMap<String, IEntityGenerator>();

    static {
        EntityGen.put("viro", Virologist::new);
    }

    public static Object GetObjectByName(String name)
    {
        return namedObjects.get(name);
    }

    public static String GetObjectName(Object object)
    {
        for (String objname : namedObjects.keySet()) {
            if ( object == namedObjects.get(objname) )
                return objname;
        };
        return null;
    }

    public static void PutNamedObject(String name, Object object)
    {
        namedObjects.put(name, object);
    }

    public static Object CreateEntity(String classname, String name)
    {
        if (!EntityGen.containsKey(classname))
            return null;

        Object ent = EntityGen.get(classname).generate();
        PutNamedObject(name, ent);
        return ent;
    }
}
