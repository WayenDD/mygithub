package   timer;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

class WriteClass{
public static void main(String[] args) {
        HashMap<String,Integer> hsTest= new HashMap<String,Integer>(16,0.75f);
        hsTest.put("1", 1);
        hsTest.put("2", 1);
        hsTest.put("3", 1);
        hsTest.put("4", 1);
        hsTest.put("1", 2);
        hsTest.put("2", 2);
        hsTest.put("3", 2);
        hsTest.put(null, 2);
        Iterator<Entry<String,Integer>> as=hsTest.entrySet().iterator();
        while(as.hasNext()){
        	Entry<String,Integer> it=as.next();
        	System.out.println(it.getKey());
        }
        System.out.println(new Date().hashCode());
    }
}