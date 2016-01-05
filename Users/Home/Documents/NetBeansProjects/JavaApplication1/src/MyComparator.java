
import java.util.Comparator;


public class MyComparator implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
      Double d1=Double.valueOf(o1.toString()).doubleValue();
      Double d2=Double.valueOf(o2.toString()).doubleValue();
    if(d1<d2)return 1;
    else if(d1>d2)return -1;
    return 0;
}
}