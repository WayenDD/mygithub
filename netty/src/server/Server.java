package server;

import java.util.concurrent.TimeUnit;

import encode.LuckDecoder;
import encode.LuckEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;


/**
 * Netty通信的步骤：
	①创建两个NIO线程组，一个专门用于网络事件处理（接受客户端的连接），另一个则进行网络通信的读写。
	②创建一个ServerBootstrap对象，配置Netty的一系列参数，例如接受传出数据的缓存大小等。
	③创建一个用于实际处理数据的类ChannelInitializer，进行初始化的准备工作，比如设置接受传出数据的字符集、格式以及实际处理数据的接口。
	④绑定端口，执行同步阻塞方法等待服务器端启动即可。
 * @author Administrator
 *
 */
public class Server {
	private int port;
	
	public Server(int port){
		this.port = port;
	}
	
	public void run(){
		 EventLoopGroup bossGroup = new NioEventLoopGroup(); //用于处理服务器端接受客户端
		 EventLoopGroup workerGroup = new NioEventLoopGroup(); //进行网络通信（读写）
		 try{
			 ServerBootstrap bootstrap = new ServerBootstrap();//辅助工具类，用于服务器通道的一系列配置
			 bootstrap.group(bossGroup, workerGroup)
			 	.channel(NioServerSocketChannel.class)
			 	.childHandler(new ChannelInitializer<SocketChannel>() { //配置具体的数据处理方式  
			 		
                    @Override  
                    protected void initChannel(SocketChannel socketChannel) throws Exception {  
                    	ChannelPipeline pipeline = socketChannel.pipeline();
                    	pipeline.addLast(new IdleStateHandler(5, 0, 0,TimeUnit.SECONDS));
                    	pipeline.addLast(new LuckEncoder());
                    	pipeline.addLast(new LuckDecoder()); 
                    	
                    	//处理事件的类
                    	//pipeline.addLast(new ServerHandler());  
                    	pipeline.addLast(new HeartBeatServerHandler());  
                    }  
                })
			 	.option(ChannelOption.SO_BACKLOG, 128)//设置tcp缓冲区(// 保持连接数  )
			 	.option(ChannelOption.SO_SNDBUF, 32*1024)//设置发送数据缓冲大小
			 	.option(ChannelOption.SO_RCVBUF, 32*1024)//设置接受数据缓冲大小
			 	.childOption(ChannelOption.SO_KEEPALIVE , true)//保持连接
			 	.option(ChannelOption.TCP_NODELAY, true)//有数据立即发送
			 	.option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT);
			 	
			 ChannelFuture future = bootstrap.bind(port).sync();
			 future.channel().closeFuture().sync();
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
		 
	}
	
	public static void main(String[] args) {
		new Server(8077).run();
	}
}
