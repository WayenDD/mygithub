package encode;

import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.handler.codec.MessageToMessageDecoder;  
import java.util.List;  

import config.IMMessage;  
/**  
 * 解码工具  
 */  
public class LuckDecoder extends MessageToMessageDecoder<ByteBuf>{  
    @Override  
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {  
    	byte appId = msg.readByte();
    	
    	int version = msg.readInt();
    	
    	int uid = msg.readInt();
    	
    	byte msgType = msg.readByte();
    	
    	int receiveId = msg.readInt();
    	
    	byte[] content = msg.readBytes(msg.readableBytes()).array();
    	
    	IMMessage message = new IMMessage(appId, version, uid, msgType, receiveId, new String(content));
    	
    	out.add(message);
    	
//        final int length = msg.readableBytes();  
//        final byte[] array = new byte[length];  
//        msg.getBytes(msg.readerIndex(), array, 0, length);  
//        out.add(msg);  
    }  
}  