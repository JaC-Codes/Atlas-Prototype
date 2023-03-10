package net.jack.atlas.backend;

import java.util.HashMap;
import java.util.Map;

public class Test {


    String[] occ = {"What1", "What1", "What1", "What2", "What2", "What3"};

    private final Map<String, Integer> map = new HashMap<>();

   public void ttt() {
       for (int i = 0; i <= 100; i++) {
           if (i % 3 == 0 && (i % 5 == 0)) {
               System.out.println("FizzBuzz");
           } else if (i % 3 == 0) {
               System.out.println("Fizz");
           } else if (i % 5 == 0) {
               System.out.println("Buzz");
           } else {
               System.out.println(i);
           }
       }
   }

   public void occurrences() {
       for (String m : occ) {
           if (!map.containsKey(m)) {
               map.put(m, 1);
           } else {
               map.put(m, map.get(m) + 1);
           }
       }

       System.out.println(map);
   }

}
