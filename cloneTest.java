// cloneTest.java
import java.util.ArrayList;
public class cloneTest extends ArrayList{
    public static void main(String[] args) {
        cloneTest myTest = new cloneTest();
        myTest.add("Hi");
        myTest.add("Hello");
        cloneTest test2 = (cloneTest)myTest.clone();
        System.out.println((String)test2.get(0));
        System.out.println((String)test2.get(1));
    } // end main


} // end class def
