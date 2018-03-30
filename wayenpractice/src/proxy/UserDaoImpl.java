package proxy;

public class UserDaoImpl implements IUserDao{

	@Override
	public void save() {
		System.out.println("save success!");
	}

}
