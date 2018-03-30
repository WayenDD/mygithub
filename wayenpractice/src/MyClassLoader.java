import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyClassLoader extends ClassLoader{
	 private String classpath;
	public MyClassLoader()
    {
        
    }
    
    public MyClassLoader(String classpath)
    {
    	 this.classpath = classpath;
    }
    
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        try
        {
            byte[] bytes = getData(name);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return super.findClass(name);
    }
    
  //返回类的字节码
    private byte[] getData(String className) throws IOException{
        InputStream in = null;
        ByteArrayOutputStream out = null;
        String path=classpath + File.separatorChar +
                    className.replace('.',File.separatorChar)+".class";
        try {
            in=new FileInputStream(path);
            out=new ByteArrayOutputStream();
            byte[] buffer=new byte[2048];
            int len=0;
            while((len=in.read(buffer))!=-1){
                out.write(buffer,0,len);
            }
            return out.toByteArray();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally{
            in.close();
            out.close();
        }
        return null;
    }
    
    public static void main(String[] args) {
		try {
			MyClassLoader mcl = new MyClassLoader("E:\\");
			Class c = mcl.loadClass("Person");
			Object obj = c.newInstance();
			System.out.println(obj);
			System.out.println(obj.getClass().getClassLoader());
			
			ExecutorService pool = Executors.newCachedThreadPool();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
