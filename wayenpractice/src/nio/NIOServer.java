package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {

	//通道管理器
	private Selector selector;
	
	/**
	 * 获得一个serversocket通道,并对通道管理器和通道进行初始化工作
	 * @param port
	 * @throws IOException
	 */
	public void initServer(int port) throws IOException{
		//获得一个通道管理器
		this.selector = Selector.open();
		//获得一个serversocketchannel通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		//设置为非阻塞通道
		serverSocketChannel.configureBlocking(false);
		//将该通道对应的serversocket绑定到port端口
		serverSocketChannel.bind(new InetSocketAddress(port));
		//将通道管理器和该通道绑定，并为该通道注册selectionkey.op_accept事件，注册后
		//当该事件到达时，selector.select()会返回，如果改事件没到达，selector.select()会一直阻塞
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	}
	
	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理 
	 * @throws IOException
	 */
	public void listen() throws IOException{
		System.out.println("服务端启动");
		//轮询访问selector
		while(true){
			//当注册的事件到达时，方法返回。否则，该方法会一直阻塞
			selector.select();
			//获得selector中注册的事件的迭代器
			Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
			while(ite.hasNext()){
				SelectionKey key = ite.next();
				// 删除改key防止重复处理
				ite.remove();
				
				if (key.isAcceptable()){//客户端请求连接事件
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					//获得和客户端的通道
					SocketChannel channel = server.accept();
					//设置成非阻塞
					channel.configureBlocking(false);
					
					//给客户端发送消息
					channel.write(ByteBuffer.wrap(new String("你好，你已经成功连接服务器").getBytes("utf-8")));
					//和客户端建立连接后，为了可以接受客户端的消息，需要给通道设置读的权限
					channel.register(selector, SelectionKey.OP_READ);
					
				} else if (key.isReadable()){
					//获得了可读事件
					read(key);
				}
			}
		}
	}
	
	/**
	 * 处理读取客户端发来的信息的事件
	 * @param key
	 * @throws IOException
	 */
	public void read(SelectionKey key) throws IOException{
		//服务器可以读消息：得到事件发生的socket通道
		SocketChannel channel = (SocketChannel) key.channel();
		//创建读的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		//吧数据读到buffer中
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("服务端收到消息:" + msg);
		 ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes("utf-8"));  
	     channel.write(outBuffer);// 将消息回送给客户端  
	}
	
	/**
	 * 启动服务端
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		NIOServer server = new NIOServer();
		server.initServer(8181);
		server.listen();
	}
}
