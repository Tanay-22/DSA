package design;

import java.util.Collection;
import java.util.HashMap;

class FrequencyTracker
{
    private HashMap<Integer, Integer> hashMap;

    public FrequencyTracker()
    {
        this.hashMap = new HashMap<>();
    }

    public void add(int number)
    {
        Integer freq = this.hashMap.get(number);

        if(freq == null)
        {
            this.hashMap.put(number, 1);
            return;
        }
        this.hashMap.put(number, freq + 1);
    }

    public void deleteOne(int number)
    {
        Integer freq = this.hashMap.get(number);

        if(freq == null)
            return;
        if(freq == 1)
        {
            this.hashMap.remove(number);
            return;
        }
        this.hashMap.put(number, freq - 1);
    }

    public boolean hasFrequency(int frequency)
    {
        Collection<Integer> collection = hashMap.values();

        return collection.contains(frequency);
    }
}
