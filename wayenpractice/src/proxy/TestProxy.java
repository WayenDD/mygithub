package proxy;

public class TestProxy {

	public static void main(String[] args) {
//		IUserDao proxy = new UserProxy();
//		proxy.save();
		
		//动态代理
//		IUserDao target = new UserDaoImpl();
//		System.out.println(target.getClass());
//		
//		IUserDao proxy = (IUserDao)new ProxyFactory(target).getProxyInstance();
//		
//		System.out.println(proxy.getClass());
//		
//		proxy.save();
		
		//Cglib代理
		IUserDao target = new UserDaoImpl();
		
		IUserDao proxy = (IUserDao)new CglibProxy(target).getProxyInstance();
		
		proxy.save();
		
	}
}
