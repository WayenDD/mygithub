package exceptiontest;

public class MyException extends RuntimeException{

	private String exStr;

	private String errorCode;
	
	public MyException(String exStr, String errorCode){
		super(exStr);
		this.errorCode = errorCode;
		this.exStr = exStr;
	}
	
	
	public String getExStr() {
		return exStr;
	}


	public void setExStr(String exStr) {
		this.exStr = exStr;
	}


	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public static void main(String[] args) {
		throw new MyException("自定义异常", "123");
	}
}
