package client;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import encode.LuckDecoder;
import encode.LuckEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import luck.LuckHeader;
import luck.LuckMessage;

public class Client {

	private static Channel channel;

	private static Bootstrap bootstrap;

	private static ChannelFutureListener channelFutureListener = null;

	public static void main(String[] args) throws Exception {
		new Client();
	}

	public Client() throws Exception{
		System.out.println("构造方法");
		init();
		sendData();
	}

	static Scanner in = new Scanner(System.in);
	public static void sendData() throws Exception {
		System.out.println("senddata");
		
		//组装协议信息
		int version = 1;
		String sessionId = UUID.randomUUID().toString();
		String content = "";
		LuckHeader header = new LuckHeader(version, content.length(), sessionId);
		LuckMessage message = null;

		do {
			content = in.nextLine();
			message = new LuckMessage(header, content);
			channel.writeAndFlush(message);
		} while (!content.equals("q"));
	}

	public void init() throws InterruptedException{
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try{
			bootstrap = new Bootstrap();


			bootstrap.group(workerGroup)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {
				@Override  
				protected void initChannel(SocketChannel socketChannel) throws Exception {  
					ChannelPipeline pipeline = socketChannel.pipeline();
					pipeline.addLast(new IdleStateHandler(0, 0, 5, TimeUnit.SECONDS));
					pipeline.addLast("encoder",new LuckEncoder());
					pipeline.addLast("decoder",new LuckDecoder());

					//						pipeline.addLast(new ClientHandler());
					pipeline.addLast(new HeartBeatClientHanlder());
				}  
			});
			//设置tcp协议的属性
			bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
			bootstrap.option(ChannelOption.TCP_NODELAY, true);
			bootstrap.option(ChannelOption.SO_TIMEOUT, 5000);

			connect();

			//			channelFutureListener = new ChannelFutureListener() {
			//				
			//				@Override
			//				public void operationComplete(ChannelFuture f) throws Exception {
			//					if (f.isSuccess()) {
			//						System.out.println("连接服务器成功");
			//					} else {
			//						System.out.println("连接服务器失败");
			//						f.channel().eventLoop().schedule(new Runnable() {
			//							
			//							@Override
			//							public void run() {
			//								reConnect();
			//							}
			//						}, 3, TimeUnit.SECONDS);
			//					}
			//					
			//				}
			//			};

			//		//组装协议信息
			//		int version = 1;
			//		String sessionId = UUID.randomUUID().toString();
			//		String content = "i am a luckmessage";
			//		LuckHeader header = new LuckHeader(version, content.length(), sessionId);
			//		LuckMessage message = new LuckMessage(header, content);
			//
			//		ch.writeAndFlush(message);
			//		//				}
			//		ch.closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			//			workerGroup.shutdownGracefully();
		}
	}

	/**
	 * 重新连接服务器
	 * @throws InterruptedException
	 */
	public static void connect(){

		if (channel != null && channel.isActive()) {
			return;
		}

		System.out.println("连接中");
		ChannelFuture channelFuture = null;
		try {
			channelFuture = bootstrap.connect("192.168.1.12", 8077).sync();
			channelFuture.addListener(new ChannelFutureListener() {
				public void operationComplete(ChannelFuture futureListener) throws Exception {
					if (futureListener.isSuccess()) {
						channel = futureListener.channel();
						System.out.println("连接成功");
//						sendData();
					} else {
						System.out.println("连接失败，3秒后准备重连");
//						
//						connect();
//						sendData();
						
//						futureListener.channel().eventLoop().schedule(new Runnable() {
//							@Override
//							public void run() {
//								connect();
//							}
//						}, 3, TimeUnit.SECONDS);
						
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
