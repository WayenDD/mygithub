package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {

	//通道管理器
	private Selector selector;
	
	public void initClient(String ip ,int port) throws IOException{
		//获得一个通道管理器
		selector = Selector.open();
		//获得一个通道
		SocketChannel channel = SocketChannel.open();
		//设置为非阻塞
		channel.configureBlocking(false);
		
		 // 客户端连接服务器,其实方法执行并没有实现连接，需要在listen（）方法中调    
        //用channel.finishConnect();才能完成连接
		channel.connect(new InetSocketAddress(ip, port));
		//吧该通道和通道管理器绑定。并为该通道注册selectionkey.op_connect事件
		channel.register(selector, SelectionKey.OP_CONNECT);
	}
	
	public void connect() throws IOException{
		//轮询访问selector
		while(true){
			 // 选择一组可以进行I/O操作的事件，放在selector中,客户端的该方法不会阻塞，    
            //这里和服务端的方法不一样，查看api注释可以知道，当至少一个通道被选中时，    
            //selector的wakeup方法被调用，方法返回，而对于客户端来说，通道一直是被选中的    
			selector.select();
			//获得selector中事件的迭代器
			Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
			
			while(ite.hasNext()){
				SelectionKey key = ite.next();
				//删除key，防止重复处理
				ite.remove();
				if(key.isConnectable()){//连接事件
					SocketChannel channel = (SocketChannel) key.channel();
					//如果正在连接，则完成连接
					if (channel.isConnectionPending()){
						channel.finishConnect();
					}
					//设置成非阻塞
					channel.configureBlocking(false);
					//给服务端发送消息
					channel.write(ByteBuffer.wrap(new String("客户端连接").getBytes("utf-8")));
					//连接服务端后，为了可以接受服务器的信息，需要给通道设置读的权限
					channel.register(selector, SelectionKey.OP_READ);
				} else if(key.isReadable()){
					read(key);
				}
			}
		}
	}
	
	 /**    
     * 处理读取服务端发来的信息 的事件    
     * @param key    
     * @throws IOException     
     */   
    public void read(SelectionKey key) throws IOException{    
        //和服务端的read方法一样    
        // 服务器可读取消息:得到事件发生的Socket通道  
        SocketChannel channel = (SocketChannel) key.channel();  
        // 创建读取的缓冲区  
        ByteBuffer buffer = ByteBuffer.allocate(1024);  
        channel.read(buffer);  
        byte[] data = buffer.array();  
        String msg = new String(data).trim();  
        System.out.println("客户端收到信息：" + msg);  
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes("utf-8"));  
        channel.write(outBuffer);// 将消息回送给客户端  
    }    
        
        
    /**    
     * 启动客户端测试    
     * @throws IOException     
     */   
    public static void main(String[] args) throws IOException {    
        NIOClient client = new NIOClient();    
        client.initClient("localhost",8181);    
        client.connect();    
    }    
}
