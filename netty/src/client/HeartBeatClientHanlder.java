package client;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import luck.LuckMessage;

public class HeartBeatClientHanlder extends SimpleChannelInboundHandler<LuckMessage>{
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, LuckMessage msg) throws Exception {
		
	}
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//	 	cause.printStackTrace();
		System.out.println("客户端发生错误:" + cause.getMessage());
        ctx.channel().close();
    }
	
	 @Override
	    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	        System.out.println("与服务器失去连接");
//	        super.channelInactive(ctx);
	        
	        Client.connect();
	        Client.sendData();
	    }

}
