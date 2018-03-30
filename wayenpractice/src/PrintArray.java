import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintArray {
	public static void main(String[] args) {
		String[] s = new String[]{"3","1","2","4"};
		List<Integer> nums = new ArrayList<>();
		print(s,0,s.length);
		
//		Collections.sort(nums);
//		System.out.println(nums);
		
//		File file=new File("shangwan_download"+File.separator);
//		if(!file.getParentFile().exists()){
//			file.getParentFile().mkdir();
//		}
	}
	
	public static void print(String[] strs, int start, int end){
		if(start == end-1){
			for(String s : strs){
				System.out.print(s);
			}
			System.out.println();
//			String r = "";
//			for (String s : strs) {
//				r+=s;
//			}
//			nums.add(Integer.parseInt(r));
		}else{
			for(int i = start; i < end; i++ ){
				String temp = strs[start];
				strs[start] = strs[i];
				strs[i] = temp;
				
				print(strs,start+1,end);
			}
		}
	}
}
