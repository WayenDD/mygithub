package server;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.UUID;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import luck.LuckHeader;
import luck.LuckMessage;

public class ServerHandler extends ChannelHandlerAdapter{

	private final static Scanner in = new Scanner(System.in);

	/**
	 * 接收到数据时触发的方法
	 */
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException{

		try{
			//			ByteBuf buf = (ByteBuf)msg;
			//			byte[] req = new byte[buf.readableBytes()];
			//			buf.readBytes(req);
			//			String body = new String(req, "utf-8");
			//			System.out.println("Server接收到消息 :" + body );
			System.out.println("Server接收到消息 :" + msg.toString());

//			//反馈消息
//			//组装协议信息
//			int version = 1;
//			String sessionId = UUID.randomUUID().toString();
//			String content =in.nextLine();
//			LuckHeader header = new LuckHeader(version, content.length(), sessionId);
//			LuckMessage message = new LuckMessage(header, content);
//
//			ctx.writeAndFlush(message);

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ReferenceCountUtil.release(msg);
		}
	}

	/**
	 * 发生异常时触发的方法
	 */
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("读完了");
		ctx.flush();
	}

	/**
	 * 发生异常的时候触发
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}

	/**
	 * 客户端连接时触发
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		System.out.println("==="+ctx.channel().id());
//		super.channelActive(ctx);
	}
}
