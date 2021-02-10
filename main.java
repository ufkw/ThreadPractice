import java.io.*;
import java.util.Random;




public class main //extends BoundedBuffer
{

    public static void main(String[] args) //throws InterruptedException
    {
        BoundedBuffer bb = new BoundedBuffer();
        //int buffer[15];
        //int[] arr;
        Random rand = new Random();
        
        long startTime = System.currentTimeMillis();
        Thread thread_p = new Thread()
        {
            public void run()
            {
                while(System.currentTimeMillis() < startTime + 999999999)
                {
                    synchronized(this)
                    {
                        try
                        {
                            bb.insert(rand.nextInt(100));
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread thread_c = new Thread()
        {
            public void run()
            {
                while(System.currentTimeMillis() < startTime + 999999999)
                {
                    synchronized(this)
                    {
                        try
                        {
                            bb.remove();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                         
                        }
                    }
                }
            }
        };

        thread_p.start();
        thread_c.start();





    }

}