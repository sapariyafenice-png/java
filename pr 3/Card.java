import java.util.Objects;
public class Card {
    String rank;
    String suit;
Card (String rank,String suit)
{
    this.rank=rank;
    this.suit=suit;
}
@Override
public String  toString()
{
    return rank   + "of" +  suit;
}
@Override
public boolean equals(Object obj)
{
    Card c=(Card)obj;
    return rank.equals(c.rank) && suit.equals(c.suit);
}
public int hashCode()
{
    return Objects.hash(rank,suit);
}
    
}
