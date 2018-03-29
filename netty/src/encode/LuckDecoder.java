package encode;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import luck.LuckHeader;
import luck.LuckMessage;

public class LuckDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		//获取协议版本
		int version = in.readInt();
		//获取消息长度
		int contentLength = in.readInt();
		//获取sessionid
		byte[] sessionByte = new byte[36];
		in.readBytes(sessionByte);
		String sessionid = new String(sessionByte);
		
		//组装协议头
		LuckHeader hearder = new LuckHeader(version, contentLength, sessionid);
		
		//读取消息内容
		byte[] content = in.readBytes(in.readableBytes()).array();
		
		LuckMessage message = new LuckMessage(hearder, new String(content));
		
		out.add(message);
	}

}
