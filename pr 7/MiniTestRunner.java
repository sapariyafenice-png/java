import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run { }

class Demo {

    @Run
    public void test1() {
        System.out.println("Test 1 Executed");
    }

    @Run
    public void test2() {
        System.out.println("Test 2 Executed");
    }

    public void normalMethod() {
        System.out.println("Normal Method");
    }
}

public class MiniTestRunner {

    public static void main(String[] args) throws Exception {

        Demo obj = new Demo();

        Method[] methods = Demo.class.getDeclaredMethods();

        int count = 0;

        for (Method m : methods) {

            if (m.isAnnotationPresent(Run.class)) {
                m.invoke(obj);
                count++;
            }
        }

        System.out.println("Methods Executed = " + count);
    }
}