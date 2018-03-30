package fanxing;

import java.util.ArrayList;
import java.util.List;

public class aaa {

	public class Season {
		  //.....  
		}

	public class Spring extends Season {
	  //......  
	}

	public class Summer extends Season {
	 //.......
	}
	
	void test(){
		 List<? extends Season> list1=new ArrayList<Spring>();
	}
}
