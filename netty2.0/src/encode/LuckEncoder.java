package encode;

import io.netty.buffer.ByteBuf;  
import io.netty.channel.ChannelHandlerContext;  
import io.netty.handler.codec.MessageToByteEncoder;  
import java.io.IOException;  

import config.IMMessage;  
/**  
 * 编码工具  
 */  
public class LuckEncoder extends MessageToByteEncoder<IMMessage>{  
    @Override  
    protected void encode(ChannelHandlerContext ctx, IMMessage msg, ByteBuf out) throws IOException {  
       out.writeByte(msg.getAppId());
       
       out.writeInt(msg.getVersion());
       
       out.writeInt(msg.getUid());
       
       out.writeByte(msg.getMsgType());
       
       out.writeInt(msg.getReceiveId());
       
       out.writeBytes(msg.getMsg().getBytes());
    }  
}  
