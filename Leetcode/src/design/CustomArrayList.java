package design;


public class CustomArrayList<T>
//      public class CustomArrayList<? extends Number>      wildcards

{
    private Object[] data;
    private int size;

    public CustomArrayList()
    {
        this.data = new Object[10];
        this.size = 0;
    }

    private boolean isFull()
    {
        return size == data.length;
    }

    private void resize()
    {
        Object[] temp = new Object[2 * size];
        for (int i = 0; i < size; i++)
        {
            temp[i] = data[i];
        }
        data = temp;
    }

    public void add(T val)
    {
        if(isFull())
        {
            resize();
        }
        data[size++] = val;
    }

    public T remove()
    {
        T removed = (T) data[--size];
        return removed;
    }

    public T get(int index)
    {
        return (T) data[index];
    }
}
