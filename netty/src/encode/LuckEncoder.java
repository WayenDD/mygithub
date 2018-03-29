package encode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import luck.LuckHeader;
import luck.LuckMessage;

public class LuckEncoder extends MessageToByteEncoder<LuckMessage>{

	@Override
	protected void encode(ChannelHandlerContext ctx, LuckMessage message, ByteBuf in) throws Exception {

		//将message转换成二进制数据
		LuckHeader header = message.getLuckHeader();
		
		//写入的顺序就是协议的顺序
		//标题头信息
		in.writeInt(header.getVersion());
		in.writeInt(message.getContent().length());
		in.writeBytes(header.getSessionId().getBytes());
		
		//主题信息
		in.writeBytes(message.getContent().getBytes());
	}

}
