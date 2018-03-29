package config;

import java.util.HashMap;

import io.netty.channel.ChannelHandlerContext;

public class OnlineUser {
	 //用户表  
    private static HashMap<Object, ChannelHandlerContext> onlineUser = new HashMap<Object, ChannelHandlerContext>();  
    public static void put(Object uid, ChannelHandlerContext uchc){  
        onlineUser.put(uid, uchc);  
    }  
    public static void remove(Object uid){  
        onlineUser.remove(uid);  
    }  
    public static ChannelHandlerContext get(Object uid){  
        return onlineUser.get(uid);  
    }  
}
