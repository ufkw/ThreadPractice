


public class BoundedBuffer extends Thread //implements Buffer
{
    private static final int BUFFER_SIZE = 5;
    private int buffer_count;
    private int in;
    private int out;
    private Object[] buffer;

    public BoundedBuffer()
    {
        buffer_count = 0;
        in = 0;
        out = 0;
        buffer = new Object[BUFFER_SIZE];
    }




    public void insert(Object item)
    {
        while( buffer_count == BUFFER_SIZE)
        {
            // do nothing
            wait();
        }

        ++buffer_count;
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        System.out.println("produced: " + item);
        notify();
    }

    public void remove()
    {
        Object item;
        while (buffer_count == 0)
        {
            //do nothing
            wait();
        }

        --buffer_count;
        item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        System.out.println("consumed: " + item);
        notify();
       
    }
}