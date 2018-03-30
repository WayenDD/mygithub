package ewq;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommonDivisor {

	public static void main(String[] args){
		//        int a,b,m,n;
		//        Scanner in=new Scanner(System.in);
		//        System.out.println("请输入一个正整数：");
		//        a=in.nextInt();
		//        System.out.println("再输入一个正整数：");
		//        b=in.nextInt();
		//        CommonDivisor use=new CommonDivisor();
		//        m=use.commonDivisor(a,b);
		//        n=a*b/m;
		//        System.out.println("最大公约数："+m);
		//        System.out.println("最小公倍数："+n); 


		//		  Scanner in = new Scanner(System.in);
		//	        System.out.println("请输入a的值");
		//	        int a = in.nextInt();
		//	        System.out.println("请输入n个数");
		//	        int n = in.nextInt();
		//	        int s = 0,t=0;
		//	        for (int i = 0; i < n; i++) {
		////	            t += a*(Math.pow(10,i));
		////	            
		//////	            a = a*10;
		////	            s += t;
		//	        	   t += a;
		//	               a = a*10;
		//	               s += t;
		//	        }
		//	        System.out.println(s);

		//		System.out.println(a(5));

		int[] aa = new int[]{5,6,4,8,23,1,9,7};
		sort(aa,0,aa.length-1);
		for (int i : aa) {
			System.out.println(i);
		}
	}

	public static void sort(int arr[],int low,int high)
	{
		int l = low ;
		int h = high ;
		int base = arr[low];
		
		while (l < h){
			
		}
	}

	public static int a(int a){
		if (a == 1) 
			return 1;
		else
			return a*(a(a-1));
	}

	public int commonDivisor(int x,int y){
		if(x<y){
			int t=x;
			x=y;
			y=t;
		}
		while(y!=0){
			if(x==y)return x;
			else{
				int k=x%y;
				x=y;
				y=k;
			}
		}
		return x;
	}
}
