package server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;  
import java.io.IOException;

import config.IMMessage;
import config.OnlineUser;  
@ChannelHandler.Sharable  
public class ServerHandler extends ChannelHandlerAdapter{  
//    private ChannelHandlerContext ctx;  
    @Override  
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  
        System.err.println("服务端Handler创建...");  
        super.handlerAdded(ctx);  
    }  
    @Override  
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {  
        System.err.println("channelInactive");  
        super.channelInactive(ctx);  
    }  
    /**  
     * tcp链路建立成功后调用  
     */  
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
//        this.ctx = ctx;  
        System.err.println("有客户端连接："+ctx.channel().remoteAddress().toString());
        
//        OnlineUser.put(ctx.channel().id(), ctx);
    }  
    /**  
     * 发送消息  
     */  
    public boolean sendMsg(IMMessage msg) throws IOException {  
        System.err.println("服务器推送消息:"+msg);  
//        ctx.writeAndFlush(msg);  
        return msg.getMsg().equals("q") ? false : true;  
    }  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws IOException {  
        System.err.println("服务器接收到消息:"+msg);  
        IMMessage message = (IMMessage)msg;  
        
        if(message.getMsgType() == 0){//如果是登录行为
	        //连接的时候将用户加到onlineuser中去
	        if(OnlineUser.get(message.getUid())==null){  
	        	//根据用户ID存储
	            OnlineUser.put(message.getUid(), ctx);
	        }  
        } else {//如果是文字消息   就转发给收信人
        	
	        //获取收信人的ctx
	        ChannelHandlerContext c = OnlineUser.get(message.getReceiveId());  
	        if(c==null){  
	           message.setMsg("对方不在线！"); 
	           //如果不在线，吧反馈信息发送给发信人
	           OnlineUser.get(message.getUid()).writeAndFlush(message);
	        } 
	        else
	        	//如果在线   则直接发送给收信人
	        	c.writeAndFlush(message);
        }
//        else  
//            c.writeAndFlush(message);  
    }  
    /**  
     * 异常处理  
     */  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {  
        System.err.println("与客户端断开连接:"+cause.getMessage());  
        cause.printStackTrace();  
        ctx.close();  
    }  
}  
