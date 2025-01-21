package maps;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapDemo
{
    public static void main(String[] args)
    {
        Map<Day, String> map = new EnumMap<>(Day.class);
        map.put(Day.TUESDAY, "Gym");
        map.put(Day.MONDAY, "Walk");
        System.out.println(Day.TUESDAY.ordinal());
        System.out.println(map);
    }
}

enum Day
{
    MONDAY, TUESDAY, WEDSNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
