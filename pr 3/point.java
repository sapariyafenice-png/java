import  java.util.Objects;
public class point {
    int x;
    int y;

point(int x,int y)
{
this.x=x;
this.y=y;
}

@Override
public String toString()
{
    return ( "("+ x + "," + y +")");
}
@Override
public boolean equals(Object obj)
{
    point p=(point)obj;
    return (p.x==x && p.y==y );
  
}
public int hash()
{
return Objects.hash(x,y);
}
}


