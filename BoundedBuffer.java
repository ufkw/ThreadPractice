import java.util.Random;
import java.util.Arrays;


public class BoundedBuffer extends Thread//implements Buffer
{
    private static final int BUFFER_SIZE = 10;
    private int buffer_count;
//    private int in;
    //private int out;
    private int[] buffer;
    private int[] emptyArr = {'\0','\0','\0','\0','\0','\0','\0','\0','\0','\0'};

    public BoundedBuffer()
    {
        buffer_count = 0;
    //    in = 0;
        //out = 0;
        buffer = new int[BUFFER_SIZE];
    }

    public void run()
    {

        Random rand = new Random();
        long startTime = System.currentTimeMillis();

        Thread thread_p = new Thread()
        {
            public void run()
            {
                while(System.currentTimeMillis() < startTime + 2000)
                {
                    synchronized(this)
                    {
                        try
                        {
                            insert(rand.nextInt(100));
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
                while(System.currentTimeMillis() < startTime + 2000)
                {
                    synchronized(this)
                    {
                        try
                        {
                            remove();
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




    public void insert(int item) throws InterruptedException
    {
        synchronized(this)
        {
            while( buffer_count == BUFFER_SIZE)
            {
                // do nothing
                wait();
            }

            System.out.print("Buffer before producing: [");
            int i;
            for(i = 0; i < buffer_count-1; i++)
            {
                System.out.print(buffer[i] + ", ");
            }
            if(!(Arrays.equals(buffer, emptyArr)))
            {
                System.out.print(buffer[i]);
            }
            System.out.println("]");

            buffer[buffer_count] = item;
            ++buffer_count;
            //in = (in + 1) % BUFFER_SIZE;
            System.out.println("produced: " + item);

            System.out.print("Buffer after producing: [");
            for(i = 0; i < buffer_count-1; i++)
            {
                System.out.print(buffer[i] + ", ");
            }
            if(!(Arrays.equals(buffer, emptyArr)))
            {
                System.out.print(buffer[i]);
            }
            System.out.println("]");

            notify();
        }
    }

    public void remove() throws InterruptedException
    {
        synchronized(this)
        {
            Object item;
            while (buffer_count == 0)
            {
                //do nothing
                wait();
            }
            System.out.print("Buffer before consuming: [");
            int i;
            for(i = 0; i < buffer_count-1; i++)
            {
                System.out.print(buffer[i] + ", ");
            }
            if(!(Arrays.equals(buffer, emptyArr)))
            {
                System.out.print(buffer[i]);
            }
            System.out.println("]");

            --buffer_count;
            item = buffer[buffer_count];
            buffer[buffer_count] = '\0';
            //out = (out + 1) % BUFFER_SIZE;
            System.out.println("consumed: " + item);


            System.out.print("Buffer after consuming: [");
            for(i = 0; i < buffer_count-1; i++)
            {
                System.out.print(buffer[i] + ", ");
            }
            if(!(Arrays.equals(buffer, emptyArr)))
            {
                System.out.print(buffer[i]);
            }
            System.out.println("]");

            notify();
        }
    }

    
}