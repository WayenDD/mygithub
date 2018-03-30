package ewq;

public class Str {

	public static void main(String[] args) {
		String s = "qwer";
		String ss = new String("qwer");
		
		System.out.println(ss instanceof String);
		System.out.println(s.equals(ss));
	}
}
