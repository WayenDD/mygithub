import java.io.IOException;  
import java.io.InputStream;  
import java.io.PrintStream;  
import java.io.UnsupportedEncodingException;  
import java.net.SocketException;  
  
import org.apache.commons.net.telnet.TelnetClient;

public class WindowsShell {  
    private TelnetClient telnet = new TelnetClient("VT220");  
  
    InputStream in;  
    PrintStream out;  
  
    String prompt = "#";  
  
    public WindowsShell(String ip, int port, String user, String password) {  
        try {  
            telnet.connect(ip, port);  
            in = telnet.getInputStream();  
            out = new PrintStream(telnet.getOutputStream());  
            login(user, password);  
        } catch (SocketException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
    /**  
     * 登录  
     *   
     * @param user  
     * @param password  
     */  
    public void login(String user, String password) {  
        // read()Until("login:");  
        readUntil("login:");  
        write(user);  
        readUntil("Password:");  
        write(password);  
        readUntil(prompt + "");  
    }  
  
    /**  
     * 读取分析结果  
     *   
     * @param pattern  
     * @return  
     */  
    public String readUntil(String pattern) {  
        try {  
            char lastChar = pattern.charAt(pattern.length() - 1);  
            StringBuffer sb = new StringBuffer();  
            char ch = (char) in.read();  
              
            while (true) {  
                sb.append(ch);  
                if (ch == lastChar) {  
                    if (sb.toString().endsWith(pattern)) {  
                        return sb.toString();  
                    }  
                }  
                ch = (char) in.read();  
              System.out.print(ch);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    /**  
     * 写操作  
     *   
     * @param value  
     */  
    public void write(String value) {  
        try {  
            out.println(value);  
            out.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    /**  
     * 向目标发送命令字符串  
     *   
     * @param command  
     * @return  
     */  
    public String sendCommand(String command) {  
        try {  
            write(command);  
            return readUntil(prompt + "");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    /**  
     * 关闭连接  
     */  
    public void disconnect() {  
        try {  
            telnet.disconnect();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void main(String[] args) throws UnsupportedEncodingException {  
            WindowsShell ws = new WindowsShell("192.168.50.75", 23, "root", "123");  
//          System.out.println(ws);  
            // 执行的命令  
            String str = ws.sendCommand("tail -n 150 /data/wjhydata_wkz/game/qq_49997/gamejar/logs/dcjserver.log");  
            str = new String(str.getBytes("ISO-8859-1"),"utf-8");  
            System.out.println(str);  
            ws.disconnect();  
    }  
  
}  