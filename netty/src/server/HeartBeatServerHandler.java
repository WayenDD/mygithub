package server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import luck.LuckMessage;

public class HeartBeatServerHandler extends SimpleChannelInboundHandler<LuckMessage>{

	//连接失败次数
	private int connectTime = 0;
	
	//定义最大未连接次数
	private static final int MAX_UN_CON_TIME = 3;
	
	 @Override  
	 public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  
	      System.err.println("服务端Handler创建...");  
	      super.handlerAdded(ctx);  
	   }  
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, LuckMessage msg) throws Exception {
		try{
			LuckMessage lmsg = (LuckMessage) msg;
			if (lmsg.getContent().equals("h")) {
				//心跳消息
				//吧连接失败次数清0
				System.out.println("Server接收到心跳消息 ，失败次数清0:" + msg.toString());
				connectTime = 0 ;
			} else {
				System.out.println("Server接收到普通消息 :" + msg.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			ReferenceCountUtil.release(msg);
		}
	}
	
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt){
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.READER_IDLE) {
				//读超时
				System.out.println("服务端读超时=====连接失败次数" + connectTime);
				if (connectTime >= MAX_UN_CON_TIME) {
					System.out.println("服务端关闭channel");
					ctx.channel().close();
					connectTime = 0;
				} else {
					connectTime ++;
				}
				
			}else if (event.state() == IdleState.WRITER_IDLE) {
                /*写超时*/  
                System.out.println("服务端写超时");
            } else if (event.state() == IdleState.ALL_IDLE) {
                /*总超时*/
                System.out.println("服务端全部超时");
            }
			
		}
	}
	
	 @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		 	cause.printStackTrace();
		 	System.out.println("服务端发生错误:" + cause.getMessage());
	        ctx.channel().close();
	    }
	 
	    @Override
	    public void channelActive(ChannelHandlerContext ctx) throws Exception {
	        System.out.println("有客户端连接");
	        super.channelActive(ctx);
	    }
	     
	    @Override
	    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	        // 关闭，等待重连
	        ctx.close();
	        System.out.println("===服务端===(客户端失效)");
	    }
}
