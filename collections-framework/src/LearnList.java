import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class LearnList
{
    public static void main(String[] args)
    {
        List<Integer> list1 = new ArrayList<>();
        System.out.println(list1.getClass().getName());

        List<Integer> list2 = Arrays.asList(1, 23, 3, 23, 44, 54);   // only modification, no addition or deletion
        System.out.println(list2.getClass().getName());

        List<Integer> list3 = List.of(1, 2, 3, 4, 5);   // no modification as well
        System.out.println(list3.getClass().getName());

        List<String> list4 = Arrays.asList("One", "Two", "Three", "Four");   // no modification as well
//        list4.sort(new StringLengthComparator());
        list4.sort(Comparator.comparingInt(String::length));

        System.out.println(list4);

        List<String> shoppingList = new CopyOnWriteArrayList<>();
        shoppingList.add("Milk");
        shoppingList.add("Eggs");
        shoppingList.add("Bread");
        System.out.println("Initial Shopping List: " + shoppingList);
        // "Copy on Write" means that whenever a write operation
        // like adding or removing an element
        // instead of directly modifying the existing list
        // a new copy of the list is created, and the modification is applied to that copy
        // This ensures that other threads reading the list while it's being modified are unaffected.
        // Read Operations: Fast and direct, since they happen on a stable list without interference from modifications.
        // Write Operations: A new copy of the list is created for every modification.
        // The reference to the list is then updated so that subsequent reads use this new list.
        // notepad --> notepad-copy
        // read more
        for(String item: shoppingList)
        {
//            System.out.println(item);
            if(item.equals("Bread"))
            {
                shoppingList.add("Butter");
                System.out.println("Added Butter to Bread");
            }
        }
        System.out.println("Updated shopping list: " + shoppingList);
    }
}

class StringLengthComparator implements Comparator<String>
{

    @Override
    public int compare(String o1, String o2)
    {
        return Integer.compare(o1.length(), o2.length());
    }
}
