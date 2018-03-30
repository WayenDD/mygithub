package proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{
	private Object target;
	public CglibProxy(Object target){
		this.target = target;
	}

	/**
	 * 给目标创建一个代理对象
	 * @return
	 */
	public Object getProxyInstance(){
		Enhancer en = new Enhancer();
		en.setSuperclass(target.getClass());
		en.setCallback(this);
		return en.create();
	}
	
	
	@Override
	public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
		System.out.println("开始事务1");
		Object returnvalue = method.invoke(target, arg);
		System.out.println("结束事务1");
		return returnvalue;
	}
	
}
