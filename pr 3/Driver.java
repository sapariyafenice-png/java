class Driver {
    public static void main(String args[])
{
    point[] p=new point[5];
       p[0]=new point(1,2);
       p[1]=new point(3,2);
       p[2]=new point(6,2);
       p[3]=new point(1,2);
       p[4]=new point(1,2);
       int count=0;
       int i,j;
       for(i=0;i<5;i++)
       {
        boolean found = false;
        for(j=0;j<i;j++)
        {
                  if(p[i].equals(p[j]))
                   { 
                    found=true;
                     break;
                   }

        }
        if(!found)
        {
            count++;
        }
     }
     System.out.println("Distinct "+count);
}
}
