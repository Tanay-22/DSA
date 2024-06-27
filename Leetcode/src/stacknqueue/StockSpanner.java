package stacknqueue;


import java.util.Stack;
//901. Online Stock Span
public class StockSpanner
{
    private class Stock
    {
        int price;
        int span;

        public Stock(int price, int span)
        {
            this.price = price;
            this.span = span;
        }
    }

    private Stack<Stock> stack;

    public StockSpanner()
    {
        this.stack = new Stack<>();
    }

    public int next(int price)
    {
        int span = 1;

        while (!stack.isEmpty() && price >= stack.peek().price)
            span += stack.pop().span;

        stack.push(new Stock(price, span));

        return span;
    }

}