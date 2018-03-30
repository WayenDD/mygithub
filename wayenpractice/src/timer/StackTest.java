package timer;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StackTest extends JFrame implements MouseListener{
	
	JLabel linklabel=null;
	JButton btnok=null;
	JTextField fromuser=null;
	JTextField frompwd=null;
	JTextField touser=null;
	JTextField title=null;
	JTextArea content=null;
	public StackTest(){
		init();
	}
	public void init(){
		this.setSize(400, 500);
		this.setResizable(false); 
		this.setTitle("发送QQ邮件mini版");
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		JPanel jp=new JPanel();
		jp.setLayout(null);
		this.add(jp);
		
		fromuser=new JTextField();
		fromuser.setBounds(150, 50, 200, 20);
		
		frompwd=new JTextField();
		frompwd.setBounds(150,80,200,20);
		
		linklabel = new JLabel("<html><a href='http://jingyan.baidu.com/article/3052f5a1ee816d97f31f86b8.html'>help</a></html>");  
		linklabel.setBounds(360,80,30,20);
		
		touser=new JTextField();
		touser.setBounds(150,110,200,20);
		
		title=new JTextField();
		title.setBounds(150,140,200,20);
		
		content=new JTextArea();
		JScrollPane jsp = new JScrollPane(content);
		jsp.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		jsp.setBounds(20, 170, 360, 260);
		
		JLabel user=new JLabel();
		user.setText("发送者QQ号码");
		user.setBounds(20,50,100,20);
		
		JLabel pwd=new JLabel();
		pwd.setText("发送者smtp密码");
		pwd.setBounds(20,80,100,20);
		
		JLabel to=new JLabel();
		to.setText("接受者QQ号码");
		to.setBounds(20,110,100,20);
		
		JLabel titlelabel=new JLabel();
		titlelabel.setText("邮件标题");
		titlelabel.setBounds(20,140,100,20);
		
		btnok=new JButton();
		btnok.setText("发送邮件");
		btnok.setBounds(280, 435, 100, 30);
		
		btnok.addMouseListener(this);
		linklabel.addMouseListener(this);
		
		jp.add(btnok);
		jp.add(jsp);
		jp.add(titlelabel);
		jp.add(title);
		jp.add(to);
		jp.add(touser);
		jp.add(fromuser);
		jp.add(frompwd);
		jp.add(pwd);
		jp.add(user);
		jp.add(linklabel);
	}
	
	public static void main(String[] args){  
		new StackTest().setVisible(true);
    }  
	
	//点击事件
	@Override
	public void mouseClicked(MouseEvent e) {
		try{
		if(e.getSource()==linklabel){
			  try {
				Runtime.getRuntime().exec("cmd.exe /c start " + "http://jingyan.baidu.com/article/3052f5a1ee816d97f31f86b8.html");
			} catch (IOException e1) {
				e1.printStackTrace();
			}  
		}else if(e.getSource()==btnok){
			String fromuser=this.fromuser.getText();
			String frompwd=this.frompwd.getText();
			String touser=this.touser.getText();
			String title=this.title.getText();
			String content=this.content.getText();
			
			if(!fromuser.isEmpty()&&!frompwd.isEmpty()&&!touser.isEmpty()&&!title.isEmpty()&&!content.isEmpty()){
				sendEmail(fromuser+"@qq.com", frompwd, touser+"@qq.com", title, content);
			}else{
				JOptionPane.showMessageDialog(null, "发送失败，请检查输入信息", "发送失败，请检查输入信息",JOptionPane.ERROR_MESSAGE);
			}
		}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "发送失败，请检查输入信息", "发送失败，请检查输入信息",JOptionPane.ERROR_MESSAGE);
		}finally {
			fromuser.setText("");
			frompwd.setText("");
			touser.setText("");
			title.setText("");
			content.setText("");
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void sendEmail(String fromuser,String frompwd,String touser,String title,String content){
		//记录邮箱的属性
				final Properties props = new Properties();
				//smtp发送邮件，必须进行身份认证
				props.put("mail.smtp.auth", "true");
				//填写服务器信息,主机和端口
				props.put("mail.smtp.host", "smtp.qq.com");
				props.put("mail.smtp.port", "587");
				
				//设置邮箱的账号和smtp密码
				props.put("mail.user", fromuser);
				props.put("mail.password", frompwd);
				
				// 构建授权信息，用于进行SMTP进行身份验证
				Authenticator auth=new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication(){
						String userName=props.getProperty("mail.user");
						String password=props.getProperty("mail.password");
						return new PasswordAuthentication(userName, password);
					}
				};
				
				// 使用环境属性和授权信息，创建邮件会话
				Session mailSession=Session.getInstance(props, auth);
				
				//创建邮件信息
				MimeMessage message=new MimeMessage(mailSession);
				
				
				try {
					
					// 设置发件人
					InternetAddress from=new InternetAddress(props.getProperty("mail.user"));
					message.setFrom(from);
					
					 // 设置收件人的邮箱
			        InternetAddress to = new InternetAddress(touser);
			        message.setRecipient(RecipientType.TO, to);
			        
			        //设置标题
			        message.setSubject(title);
			        //设置内容
			        message.setContent(content, "text/html;charset=UTF-8");
			        
			        Transport.send(message);
				
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
