class Cinemashow
{
    private String title;
    private int setsAvailable;
    private final int capacity;
    private static int totalBooked=0; 
 Cinemashow(String title,int capacity )
 {
     this.title=title;
     this.capacity=capacity;
     this.setsAvailable=capacity;
   

 }
 Cinemashow( String title )
 {
this(title,100);
 }

public boolean book(int n)
 {
    if(n<=setsAvailable)
    {
        setsAvailable-=n;
        totalBooked+=n;
        return true;
    }
    else
    {
           return false;
    }
 }
public void cancel(int n)
{
    if(setsAvailable+n<=capacity)
    {
          setsAvailable+=n;
    }
    else
    {
        setsAvailable=capacity;
    }
 }
 public int getSetsAvailable()
 {
    return setsAvailable;
 }
 public static int getTotalBooked()
 {
    return totalBooked;
 }
}
 public class cinema
 {
  public static void main(String args[])
  {
    Cinemashow c1 = new Cinemashow("Avengers");
   System.out.println(c1.book(20));
   System.out.println(c1.getSetsAvailable());
   c1.cancel(5);
    System.out.println(c1.getSetsAvailable());
     System.out.println(c1.getTotalBooked());


  }
}
