package VeryGoodViroGame;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : VeryGoodViroGame.Resource.java
//  @ Date : 2022. 03. 27.
//  @ Author : 
//
//

/**
 * Ez az osztály fogja össze a játékban összegyűjthető erőforrásokat, amiket craftolásra lehet használni.
 */
public class Resource
{
    
    /**
     * ami - aminosav
     * nuki - nukleotid
     */
    private int ami;
    private int nuki;
    
    
    public Resource()
    {
        this(0, 0);
    }
    
    public Resource(int ami, int nuki)
    {
        this.ami = ami;
        this.nuki = nuki;
    }
    
    
    /**
     * Hozzáadja a paraméterként kapott erőforrásokat a meglévőkhöz.
     *
     * @param resource paraméterként kapott erőforrás
     */
    public void Add(Resource resource)
    {
        ami += resource.ami;
        nuki += resource.nuki;
        //TODO: maxResource ötlet?
        //plusz egy paraméter?
    }
    
    /**
     * A paraméterként kapott erőforrásokat elveszi a meglévőektől.
     *
     * @param resource paraméterként kapott erőforrás
     */
    public Resource Remove(Resource resource)
    {
        if(ami >= resource.ami && nuki >= resource.nuki)
        {
            ami -= resource.ami;
            nuki -= resource.nuki;
            return resource;
        }
        else
        {
            int retami = 0, retnuki = 0;
            retami = Math.min(ami, resource.ami);
            retnuki = Math.min(nuki, resource.nuki);
            ami = Math.max(ami - resource.ami, 0);
            nuki = Math.max(nuki - resource.nuki, 0);
            return new Resource(retami, retnuki);
        }
    }
    
    public boolean hasEnough(Resource r)
    {
        return ami >= r.ami && nuki >= r.nuki;
    }
        /*public void AddAmi(int ami)
        {
            throw new System.NotImplementedException();
        }

        public void RemoveAmi(int ami)
        {
            throw new System.NotImplementedException();
        }
        public void AddNuki(int nuki)
        {
            throw new System.NotImplementedException();
        }

        public void RemoveNuki(int nuki)
        {
            throw new System.NotImplementedException();
        }*/
    
    @Override
    public String toString()
    {
        return "(" + "ami:" + ami + ", nuki:" + nuki + ')';
    }
}
