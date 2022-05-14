package VeryGoodViroGame;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class XRandom
{
    static Queue<Float> randoms = new LinkedList<>();
    private final Random r = new Random();
    
    public XRandom()
    {
    
    }
    
    
    public int nextInt(int size)
    {
        if(randoms.size() == 0)
            return r.nextInt(size);
        return Math.round(randoms.poll()) % size;
    }
    
    public float nextFloat()
    {
        if(randoms.size() == 0)
            return r.nextFloat();
        return randoms.poll();
    }
}
