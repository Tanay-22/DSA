package design;

public class ATM
{
    private int notes[];
    private int values[];
    public ATM()
    {
        this.notes = new int[5];
        this.values = new int[]{20, 50, 100, 200, 500};
    }

    public void deposit(int[] banknotesCount)
    {
        for (int i = 0; i < banknotesCount.length; i++)
        {
            this.notes[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount)
    {
        int withdrawal[] = new int[5];

        for (int i = 4; i >= 0 && amount > 0; i--)
        {
            withdrawal[i] = amount / values[i];
            withdrawal[i] = (withdrawal[i] <= notes[i]) ? withdrawal[i] : notes[i];

            amount = amount - withdrawal[i] * values[i];
        }
        if(amount == 0)
        {
            for (int i = 0; i < 5; i++)
                notes[i] -= withdrawal[i];
            return withdrawal;
        }
        else
            return new int[]{-1};
    }
}