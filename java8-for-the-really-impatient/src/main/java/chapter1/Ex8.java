/**
 * file       : Ex8.java
 * author     : Kiran Mohan
 * created on : 10-Mar-2015
 */
package chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * What happens when a lambda expression captures values in an enhanced
for loop such as this one?
<pre>
    String[] names = { "Peter", "Paul", "Mary" };
    List<Runnable> runners = new ArrayList<>();
    for (String name : names)
        runners.add(() -> System.out.println(name));
</pre>

Is it legal? Does each lambda expression capture a different value, or do they
all get the last value? What happens if you use a traditional loop {@code for (int i = 0;
i < names.length; i++) }?

 * @author Kiran Mohan
 *
 */
public class Ex8 {
    
    public static List<Runnable> getRunners() {
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        
        // the below code does not work.
        // the variable "i" must be effectively final.
        /* for (int i = 0; i < names.length; i++) 
            runners.add(() -> System.out.println(names[i]));
        */
        
        for (String name : names)
            runners.add(() -> System.out.println(name));
        return runners;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        ExecutorService executors = Executors.newSingleThreadExecutor();       
        for(Runnable r: getRunners()) {
            executors.execute(r);
        }
        executors.shutdown();
    }

}
