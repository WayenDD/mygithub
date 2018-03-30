
public class suanfa {

	public static void main(String[] args) {
		int[] a = {49,38,65,97,76,13,27,49,78,34,12,64,1};
		for (int i : a) {
			System.out.print(i+",");
		}
		int d = a.length;

		while(true){
			d = d/2;
			for(int x = 0; x < d ; x++){

				for (int i = x+d; i < a.length; i=i+d) {
					int t = a[i];
					int j;
					for(j=i-d;j>=0&&a[j]>t;j=j-d){
						a[j+d]=a[j];
					}
					a[j+d]=t;
				}
			}
			if(d==1)
				break;
		}
		System.out.println();
		for (int i : a) {
			System.out.print(i+",");
		}
		suanfa gcd = new suanfa();
		int div = gcd.getGCD(90, 60);
		System.out.println("最大公约数为：" + div);
	}

	/**
	 * 递归算法：求最大公约数，根据欧几里德知道－》m和n（m > n）的最大公约数 ＝ n 和m％n的最大公约数
	 *
	 * @author timmy1
	 *
	 */
	public int getGCD(int m, int n) {
		if (n == 0) {
			return m;
		} else {
			return getGCD(n, m % n);
		}
	}
}
