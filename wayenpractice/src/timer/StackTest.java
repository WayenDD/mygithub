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
		this.setTitle("����QQ�ʼ�mini��");
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
		user.setText("������QQ����");
		user.setBounds(20,50,100,20);
		
		JLabel pwd=new JLabel();
		pwd.setText("������smtp����");
		pwd.setBounds(20,80,100,20);
		
		JLabel to=new JLabel();
		to.setText("������QQ����");
		to.setBounds(20,110,100,20);
		
		JLabel titlelabel=new JLabel();
		titlelabel.setText("�ʼ�����");
		titlelabel.setBounds(20,140,100,20);
		
		btnok=new JButton();
		btnok.setText("�����ʼ�");
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
	
	//����¼�
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
				JOptionPane.showMessageDialog(null, "����ʧ�ܣ�����������Ϣ", "����ʧ�ܣ�����������Ϣ",JOptionPane.ERROR_MESSAGE);
			}
		}
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null, "����ʧ�ܣ�����������Ϣ", "����ʧ�ܣ�����������Ϣ",JOptionPane.ERROR_MESSAGE);
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
		//��¼���������
				final Properties props = new Properties();
				//smtp�����ʼ���������������֤
				props.put("mail.smtp.auth", "true");
				//��д��������Ϣ,�����Ͷ˿�
				props.put("mail.smtp.host", "smtp.qq.com");
				props.put("mail.smtp.port", "587");
				
				//����������˺ź�smtp����
				props.put("mail.user", fromuser);
				props.put("mail.password", frompwd);
				
				// ������Ȩ��Ϣ�����ڽ���SMTP���������֤
				Authenticator auth=new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication(){
						String userName=props.getProperty("mail.user");
						String password=props.getProperty("mail.password");
						return new PasswordAuthentication(userName, password);
					}
				};
				
				// ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
				Session mailSession=Session.getInstance(props, auth);
				
				//�����ʼ���Ϣ
				MimeMessage message=new MimeMessage(mailSession);
				
				
				try {
					
					// ���÷�����
					InternetAddress from=new InternetAddress(props.getProperty("mail.user"));
					message.setFrom(from);
					
					 // �����ռ��˵�����
			        InternetAddress to = new InternetAddress(touser);
			        message.setRecipient(RecipientType.TO, to);
			        
			        //���ñ���
			        message.setSubject(title);
			        //��������
			        message.setContent(content, "text/html;charset=UTF-8");
			        
			        Transport.send(message);
				
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
