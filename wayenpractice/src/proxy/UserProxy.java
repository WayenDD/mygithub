package proxy;

public class UserProxy implements IUserDao{
	
	IUserDao userDao ;
	
	public UserProxy(){
		userDao = new UserDaoImpl();
	}

	@Override
	public void save() {
		System.out.println("事务1");
		userDao.save();
		System.out.println("事务2");
	}
	
}
