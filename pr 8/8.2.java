import java.util.*;

class OutOfStockException extends Exception
{
    OutOfStockException(String msg)
    {
        super(msg);
    }
}

class InvalidQuantityException extends Exception
{
    InvalidQuantityException(String msg)
    {
        super(msg);
    }
}

class Stock
{
    int stock = 10;

    void issue(String name, int qty)
        throws OutOfStockException, InvalidQuantityException
    {
        if(qty <= 0)
        {
            throw new InvalidQuantityException("Invalid quantity");
        }

        if(qty > stock)
        {
            throw new OutOfStockException("Stock is over");
        }

        stock = stock - qty;

        System.out.println(qty + " " + name + " issued");
        System.out.println("Remaining stock: " + stock);
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        Stock s = new Stock();

        System.out.println("Enter the item name:");
        String name = sc.next();

        System.out.println("Enter the item qty:");
        int qty = sc.nextInt();

        try
        {
            s.issue(name, qty);
        }
        catch(OutOfStockException e)
        {
            System.out.println(e.getMessage());
        }
        catch(InvalidQuantityException e)
        {
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}

