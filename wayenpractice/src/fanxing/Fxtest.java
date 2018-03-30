package fanxing;

import java.util.Arrays;
import java.util.List;

public class Fxtest {

	public static void main(String[] args) {
		Fx<String> fx1 = new Fx<String>();
		Fx<Integer> fx2 = new Fx<Integer>();
		Fx<Float> fx3 = new Fx<Float>();
		
		fx1.set("qwe");
		fx2.set(123);
		fx3.set(123f);
		
		System.out.println(fx1.get());
		System.out.println(fx2.get());
		System.out.println(fx3.get());
		
		
		
		
	}
	
	static List<Apple> list1 = Arrays.asList(new Apple());
	
	static List<Fruit> list2 = Arrays.asList(new Fruit());
	
	public static class Reader<T>{
		T readExact(List<? extends T> list){
			return list.get(0);
		}
	}
	
	public static void fun1(){
		Reader<Fruit> reader = new Reader<Fruit>();
		
		reader.readExact(list1);
		reader.readExact(list2);
		
	}
}
