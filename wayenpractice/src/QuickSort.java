
public class QuickSort {
	
	public static void main(String[] args) {
		int[] arr = new int[]{1,23,34,54,15,55,46,78,44,32,58};
		qSort(arr, 0, 10);
		for (int i : arr) {
			System.out.println(i);
		}
	}

	public static void qSort(int arr[],int low,int high){
		int l=low;
		int h=high;
		int povit=arr[low];

		while(l<h){
			
			while(l<h&&arr[h]>=povit)
				h--;
			
			if(l<h){
				int temp=arr[h];
				arr[h]=arr[l];
				arr[l]=temp;
				l++;
			}

			while(l<h&&arr[l]<=povit)
				l++;

			if(l<h){
				int temp=arr[h];
				arr[h]=arr[l];
				arr[l]=temp;
				h--;
			}
		}
		
//		System.out.print("l="+(l+1)+"h="+(h+1)+"povit="+povit+"\n");
		if(l>low)qSort(arr,low,l-1);
		if(h<high)qSort(arr,l+1,high);

	}
}
