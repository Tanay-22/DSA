public class Main
{
    public static void main(String[] args) throws Exception
    {
        Heap<Integer> heap = new Heap<>();

        heap.insert(23);
        heap.insert(54);
        heap.insert(32);
        heap.insert(12);
        heap.insert(89);

        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
    }
}
