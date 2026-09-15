public class Driver1 {

    public static void main(String args[]) {
        Card c[] = new Card[5];
        c[0] = new Card("Queen", "Hearts");
        c[1] = new Card("king", "Hearts");
        c[2] = new Card("jocker", "Spades");
        c[3] = new Card("king", "Spades");
        c[4] = new Card("king", "Hearts");
       outer:
        for(int i =0;i<5;i++)
        {
            for(int j=0;j<i;j++)
            {

                  if(c[i].equals(c[j]))
                   { 
                    System.out.println("Duplicate found:"+c[i]);
                    break outer;
                  
                   }
                

            }
              
        }

    }
}
