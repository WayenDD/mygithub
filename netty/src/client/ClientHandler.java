package client;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ClientHandler extends ChannelHandlerAdapter{
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
			System.out.println("client接收到消息 :" + msg.toString());
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ReferenceCountUtil.release(msg);
		}
	}
	
	 public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {  
	        cause.printStackTrace();  
	        ctx.close();  
	    }  
}
